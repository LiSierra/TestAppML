package com.example.testapimla.models;

import com.google.gson.annotations.SerializedName;

/**
 * Objeto para la información de los países de la busqueda
 */
public class Site {
    /**
     * Mapea el id del país asignado en ML
     */
    @SerializedName("id")
    private String idSite;
    /**
     * Mapea el nombre del país
     */
    @SerializedName("name")
    private String nameSite;

    /**
     * Obtiene el id del país
     * @return
     */
    public String getId() {
        return idSite;
    }

    /**
     * Asigna el id del país
     * @param id
     */
    public void setId(String id) {
        this.idSite = id;
    }

    /**
     * Obtiene el nombre del país
     * @return
     */
    public String getName() {
        return nameSite;
    }

    /**
     * Asinga el nombre del país
     * @param name
     */
    public void setName(String name) {
        this.nameSite = name;
    }
}
