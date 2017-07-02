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

        dbHandler = new DBHandler(this,null,null,1);

        final ArrayList<String> a = dbHandler.dbToarr();
        final ArrayList<String> b = dbHandler.dbToddesc();
        final ArrayList<Integer> c = dbHandler.dbToid();
        ListAdapter myadaptor = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,a);
        ListView mylist = (ListView) findViewById(R.id.mylist);
        mylist.setAdapter(myadaptor);

        mylist.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //int pos = parent.getPositionForView(view);
                            String title = String.valueOf(parent.getItemAtPosition(position));
                            String desc = b.get(position);
                            Integer idi = c.get(position);
                            Intent I = new Intent(MainActivity.this,DisplayTodo.class);
                            I.putExtra("prtitle",title);
                            I.putExtra("prdesc",desc);
                            I.putExtra("prid",idi);
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
