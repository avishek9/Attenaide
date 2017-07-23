package com.example.wacky.attenaide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void openDataEntry(View view){

        Intent intent = new Intent(this, DataEntry.class);
        startActivity(intent);
    }

    public void openAttenEntry(View view){
        Intent intent = new Intent (this, AttenEntry.class);
        startActivity(intent);
    }

    public void openClassEntry(View view){
        Intent intent = new Intent (this, ClassEntry.class);
        startActivity(intent);
    }
}
