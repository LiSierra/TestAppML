package com.example.testapimla.presenter.search;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.testapimla.MainActivity;
import com.example.testapimla.R;
import com.example.testapimla.api.adapter.ApiClient;
import com.example.testapimla.api.adapter.IEndPoints;
import com.example.testapimla.models.GetItems;
import com.example.testapimla.models.Search;
import com.example.testapimla.models.Site;
import com.example.testapimla.preferences.CacheFactory;
import com.example.testapimla.preferences.FactoryCachePreference;
import com.example.testapimla.utils.Constants;
import com.example.testapimla.view.IGetSearchView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Clase que obtiene los datos para la consulta del producto
 */
public class GetSearchPresenter implements IGetSearchPresenter {
    private IEndPoints iEndPoints;
    private IGetSearchView iGetSearchView;
    private MainActivity activity;
    private List<Site> sites;
    private int newPage;
    private int totalItems;

    public GetSearchPresenter(IGetSearchView iGetSearchView, MainActivity activity) {
        Retrofit retrofit = ApiClient.establishConection();
        this.iEndPoints = retrofit.create(IEndPoints.class);
        this.iGetSearchView = iGetSearchView;
        this.activity = activity;

        CacheFactory.getInstance().setCurrent(new FactoryCachePreference());
        this.getListSites();
    }

    /**
     * Método que se encarga de obtener los datos de los países.
     */
    @Override
    public void getListSites() {
        Call<List<Site>> getSiteCall = iEndPoints.getSites();
        getSiteCall.enqueue(new Callback<List<Site>>() {
            @Override
            public void onResponse(Call<List<Site>> call, Response<List<Site>> response) {
                if (response.body() != null) {
                    sites = response.body();
                    CacheFactory.getInstance().createCache(activity.getApplicationContext()).setList(Constants.KEY_SITE, sites);
                    fillListSiteSP();
                }
            }

            @Override
            public void onFailure(Call<List<Site>> call, Throwable t) {
                Log.d("ApiCall_Error", t.toString());
            }
        });
    }

    /**
     * Método que se encarga de obtener los datos de la consulta
     * de productos y su posterior paginación.
     * @param idSite
     * @param keyWord
     * @param offset
     * @param limit
     */
    @Override
    public void getSearchItems(String idSite, String keyWord,
                               String offset, String limit) {
        Call<GetItems> getItemsCall = iEndPoints.getItems(this.getIdSite(idSite), keyWord,
                                                           offset, limit);
        getItemsCall.enqueue(new Callback<GetItems>() {
            @Override
            public void onResponse(Call<GetItems> call, Response<GetItems> response) {
                if (response.body() != null) {
                    GetItems getItems = response.body();
                    List<Search> items = getItems.getGetItems();
                    totalItems = getItems.getPaging().getTotalItems();
                    if (totalItems == 0) {
                        Toast.makeText(activity, R.string.empty_items,Toast.LENGTH_LONG).show();
                    }
                    fillSearchMoviesRV(items);

                }
            }

            @Override
            public void onFailure(Call<GetItems> call, Throwable t) {
                Log.d("ApiCall_Error", t.toString());
            }
        });

    }

    /**
     * Método que se encarga de llenar los datos del Spinner
     * de países.
     */
    @Override
    public void fillListSiteSP() {
        String[] sites = new String[this.sites.size()];
        Collections.sort(this.sites, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        for(int i = 0;i<this.sites.size();i++)
        {
            sites[i] =  this.sites.get(i).getName();
        }
        ArrayAdapter adapter = new ArrayAdapter<>(activity.getApplicationContext(),
                                                android.R.layout.simple_list_item_1 ,
                                                sites);
        iGetSearchView.initializeAdapterSP(adapter);
    }

    /**
     * Método que se encarga de llenar los datos del RecyclerView
     * para la consulta de productos
     * @param items
     */
    @Override
    public void fillSearchMoviesRV(List<Search> items) {
        iGetSearchView.initializeAdapterRV(iGetSearchView.createAdapter(items));
        iGetSearchView.generateLinearLayoutVertical();
    }

    /**
     * Método que se encarga de obtener el Id del país a partir de la
     * obtención del guardado en los preferences.
     * @param country
     * @return
     */
    @Override
    public String getIdSite(String country) {
        this.sites = CacheFactory.getInstance().createCache(activity.getApplicationContext()).getList(Constants.KEY_SITE);
        Map<String,String> hashmap = new HashMap<>();
        for (Site obj:
             sites) {
            hashmap.put(obj.getName(), obj.getId());
        }
        return hashmap.get(country);
    }

    /**
     * Método que se encarga de validar los datos que se envían
     * desde la vista de consulta del producto, para su solicitud a
     * la API.
     * @param country
     * @param keyWord
     * @param isNewPage
     */
    @Override
    public void validateSearchItem(String country, String keyWord, boolean isNewPage) {
        if(country.equals(Constants.EMPTY)){
            Toast.makeText(activity, R.string.error_pais,Toast.LENGTH_LONG).show();
        } else if (keyWord.equals(Constants.EMPTY)){
            Toast.makeText(activity,R.string.error_producto,Toast.LENGTH_LONG).show();
        } else if (isNewPage) {
            newPage += 20;
            if (newPage <= totalItems) {
                this.getSearchItems(country,
                        keyWord,
                        String.valueOf(newPage),
                        Constants.TWENTY);
            } else {
                Toast.makeText(activity, R.string.without_items,Toast.LENGTH_LONG).show();
            }
        } else {
            this.newPage = 0;
            this.getSearchItems(country,
                    keyWord,
                    Constants.ZERO,
                    Constants.TWENTY);
        }
    }

}
