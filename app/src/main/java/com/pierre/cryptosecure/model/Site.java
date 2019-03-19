package com.pierre.cryptosecure.model;

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

    public Site() {
    }

    public Site(String url, String name, String identifiant, String password, User user) {
        this.url = url;
        this.name = name;
        this.identifiant = identifiant;
        this.password = password;
        this.user = user;
    }

    public Site(Integer ID, String url, String name, String identifiant, String password) {
        this.ID = ID;
        this.url = url;
        this.name = name;
        this.identifiant = identifiant;
        this.password = password;
    }

    public Site(Integer ID, String url, String name, String identifiant, String password, User user) {
        this.ID = ID;
        this.url = url;
        this.name = name;
        this.identifiant = identifiant;
        this.password = password;
        this.user = user;
    }

    public Integer getID() {
        return ID;
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
}
