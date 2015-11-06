package com.example.bernard.barker.contactlist;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Andrew on 11/6/2015.
 */
public class ContactAdd extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_contact_newcontact);
    }

    public void saveContactInfo(View view) {

        EditText nameET = (EditText)findViewById(R.id.contactName);
        EditText emailET = (EditText)findViewById(R.id.emailAddress);
        EditText phoneET = (EditText)findViewById(R.id.phoneNumber);

        String name = String.valueOf(nameET.getText());
        String email = String.valueOf(emailET.getText());
        String phone = String.valueOf(phoneET.getText());

        if((email.contains("@")) && (phone.length() == 10) && (!name.isEmpty())){

            Contact newContact = new Contact(name,email,phone);
            ContactsSaver cs = new ContactsSaver(getApplicationContext());
            cs.insert(newContact);

            setResult(Activity.RESULT_OK);
            finish();

        }
        else if((!email.contains("@")) && (phone.length()==10) && (!name.isEmpty())){
            Toast.makeText(this,"Email field must contain @ sign",Toast.LENGTH_LONG).show();

        }
        else if((email.contains("@")) && (phone.length()!=10) && (!name.isEmpty())){
            Toast.makeText(this,"Phone number must be 10 digits",Toast.LENGTH_LONG).show();

        }
        else if((!email.contains("@")) && (phone.length()!=10) && (!name.isEmpty())){
            Toast.makeText(this,"Please input valid email and phone number",Toast.LENGTH_LONG).show();
        }
        else if((email.contains("@")) && (phone.length()!=10) && (name.isEmpty())){
            Toast.makeText(this,"Please input contact name and valid phone number",Toast.LENGTH_LONG).show();
        }
        else if((!email.contains("@")) && (phone.length()==10) && (name.isEmpty())){
            Toast.makeText(this,"Please input contact name and valid email address",Toast.LENGTH_LONG).show();
        }
        else if((!email.contains("@")) && (phone.length()!=10) && (name.isEmpty())){
            Toast.makeText(this,"Please fill out all the fields",Toast.LENGTH_LONG).show();
        }
        else if((email.contains("@")) && (phone.length()==10) && (name.isEmpty())){
            Toast.makeText(this,"Please input contact name",Toast.LENGTH_LONG).show();
        }



    }
}
