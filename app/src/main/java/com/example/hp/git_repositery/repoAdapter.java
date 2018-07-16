package com.example.hp.git_repositery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class repoAdapter extends ArrayAdapter {
   private   ArrayList<repo> repolist;
    private LayoutInflater inflater;
    public repoAdapter(@NonNull Context context,ArrayList<repo> list ) {
        super(context, 0,list);
        repolist = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
       return repolist.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return repolist.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View output = convertView;
        output = inflater.inflate(R.layout.activity_repositery,parent,false);
        if(output == null){
            TextView id = output.findViewById(R.id.textView2);
            TextView node_id = output.findViewById(R.id.textView3);
            TextView name = output.findViewById(R.id.textView4);
            TextView fullname = output.findViewById(R.id.textView5);
            repoviewHolder holder = new repoviewHolder();
            holder.id = id;
            holder.node_id = node_id;
            holder.name = name;
            holder.fullname = fullname;
            output.setTag(holder);
        }
       repoviewHolder holder = (repoviewHolder) output.getTag();
        repo repo = repolist.get(position);
        holder.id.setText(""+repo.getId());
        holder.node_id.setText("" +repo.getNode_id());
        holder.name.setText(repo.getName());
        holder.fullname.setText(repo.getFullname());
        return output;
    }
}
