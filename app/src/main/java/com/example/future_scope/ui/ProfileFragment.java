package com.example.future_scope.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.future_scope.R;

import java.util.Random;

public class ProfileFragment extends Fragment {

    int images[] = {R.drawable.carol};
    int n= Math.abs((new Random().nextInt())%5);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        root.setBackgroundResource(images[0]);

        final TextView text_home = root.findViewById(R.id.text_profile);
        text_home.setText("This is profile fragment");

        return root;
    }



}
