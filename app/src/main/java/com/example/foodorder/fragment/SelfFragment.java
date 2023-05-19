package com.example.foodorder.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.foodorder.MainActivity;
import com.example.foodorder.R;

import java.util.ArrayList;

public class SelfFragment extends Fragment {
    private ImageButton btnCart;
    private View mView;
    private MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_self, container, false);
        initItem();
        setValueItem();
        return  mView;
    }

    private void initItem(){
        //isAddToCart = false;
        mainActivity = (MainActivity) getActivity();
        btnCart = mView.findViewById(R.id.btn_self_order);

    }

    private void setValueItem(){
        btnCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainActivity.gotoBillFragment();
                }
            });

    }
}
