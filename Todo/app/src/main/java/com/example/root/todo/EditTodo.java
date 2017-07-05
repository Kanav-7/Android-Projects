package com.example.root.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditTodo extends AppCompatActivity {

    EditText mytitle;
    EditText mydesc;
    DBHandler dbHandler;
    String edtitle,eddesc;
    int edid,eddone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        mytitle = (EditText) findViewById(R.id.editText3);
        mydesc = (EditText) findViewById(R.id.editText4);
        dbHandler = new DBHandler(this,null,null,2);

        Bundle tododata = getIntent().getExtras();
        if(tododata == null)
            return;

        edtitle = tododata.getString("edtitle");
        eddesc = tododata.getString("eddesc");
        edid = tododata.getInt("edid");
        eddone = tododata.getInt("eddone");

        mytitle.setText(edtitle);
        mydesc.setText(eddesc);

    }

    public void edittodo(View view){
        Todo newtodo = new Todo(mytitle.getText().toString(),mydesc.getText().toString(),eddone);
        newtodo.set_id(edid);
        dbHandler.updatetodo(newtodo);
        Toast.makeText(EditTodo.this,"To-Do Edited Successfully",Toast.LENGTH_SHORT).show();
        backhome(view);
    }

    public void backhome(View view){
        Intent I = new Intent(this,MainActivity.class);
        startActivity(I);
    }
}
