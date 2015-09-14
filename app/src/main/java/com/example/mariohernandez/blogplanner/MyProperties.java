package com.example.mariohernandez.blogplanner;

/**
 * Created by mariohernandez on 14/09/15.
 */
public class MyProperties {

    private static MyProperties mInstance= null;

    public String nomBlog;

    public String objetivo;

    protected MyProperties(){}

    public static synchronized MyProperties getInstance(){
        if(null == mInstance){
            mInstance = new MyProperties();
        }
        return mInstance;
    }

    public String getNomBlog(){
        return nomBlog;
    }

    public void setNomBlog(String n){
        nomBlog = n;
    }

    public String getObjetivo(){
        return objetivo;
    }

    public void setObjetivo(String n){
        objetivo = n;
    }

}
