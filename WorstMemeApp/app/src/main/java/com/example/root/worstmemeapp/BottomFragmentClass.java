package com.example.root.worstmemeapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BottomFragmentClass extends Fragment{

    private static TextView topprint;
    private static TextView bottomprint;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);

        topprint = (TextView) view.findViewById(R.id.textView);
        bottomprint = (TextView) view.findViewById(R.id.textView3);

        return view;
    }

    public void GenerateMeme(String a,String b)
    {
        topprint.setText(a);
        bottomprint.setText(b);
    }
}
