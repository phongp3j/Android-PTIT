package com.example.appbansach;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.appbansach.dal.SQLiteHelper;
import com.example.appbansach.model.User;

import java.util.Random;

public class ForgotActivity extends AppCompatActivity {
    private EditText email;
    private Button btnForgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        initview();
        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailForgot = email.getText().toString();
                if(emailForgot.isEmpty()){
                    Toast.makeText(ForgotActivity.this, "Vui long dien thong tin!", Toast.LENGTH_SHORT).show();
                }
                else{
                    SQLiteHelper db = new SQLiteHelper(getApplicationContext());
                    if(db.getUserByEmail(emailForgot) == null){
                        Toast.makeText(ForgotActivity.this, "Email khong dung", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        String subject = "Forgot password!";
                        String pass = generateRandomString();
                        String body = "Password mới: "+ pass;
                        User user = db.getUserByEmail(emailForgot);
                        user.setPass(pass);
                        db.updateUser(user);
                        Toast.makeText(ForgotActivity.this, "Reset pass success!", Toast.LENGTH_SHORT).show();
                        SendMailTask sendMailTask = new SendMailTask();
                        sendMailTask.execute(emailForgot, subject, body);
                        finish();
                    }
                }
            }
        });
    }

    public void initview(){
        email = findViewById(R.id.edUsername);
        btnForgot = findViewById(R.id.btnForgot);
    }
    private class SendMailTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String email = params[0];
            String subject = params[1];
            String body = params[2];
            try {
                EmailSender.sendEmail(email, subject, body);
                return true; // Gửi email thành công
            } catch (Exception e) {
                e.printStackTrace();
                return false; // Gửi email thất bại
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(ForgotActivity.this, "Email đã được gửi", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ForgotActivity.this, "Gửi email thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static String generateRandomString() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
}