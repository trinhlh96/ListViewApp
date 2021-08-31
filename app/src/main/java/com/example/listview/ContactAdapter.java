package com.example.listview;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    private static final int PERMISSION_REQUEST_CODE = 1;
    private List<Contact> contactList;
    private Activity activity;
    private Context mContext;
    private IOnChildItemClick itemClick;

    public ContactAdapter(Context mContext, List<Contact> contactList) {
        this.mContext = mContext;
        this.contactList = contactList;
    }
    public void registerChildItemClick(IOnChildItemClick itemClick){
        this.itemClick = itemClick;
    }
    public void unRegisterChildItemClick(){
        this.itemClick = null;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        View rowView = convertView;
        if (rowView == null){
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            rowView = inflater.inflate(R.layout.item_contact, null);
            ViewHolder holder = new ViewHolder();
            holder.tvName = (TextView) rowView.findViewById(R.id.tvName);
            holder.tvPhone = (TextView) rowView.findViewById(R.id.tvPhone);
            holder.ivAvatar = (ImageView) rowView.findViewById(R.id.ivAvatar);
            holder.btEdit = (Button) rowView.findViewById(R.id.btEdit);
            holder.btCall = (Button) rowView.findViewById(R.id.btCall);
            rowView.setTag(holder);
        }
        //fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.tvName.setText(contactList.get(i).getName());
        holder.tvPhone.setText(contactList.get(i).getPhone());
        holder.ivAvatar.setImageResource(contactList.get(i).getImage());
        holder.btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("den day roi");
                onCall(i);
            }
        });
        return rowView;
    }

    private void onCall(int pro) {
        Contact contact = contactList.get(pro);
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + contact.getPhone()));
        if (ActivityCompat.checkSelfPermission(mContext,  Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            requestForCallPermission(); // Cấp quyền thực hiện cuộc gọi
        }
        mContext.startActivity(intent);
        System.out.println("buoc 4");

    }

    static class ViewHolder {
        TextView tvName;
        TextView tvPhone;
        ImageView ivAvatar;
        Button btCall;
        Button btEdit;
    }
    // Cấp quyền thực hiện cuộc gọi
    public void requestForCallPermission()
    {

        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) mContext,Manifest.permission.CALL_PHONE))
        {
        }
        else {

            ActivityCompat.requestPermissions((Activity) mContext,new String[]{Manifest.permission.CALL_PHONE},PERMISSION_REQUEST_CODE);
        }
    }

}
