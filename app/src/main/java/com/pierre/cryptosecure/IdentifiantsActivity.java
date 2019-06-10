package com.pierre.cryptosecure;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.pierre.cryptosecure.dao.DAOSite;
import com.pierre.cryptosecure.model.Site;

public class IdentifiantsActivity extends AppCompatActivity  {
    private TextView websiteName;
    private TextView websiteUrl;
    private TextView websiteIdentifiant;
    private TextView websitePassword;
    private Site site;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identifiants);

        final int siteId = getIntent().getExtras().getInt("siteId");

        this.websiteName = findViewById(R.id.name_site);
        this.websiteUrl = findViewById(R.id.url_site);
        this.websiteIdentifiant = findViewById(R.id.identifiant_site);

        DAOSite dao = new DAOSite();

        this.site = dao.getSiteById(this, siteId);

        try {
            this.websiteName.setText(this.site.getName());
            this.websiteUrl.setText(this.site.getUrl());
            this.websiteIdentifiant.setText(this.site.getIdentifiant());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void CopyToClipboard(View v) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Mot de passe", this.site.getPassword());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(IdentifiantsActivity.this, "Mot de passe copié dans le presse papier", Toast.LENGTH_LONG).show();
    }
    public void CopyIdToClipboard(View v) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Identifiant", this.site.getIdentifiant());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(IdentifiantsActivity.this, "Identifiant copié dans le presse papier", Toast.LENGTH_LONG).show();
    }

    public void delete(View v) {
        DialogFragmentShower f = new DialogFragmentShower();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("siteId", this.site.getID());
        f.setArguments(args);

        f.show(getSupportFragmentManager(), "dialog");
    }

    public void modify(View v) {
    }

}
