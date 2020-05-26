package com.example.taskapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Collection;


@Entity
public class Task implements Serializable {
  @PrimaryKey(autoGenerate = true)
  private  long id;
    private String title;
    private String desc;
    private  String How_are_you;



    public Task() {
    }


    public Task(String title, String desc, String how_are_you) {
        this.title = title;
        this.desc = desc;
       this.How_are_you = how_are_you;
    }

    public Task(String title, String desc) {
    this.title=title;
    this.desc=desc;

}

    public String getHow_are_you() {

        return How_are_you;
    }

    public void setHow_are_you(String how_are_you) {
        How_are_you = how_are_you;
    }





    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public String getDesc() {
        return desc;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }



}
