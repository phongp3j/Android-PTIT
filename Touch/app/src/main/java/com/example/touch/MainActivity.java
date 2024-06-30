package com.example.touch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private EditText e1,e2,e3,e4;
    private ImageView img;

    private float x0,y0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int a = motionEvent.getAction();
                switch (a){
                    case MotionEvent.ACTION_DOWN:
                        x0 = motionEvent.getX();
                        y0 = motionEvent.getY();
                        e1.setText(x0+"");
                        e2.setText(y0+"");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float x01 = motionEvent.getX();
                        float y01 = motionEvent.getY();
                        e3.setText(x01+"");
                        e4.setText(y01+"");
                        img.setX(img.getX()+(x01-x0));
                        img.setY(img.getY()+(y01-y0));
                        break;
                }
                return true;
            }
        });
    }

    private void initView() {
        e1 = findViewById(R.id.x);
        e2 = findViewById(R.id.y);
        e3 = findViewById(R.id.x1);
        e4 = findViewById(R.id.y1);
        img = findViewById(R.id.img);
    }
}