package com.example.root.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   // private final Button mybutton = (Button) findViewById(R.id.button);

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(this,null,null,2);

        final ArrayList<Todo> a = dbHandler.dbToarr();
       // ListAdapter myadaptor = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,a.);
        ListAdapter myadaptor = new custom_list(this,a);
        ListView mylist = (ListView) findViewById(R.id.mylist);
        mylist.setAdapter(myadaptor);
        mylist.setFocusable(false);
        mylist.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //int pos = parent.getPositionForView(view);
                            String title = a.get(position).get_title();
                            String desc = a.get(position).get_description();
                            Integer idi = a.get(position).get_id();
                            Integer md = a.get(position).get_done();
                            Intent I = new Intent(MainActivity.this,DisplayTodo.class);
                            I.putExtra("prtitle",title);
                            I.putExtra("prdesc",desc);
                            I.putExtra("prid",idi);
                            I.putExtra("prdone",md);
                            startActivity(I);
                    }
                }
        );

    }

    public void Onchange(View view)
    {
        Intent I = new Intent(this,AddTodo.class);
        startActivity(I);
    }



}
