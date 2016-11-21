package com.house.checkhouse.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;

import com.house.checkhouse.R;
import com.house.checkhouse.model.message.ImageInfo;
import com.house.checkhouse.util.Logs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SecondActivity extends Activity {
	public static final String IMAGE = "images";
	private ImageView img;
	private Bitmap mBitmap,nowBitmap;
	private Canvas canvas;
	private Paint paint;
	private ImageInfo imageInfo;
	private String fileUri;
	// 重置按钮
	private Button reset_btn,save_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		imageInfo = getIntent().getParcelableExtra("image");

		fileUri = getNowFileName();

		mBitmap = imageInfo.getBitmap();
		nowBitmap = mBitmap;

		img = (ImageView) findViewById(R.id.img);


		reset_btn = (Button) findViewById(R.id.reset_btn);
		save_btn = (Button) findViewById(R.id.save_btn);

		reset_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				img.setImageBitmap(null);
				showImage();
			}
		});

		save_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
//				setmBitmap();
			}
		});
		// 绘图
		showImage();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		System.gc();
		setmBitmap();
		Intent intent = new Intent();
		intent.putExtra(IMAGE,fileUri);
		setResult(RESULT_OK, intent);
		return super.onKeyDown(keyCode, event);

	}

	private void showImage() {
		// 创建一张空白图片
		nowBitmap = mBitmap.copy(Bitmap.Config.ARGB_8888,true);
		nowBitmap = big(nowBitmap);
		// 创建一张画布
		canvas = new Canvas(nowBitmap);
		// 画布背景为白色
//		canvas.drawColor(Color.WHITE);
		// 创建画笔
		paint = new Paint();
		// 画笔颜色为蓝色
		paint.setColor(Color.BLUE);
		// 宽度1个像素
		paint.setStrokeWidth(3);
		// 先将白色背景画上
		canvas.drawBitmap(nowBitmap, new Matrix(), paint);
		img.setImageBitmap(nowBitmap);

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
					img.setImageBitmap(nowBitmap);
					break;
				}
				return true;
			}
		});

	}

	/**
	 * 获取文件路径和名字
	 * @return
     */
	public String getNowFileName(){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");//格式大小写有区别
		String sysDatetime = fmt.format(Calendar.getInstance().getTime());//2016年02月25日  13:23:40
		String fileName = Environment.getExternalStorageDirectory()+File.separator+"com.house"+File.separator+"check"+sysDatetime+".png";
		Logs.d("fileName-----"+fileName);
		File file = new File(Environment.getExternalStorageDirectory()+File.separator+"com.house");
		if (!file.exists()){
			file.mkdir();
		}
		return fileName;
	}

	 public void setmBitmap(){

		 File file = new File(fileUri);
		 if(!file.exists()){
			 OutputStream stream;
			 try {
				 file.createNewFile() ;
				 stream = new FileOutputStream(file);
				 nowBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
				 stream.close();
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
		 }else {
			 OutputStream stream;
			 try {
				 stream = new FileOutputStream(file);
				 nowBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
				 stream.close();
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
		 }
	 }
	/**
	 * 放大bitmap
	 * @param bitmap
	 * @return
     */
	private Bitmap big(Bitmap bitmap) {
		int height = bitmap.getHeight();
		int width = bitmap.getWidth();
		int heightWon = getWindow().getWindowManager().getDefaultDisplay().getHeight();
		int widthWon = getWindow().getWindowManager().getDefaultDisplay().getWidth();
		float sizeW = (float) widthWon/width;
		float sizeH = (float) heightWon/height;
		Matrix matrix = new Matrix();
		float size = sizeH<sizeW?sizeH:sizeW;
		matrix.postScale(size,size); //长和宽放大缩小的比例
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
		return resizeBmp;
	}

}
