package com.example.scaledetector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.opengl.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView img;
    private  Matrix matrix = new Matrix();
    private float scale = 1f;
    private ScaleGestureDetector detector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.img);
        if(detector==null){
            detector = new ScaleGestureDetector(this,new ScaleListener());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(@NonNull ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            scale=Math.max(0.1f,Math.min(scale,10.0f));
            matrix.setScale(scale,scale);
            img.setImageMatrix(matrix);
            return true;
        }
    }
}