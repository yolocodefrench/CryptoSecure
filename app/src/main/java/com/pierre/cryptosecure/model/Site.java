package com.pierre.cryptosecure.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

/**
 * Created by Utilisateur on 18/03/2019.
 */

public class Site {

    private Integer ID;
    private String url;
    private String name;
    private String identifiant;
    private String password;
    private User user;
    private String imageBase64;

    public Site() {
    }

    public Site(String url, String name, String identifiant, String password, User user, String imageBAse64) {
        this.url = url;
        this.name = name;
        this.identifiant = identifiant;
        this.password = password;
        this.user = user;
        if(imageBAse64 != null) {
            Log.i("Image", imageBAse64);
            this.imageBase64 = imageBAse64;
        }
    }

    public Site(Integer ID, String url, String name, String identifiant, String password, String image) {
        Log.i("Essai", "J'essaye" + ID);
        this.ID = ID;
        this.url = url;
        this.name = name;
        this.identifiant = identifiant;
        this.password = password;
        this.imageBase64 = image;
    }

    public Site(Integer ID, String url, String name, String identifiant, String password, User user, String image) {
        this.ID = ID;
        this.url = url;
        this.name = name;
        this.identifiant = identifiant;
        this.password = password;
        this.user = user;
        this.imageBase64 = image;
    }

    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImageBase64() { return this.imageBase64;    }

    public void setImageBase64(String imageBase64) { this.imageBase64 = imageBase64; }

    public Bitmap getImage() {
        if(this.getImageBase64() != null){
            final byte[] decodedBytes = Base64.decode(this.getImageBase64(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
            Log.i("ImageToTest", decodedByte.toString());
            return decodedByte;
        }
        return null;
    }

    public String toString() {
        return this.name + " " + this.imageBase64;
    }
}
