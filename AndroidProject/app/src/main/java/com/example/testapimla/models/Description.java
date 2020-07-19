package com.example.testapimla.models;

import com.google.gson.annotations.SerializedName;

/**
 * Objeto para el detalle del ítem
 */
public class Description {
    /**
     * Mapea la descripción para el detalle del ítem
     */
    @SerializedName("plain_text")
    private String description;

    /**
     * Obtiene la descripción del ítem
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Asigna la descripción del ítem
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
