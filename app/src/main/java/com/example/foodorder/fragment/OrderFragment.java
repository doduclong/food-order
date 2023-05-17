package com.example.foodorder.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.MainActivity;
import com.example.foodorder.R;
import com.example.foodorder.model.Bill;

import java.text.DecimalFormat;

public class OrderFragment extends Fragment {
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    public static final String TAG = OrderFragment.class.getName();
    private Bill bill;
    private MainActivity mainActivity;

    private View mView;
    private Button btnOrderInfoBack;
    private TextView tvOrderInfoNo,tvOrderInfoDate,tvOrderInfoCustName,tvOrderInfoCustAddress
            ,tvOrderInfoCustPhone,tvOrderInfoTotal,tvOrderInfoStatus;
    private RecyclerView rcvOrderInfo;


    public OrderFragment(Bill billInfo) {
        bill = billInfo;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_bill_detail, container, false);
        initItem();
        setInfoForBill();
        return mView;
    }
    // Khởi tạo các item
    private void initItem(){
        mainActivity = (MainActivity) getActivity();
        tvOrderInfoNo = mView.findViewById(R.id.tv_order_info_no);
        tvOrderInfoDate = mView.findViewById(R.id.tv_order_info_date);
        tvOrderInfoCustName = mView.findViewById(R.id.tv_order_info_cust_name);
        tvOrderInfoCustAddress = mView.findViewById(R.id.tv_order_info_cust_address);
        tvOrderInfoCustPhone = mView.findViewById(R.id.tv_order_info_cust_phone);
        tvOrderInfoTotal = mView.findViewById(R.id.tv_order_info_total);
        tvOrderInfoStatus = mView.findViewById(R.id.tv_order_info_status);
        rcvOrderInfo = mView.findViewById(R.id.rcv_order_info);
        btnOrderInfoBack = mView.findViewById(R.id.btn_order_info_back);
        btnOrderInfoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null){
                    getFragmentManager().popBackStack();
                }
            }
        });
    }
    // Set nội dung cho các item
    private void setInfoForBill(){
        tvOrderInfoNo.setText(bill.getBillId().toUpperCase());
        tvOrderInfoDate.setText(bill.getTime());
        tvOrderInfoCustName.setText(bill.getName());
        tvOrderInfoCustAddress.setText(bill.getAddress());
        tvOrderInfoCustPhone.setText(bill.getPhoneNumber());
        tvOrderInfoTotal.setText(formatPrice.format(bill.getTotal()) + "VNĐ");
        tvOrderInfoStatus.setText(bill.getStatus());
    }
}
