package com.pierre.cryptosecure;

import android.provider.BaseColumns;

/**
 * Created by Utilisateur on 18/03/2019.
 */

public class BaseContrat {

    private BaseContrat() {}
    // contenu de la table "country" :
    public static class UserContrat implements BaseColumns
    {
        public static final String TABLE_USER = "app_user";
        public static final String ID ="id";
        public static final String COLONNE_IDENTIFIANT = "name";
        public static final String COLONNE_PASSWORD = "password";
    }

    public static class CryptoContrat implements BaseColumns
    {
        public static final String TABLE_CRYPTO = "crypto";
        public static final String ID ="id";
        public static final String COLONNE_USER = "user_id";
        public static final String COLONNE_PUBLIC_KEY = "public_key";
        public static final String COLONNE_PRIVATE_KEY = "private_key";
    }

    public static class SiteContrat implements BaseColumns
    {
        public static final String TABLE_SITE = "site";
        public static final String ID ="id";
        public static final String COLONNE_URL = "url";
        public static final String COLONNE_NAME = "name";
        public static final String COLONNE_IDENTIFIANT = "identifiant";
        public static final String COLONNE_PASSWORD = "password";
        public static final String COLONNE_ICONE = "icone";
        public static final String COLONNE_USER = "user_id";
    }
}
