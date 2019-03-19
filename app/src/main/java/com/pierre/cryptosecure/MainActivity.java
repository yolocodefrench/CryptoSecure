package com.pierre.cryptosecure;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonConnection = null;
    private Button buttonRegistration = null;
    private TextInputEditText identifiantText = null;
    private TextInputEditText passwordText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonConnection = findViewById(R.id.buttonConnection);
        buttonRegistration = findViewById(R.id.buttonRegistration);

        identifiantText = findViewById(R.id.identifiantText);
        passwordText = findViewById(R.id.passwordText);
        buttonConnection.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String identifiant = identifiantText.getText().toString();
                String password = passwordText.getText().toString();

                Toast.makeText(MainActivity.this, identifiant + " " + password, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(view.getContext(), HomePageActivity.class);
                startActivity(intent);
            }
        });

        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }

}
