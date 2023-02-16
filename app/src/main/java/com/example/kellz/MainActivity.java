package com.example.kellz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button login_button = findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = username.getText().toString();
                String passwords = password.getText().toString();
                try {
                    if(userName.equals("")){
                        Toast.makeText(MainActivity.this, "Enter username", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(passwords.equals("")){
                        Toast.makeText(MainActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                        Passecurity.password = passwords;
                        Passecurity.pass();
                    }

                    Toast.makeText(MainActivity.this, "Send data check terminal", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "failed to check password", Toast.LENGTH_SHORT).show();
                }
              }
        });
    }
}