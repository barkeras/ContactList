package com.example.bernard.barker.contactlist;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

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

        Contact newContact = new Contact(name,email,phone);
        ContactsSaver cs = new ContactsSaver(getApplicationContext());
        cs.insert(newContact);

        setResult(Activity.RESULT_OK);
        finish();

    }
}
