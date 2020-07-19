package com.example.testapimla.api.adapter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.example.testapimla.models.Description;
import com.example.testapimla.models.GetItems;
import com.example.testapimla.models.Site;

import java.util.List;

/**
 * Interfaz de configuración para las solicitudes con restrofit
 */
public interface IEndPoints {
    @GET(ConstantsApiClient.SITES_KEY)
    Call<List<Site>> getSites();

    @GET(ConstantsApiClient.SITES_KEY +
            ConstantsApiClient.SEPARATOR +
            ConstantsApiClient.START_KEY + ConstantsApiClient.PATH + ConstantsApiClient.END_KEY +
            ConstantsApiClient.SEARCH_KEY)
    Call<GetItems> getItems(@Path (ConstantsApiClient.PATH) String idSite,
                            @Query(ConstantsApiClient.QUERY) String item,
                            @Query(ConstantsApiClient.OFFSET) String offset,
                            @Query(ConstantsApiClient.LIMIT) String limit);

    @GET(ConstantsApiClient.ITEM_KEY +
            ConstantsApiClient.SEPARATOR +
            ConstantsApiClient.START_KEY + ConstantsApiClient.PATH + ConstantsApiClient.END_KEY +
            ConstantsApiClient.DESCRIPTIONS)
    Call<List<Description>> getDescription(@Path(ConstantsApiClient.PATH) String idItem);

}
