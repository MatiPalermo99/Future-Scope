package com.example.future_scope.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.future_scope.MainActivity;
import com.example.future_scope.R;
import com.example.future_scope.model.Pelicula;
import com.example.future_scope.ui.Controlador.PagerController;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeFragment extends Fragment {

    int images[] = {R.drawable.carol};
    int n= Math.abs((new Random().nextInt())%5);

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem tabitem1,tabitem2;
    private PagerController pagerController;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        tabLayout= root.findViewById(R.id.tablayout);
        viewPager=root.findViewById(R.id.viewpager);
        tabitem1=root.findViewById(R.id.tabitem1);
        tabitem2=root.findViewById(R.id.tabitem2);

        pagerController = new PagerController(getChildFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerController);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0||tab.getPosition()==1) pagerController.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        return root;
    }
}
