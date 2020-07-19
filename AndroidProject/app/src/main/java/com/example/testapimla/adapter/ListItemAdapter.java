package com.example.testapimla.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapimla.R;
import com.example.testapimla.models.Search;
import com.example.testapimla.utils.Constants;
import com.example.testapimla.view.ItemDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Clase que se encarga de configurar el adaptador del RecyclerView
 */

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>{
    private List<Search> searchList;
    private Activity activity;

    public ListItemAdapter(List<Search> searchList, Activity activity){
            this.searchList = searchList;
            this.activity = activity;
            }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_search, parent, false);
        return new ListItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        final Search search = searchList.get(position);
        String price = Constants.PRICE + search.getPriceItem();
        Picasso.with(activity)
                .load(search.getImageItem())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.ivItem);
        holder.tvTitle.setText(search.getTitleItem());
        holder.tvPrice.setText(price);
        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            /**
             * Listener para pasar datos a la vista de detalle del producto
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ItemDetailsActivity.class);
                intent.putExtra(Constants.TITLE, search.getTitleItem());
                intent.putExtra(Constants.IMAGE, search.getImageItem());
                intent.putExtra(Constants.ID, search.getIdItem());
                intent.putExtra(Constants.PRICE_KEY,search.getPriceItem());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity);
                    activity.startActivity(intent,options.toBundle());
                }else {
                    activity.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    /**
     * Clase para la configuración de los controles del ítem
     */
    public static class ListItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cvItem)
        protected CardView cvItem;
        @BindView(R.id.tvTitle)
        protected TextView tvTitle;
        @BindView(R.id.tvPrice)
        protected  TextView tvPrice;
        @BindView(R.id.ivItem)
        protected ImageView ivItem;


        ListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
