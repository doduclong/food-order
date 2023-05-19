package com.example.foodorder.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.MainActivity;
import com.example.foodorder.R;
import com.example.foodorder.adapter.BillAdapter;
import com.example.foodorder.adapter.BillDetailAdapter;
import com.example.foodorder.model.Bill;
import com.example.foodorder.model.BillDetail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BillDetailFragment extends Fragment {
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    public static final String TAG = BillDetailFragment.class.getName();
    private Bill bill;
    private MainActivity mainActivity;
    private View mView;
    private Button btnOrderInfoBack;
    private TextView tvBillDetailId, tvBillDetailTime, tvBillDetailNameCustomer, tvBillDetailAddress
            , tvBillDetailPhoneNumber, tvBillTotal;
    private RecyclerView recyclerView;

    private List<Bill> listBill;
    private List<BillDetail> listBillDetail = new ArrayList<>();;

    private BillDetailAdapter billDetailAdapter;


    public BillDetailFragment() {
    }

    public BillDetailFragment(Bill billInfo) {
        bill = billInfo;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_bill_detail, container, false);
        initItem();
        findBillDetail();
        setInfoForBill();

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bill.getPhoneNumber().trim().isEmpty()){
            findBillDetail();
        }
    }
    // Khởi tạo các item
    private void initItem(){
        mainActivity = (MainActivity) getActivity();
        billDetailAdapter = new BillDetailAdapter();
        tvBillDetailId = mView.findViewById(R.id.tv_bill_detail_id);
        tvBillDetailTime = mView.findViewById(R.id.tv_bill_detail_time);
        tvBillDetailNameCustomer = mView.findViewById(R.id.tv_bill_detail_name_customer);
        tvBillDetailAddress = mView.findViewById(R.id.tv_bill_detail_address);
        tvBillDetailPhoneNumber = mView.findViewById(R.id.tv_phone_number);
        tvBillTotal = mView.findViewById(R.id.tv_bill_detail_total);
        recyclerView = mView.findViewById(R.id.rcv_bill_info);
        btnOrderInfoBack = mView.findViewById(R.id.btn_bill_detail_back);
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
        tvBillDetailId.setText(bill.getBillId().toUpperCase());
        tvBillDetailTime.setText(bill.getTime());
        tvBillDetailNameCustomer.setText(bill.getName());
        tvBillDetailAddress.setText(bill.getAddress());
        tvBillDetailPhoneNumber.setText(bill.getPhoneNumber());
        tvBillTotal.setText(formatPrice.format(bill.getTotal()) + "VNĐ");
    }

    private void findBillDetail(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("DbBill");
            myRef.child(bill.getBillId()).child("detail").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataDetail : snapshot.getChildren()){
                        billDetailAdapter.notifyDataSetChanged();
                        BillDetail billDetail = dataDetail.getValue(BillDetail.class);
                        billDetail.setBillId(bill.getBillId());
                        listBillDetail.add(billDetail);
                    }
                    if (listBillDetail.size() > 0){
                        setDataBillAdapter();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(),"Lỗi kết nối firebase",Toast.LENGTH_SHORT).show();
                }
            });
    }

    private void setDataBillAdapter(){
        billDetailAdapter.setData(listBillDetail, mainActivity);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(billDetailAdapter);
    }
}
