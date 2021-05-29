package com.tac.pickapp.ui.customer.freshdaily;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tac.pickapp.data.remote.dto.Product;
import com.tac.pickapp.databinding.ItemCusProductBinding;
import com.tac.pickapp.databinding.ItemProductBinding;
import com.tac.pickapp.util.Constants;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private OnItemClickListener itemClickListener;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList, OnItemClickListener itemClickListener) {
        this.context = context;
        this.productList = productList;
        this.itemClickListener = itemClickListener;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    public List<Product> getProductList() {
        return productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCusProductBinding binding = ItemCusProductBinding.inflate(LayoutInflater.from(context),
                parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);

        ImageLoader.getInstance().displayImage(Constants.HOST+product.getCoverUrl(), holder.binding.img);
        holder.binding.name.setText(product.getName());
        holder.binding.quantity.setText("Price per "+product.getQuantity().toLowerCase());
        holder.binding.price.setText("P"+ product.getPrice());
        holder.binding.stock.setText("Available stock: "+ product.getNumOfStock());

        holder.binding.btnOrder.setOnClickListener(v -> {
            itemClickListener.onClick(product);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ItemCusProductBinding binding;

        public ViewHolder(@NonNull ItemCusProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickListener {
        void onClick(Product product);
    }
}
