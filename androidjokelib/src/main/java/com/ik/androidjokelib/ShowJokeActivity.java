package com.ik.androidjokelib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ShowJokeActivity extends AppCompatActivity {

    public static final String KEY_JOKE = "joke";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_joke);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(KEY_JOKE)) {
            String joke = bundle.getString(KEY_JOKE);
            ((TextView)findViewById(R.id.tv_joke)).setText(joke);
        } else {
            Toast.makeText(this, "Invalid intent passed", Toast.LENGTH_LONG).show();
            finish();
        }

    }
}
