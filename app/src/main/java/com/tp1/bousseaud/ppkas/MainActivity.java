package com.tp1.bousseaud.ppkas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tp1.bousseaud.ppkas.dao.NewsDAO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NewsDAO newsDAO = NewsDAO.getInstance(getApplicationContext());
    }
}
