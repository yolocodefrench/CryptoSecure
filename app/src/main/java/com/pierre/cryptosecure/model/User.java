package com.pierre.cryptosecure.model;

import android.os.Parcelable;

import com.pierre.cryptosecure.utils.Crypter;

import org.parceler.Parcel;

import java.security.MessageDigest;

/**
 * Created by Utilisateur on 18/03/2019.
 */

@Parcel( Parcel.Serialization.BEAN )
public class User {
    private int ID;
    private String identifiant;
    private String password;
    private String hashedPassword;

    public User() {
    }

    public User(String identifiant, String password) {
        this.identifiant = identifiant;
        this.password = password;
        this.hashedPassword = Crypter.getSha256(this.password);
    }

    public User(int ID, String identifiant, String password) {
        this.ID = ID;
        this.identifiant = identifiant;
        this.password = password;
        this.hashedPassword = Crypter.getSha256(this.password);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
        this.setHashedPassword(Crypter.getSha256(this.password));
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String toString(){
        return this.getID() + " " + this.getIdentifiant();
    }
}
