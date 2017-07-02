package com.example.root.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayTodo extends AppCompatActivity {

    TextView mytitle;
    TextView mydesc;
    DBHandler dbHandler;
    Integer prid = new Integer(10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_todo);

        mytitle = (TextView) findViewById(R.id.textView3);
        mydesc = (TextView) findViewById(R.id.textView4);

        Bundle tododata = getIntent().getExtras();
        if(tododata == null)
            return;


        String prtitle = tododata.getString("prtitle");
        String prdesc = tododata.getString("prdesc");
        prid = new Integer(tododata.getInt("prid"));

        mytitle.setText(String.valueOf(prid));
        mydesc.setText(prdesc);

    }

    public void deletetodo(View view){
        dbHandler.deletetodo(String.valueOf(prid));
        backhome2(view);
    }

    public void backhome2(View view){
        Intent I = new Intent(this,MainActivity.class);
        startActivity(I);
    }

}
