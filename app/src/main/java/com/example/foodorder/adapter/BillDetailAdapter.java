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
import com.example.foodorder.model.Bill;
import com.example.foodorder.model.BillDetail;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BillDetailAdapter extends RecyclerView.Adapter<BillDetailAdapter.BillViewHolder>{
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private List<BillDetail> listBillDetail;
    private Bill billInfo;
    private MainActivity mainActivity;

    public void setData(List<BillDetail> listBillDetail, MainActivity mainActivity) {
        this.listBillDetail = new ArrayList<>();
        this.listBillDetail = listBillDetail;
        this.mainActivity = mainActivity;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill_detail,parent,false);
        return new BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
        BillDetail billDetail = listBillDetail.get(position);
        if(billDetail == null){
            return;
        }else{
            Glide.with(holder.imgBillProduct.getContext()).load(billDetail.getImg()).disallowHardwareConfig().into(holder.imgBillProduct);
            holder.tvBillProductName.setText(billDetail.getName());
            holder.tvBillProductQuantity.setText(String.valueOf(billDetail.getQuantity()));
            holder.tvBillTotal.setText(formatPrice.format(billDetail.getPrice()) + "VNĐ");
            //holder.tvBillId.setText(billDetail.getBillId().toUpperCase());
        }

//        holder.tvBillId.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for (BillDetail itemBillDetail : listBillDetail){
//                    if(billDetail.getBillId().equals(itemBillDetail.getBillId())){
//                        billInfo.addToListDetailOrder(itemBillDetail);
//                    }
//                }
//                mainActivity.gotoBillDetailFragment(billInfo);
//            }
//        }
//        );
    }

    @Override
    public int getItemCount() {
        if(listBillDetail.size() != 0){
            return listBillDetail.size();
        }else{
            return 0;
        }
    }

    public class BillViewHolder extends RecyclerView.ViewHolder{
        ImageView imgBillProduct;
        TextView tvBillProductName, tvBillProductQuantity, tvBillTotal, tvBillTime;
        //TextView tvBillProductName, tvBillProductQuantity, tvBillTotal, tvBillTime, tvBillId;

        public BillViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBillProduct = itemView.findViewById(R.id.img_bill_product);
            tvBillProductName = itemView.findViewById(R.id.tv_bill_name_customer);
            tvBillProductQuantity = itemView.findViewById(R.id.tv_bill_product_quantity);
            tvBillTotal = itemView.findViewById(R.id.tv_bill_total);
            tvBillTime = itemView.findViewById(R.id.tv_bill_time);
            //tvBillId = itemView.findViewById(R.id.tv_bill_id);
        }
    }
}

