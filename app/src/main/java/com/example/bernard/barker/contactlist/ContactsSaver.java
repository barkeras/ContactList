package com.example.bernard.barker.contactlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Bernard on 11/5/2015.
 */
public class ContactsSaver extends SQLiteOpenHelper{
    public static final String CONTACTS = "CONTACTS";
    public static final String NAME = "NAME";
    public static final String CACHE_ID = "CACHE_ID";
    public static final String NUMBER = "NUMBER";
    public static final String EMAIL = "EMAIL";


    int numberOfColumns = 0;

    public static final int NAME_INDEX = 1;
    public static final int EMAIL_INDEX = 2;
    public static final int NUMBER_INDEX = 3;

    public ContactsSaver(Context ctx) {
        super(ctx, "contacts2.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        numberOfColumns = 4;
        String createTimes = "CREATE TABLE " + CONTACTS + " ( " + CACHE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, " + EMAIL + " TEXT, " + NUMBER + " TEXT " + ");";
        db.execSQL(createTimes);

    }

    /**
     * Inserts a Contact into the database
     * @param  Contact entry
     * @return  long cacheID
     *
     */
    public long insert(Contact contact){
        ContentValues cv = new ContentValues();
        cv.put(NAME, contact.getName());
        cv.put(EMAIL, contact.getEmail());
        cv.put(NUMBER, contact.getNumber());
        long cacheID = getWritableDatabase().insert(CONTACTS, NUMBER, cv);
        contact.setCacheID(cacheID);
        return cacheID;
    }



    public void delete(Contact contact){

        Contact tempContact = contact;


        String whereClause = NAME + " = '" + tempContact.getName() + "'";

        getWritableDatabase().delete(CONTACTS, whereClause,null);

    }




    /*
    Initializes the database
     */
    public void initialCreate(){


        String sql = "SELECT *  FROM  CONTACTS";

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if(!(cursor.getCount() >0)) {

            Contact joe = new Contact("Joe Smith", "JoeSmith@gmail.com", "513-280-1191");
            Contact jane = new Contact("Jane Smith", "JaneSmith@gmail.com", "513-288-1991");
            Contact jack = new Contact("Jack Collinsworth", "JackCollinsworth@gmail.com", "513-333-1191");
            Contact sally = new Contact("Sally Cardish", "SallyCardish@gmail.com", "513-440-1191");
            Contact tim = new Contact("Tim Finnigan", "TimFinigan@gmail.com", "513-567-1191");
            Contact hazem = new Contact("Hazeem Said", "HazemSaid@gmail.com", "513-287-1191");
            Contact kyle = new Contact("Kyle Smith", "KyleSmith@gmail.com", "513-288-1991");
            Contact ryan = new Contact("Ryan Collinsworth", "RyanCollinsworth@gmail.com", "513-333-1191");
            Contact terrence= new Contact("Terrence Cardish", "TerrenceCardish@gmail.com", "513-440-1191");
            Contact flynn = new Contact("Flynn Finnigan", "FlynnFinnigan@gmail.com", "513-567-1191");
            Contact sarah = new Contact("Sarah Chalmers", "SarahChalmers@gmail.com", "513-280-1191");
            Contact jean = new Contact("Jean Jean", "JeanJean@gmail.com", "513-288-1991");
            Contact sam = new Contact("Sam Collinsworth", "SamCollinsworth@gmail.com", "513-333-1191");
            Contact kourtney = new Contact("Kourtney Cordaline", "KourtneyCordaline@gmail.com", "513-440-1191");
            Contact ali = new Contact("Ali Finnigan", "AliFinnigan@gmail.com", "513-567-1191");
            insert(joe);
            insert(jane);
            insert(jack);
            insert(sally);
            insert(tim);
            insert(hazem);
            insert(kyle);
            insert(ryan);
            insert(terrence);
            insert(flynn);
            insert(sarah);
            insert(jean);
            insert(sam);
            insert(kourtney);
            insert(ali);
        }
        cursor.close();


    }



    /**
     * Fetches all of the times from the database and returns them
     * as an arrayList of data type Individual times uses a while to loop through
     * each entry and then grabs from each column
     * @return  ArrayList<IndividualTime>
     *
     */
    public ArrayList<Contact> fetchAllContacts(String order){
        ArrayList<Contact> results = new ArrayList<Contact>();
        String sql = "SELECT *  FROM  CONTACTS ORDER BY " + order;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                String name = cursor.getString(NAME_INDEX);
                String email = cursor.getString(EMAIL_INDEX);
                String number = cursor.getString(NUMBER_INDEX);


                Contact newContact= new Contact(name, email, number );
                results.add(newContact);
                cursor.moveToNext();

            }
        }
        cursor.close();
        return results;


    }





    /*
    public ArrayList<ArrayList<Unit>> getNumberOfCategories(){
        ArrayList<Unit> results = new ArrayList<Unit>();
        String sql = "SELECT *  FROM  UNITS";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){

                String category = cursor.getString(CATEGORY_INDEX);





                cursor.moveToNext();

            }
        }
        cursor.close();
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
