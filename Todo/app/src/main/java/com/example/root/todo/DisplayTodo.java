package com.example.root.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayTodo extends AppCompatActivity {

    TextView mytitle;
    TextView mydesc;
    DBHandler dbHandler;
    Integer prid;
    String prtitle;
    String prdesc;
    Integer prdone;
    CheckBox check;
    TextView mydone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_todo);

        mytitle = (TextView) findViewById(R.id.textView3);
        mydesc = (TextView) findViewById(R.id.textView4);
        check = (CheckBox) findViewById(R.id.checkBox2);
        mydone = (TextView) findViewById(R.id.textView5);

        Bundle tododata = getIntent().getExtras();
        if(tododata == null)
            return;


        prtitle = tododata.getString("prtitle");
        prdesc = tododata.getString("prdesc");
        prid = tododata.getInt("prid");
        prdone = tododata.getInt("prdone");

        if (prdone == 0){
            mydone.setText("Task is not done");
            check.setText("Mark Done");
        }
        else
        {
            mydone.setText("Task is done");
            check.setChecked(true);
            check.setText("Mark not done");
        }
        mytitle.setText(prtitle);
        mydesc.setText(prdesc);
        dbHandler = new DBHandler(this,null,null,2);

    }


    public void deletetodo(View view){
        //mydesc.setText(String.valueOf(prid));
        dbHandler.deletetodo(prid);
        Toast.makeText(DisplayTodo.this,"To-Do Deleted Successfully",Toast.LENGTH_SHORT).show();
        backhome2(view);
    }

    public void edittodo(View view){
        Intent I = new Intent(DisplayTodo.this,EditTodo.class);
        I.putExtra("edtitle",prtitle);
        I.putExtra("eddesc",prdesc);
        I.putExtra("edid",prid);
        I.putExtra("eddone",prdone);
        startActivity(I);
    }

    public void backhome2(View view){
        Intent I = new Intent(this,MainActivity.class);
        startActivity(I);
    }

    public void doneclicked(View view){
        if(prdone == 0){
            Todo mytodo = new Todo(prtitle,prdesc,1);
            mytodo.set_id(prid);
            dbHandler.updatetodo(mytodo);
            mydone.setText("Task is done");
            check.setText("Mark not done");
        }
        else {
            Todo mytodo = new Todo(prtitle,prdesc,0);
            mytodo.set_id(prid);
            dbHandler.updatetodo(mytodo);
            mydone.setText("Task is not done");
            check.setText("Mark Done");
        }
    }

}
