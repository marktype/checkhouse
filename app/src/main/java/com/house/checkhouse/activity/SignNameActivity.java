package com.house.checkhouse.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import com.house.checkhouse.R;
import com.house.checkhouse.util.MethodUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SignNameActivity extends Activity {

	private ImageView img;
	private Bitmap mBitmap;
	private Canvas canvas;
	private Paint paint;
	private String fileUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_name);

		img = (ImageView) findViewById(R.id.img);
		fileUri = MethodUtils.getNowFileName();

		// 绘图
		showImage();

	}

	private void showImage() {
		int height = getWindow().getWindowManager().getDefaultDisplay().getHeight();
		int wehit = getWindow().getWindowManager().getDefaultDisplay().getWidth();
		// 创建一张空白图片
		mBitmap = Bitmap.createBitmap(wehit, height, Bitmap.Config.ARGB_8888);
		// 创建一张画布
		canvas = new Canvas(mBitmap);
		// 画布背景为白色
		canvas.drawColor(Color.WHITE);
		// 创建画笔
		paint = new Paint();
		// 画笔颜色为蓝色
		paint.setColor(Color.BLUE);
		// 宽度5个像素
		paint.setStrokeWidth(15);
		// 先将白色背景画上
		canvas.drawBitmap(mBitmap, new Matrix(), paint);
		img.setImageBitmap(mBitmap);

		img.setOnTouchListener(new OnTouchListener() {
			int startX;
			int startY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					// 获取手按下时的坐标
					startX = (int) event.getX();
					startY = (int) event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					// 获取手移动后的坐标
					int endX = (int) event.getX();
					int endY = (int) event.getY();
					// 在开始和结束坐标间画一条线
					canvas.drawLine(startX, startY, endX, endY, paint);
					// 刷新开始坐标
					startX = (int) event.getX();
					startY = (int) event.getY();
					img.setImageBitmap(mBitmap);
					break;
				}
				return true;
			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		System.gc();
		setmBitmap();
		Intent intent = new Intent();
		intent.putExtra(SecondActivity.IMAGE,fileUri);
		setResult(RESULT_OK, intent);
		return super.onKeyDown(keyCode, event);

	}

	public void setmBitmap(){

		File file = new File(fileUri);
		if(!file.exists()){
			OutputStream stream;
			try {
				file.createNewFile() ;
				stream = new FileOutputStream(file);
				mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			OutputStream stream;
			try {
				stream = new FileOutputStream(file);
				mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
