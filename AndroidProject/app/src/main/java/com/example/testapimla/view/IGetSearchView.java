package com.example.testapimla.view;


import android.widget.ArrayAdapter;

import com.example.testapimla.adapter.ListItemAdapter;
import com.example.testapimla.models.Search;

import java.util.List;

/**
 * Interfaz donde se definen los métodos a implementar en la vista
 * para la consulta del producto.
 */
public interface IGetSearchView {
    void generateLinearLayoutVertical();
    ListItemAdapter createAdapter(List<Search> movieSearches);
    void initializeAdapterRV(ListItemAdapter adapter);
    void initializeAdapterSP(ArrayAdapter adapter);
    void sendData(boolean isNewPage);
    void listenerScroll();
    void animationSetting();
}
