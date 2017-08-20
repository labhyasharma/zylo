package com.charpixel.zylo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.testButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZyloFragment.showPopup(MainActivity.this,"This is for Testing",ZyloFragment.LENGTH_NORMAL,50);
            }
        });

    }
}
