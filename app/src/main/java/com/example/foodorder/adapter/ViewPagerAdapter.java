//package com.example.foodorder.adapter;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentPagerAdapter;
//
//import com.example.foodorder.fragment.ProductFragment;
//
//public class ViewPagerAdapter extends FragmentPagerAdapter {
//
//    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
//        super(fm);
//    }
//
//    @NonNull
//    @Override
//    public Fragment getItem(int position) {
//        switch (position){
//            case 0:return new ProductFragment();
//            case 1:return new FragmentDrink();
//            case 2:return new FragmentCart();
//        }
//        return null;
//    }
//
//    @Override
//    public int getCount() {
//        return 3;
//    }
//}
