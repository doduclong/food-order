package com.example.foodorder.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.MainActivity;
import com.example.foodorder.R;
import com.example.foodorder.model.Bill;
import com.example.foodorder.model.BillDetail;

import java.text.DecimalFormat;
import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder>{
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private List<BillDetail> listBillDetail;
    private List<Bill> listBill;
    private Bill billInfo;
    private MainActivity mainActivity;

//    public void setData(List<BillDetail> listBillDetail, List<Bill> listBill,MainActivity mainActivity) {
//        this.listBillDetail = new ArrayList<>();
//        this.listBillDetail = listBillDetail;
//        this.listBill = listBill;
//        this.mainActivity = mainActivity;
//        notifyDataSetChanged();
//    }

    public void setData(List<Bill> listBill,MainActivity mainActivity) {
        this.listBill = listBill;
        this.mainActivity = mainActivity;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill,parent,false);
        return new BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
        Bill bill2 = listBill.get(position);
        if(bill2 == null){
            return;
        }else{
            holder.tvBillNameCustomer.setText(bill2.getName());
            holder.tvBillAddress.setText(bill2.getAddress());
            holder.tvBillTotal.setText(formatPrice.format(bill2.getTotal()) + "VNĐ");
            holder.tvBillId.setText(bill2.getBillId().toUpperCase());
            holder.tvBillTime.setText(bill2.getTime());
        }

        holder.tvBillId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Bill bill: listBill) {
                    if(bill.getBillId().equals(bill2.getBillId())){
                        billInfo = bill;
                        break;
                    }
                }
                mainActivity.gotoBillDetailFragment(billInfo);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listBill.size() != 0){
            return listBill.size();
        }else{
            return 0;
        }
    }

    public class BillViewHolder extends RecyclerView.ViewHolder{
        TextView tvBillNameCustomer, tvBillAddress, tvBillTotal, tvBillTime, tvBillId;

        public BillViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBillNameCustomer = itemView.findViewById(R.id.tv_bill_name_customer);
            tvBillAddress = itemView.findViewById(R.id.tv_bill_address);
            tvBillTotal = itemView.findViewById(R.id.tv_bill_total);
            tvBillTime = itemView.findViewById(R.id.tv_bill_time);
            tvBillId = itemView.findViewById(R.id.tv_bill_id);
        }
    }
//    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
//    private List<BillDetail> listBillDetail;
//    private List<Bill> listBill;
//    private Bill billInfo;
//    private MainActivity mainActivity;
//
//    public void setData(List<BillDetail> listBillDetail, List<Bill> listBill,MainActivity mainActivity) {
//        this.listBillDetail = new ArrayList<>();
//        this.listBillDetail = listBillDetail;
//        this.listBill = listBill;
//        this.mainActivity = mainActivity;
//        notifyDataSetChanged();
//    }
//
////    public void setData(List<Bill> listBill,MainActivity mainActivity) {
////        this.listBill = new ArrayList<>();
////        this.mainActivity = mainActivity;
////        notifyDataSetChanged();
////    }
//    @NonNull
//    @Override
//    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill,parent,false);
//        return new BillViewHolder(view);
//    }
//
////    @Override
////    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
////        BillDetail billDetail = listBillDetail.get(position);
////        if(billDetail == null){
////            return;
////        }else{
////            Glide.with(holder.imgBillProduct.getContext()).load(billDetail.getImg()).disallowHardwareConfig().into(holder.imgBillProduct);
////            holder.tvBillProductName.setText(billDetail.getName());
////            holder.tvBillProductQuantity.setText(String.valueOf(billDetail.getQuantity()));
////            holder.tvBillTotal.setText(formatPrice.format(billDetail.getPrice()) + "VNĐ");
////            holder.tvBillId.setText(billDetail.getBillId().toUpperCase());
////        }
////        for (Bill bill : listBill) {
////            if(bill.getBillId().equals(billDetail.getBillId())){
////                holder.tvBillTime.setText(bill.getTime());
////                break;
////            }
////        }
////
////        holder.tvBillId.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                for (Bill bill: listBill) {
////                    if(bill.getBillId().equals(billDetail.getBillId())){
////                        billInfo = bill;
////                        break;
////                    }
////                }
////                for (BillDetail itemBillDetail : listBillDetail){
////                    if(billDetail.getBillId().equals(itemBillDetail.getBillId())){
////                        billInfo.addToListDetailOrder(itemBillDetail);
////                    }
////                }
////                mainActivity.gotoBillDetailFragment(billInfo);
////            }
////        });
////    }
//
//    @Override
//    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
//        BillDetail billDetail = listBillDetail.get(position);
////        if(billDetail == null){
////            return;
////        }else{
////            Glide.with(holder.imgBillProduct.getContext()).load(billDetail.getImg()).disallowHardwareConfig().into(holder.imgBillProduct);
////            holder.tvBillProductName.setText(billDetail.getName());
////            holder.tvBillProductQuantity.setText(String.valueOf(billDetail.getQuantity()));
////            holder.tvBillTotal.setText(formatPrice.format(billDetail.getPrice()) + "VNĐ");
////            holder.tvBillId.setText(billDetail.getBillId().toUpperCase());
////        }
//        for (Bill bill : listBill) {
//            holder.tvBillProductName.setText(bill.getName());
//            holder.tvBillTotal.setText(formatPrice.format(bill.getTotal()) + "VNĐ");
//            holder.tvBillId.setText(bill.getBillId().toUpperCase());
//            holder.tvBillTime.setText(bill.getTime());
//        }
//
//        holder.tvBillId.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for (Bill bill: listBill) {
//                    if(bill.getBillId().equals(billDetail.getBillId())){
//                        billInfo = bill;
//                        break;
//                    }
//                }
//                for (BillDetail itemBillDetail : listBillDetail){
//                    if(billDetail.getBillId().equals(itemBillDetail.getBillId())){
//                        billInfo.addToListDetailOrder(itemBillDetail);
//                    }
//                }
//                mainActivity.gotoBillDetailFragment(billInfo);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        if(listBillDetail.size() != 0){
//            return listBillDetail.size();
//        }else{
//            return 0;
//        }
//    }
//
//    public class BillViewHolder extends RecyclerView.ViewHolder{
//        ImageView imgBillProduct;
//        TextView tvBillProductName, tvBillProductQuantity, tvBillTotal, tvBillTime, tvBillId;
//
//        public BillViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imgBillProduct = itemView.findViewById(R.id.img_bill_product);
//            tvBillProductName = itemView.findViewById(R.id.tv_bill_product_name);
//            tvBillProductQuantity = itemView.findViewById(R.id.tv_bill_product_quantity);
//            tvBillTotal = itemView.findViewById(R.id.tv_bill_total);
//            tvBillTime = itemView.findViewById(R.id.tv_bill_time);
//            tvBillId = itemView.findViewById(R.id.tv_bill_id);
//        }
//    }
}
