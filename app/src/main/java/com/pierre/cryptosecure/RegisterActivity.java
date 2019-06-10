package com.pierre.cryptosecure;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pierre.cryptosecure.dao.DAOUser;
import com.pierre.cryptosecure.model.User;
import com.pierre.cryptosecure.utils.PasswordChecker;

public class RegisterActivity extends AppCompatActivity {

    private Button buttonRegister = null;
    private TextInputEditText identifiantText = null;
    private TextInputEditText passwordText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Log.i("Test", "Je sui srneyé");

        final DAOUser daoUser = new DAOUser();

        buttonRegister = findViewById(R.id.buttonRegistration);
        identifiantText = findViewById(R.id.identifiantText);
        passwordText = findViewById(R.id.passwordText);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String identifiant = identifiantText.getText().toString();
                String password = passwordText.getText().toString();
                Log.i("Test", identifiant + ":"+password);
                if( PasswordChecker.isPasswordValid(password) ) {
                    if( PasswordChecker.isEmailValid(identifiant) ) {
                        if(!daoUser.hasAUserThisId(RegisterActivity.this, identifiant)) {
                            try {
                                daoUser.insertUser(RegisterActivity.this, new User(identifiant, password));
                                RegisterActivity.this.finish();
                            } catch (Exception e) {
                                Toast.makeText(RegisterActivity.this, "Can't register you!! my bad <3", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this, "A user with this identifiant already exists", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "The email is invalid", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(RegisterActivity.this, "The password is too weak", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}
