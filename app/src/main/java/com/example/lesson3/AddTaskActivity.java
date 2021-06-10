package com.example.lesson3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.text.SimpleDateFormat;
import java.util.Date;

public class  AddTaskActivity extends AppCompatActivity {


    public static final String KEYDIFFICULY = "title";
    EditText etTitle, etDescription;
    Button btnAdd;
    TaskModel task;
    private String tit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        etTitle = findViewById(R.id.ed_title);
        etDescription = findViewById(R.id.et_description);
        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String description = etDescription.getText().toString().trim();
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                String date = sdf.format(new Date());
                if (title.isEmpty()) {
                    etTitle.setError("Введите title");
                    return;
                }
                if (description.isEmpty()) {
                    etDescription.setError("Введите Desc");
                    return;
                }
                    Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
                    intent.putExtra("title", title);
                    intent.putExtra("description", description);
                    intent.putExtra("date",date);
                    setResult(RESULT_OK, intent);
                    finish();
            }
        });

        Intent intent = getIntent();
        tit = intent.getStringExtra(KEYDIFFICULY);

        etTitle.setText(getIntent().getStringExtra("task"));

    }
}