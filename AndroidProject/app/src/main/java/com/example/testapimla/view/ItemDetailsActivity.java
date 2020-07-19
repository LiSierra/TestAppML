package com.example.testapimla.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testapimla.R;
import com.example.testapimla.presenter.item_details.GetDescriptionItemPresenter;
import com.example.testapimla.utils.Constants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Clase donde se implementa la vista
 * de detalles del producto
 */
public class ItemDetailsActivity extends AppCompatActivity implements IItemDetailsView {

    @BindView(R.id.tvTitleDescription)
    protected TextView tvTitle;
    @BindView(R.id.tvPriceDescription)
    protected  TextView tvPrice;
    @BindView(R.id.tvOverview)
    protected TextView tvOverview;
    @BindView(R.id.ivItemDetail)
    protected ImageView ivItemDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        ButterKnife.bind(this);

        this.settingsActivity();

    }

    /**
     * Método utilizado para la configurar los controles
     * de la vista, donde se reciben los parámetros de la
     * vista de consulta del producto e intancia el presentador.
     */
    @Override
    public void settingsActivity(){
        Bundle extras = getIntent().getExtras();
        String title   = extras.getString(Constants.TITLE);
        String image  = extras.getString(Constants.IMAGE);
        String idItem = extras.getString(Constants.ID);
        String price = extras.getString(Constants.PRICE_KEY);

        Picasso.with(this)
                .load(image)
                .placeholder(R.mipmap.ic_launcher)
                .into(ivItemDetail);

        new GetDescriptionItemPresenter(this, idItem);
        tvTitle.setText(title);
        tvPrice.setText(price);
    }

    /**
     * Método para obtener el resultado de la petición de
     * descripción del producto, que permite asignar el texto
     * al control de descripción del producto.
     * @param description
     */
    @Override
    public void getDescription(String description) {
        tvOverview.setText(description);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            tvOverview.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }
    }
}
