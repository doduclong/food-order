package com.example.foodorder.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.MainActivity;
import com.example.foodorder.R;
import com.example.foodorder.adapter.ProductAdapter;
import com.example.foodorder.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class ProductFragment extends Fragment {
    private MainActivity mainActivity;
    private List<Product> listAllProduct;
    private View mView;
    private RecyclerView recyclerView;
    private ImageView titleImage;
    private ProductAdapter productAdapter;

    public ProductFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_food,container,false);
        initItem();
        setDataProductAdapter();
        return mView;
    }
    private void initItem(){
        recyclerView = mView.findViewById(R.id.rcv_product);
        titleImage = mView.findViewById(R.id.titleImage);
        listAllProduct = getDataProduct();
        mainActivity = (MainActivity) getActivity();
    }
    private void setDataProductAdapter(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mainActivity, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        productAdapter = new ProductAdapter();
        productAdapter.setData(listAllProduct, mainActivity);
        recyclerView.setAdapter(productAdapter);
    }
    private List<Product> getDataProduct(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("DbProduct");

        List<Product> mListProduct = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productAdapter.notifyDataSetChanged();
                Log.i("Snapshot",snapshot.toString());
                for (DataSnapshot data : snapshot.getChildren()){
                    Product product = data.getValue(Product.class);
                    product.setId(data.getKey());
                    mListProduct.add(product);
                }
//                setProductSearchAdapter(mListProduct);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Không tải được dữ liệu từ firebase"
                        +error.toString(),Toast.LENGTH_LONG).show();
                Log.d("MYTAG","onCancelled"+ error.toString());
            }
        });
        return mListProduct;
    }
}
