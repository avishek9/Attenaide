package com.example.wacky.attenaide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AttenEntry extends AppCompatActivity {

    private TextView mEnrollmentTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atten_entry);
        mEnrollmentTextView = (TextView) findViewById(R.id.enrollment_display);
    }
}
