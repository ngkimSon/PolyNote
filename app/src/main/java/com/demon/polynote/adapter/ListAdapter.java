package com.demon.polynote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.demon.polynote.R;
import com.demon.polynote.model.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Welcome on 8/27/2016.
 */
public class ListAdapter extends ArrayAdapter<list> {

    private Context context;
    private int resource;
    private List<list> arrContact;

    public ListAdapter(Context context, int resource, ArrayList<list> arrContact) {
        super(context, resource, arrContact);
        this.context = context;
        this.resource = resource;
        this.arrContact = arrContact;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_about, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.title);
            viewHolder.tvSub = (TextView) convertView.findViewById(R.id.subtitle);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        list contact = arrContact.get(position);

        viewHolder.tvName.setText(contact.getName());
        viewHolder.tvSub.setText(contact.getSub());
        return convertView;
    }

    public class ViewHolder {
        TextView tvName, tvSub;

    }
}