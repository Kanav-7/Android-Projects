package com.example.root.todo;

/**
 * Created by root on 24/6/17.
 */

public class Todo {
    private int _id;
    private String _title;
    private String _description;
    private int _done;

    public Todo(String title, String description,int done) {
        this._title = title;
        this._description = description;
        this._done = done;
    }
    public Todo(){

    }

    public String get_title() {
        return _title;
    }

    public int get_done() {return _done; }

    public String get_description() {
        return _description;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id){
        this._id = _id;
    }

    public void set_done(int _done){
        this._done = _done;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public void set_description(String _description) {
        this._description = _description;
    }
}
