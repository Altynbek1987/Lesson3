package com.example.lesson3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int ID_HOME = 1;
    private final int ID_MESSAGE = 2;
    private final int ID_ACCOUNT = 3;

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private TaskAdapter taskAdapter;
    private ArrayList<TaskModel> list = new ArrayList<>();
    private String diff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MeowBottomNavigation bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_MESSAGE, R.drawable.ic_message));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.ic_account));

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this, "clicked item : " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                String name;
                switch (item.getId()) {
                    case ID_MESSAGE:
                        name = "Message";
                        break;
                    case ID_HOME:
                        name = "Home";
                        break;
                    case ID_ACCOUNT:
                        name = "Account";
                        break;
                    default:name = "";
                }
            }
        });
       // bottomNavigation.setCount(ID_NOTIFICATION,"4");
        bottomNavigation.show(ID_HOME,true);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, 100);
            }
        });
        recyclerView = findViewById(R.id.recycler);
//        List<String> list = new ArrayList<>();
//        list.add("Значение 1");
//        list.add("Значение 2");
//        list.add("Значение 3");
//        list.add("Значение 4");
//        list.add("Значение 5");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(this, MainActivity.this);
        recyclerView.setAdapter(taskAdapter);
        taskAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this,AddTaskActivity.class);
               // intent.putExtra(AddTaskActivity.KEYDIFFICULY,diff);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            if (data != null) {
                TaskModel model = new TaskModel(data.getStringExtra("title"), data.getStringExtra("description"), data.getStringExtra("date"));
                Toast.makeText(MainActivity.this, "Information saved", Toast.LENGTH_LONG).show();
                taskAdapter.addTask(model);

            }
        }
    }



}