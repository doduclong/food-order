package com.example.foodorder.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.MainActivity;
import com.example.foodorder.R;

public class SelfAdapter extends RecyclerView.Adapter<SelfAdapter.SelfViewHolder>{
    private MainActivity mainActivity;
    @NonNull
    @Override
    public SelfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//Định nghĩa các Item layout và khởi tạo Holder.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_self,parent,false);
        return new SelfAdapter.SelfViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelfAdapter.SelfViewHolder holder, int position) {//Thiết lập các thuộc tính của View và dữ liệu.
        holder.imgOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.gotoBillFragment();
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SelfViewHolder extends RecyclerView.ViewHolder {
        ImageView imgOrder;

        public SelfViewHolder(@NonNull View itemView) {
            super(itemView);
            imgOrder = itemView.findViewById(R.id.btn_self_order);
        }
    }
}
