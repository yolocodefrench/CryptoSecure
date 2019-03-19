package com.pierre.cryptosecure.model;

/**
 * Created by Utilisateur on 18/03/2019.
 */

public class Crypto {
    private Integer ID;
    private String publicKey;
    private String privateKey;
    private User user;

    public Crypto() {
    }

    public Crypto(String publicKey, String privateKey, User user) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.user = user;
    }

    public Crypto(Integer ID, String publicKey, String privateKey, User user) {
        this.ID = ID;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.user = user;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
