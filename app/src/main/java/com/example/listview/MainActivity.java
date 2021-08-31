package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IOnChildItemClick{


    private ListView lvContact;
    private List<Contact> contactList = new ArrayList<>();
    private ImageView ivUser;
    private TextView tvName;
    private ContactAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        mAdapter = new ContactAdapter(this,contactList);
        mAdapter.registerChildItemClick(this);
        lvContact.setAdapter(mAdapter);
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contactModel = contactList.get(i);
                Toast.makeText(MainActivity.this, contactModel.getName() + ": "+ contactModel.getPhone(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        contactList.add(new Contact("le hoang trinh", "0911549898",R.drawable.ic_avatar2));
        contactList.add(new Contact("le hoang thuong", "09321343221",R.drawable.ic_avatar3));
        contactList.add(new Contact("le hoang chuong", "09321343221",R.drawable.ic_avatar1));
        contactList.add(new Contact("le hoang chuong", "09321343221",R.drawable.ic_avatar1));
        contactList.add(new Contact("le hoang chuong", "09321343221",R.drawable.ic_avatar1));
        contactList.add(new Contact("le hoang chuong", "09321343221",R.drawable.ic_avatar1));
        contactList.add(new Contact("le hoang chuong", "09321343221",R.drawable.ic_avatar1));
        contactList.add(new Contact("le hoang chuong", "09321343221",R.drawable.ic_avatar1));
        contactList.add(new Contact("le hoang chuong", "09321343221",R.drawable.ic_avatar1));
    }
    private void initView(){
        lvContact =(ListView) findViewById(R.id.lvContact);
        ivUser =(ImageView) findViewById(R.id.ivUser);
        tvName =(TextView) findViewById(R.id.tvName);
    }

    @Override
    public void onItemChildClick(int i) {
        Contact contact = contactList.get(i);
        ivUser.setImageResource(contact.getImage());
        tvName.setText(contact.getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.unRegisterChildItemClick();
    }
}