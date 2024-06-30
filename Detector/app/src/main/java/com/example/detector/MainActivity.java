package com.example.detector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener, View.OnTouchListener {
    private TextView e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12;

    private ImageView img;

    private GestureDetector detector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        img.setOnTouchListener(this);
        detector = new GestureDetector(this, this);
        detector.setOnDoubleTapListener(this);
    }
    public void initView(){
        e1 = findViewById(R.id.tv1);
        e2 = findViewById(R.id.tv2);
        e3 = findViewById(R.id.tv3);
        e4 = findViewById(R.id.tv4);
        e5 = findViewById(R.id.tv5);
        e6 = findViewById(R.id.tv6);
        e7 = findViewById(R.id.tv7);
        e8 = findViewById(R.id.tv8);
        e9 = findViewById(R.id.tv9);
        e10 = findViewById(R.id.tv10);
        e11 = findViewById(R.id.tv11);
        e12 = findViewById(R.id.tv12);
        img = findViewById(R.id.img);
    }

    @Override
    public boolean onSingleTapConfirmed(@NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(@NonNull MotionEvent motionEvent) {
        e11.setText("On double tap");
        e12.setText("X "+ motionEvent.getX()+" Y "+ motionEvent.getY());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(@NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDown(@NonNull MotionEvent motionEvent) {
        e1.setText("On down");
        e2.setText("X "+ motionEvent.getX()+" Y "+ motionEvent.getY());
        return true;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent motionEvent) {
        e3.setText("On show press");
        e4.setText("X "+ motionEvent.getX()+" Y "+ motionEvent.getY());
    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
        e5.setText("On single tap up");
        e6.setText("X "+ motionEvent.getX()+" Y "+ motionEvent.getY());
        return true;
    }

    @Override
    public boolean onScroll(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
        e7.setText("On scroll");
        e8.setText("X "+ motionEvent.getX()+" Y "+ motionEvent.getY());
        return true;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
        e9.setText("On fling");
        e10.setText("X "+ motionEvent.getX()+" Y "+ motionEvent.getY());
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        detector.onTouchEvent(motionEvent);
        return true;
    }
}