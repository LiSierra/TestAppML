package com.example.testapimla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.testapimla.adapter.ListItemAdapter;
import com.example.testapimla.models.Search;
import com.example.testapimla.presenter.search.GetSearchPresenter;
import com.example.testapimla.presenter.search.IGetSearchPresenter;
import com.example.testapimla.view.IGetSearchView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;


/**
 * Clase donde se implementa la vista
 * de consulta de productos.
 */
public class MainActivity extends AppCompatActivity implements IGetSearchView {
    @BindView(R.id.spSiteList)
    protected Spinner spSiteList;
    @BindView(R.id.rvItems)
    protected RecyclerView rvItems;
    @BindView(R.id.etSearch)
    protected EditText etSearch;
    private IGetSearchPresenter getSearchPresenter;
    private String keyWord;
    private String country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSearchPresenter = new GetSearchPresenter(this, MainActivity.this);
        this.listenerScroll();
        this.animationSetting();
    }

    @OnItemSelected(R.id.spSiteList)
    void onItemSelected(int position) {
        spSiteList.getItemAtPosition(position);
    }

    @OnClick(R.id.btnSearch)
    public void submit(View view) {
        this.keyWord = etSearch.getText().toString();
        this.country = this.spSiteList.getSelectedItem().toString();
        this.sendData(false);
        etSearch.setText("");
    }

    /**
     * Método donde se realiza la configuración del RecyclerView
     */
    @Override
    public void generateLinearLayoutVertical() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvItems.setLayoutManager(linearLayoutManager);
    }

    /**
     * Método para configurar el adaptador para el RecyclerView de
     * productos.
     * @param movieSearches
     * @return
     */
    @Override
    public ListItemAdapter createAdapter(List<Search> movieSearches) {
        return new ListItemAdapter(movieSearches, this);
    }

    /**
     * Método para inicializar el adapter del RecyclerView
     * de productos.
     * @param adapter
     */
    @Override
    public void initializeAdapterRV(ListItemAdapter adapter) {
        rvItems.setAdapter(adapter);
    }

    /**
     * Método para inicializar el adapter del Spinner "Países"
     * @param adapter
     */
    @Override
    public void initializeAdapterSP(ArrayAdapter adapter) {
        this.spSiteList.setAdapter(adapter);
    }

    /**
     * Método donde se configura la solicitud de busqueda
     * de productos.
     * @param isNewPage
     */
    @Override
    public void sendData(boolean isNewPage) {
        this.getSearchPresenter.validateSearchItem(country,
                                                   keyWord,
                                                   isNewPage);
    }

    /**
     * Método para dectetar el final del scroll del RecyclerView
     * y enviar la solicitud de paginación.
     */
    @Override
    public void listenerScroll() {
        rvItems.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!recyclerView.canScrollVertically(1)) {
                    sendData(true);

                }
            }
        });
    }

    /**
     * Método para la implentación de animación cuando
     * se sale de la actividad.
     */
    @Override
    public void animationSetting() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Explode explode = new Explode();
            explode.setDuration(500);
            getWindow().setExitTransition(explode);
        }
    }
}
