package com.example.root.todo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


public class custom_list extends ArrayAdapter<Todo> {

    DBHandler dbHandler = new DBHandler(this.getContext(),null,null,2);

    public custom_list(Context context,ArrayList<Todo> data) {
        super(context, R.layout.list_row ,data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater myCustomInflater = LayoutInflater.from(getContext());
        final View customView = myCustomInflater.inflate(R.layout.list_row, parent, false);

        final Todo mydata = getItem(position);
        TextView itemText = (TextView) customView.findViewById(R.id.item_text);

        final CheckBox check = (CheckBox) customView.findViewById(R.id.checkBox);

        if(mydata.get_done() == 0)
        {
            check.setText("Mark done");
            customView.setBackgroundColor(Color.rgb(62,65,79));
        }
        else
        {
            check.setChecked(true);
            check.setText("Mark not done");
            customView.setBackgroundColor(Color.rgb(73,188,174));
        }

        check.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mydata.get_done() == 1) {
                            mydata.set_done(0);
                            dbHandler.updatetodo(mydata);
                            check.setText("Mark done");
                            customView.setBackgroundColor(Color.rgb(62,65,79));

                        }
                        else
                        {
                            mydata.set_done(1);
                            dbHandler.updatetodo(mydata);
                            check.setText("Mark not done");
                            customView.setBackgroundColor(Color.rgb(73,188,174));
                        }
                    }
                }
        );

        itemText.setText(mydata.get_title());

        return customView;
    }
}
