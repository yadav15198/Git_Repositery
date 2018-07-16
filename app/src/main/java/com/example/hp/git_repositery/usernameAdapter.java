package com.example.hp.git_repositery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class usernameAdapter extends ArrayAdapter{
    ArrayList<UserDetail> userList;
    LayoutInflater inflater;
    public usernameAdapter(@NonNull Context context, ArrayList<UserDetail> arrayList) {
        super(context, 0,arrayList);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        userList = arrayList;
    }


    @Override
    public int getCount(){
        return userList.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View output= convertView;
       if(output == null){
           output=inflater.inflate(R.layout.row_layout,parent,false);
           TextView login = output.findViewById(R.id.UsertextView);
           ImageView avtar_url = output.findViewById(R.id.userImage);
           UsernameViewHolder holder = new UsernameViewHolder();
           holder.avtar_url =avtar_url;
           holder.login = login;
           output.setTag(holder);
       }
       UsernameViewHolder holder = (UsernameViewHolder) output.getTag();
       UserDetail detail = userList.get(position);
       holder.login.setText(detail.getLogin());
        Picasso.get().load(detail.getAvatar_url()).into(holder.avtar_url);
       return output;

    }
}
