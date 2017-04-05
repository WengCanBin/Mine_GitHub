package com.example.administrator.ddms_try;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class DDMSActivity extends AppCompatActivity {
    private static final String ACTIVITY_TAG="DDMSActivity";
    private Button bt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddms);

        bt=(Button)findViewById(R.id.myButton);
        bt.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {
                Log.v(DDMSActivity.ACTIVITY_TAG, "This is Verbose.");
                Log.d(DDMSActivity.ACTIVITY_TAG, "This is Debug.");
                Log.i(DDMSActivity.ACTIVITY_TAG, "This is Information");
                Log.w(DDMSActivity.ACTIVITY_TAG, "This is Warnning.");
                Log.e(DDMSActivity.ACTIVITY_TAG, "This is Error.");
            }
        }); }
}


