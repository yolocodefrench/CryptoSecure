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

import com.pierre.cryptosecure.dao.DAOUser;
import com.pierre.cryptosecure.model.User;

import org.parceler.Parcels;

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

                DAOUser daoUser = new DAOUser();
                User user = new User(identifiant, password);

                User mainUser = daoUser.getUserByIds(MainActivity.this, user);


                if( mainUser != null){
                    Intent intent = new Intent(view.getContext(), HomePageActivity.class);
                    intent.putExtra("User", Integer.toString(mainUser.getID()));
                    intent.putExtra("Password", user.getPassword());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "No user with these credentidals", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}
