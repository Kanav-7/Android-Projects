package com.example.root.worstmemeapp;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TopFragmentClass.TopFragmentListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void CreateMeme(String top, String bottom) {
       // FragmentManager manager = getFragmentManager();
        BottomFragmentClass bottomFragment = (BottomFragmentClass) getSupportFragmentManager().findFragmentById(R.id.fragment5);
        bottomFragment.GenerateMeme(top,bottom);
    }
}
