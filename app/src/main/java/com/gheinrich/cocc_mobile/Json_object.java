package com.gheinrich.cocc_mobile;/**
 * Created by gheinrich on 8/4/2014.
 */
public class Json_object  {

    private String propeties, desc, color, title;

    public Json_object(){
        this.propeties = "empty";
        this.desc = "empty";
        this.color = "empty";
        this.title = "empty";
    }
    public void setPropeties(String p){

        this.propeties = p;
    }

    public void setDesc(String d){
        this.color = d;
    }

    public void setColor(String c){
        this.color = c;
    }
    public void setTitle(String t){
        this.title = t;
    }
    public String getProperties(){
        return propeties;
    }
    public String getDesc(){
        return desc;
    }
    public String getColor(){
        return color;
    }
    public String getTitle(){
        return title;
    }







}
