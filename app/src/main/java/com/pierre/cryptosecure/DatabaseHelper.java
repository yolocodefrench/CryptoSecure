package com.pierre.cryptosecure;

/**
 * Created by Utilisateur on 18/03/2019.
 */

import android.bluetooth.BluetoothAssignedNumbers;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Utilisateur on 18/03/2019.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NOM_BASE = "crypto.db";
    private static final int VERSION = 5;

    public static final String TABLE_USER_CREATE = "CREATE TABLE " + BaseContrat.UserContrat.TABLE_USER + " (" +
            BaseContrat.UserContrat.ID + " integer primary key autoincrement not null, " +
            BaseContrat.UserContrat.COLONNE_IDENTIFIANT + " string unique, " +
            BaseContrat.UserContrat.COLONNE_PASSWORD + " string " +
            ");";

    public static final String TABLE_CRYPTO_CREATE = "CREATE TABLE " + BaseContrat.CryptoContrat.TABLE_CRYPTO+ "(" +
            BaseContrat.CryptoContrat.ID + " integer primary key autoincrement not null, " +
            BaseContrat.CryptoContrat.COLONNE_PUBLIC_KEY + " string, " +
            BaseContrat.CryptoContrat.COLONNE_PRIVATE_KEY + " string, " +
            BaseContrat.CryptoContrat.COLONNE_USER + " integer, " +
            "FOREIGN KEY(" + BaseContrat.CryptoContrat.COLONNE_USER + ") REFERENCES "+ BaseContrat.UserContrat.TABLE_USER + "(" + BaseContrat.UserContrat.ID + ")" +
            ");";

    public static final String TABLE_SITE_CREATE = "CREATE TABLE " + BaseContrat.SiteContrat.TABLE_SITE+ "(" +
            BaseContrat.SiteContrat.ID + " integer primary key autoincrement not null, " +
            BaseContrat.SiteContrat.COLONNE_IDENTIFIANT + " string, " +
            BaseContrat.SiteContrat.COLONNE_PASSWORD + " string, " +
            BaseContrat.SiteContrat.COLONNE_NAME + " string, " +
            BaseContrat.SiteContrat.COLONNE_URL + " string, " +
            BaseContrat.SiteContrat.COLONNE_USER + " integer, " +
            "FOREIGN KEY(" + BaseContrat.SiteContrat.COLONNE_USER + ") REFERENCES "+ BaseContrat.UserContrat.TABLE_USER + "(" + BaseContrat.UserContrat.ID + ")" +
            ");";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context) {
        super(context, NOM_BASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(TABLE_USER_CREATE);
            db.execSQL(TABLE_CRYPTO_CREATE);
            db.execSQL(TABLE_CRYPTO_CREATE);

            Log.i("sqlite", DatabaseHelper.TABLE_USER_CREATE);
            Log.i("sqlite", DatabaseHelper.TABLE_CRYPTO_CREATE);
            Log.i("sqlite", DatabaseHelper.TABLE_SITE_CREATE);

            Log.i("test", "Initialisation db");

            Cursor cursor = db.rawQuery("SELECT * FROM "+BaseContrat.UserContrat.TABLE_USER, null);
            if(cursor != null){
                try {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        cursor.moveToNext();
                        Log.i("test", "Réussi");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    Log.i("test", "Il n'y a rien");
                } finally {
                    cursor.close();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+BaseContrat.UserContrat.TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS "+BaseContrat.SiteContrat.TABLE_SITE);
        db.execSQL("DROP TABLE IF EXISTS "+BaseContrat.CryptoContrat.TABLE_CRYPTO);
        onCreate(db);
    }
}
