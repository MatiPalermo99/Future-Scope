package com.example.future_scope.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.future_scope.R;

import java.util.Random;

public class WatchlistFragment extends Fragment {

    int images[] = {R.drawable.carol};
    int n= Math.abs((new Random().nextInt())%4);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_watchlist, container, false);
        root.setBackgroundResource(images[0]);

        final TextView text_dashboard = root.findViewById(R.id.text_watchlist);
        text_dashboard.setText("This is watchlist fragment");

        return root;
    }
}