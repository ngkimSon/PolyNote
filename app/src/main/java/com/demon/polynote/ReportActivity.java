package com.demon.polynote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class ReportActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private CheckBox ckb1;
    private CheckBox ckb2;
    private CheckBox ckb3;
    private EditText edtReport;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ckb1 = (CheckBox) findViewById(R.id.ckb1);
        ckb2 = (CheckBox) findViewById(R.id.ckb2);
        ckb3 = (CheckBox) findViewById(R.id.ckb3);
        edtReport = (EditText) findViewById(R.id.edtReport);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void report(View view) {
    }
}
