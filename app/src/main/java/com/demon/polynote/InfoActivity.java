package com.demon.polynote;

import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.demon.polynote.model.list;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView lvContact;
    private ArrayList<list> arrContact = new ArrayList<>();
    private ListAdapter customAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        lvContact = (ListView) findViewById(R.id.listview);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        list contact1 = new list("Tác giả", "Nguyễn Kim Sơn");
        list contact2 = new list("Email", "sonnkph06556@fpt.edu.vn");
        list contact3 = new list("Mã nguồn", "https://github.com/ngkimSon/PolyNote");
        list contact4 = new list("Liên hệ", "0382 415 818");


        arrContact.add(contact1);
        arrContact.add(contact2);
        arrContact.add(contact3);
        arrContact.add(contact4);


        customAdaper = new com.demon.polynote.adapter.ListAdapter(this, R.layout.list_about, arrContact);
        lvContact.setAdapter(customAdaper);
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(InfoActivity.this, "Cảm ơn bạn đã sử dụng ứng dụng!", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto", "sonnkph06556@fpt.edu.vn", null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "How can I help you?");
                        startActivity(Intent.createChooser(emailIntent, null));
                        break;
                    case 2:
                        String url = "https://github.com/ngkimSon/PolyNote";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                        break;
                    case 3:
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:0382415818"));
                        startActivity(intent);
                        break;
                }
            }
        });

    }
}
