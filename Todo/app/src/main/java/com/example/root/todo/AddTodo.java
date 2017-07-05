package com.example.root.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddTodo extends AppCompatActivity {

    EditText mytitle;
    EditText mydesc;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        mytitle = (EditText) findViewById(R.id.editText);
        mydesc = (EditText) findViewById(R.id.editText2);
        dbHandler = new DBHandler(this,null,null,2);
    }

    public void addtodo(View view){
        Todo newtodo = new Todo(mytitle.getText().toString(),mydesc.getText().toString(),0);
        dbHandler.addtodo(newtodo);
        Toast.makeText(AddTodo.this,"To-Do Added Successfully",Toast.LENGTH_LONG).show();
        backhome(view);
    }

    public void backhome(View view){
        Intent I = new Intent(this,MainActivity.class);
        startActivity(I);
    }
}
