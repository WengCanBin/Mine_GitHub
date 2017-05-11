package com.ztx.ddls;

import java.util.HashMap;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private int i = 0;
	private Handler handler;
	private ImageView mouse;
	private TextView textView ;
	private ImageView integral;
	private Chronometer ch ;
	private SoundPool pool;
	private static MediaPlayer mp = null;
	private HashMap<Integer, Integer> soundmap = new HashMap<Integer, Integer>();
	public int[][] position = new int[][] { { 150, 100 }, { 250, 100 },
			{ 350, 100 }, { 450, 100 }, { 550, 100 }, { 650, 100 },
			{ 50, 100 }, { 150, 220 }, { 250, 220 }, { 350, 510 },
			{ 450, 220 }, { 550, 220 }, { 650, 220 }, { 50, 220 } };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//音乐
		if(mp != null){
			mp.release();
		}
		mp = MediaPlayer.create(MainActivity.this, R.raw.dalaoshu);
		mp.start();
		
		//计时器
		ch = (Chronometer) findViewById(R.id.chronometer1);
		ch.setBase(SystemClock.elapsedRealtime());
		ch.setFormat("已用时间：%s");
		ch.start();
		ch.setOnChronometerTickListener(new OnChronometerTickListener() {
			
			@Override
			public void onChronometerTick(Chronometer chronometer) {
				if(SystemClock.elapsedRealtime() - ch.getBase() >= 30000){
					Intent intent = new Intent(MainActivity.this,Finish.class);
					Bundle bundle = new Bundle();
					bundle.putInt("num", i);
					intent.putExtras(bundle);
					startActivity(intent);
					finish();
				}
			}
		});
		
		textView = (TextView) findViewById(R.id.textView1);
		mouse = (ImageView) findViewById(R.id.imageView1);
		integral=(ImageView) findViewById(R.id.imageView2);
		integral.setVisibility(View.INVISIBLE);
		pool = new SoundPool(2, AudioManager.STREAM_SYSTEM, 0);
		soundmap.put(1, pool.load(MainActivity.this, R.raw.dalaoshu,1));
		soundmap.put(2, pool.load(MainActivity.this, R.raw.enter,1));
		pool.play(soundmap.get(1), 1, 1, 0, -1, 1);
		final Animation translate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_translate);
		mouse.setOnTouchListener(new OnTouchListener() {

			@Override
			public  boolean onTouch(View v, MotionEvent event) {
				v.setVisibility(View.INVISIBLE);// 设置地老鼠不显示
				i++;
				pool.play(soundmap.get(2), 1, 1, 0, 0, 1);
				integral.startAnimation(translate);
				integral.setVisibility(View.INVISIBLE);
				textView.setText("积分："+i+"0");
				return false;
			}
		});
		handler = new Handler(){
			
			@Override
			public void handleMessage(Message msg) {
				int index = 0;
				if(msg.what == 0x111){
					index = msg.arg1;
					mouse.setX(position[index][0]);
					mouse.setY(position[index][1]);
					mouse.setVisibility(View.VISIBLE);
					
				}
				super.handleMessage(msg);
			}
		};
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int index = 0;
				while(!Thread.currentThread().isInterrupted()){
					index = new Random().nextInt(position.length);
					Message m = handler.obtainMessage();
					m.what = 0x111;
					m.arg1 = index;
					handler.sendMessage(m);				
					try {
						Thread.sleep(new Random().nextInt(240)+200);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		t.start();
	}
	@Override
	protected void onDestroy() {
		if(mp != null){
			mp.stop();
			mp.release();
			mp=null;
		}
		super.onDestroy();
	}
}
