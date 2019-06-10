package com.pierre.cryptosecure;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pierre.cryptosecure.dao.DAOSite;
import com.pierre.cryptosecure.dao.DAOUser;
import com.pierre.cryptosecure.model.Site;
import com.pierre.cryptosecure.model.User;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class AddWebsite extends AppCompatActivity implements Response.Listener<Bitmap>, Response.ErrorListener {

    private Button buttonConfirmAdd;

    private TextInputEditText websiteName;
    private TextInputEditText websiteUrl;
    private TextInputEditText websiteIdentifiant;
    private TextInputEditText websitePassword;
    private ImageView image;
    private String imageBase64 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_website);

        this.websiteName = findViewById(R.id.nameInputEdit);
        this.websiteUrl = findViewById(R.id.urlInputEdit);
        this.websiteIdentifiant = findViewById(R.id.identifiantInputEdit);
        this.websitePassword = findViewById(R.id.passwordInputEdit);
        this.buttonConfirmAdd = findViewById(R.id.buttonConfirmAdd);
        this.image = (ImageView) this.findViewById(R.id.imageView2);


        final String userId = getIntent().getExtras().getString("User");
        final String password = getIntent().getExtras().getString("Password");

        this.websiteUrl.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    String websiteFormUrl = websiteUrl.getText().toString().replace("//", "/");
                    RequestQueue queue = Volley.newRequestQueue(AddWebsite.this);

                    String url = "https://logo.clearbit.com/" + websiteFormUrl;
                    ImageRequest request = new ImageRequest(url,
                            AddWebsite.this,
                            0,
                            0,
                            ImageView.ScaleType.CENTER_CROP,
                            Bitmap.Config.RGB_565,
                            AddWebsite.this);
                    queue.add(request);
                }
            }
        });

        final int result = 200;

        this.buttonConfirmAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String websiteFormName = websiteName.getText().toString();
                String websiteFormUrl = websiteUrl.getText().toString();
                String websiteFormIdentifiant = websiteIdentifiant.getText().toString();
                String websiteFormPassword = websitePassword.getText().toString();
                try{

                    DAOUser daoUser = new DAOUser();
                    User user = daoUser.getUserById(AddWebsite.this, Integer.parseInt(userId));
                    Site site = new Site(websiteFormUrl, websiteFormName, websiteFormIdentifiant, websiteFormPassword, user, imageBase64);
                    DAOSite daoSite = new DAOSite();
                    try {
                        daoSite.insertSite(AddWebsite.this, site);
                        Log.i("Insertion", "site : "+ websiteFormName);

                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result",result);
                        setResult(RESULT_OK,returnIntent);
                        finish();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }catch (Exception e){
                    Log.i("Essai", "Ca plante fraté");

                    e.printStackTrace();
                }
            }
        });



    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("Response", error.toString());
    }

    @Override
    public void onResponse(Bitmap response) {
        this.image.setImageBitmap(response);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        response.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

        this.imageBase64 = encoded;
    }
}
