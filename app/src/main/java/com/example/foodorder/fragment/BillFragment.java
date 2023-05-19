package com.example.foodorder.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.MainActivity;
import com.example.foodorder.R;
import com.example.foodorder.adapter.BillAdapter;
import com.example.foodorder.model.Bill;
import com.example.foodorder.model.BillDetail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BillFragment extends Fragment {
//    private MainActivity mainActivity;
//    private List<Bill> listBill;
//    private List<BillDetail> listBillDetail;
//
//    private View mView;
//    private EditText etSearchPhoneNumber;
//    private ImageButton btnSearch;
//    private RecyclerView recyclerView;
//
//    private BillAdapter billAdapter;
//
//    public BillFragment() {
//    }
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        mView = inflater.inflate(R.layout.fragment_bill,container,false);
//        initItem();
//        return mView;
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (!etSearchPhoneNumber.getText().toString().trim().isEmpty()){
//            findBill();
//        }
//    }
//    private void initItem(){
//        listBill = new ArrayList<>();
//        listBillDetail = new ArrayList<>();
//        mainActivity = (MainActivity) getActivity();
//        billAdapter = new BillAdapter();
//        etSearchPhoneNumber = mView.findViewById(R.id.et_search_phone_number);
//        recyclerView = mView.findViewById(R.id.rcv_bill_search);
//        btnSearch = mView.findViewById(R.id.btn_search);
//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                findBill();
//            }
//        });
//    }
//
//    private void findBill(){
//        listBill.clear();
//        listBillDetail.clear();
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("DbBill");
//        myRef.orderByChild("phoneNumber").equalTo(etSearchPhoneNumber.getText().toString())
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        billAdapter.notifyDataSetChanged();
//                        for (DataSnapshot dataBill : snapshot.getChildren()){
//                            Log.i("dataBill", dataBill.toString());
//                            Log.i("dataBill", dataBill.getValue(Bill.class).toString());
//                            Log.i("dataBill", dataBill.getKey());
//                            Bill bill = dataBill.getValue(Bill.class);
//                            bill.setBillId(dataBill.getKey());
//                            listBill.add(bill);
//                        }
//
//                        if (listBill.size() > 0){
//                            findBillDetail(myRef);
//                            //setDataBillAdapter();
//                        }
//                        else {
//                            Toast.makeText(getContext(),"Không tìm thấy đơn hàng",Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(getContext(),"Lỗi kết nối firebase",Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//
//    private void findBillDetail(DatabaseReference myRef){
//        if (listBill.size() > 0){
//            for (int i = 0; i< listBill.size(); i++){
//                Bill order = listBill.get(i);
//                myRef.child(order.getBillId()).child("detail").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot dataDetail : snapshot.getChildren()){
//                            billAdapter.notifyDataSetChanged();
//                            BillDetail detailOrder = dataDetail.getValue(BillDetail.class);
//                            listBillDetail.add(detailOrder);
//                        }
//                        if (listBillDetail.size() > 0){
//                            setDataBillAdapter();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(getContext(),"Lỗi kết nối firebase",Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        }
//    }
//    private void setDataBillAdapter(){
//        billAdapter.setData(listBillDetail, listBill, mainActivity);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity,RecyclerView.VERTICAL,false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(billAdapter);
//    }
private MainActivity mainActivity;
    private List<Bill> listBill;
    private List<BillDetail> listBillDetail;

    private View mView;
    private EditText etSearchPhoneNumber;
    private ImageButton btnSearch;
    private RecyclerView recyclerView;

    private BillAdapter billAdapter;

    public BillFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_bill,container,false);
        initItem();
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!etSearchPhoneNumber.getText().toString().trim().isEmpty()){
            findBill();
        }
    }
    private void initItem(){
        listBill = new ArrayList<>();
        listBillDetail = new ArrayList<>();
        mainActivity = (MainActivity) getActivity();
        billAdapter = new BillAdapter();
        etSearchPhoneNumber = mView.findViewById(R.id.et_search_phone_number);
        recyclerView = mView.findViewById(R.id.rcv_bill_search);
        btnSearch = mView.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findBill();
            }
        });
    }

    private void findBill(){
        listBill.clear();
        listBillDetail.clear();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("DbBill");
        myRef.orderByChild("phoneNumber").equalTo(etSearchPhoneNumber.getText().toString())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        billAdapter.notifyDataSetChanged();
                        for (DataSnapshot dataBill : snapshot.getChildren()){
                            Log.i("dataBill", dataBill.toString());
                            Log.i("dataBill", dataBill.getValue(Bill.class).toString());
                            Log.i("dataBill", dataBill.getKey());
                            Bill bill = dataBill.getValue(Bill.class);
                            bill.setBillId(dataBill.getKey());
                            listBill.add(bill);
                        }

                        if (listBill.size() > 0){
                            //findBillDetail(myRef);
                            setDataBillAdapter();
                        }
                        else {
                            Toast.makeText(getContext(),"Không tìm thấy đơn hàng",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(),"Lỗi kết nối firebase",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setDataBillAdapter(){
        billAdapter.setData(listBill, mainActivity);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(billAdapter);
    }
}
