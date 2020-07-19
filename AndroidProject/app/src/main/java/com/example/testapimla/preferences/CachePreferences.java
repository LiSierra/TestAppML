package com.example.testapimla.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.testapimla.models.Site;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Clase que implementa los métodos de cache
 */
public class CachePreferences implements ICache{
    private Context mContext;

    CachePreferences(Context mContext){
        this.mContext = mContext;
    }

    /**
     * Asigna el guardado de datos en el preferences
     * @param key
     * @param objectList
     */
    @Override
    public void setList(String key, List<Site> objectList) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        String json = new Gson().toJson(objectList);
        edit.putString(key,json);
        edit.apply();

    }

    /**
     * Obtiene los datos guardados en el preferences
     * @param key
     * @return
     */
    @Override
    public List<Site> getList(String key) {
        String json = getSharedPreferences().getString(key,null);
        Type listSite = new TypeToken<List<Site>>(){}.getType();
        return new Gson().fromJson(json, listSite);
    }

    /**
     * Instancia el objeto preferences
     * @return
     */
    private SharedPreferences getSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(mContext);
    }
}
