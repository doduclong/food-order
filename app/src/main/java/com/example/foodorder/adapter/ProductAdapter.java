package com.example.foodorder.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorder.MainActivity;
import com.example.foodorder.R;
import com.example.foodorder.model.Product;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHoder>{
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private List<Product> listProduct;
    private MainActivity mainActivity;

    // Khai báo Interface giúp cho việc click vào phần tử của recycleview
    public interface IClickItemProductListener{
        void onClickItemProduct(Product product);
    }

    //    set dữ liệu vào recycle view
    public void setData(List<Product> mList, MainActivity mainActivity) {
        this.listProduct = mList;
        this.mainActivity = mainActivity;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ProductViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHoder holder, int position) {
        Product product = listProduct.get(position);
        if(product == null){
            return;
        }
        else{

            Glide.with(holder.imgProduct.getContext())
                    .load(product.getImg())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .into(holder.imgProduct);
            holder.tvProductName.setText(product.getName());
            holder.tvProductPrice.setText(formatPrice.format(product.getPrice()) + " VNĐ");
            holder.setItemClickListener(new IClickItemProductListener() {
                @Override
                public void onClickItemProduct(Product product) {
                    mainActivity.gotoAddProductFragment(product);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(listProduct != null){
            return listProduct.size();
        }else return 0;
    }

    public class ProductViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imgProduct;
        TextView tvProductName,tvProductPrice;
        IClickItemProductListener iClickItemProductListener;

        public ProductViewHoder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            tvProductPrice = itemView.findViewById(R.id.tv_product_price);
            itemView.setOnClickListener(this);
        }
        public void setItemClickListener(IClickItemProductListener itemClickListener){
            this.iClickItemProductListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            this.iClickItemProductListener.onClickItemProduct(listProduct.get(getAdapterPosition()));
        }
    }
}
