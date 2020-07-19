package com.example.testapimla.api.adapter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase para establecer la conexión con retrofit
 */
public class ApiClient {
    private static Retrofit retrofit;

    public static  Retrofit establishConection() {

        if (retrofit== null) {
            try {
                retrofit = new Retrofit.Builder()
                        .baseUrl(ConstantsApiClient.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        }
        return retrofit;
    }
}
