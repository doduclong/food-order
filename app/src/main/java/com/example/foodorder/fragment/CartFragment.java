package com.example.foodorder.fragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.MainActivity;
import com.example.foodorder.R;
import com.example.foodorder.adapter.ProductCartAdapter;
import com.example.foodorder.model.BillDetail;
import com.example.foodorder.model.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartFragment extends Fragment {
    private int totalPrice;
    private View mView;
    private MainActivity mainActivity;
    private DecimalFormat format;
    private RelativeLayout rlCartEmpty,rlCart;
    private List<Product> listCartProduct;
    private RecyclerView recyclerViewCartProduct;
    private TextView tvTotalCart;
    private EditText etName, etAddress, etPhoneNumber, etNote;
    private Button btnOrder;

    private ProductCartAdapter productCartAdapter;
    public CartFragment(List<Product> listCartProduct) {
        //this.listCartProduct = new ArrayList<>();
        this.listCartProduct = listCartProduct;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_cart,container,false);
        initItem();
        setVisibilityView();
        return mView;
    }

    // Khởi tạo các item
    private void initItem(){
        productCartAdapter = new ProductCartAdapter();
        rlCartEmpty = mView.findViewById(R.id.rl_empty_cart);
        rlCart = mView.findViewById(R.id.rl_cart);
        recyclerViewCartProduct = mView.findViewById(R.id.recycler_view_cart);
        tvTotalCart = mView.findViewById(R.id.tv_total_money_bill);
        etName = mView.findViewById(R.id.et_name);
        etAddress = mView.findViewById(R.id.et_address);
        etPhoneNumber = mView.findViewById(R.id.et_phoneNumber);
        etNote = mView.findViewById(R.id.et_note);
        btnOrder = mView.findViewById(R.id.btn_order);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                addProductToOrder();
            }
        });
        mainActivity = (MainActivity) getActivity();
        format = new DecimalFormat("###,###,###");
    }
    // Set trạng thái hiển thị các view
    // Set trạng thái hiển thị các view
    private void setVisibilityView(){
        if (listCartProduct.size() == 0){
            displayEmptyCart();
        }else {
            displayCart();
            setDataProductCartAdapter();
        }
    }
    // set data ProductCartAdapter
    private void setDataProductCartAdapter(){
        productCartAdapter.setData(listCartProduct, mainActivity,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity,RecyclerView.VERTICAL,false);
        recyclerViewCartProduct.setLayoutManager(linearLayoutManager);
        recyclerViewCartProduct.setAdapter(productCartAdapter);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addProductToOrder(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("DbBill");
        Map<String,Object> map = new HashMap<>();

        // Lấy ngày order = now
        //Date date = new Date(System.currentTimeMillis());
        LocalDateTime time = LocalDateTime.now();
        map.put("time", time.toString());
        map.put("name", etName.getText().toString());
        map.put("address", etAddress.getText().toString());
        map.put("phoneNumber", etPhoneNumber.getText().toString());
        map.put("note", etNote.getText().toString());

        int num = 0;
        for (Product product : listCartProduct){
            num = num + product.getNumProduct();
        }
        map.put("quantity",num);
        map.put("total",totalPrice);
        map.put("status","Chờ xác nhận");

        String odrKey = myRef.push().getKey();
        Log.i("oderKey", odrKey);
        myRef.child(odrKey).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                List<BillDetail> listBillDetail = makeDetailOrder(odrKey);
                for (BillDetail billDetail : listBillDetail){
                    myRef.child(odrKey).child("detail").child(myRef.push().getKey())
                            .setValue(billDetail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(),"Đặt hàng thành công",Toast.LENGTH_SHORT).show();
                                    listCartProduct.clear();
                                    displayEmptyCart();
                                }
                            });

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"Đặt hàng không thành công",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void displayCart(){
        rlCartEmpty.setVisibility(View.GONE);
        rlCart.setVisibility(View.VISIBLE);
        String total = format.format(getTotalProduct());
        tvTotalCart.setText( total +" VNĐ" );
    }
    public void displayEmptyCart(){
        rlCartEmpty.setVisibility(View.VISIBLE);
        rlCart.setVisibility(View.GONE);
    }


    // lấy giá trị tổng tiền tất cả sản phẩm trong giỏ hàng
    private int getTotalProduct(){
        for (Product product : listCartProduct){
            int priceProduct = product.getPrice() ;
            totalPrice = totalPrice +  priceProduct * product.getNumProduct();
        }
        return totalPrice;
    }

    //    lấy ra các sản phầm vừa thêm vào giỏ hàng để thêm vào danh sách hàng sau khi đã bấm đặt hàng
    private List<BillDetail> makeDetailOrder(String odrNo){
        List<BillDetail> listBillDetail = new ArrayList<>();
        for (Product product : mainActivity.getProductInCart()){
            BillDetail billDetail = new BillDetail();
            billDetail.setBillId(odrNo);
            billDetail.setName(product.getName());
            billDetail.setPrice(product.getPrice());
            billDetail.setImg(product.getImg());
            billDetail.setNumProduct(product.getNumProduct());
            billDetail.setStatus("Chờ xác nhận");
            listBillDetail.add(billDetail);
        }
        return listBillDetail;
    }

    public void setTotalPrice(int increase, int count, int price ){
        if( increase == 0){//giảm số lượng sản phầm -> giảm tiền
            totalPrice = totalPrice - price * count;
        }else if (increase == 1){
            totalPrice = totalPrice + price * count;
        }

        tvTotalCart.setText( format.format(totalPrice) + " VNĐ");
    }
    public void setCountForProduct(int position, int numProduct){
        listCartProduct.get(position).setNumProduct(numProduct);
    }
}
