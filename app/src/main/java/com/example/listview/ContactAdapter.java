package com.example.listview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    private List<Contact> contactList;
    private Activity activity;

    public ContactAdapter(List<Contact> contactList, Activity activity) {
        this.contactList = contactList;
        this.activity = activity;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater inflater = activity.getLayoutInflater();
            view = inflater.inflate(R.layout.item_contact, viewGroup,false);
            ViewHolder holder = new ViewHolder();
            holder.tvName = (TextView) view.findViewById(R.id.tvName);
            holder.tvPhone = (TextView) view.findViewById(R.id.tvPhone);
            holder.ivAvatar = (ImageView) view.findViewById(R.id.ivAvatar);
            view.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        Contact model = contactList.get(i);
        holder.tvName.setText(model.getName());
        holder.tvPhone.setText(model.getPhone());
        holder.ivAvatar.setImageResource(model.getImage());
        return view;
    }

    static class ViewHolder {
        TextView tvName;
        TextView tvPhone;
        ImageView ivAvatar;
    }
}
