package com.example.bernard.barker.contactlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactHome extends AppCompatActivity {

    private static final int ADDCONTACT_ID = 20;
    ContactsSaver contacts;
    ArrayList<Contact> contactArray;
    ArrayAdapter<String> contactAdapter;
    ArrayList<String> contactNames = new ArrayList<String>();
    ListView contactsList;
    ArrayList<Integer> selectedDeletes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_home);

       // final Button deleteBtn = (Button)findViewById(R.id.delete_btn);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        contactsList = (ListView)findViewById(R.id.listView);
        contacts = new ContactsSaver(getApplicationContext());
        contacts.initialCreate();
        contactArray = contacts.fetchAllContacts();
        populateStringArray();
        contactAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactNames);
        //contactAdapter = new ArrayAdapter<String>(this, R.layout.listview_contacts, contactNames);
        contactsList.setAdapter(contactAdapter);
        contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View clickView, int position, long id) {
                //Toast.makeText(ContactHome.this, "hello", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ContactHome.this, ContactDetails.class);
                Contact tempContact = getItemClicked(position);
                intent.putExtra("Contact", tempContact);
                startActivity(intent);


            }

        });

     contactsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

         @Override
         public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

             if(selectedDeletes.contains(i)){
                 int removeFromListIndex = selectedDeletes.indexOf(i);
                 selectedDeletes.remove(removeFromListIndex);




                 view.setSelected(false);
                 Toast.makeText(getApplicationContext(), String.valueOf(selectedDeletes), Toast.LENGTH_LONG).show();

//             deleteBtn.setOnClickListener(new View.OnClickListener() {
//                 @Override
//                 public void onClick(View view) {
//                     deleteExistingContact(longClickID);
//                 }
//             });

                 int highlightColor = getResources().getColor(R.color.white);

                 view.setBackgroundColor(highlightColor);

             }
             else if(!selectedDeletes.contains(i)){

                 selectedDeletes.add(i);
                 view.setSelected(true);
                 Toast.makeText(getApplicationContext(), String.valueOf(selectedDeletes), Toast.LENGTH_LONG).show();

//             deleteBtn.setOnClickListener(new View.OnClickListener() {
//                 @Override
//                 public void onClick(View view) {
//                     deleteExistingContact(longClickID);
//                 }
//             });

                 int highlightColor = getResources().getColor(R.color.colorAccentHighlight);

                 view.setBackgroundColor(highlightColor);


             }




             //Change bg color

             return true;
         }
     });

    }
    public Contact getItemClicked(int i){
        String contact = contactsList.getItemAtPosition(i).toString();
        Contact tempContact = new Contact("", "", "");
        for(int j = 0; j< contactArray.size(); j++){
            String foo = contactArray.get(j).toString();
            if(foo.equalsIgnoreCase(contact)){
                tempContact = contactArray.get(j);
            }

        }
        return tempContact;

    }
    public void populateStringArray(){

        for(int i =0; i < contactArray.size(); i++) {
            contactNames.add(contactArray.get(i).toString());
        }
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addNewContact(View view) {
        Intent intent = new Intent(ContactHome.this, ContactAdd.class);
        startActivityForResult(intent,ADDCONTACT_ID);

    }


    public void deleteExistingContact(View view) {
        //Toast.makeText(this,"Delete",Toast.LENGTH_LONG).show();

        if(selectedDeletes.isEmpty()){

            Toast.makeText(this, "First long click items to select to delete",Toast.LENGTH_LONG).show();

        }
        if(!selectedDeletes.isEmpty()){

            for(int x = 0; x < selectedDeletes.size(); x++){

                Contact tempContact = getItemClicked(selectedDeletes.get(x));
                contacts.delete(tempContact);

            }

            selectedDeletes.clear();

            contactArray = null;
            contactAdapter.clear();
            contactArray = contacts.fetchAllContacts();
            populateStringArray();
            contactAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactNames);
            contactsList.setAdapter(contactAdapter);


        }


    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if((requestCode == ADDCONTACT_ID) && (resultCode == RESULT_OK)){


            contactArray = null;
            contactAdapter.clear();
            contactArray = contacts.fetchAllContacts();
            populateStringArray();
            contactAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactNames);
            contactsList.setAdapter(contactAdapter);


        }



    }
}


