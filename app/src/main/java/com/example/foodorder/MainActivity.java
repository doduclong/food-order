package com.example.foodorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodorder.fragment.AboutFragment;
import com.example.foodorder.fragment.CartFragment;
import com.example.foodorder.fragment.AddProductFragment;
import com.example.foodorder.fragment.OrderFragment;
import com.example.foodorder.fragment.ProductFragment;
import com.example.foodorder.model.Bill;
import com.example.foodorder.model.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //private List<Product> listProduct;
    private List<Product> listCartProduct;
    private FragmentTransaction fragmentTransaction;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listCartProduct = new ArrayList<>();
        //listProduct = new ArrayList<>();
        initItem();
        setDataBotNavHome();
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("DbProduct");
//        Product product = new Product("1", "https://firebasestorage.googleapis.com/v0/b/food-order-55191.appspot.com/o/bunbo.png?alt=media&token=6058086e-ac1b-4b63-bea9-416aaa0140b9", "Bún bò",
//                "Bò, Chân giò, Mọc", 35000);
//        Product product1 = new Product("2", "https://firebasestorage.googleapis.com/v0/b/food-order-55191.appspot.com/o/bunca.png?alt=media&token=b53b1f07-942d-44c9-8453-72fa35add97d", "Bún cá",
//                "Cá rô", 25000);
//        Product product2 = new Product("3", "https://firebasestorage.googleapis.com/v0/b/food-order-55191.appspot.com/o/bunhaisan.png?alt=media&token=a7bfd9e8-2404-497c-b073-704338f9365c", "Bún hải sản",
//                "Tôm, mực, bề bề", 40000);
//        Product product3 = new Product("4", "https://firebasestorage.googleapis.com/v0/b/food-order-55191.appspot.com/o/buntron.png?alt=media&token=fe1e15bd-b943-426d-8ccc-3fa3d375b0db", "Bún trộn",
//                "Cá, mọc, giò", 35000);
//        Product product4 = new Product("5", "https://firebasestorage.googleapis.com/v0/b/food-order-55191.appspot.com/o/crduabo.png?alt=media&token=7aca7f90-12d1-4efc-9230-c2a0fd6aa314", "Cơm rang dưa bò",
//                "Dưa chua, bò", 35000);
//        Product product5 = new Product("6", "https://firebasestorage.googleapis.com/v0/b/food-order-55191.appspot.com/o/crhaisan.png?alt=media&token=a0650876-c312-49e0-b818-d751be8f268b", "Cơm rang hải sản",
//                "Tôm, mực, bề bề", 40000);
//        Product product6 = new Product("7", "https://firebasestorage.googleapis.com/v0/b/food-order-55191.appspot.com/o/crthapcam.png?alt=media&token=c0eecf6d-dbc1-4a51-9392-9951b7ef1f78", "Cơm rang thập cẩm",
//                "Xúc xích, bò", 30000);
//        Product product7 =  new Product("8","https://firebasestorage.googleapis.com/v0/b/food-order-55191.appspot.com/o/mixaobo.png?alt=media&token=091875cc-a46c-436c-9f8f-7b95867a468f", "Mì xào bò",
//                "Bò, rau cải", 35000);
//        Product product8 =  new Product("9","https://firebasestorage.googleapis.com/v0/b/food-order-55191.appspot.com/o/mixaohaisan.png?alt=media&token=a975b6e7-fdd5-40be-b0c0-5e66f32bb402", "Mì xào hải sản",
//                "Tôm, mực, bề bề", 40000);
//        listProduct.add(product);
//        listProduct.add(product1);
//        listProduct.add(product2);
//        listProduct.add(product3);
//        listProduct.add(product4);
//        listProduct.add(product5);
//        listProduct.add(product6);
//        listProduct.add(product7);
//        listProduct.add(product8);
//        myRef.setValue(listProduct);
    }

    private void setDataBotNavHome() {
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.mFood:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout, new ProductFragment());
                        fragmentTransaction.commit();
                        break;
                    case R.id.mCart:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout, new CartFragment(listCartProduct));
                        fragmentTransaction.commit();
                        break;
                    case R.id.mAbout:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout, new AboutFragment());
                        fragmentTransaction.commit();
                        break;
                }
                return true;
            }
        });
    }

    private void initItem() {
        bottomNavigation = findViewById(R.id.bottom_navigation);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new ProductFragment());
        fragmentTransaction.commit();
    }

    public void gotoAddProductFragment(Product product){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new AddProductFragment(product,listCartProduct));
        fragmentTransaction.commit();
    }

    public void gotoProductFragment(){
        bottomNavigation = findViewById(R.id.bottom_navigation);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new ProductFragment());
        fragmentTransaction.commit();
    }
    public void addProductToCart(Product product){
        listCartProduct.add(product);
    }
    public void setNumberOfProduct(int position, int numberProduct){
        listCartProduct.get(position).setNumProduct(numberProduct);
    }
    public List<Product> getProductInCart() {
        return listCartProduct;
    }
    public void toBillDetailFragment(Bill billInfo){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new OrderFragment(billInfo));
        fragmentTransaction.addToBackStack(OrderFragment.TAG);
        fragmentTransaction.commit();
    }
}