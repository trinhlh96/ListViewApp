package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView lvContact;
    private List<Contact> contactList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        lvContact = (ListView) findViewById(R.id.lvContact);
        ContactAdapter adapter = new ContactAdapter(contactList, this);
        lvContact.setAdapter(adapter);
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contactModel = contactList.get(i);
                Toast.makeText(MainActivity.this, contactModel.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        contactList.add(new Contact("le hoang trinh", "09321321",R.drawable.ic_avatar2));
        contactList.add(new Contact("le hoang thuong", "09321343221",R.drawable.ic_avatar3));
        contactList.add(new Contact("le hoang chuong", "09321343221",R.drawable.ic_avatar1));
    }
}