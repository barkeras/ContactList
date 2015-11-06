package com.example.bernard.barker.contactlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

public class ContactDetails extends AppCompatActivity {


    TextView name;
    TextView number;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        Contact foo = (Contact) getIntent().getSerializableExtra("Contact");


        Toast.makeText(ContactDetails.this, foo.toString(), Toast.LENGTH_LONG).show();

        name = (TextView)findViewById(R.id.contactName);
        email = (TextView)findViewById(R.id.emailAddress);
        number = (TextView)findViewById(R.id.phoneNumber);

        name.setText(foo.getName());
        email.setText(foo.getEmail());
        number.setText(foo.getNumber());


    }

}
