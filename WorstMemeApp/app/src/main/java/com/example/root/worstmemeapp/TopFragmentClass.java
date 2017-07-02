package com.example.root.worstmemeapp;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class TopFragmentClass extends Fragment{

    private static EditText toptext;
    private static EditText bottomtext;

    TopFragmentListener ActivityCommander;

    public interface TopFragmentListener{
        void CreateMeme(String a,String b);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            ActivityCommander = (TopFragmentListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container,false);

        toptext = (EditText) view.findViewById(R.id.editText);
        bottomtext = (EditText) view.findViewById(R.id.editText2);

        final Button button = (Button) view.findViewById(R.id.button);

        button.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        ButtonClicked(v);
                    }
                }
        );

        return  view;

    }

    public void ButtonClicked(View v)
    {
        ActivityCommander.CreateMeme(toptext.getText().toString(),bottomtext.getText().toString());
    }

}


