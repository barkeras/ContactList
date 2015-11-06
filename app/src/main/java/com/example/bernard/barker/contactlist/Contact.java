package com.example.bernard.barker.contactlist;

import java.io.Serializable;

/**
 * Created by Bernard on 11/5/2015.
 */
public class Contact implements Serializable{

    private String NAME = "";
    private String email = "";
    private String number = "";
    private long cacheID = 0;

    public Contact(String name, String emailA, String numberA){
        NAME = name;
        email = emailA;
        number = numberA;
    }

    public String getName(){return NAME;}
    public String getEmail(){return email;}
    public String getNumber(){return number;}
    public long getCacheID(){return cacheID;}
    public void setCacheID(long cacheID2){cacheID =cacheID2;}
    public String toString(){
        return(NAME);
    }


}
