package com.myapplicationdev.android.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lvTask;
    EditText etTask;
    EditText etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResult);
        lvTask = findViewById(R.id.lvTask);
        etTask=findViewById(R.id.etTask);
        etDate=findViewById(R.id.etDate);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newetTask=etTask.getText().toString();
                String newetDate = etDate.getText().toString();
                DBHelper db = new DBHelper(MainActivity.this);
                db.insertTask(newetTask , newetDate);
                db.close();
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                ArrayList<String> data = db.getTaskContent();
                db.close();
                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i + ". " + data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

                DBHelper db2 = new DBHelper(MainActivity.this);
                ArrayList<Task> al = db2.getTasks();
                db2.close();
                ArrayAdapter aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, al);
                lvTask.setAdapter(aa);
            }
        });
    }
}