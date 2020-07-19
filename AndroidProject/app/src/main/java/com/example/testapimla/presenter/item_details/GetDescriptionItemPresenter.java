package com.example.testapimla.presenter.item_details;

import android.util.Log;

import com.example.testapimla.api.adapter.ApiClient;
import com.example.testapimla.api.adapter.IEndPoints;
import com.example.testapimla.models.Description;
import com.example.testapimla.view.IItemDetailsView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Clase que obtiene la descripción para el detalle del producto
 */
public class GetDescriptionItemPresenter implements IGetDescriptionItemPresenter {
    private IEndPoints iEndPoints;
    private IItemDetailsView iItemDetailsView;

    public GetDescriptionItemPresenter(IItemDetailsView iItemDetailsView, String idItem) {
        Retrofit retrofit = ApiClient.establishConection();
        iEndPoints = retrofit.create(IEndPoints.class);
        this.iItemDetailsView = iItemDetailsView;
        this.getDescriptionItem(idItem);
    }

    /**
     * Método utilizado para implementación de solicitud a la API
     * de la descripción del producto
     * @param idItem
     */
    @Override
    public void getDescriptionItem(String idItem) {
        Call<List<Description>> getSiteCall = iEndPoints.getDescription(idItem);
        getSiteCall.enqueue(new Callback<List<Description>>() {
            @Override
            public void onResponse(Call<List<Description>> call, Response<List<Description>> response) {
                if (response.body() != null) {
                    List <Description> listDescription = response.body();
                    String description = listDescription.get(0).getDescription();
                    iItemDetailsView.getDescription(description);
                    System.out.println(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<Description>> call, Throwable t) {
                Log.d("ApiCall_Error", t.toString());
            }
        });
    }
}
