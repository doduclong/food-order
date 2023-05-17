package com.example.foodorder.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.foodorder.MainActivity;
import com.example.foodorder.R;
import com.example.foodorder.model.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AddProductFragment extends Fragment {
    private DecimalFormat format = new DecimalFormat("###,###,###");
    //private Boolean isAddToCart;
    private MainActivity mainActivity;
    private View mView;
    private Product product;
    private List<Product> listCartProduct;
    private ImageView imgProduct;
    private TextView tvName, tvPrice, tvDescription, tvQuantity, tvTotal;
    private Button btnAddProduct, btnDecrease, btnIncrease, btnClose;

    private int countProduct =1;

    public AddProductFragment(Product product, List<Product> listProduct) {
        this.product = product;
        listCartProduct = listProduct;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_add_food, container, false);
        initItem();
        setValueItem();
        return  mView;
    }
    // Khởi tạo các item
    private void initItem(){
        //isAddToCart = false;
        mainActivity = (MainActivity) getActivity();
        tvName = mView.findViewById(R.id.tvName);
        tvPrice = mView.findViewById(R.id.tvPrice);
        tvDescription = mView.findViewById(R.id.tvDescribe);
        imgProduct = mView.findViewById(R.id.img);
        tvTotal = mView.findViewById(R.id.tvTotal);
        tvQuantity = mView.findViewById(R.id.tvQuantity);
        btnAddProduct = mView.findViewById(R.id.btnAdd);
        btnClose = mView.findViewById(R.id.btnClose);
        btnDecrease = mView.findViewById(R.id.btnDecrease);
        btnIncrease = mView.findViewById(R.id.btnIncrease);
        if(listCartProduct == null){
            listCartProduct = new ArrayList<>();
        }
    }


    private void setValueItem(){
        if(product != null){
            tvName.setText(product.getName());
            tvPrice.setText(format.format(product.getPrice() ) + " VNĐ");
            tvQuantity.setText(1+"");
            tvTotal.setText(format.format(product.getPrice() ) + " VNĐ");
            Glide.with(getContext()).load(product.getImg()).into(imgProduct);
            tvDescription.setText(product.getDescription());
//            for (int i = 0;i< listCartProduct.size();i++){
//                if (listCartProduct.get(i).getName().equals(product.getName())){
//                    //isAddToCart = true;
//                    btnAddProduct.setText("Đã Mua");
//                    btnAddProduct.setBackgroundResource(R.drawable.custom_button_gray);
//                    break;
//                }
//            }

            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainActivity.gotoProductFragment();
                }
            });

            btnDecrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(countProduct >1) {
                        countProduct--;
                        tvQuantity.setText(Integer.toString(countProduct));
                        //tvTotal.setText(Integer.toString(countProduct * product.getPrice()));
                        tvTotal.setText(format.format(countProduct * product.getPrice() ) + " VNĐ");

                    }
                }
            });

            btnIncrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    countProduct++;
                    tvQuantity.setText(Integer.toString(countProduct));
                    //tvTotal.setText(Integer.toString(countProduct * product.getPrice()));
                    tvTotal.setText(format.format(countProduct * product.getPrice() ) + " VNĐ");

                }
            });

            btnAddProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (isAddToCart){
//                        Toast.makeText(getActivity(),"Sản Phẩm này đã tồn tại trong giỏ hàng",Toast.LENGTH_SHORT).show();
//                    }
                        //isAddToCart = true;
                        tvQuantity = mView.findViewById(R.id.tvQuantity);
                        product.setNumProduct(Integer.parseInt(tvQuantity.getText().toString()));
                        mainActivity.addProductToCart(product);
                        Toast.makeText(getActivity(),"Đã thêm món ăn vào giỏ hàng",Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}

