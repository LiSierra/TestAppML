package com.example.testapimla.preferences;

import com.example.testapimla.models.Site;

import java.util.List;

/**
 * Interfaz que define los métodos para el cache
 */
public interface ICache {
    /**
     * Asigna los valores para la preferencia Site
     * @param key
     * @param objectList
     */
    void setList(String key, List<Site> objectList);

    /**
     * Obtiene los valores guardados para la preferencia Site
     * @param key
     * @return
     */
    List<Site> getList(String key);
}
