package com.ztx.ddls;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Finish extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.finish);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		TextView view = (TextView) findViewById(R.id.textView1);
		view.setText("你的积分："+bundle.getInt("num")+"0");
		Button start = (Button) findViewById(R.id.start);
		Button stop = (Button) findViewById(R.id.stop);
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent inten = new Intent(Finish.this,MainActivity.class);
				startActivity(inten);
				finish();
			}
		});
		stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
