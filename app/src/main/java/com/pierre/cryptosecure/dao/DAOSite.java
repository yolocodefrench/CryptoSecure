package com.pierre.cryptosecure.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pierre.cryptosecure.BaseContrat;
import com.pierre.cryptosecure.DatabaseHelper;
import com.pierre.cryptosecure.DialogFragmentShower;
import com.pierre.cryptosecure.model.Crypto;
import com.pierre.cryptosecure.model.Site;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Utilisateur on 18/03/2019.
 */

public class DAOSite {

    public List<Site> getAllSiteByUser(Context context, int id) {
        // classe qui étend SQLiteOpenHelper :
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        // accès en lecture (query) :
        SQLiteDatabase dbRead = databaseHelper.getReadableDatabase();

        // projection (colonnes utilisées après la requète) :
        String[] projection = {
                BaseContrat.SiteContrat.ID,
                BaseContrat.SiteContrat.COLONNE_NAME,
                BaseContrat.SiteContrat.COLONNE_URL,
                BaseContrat.SiteContrat.COLONNE_IDENTIFIANT,
                BaseContrat.SiteContrat.COLONNE_PASSWORD,
                BaseContrat.SiteContrat.COLONNE_ICONE,
        };
        String tri = BaseContrat.SiteContrat.ID + " ASC ";

        String selection = BaseContrat.SiteContrat.COLONNE_USER + " = ? ";
        String[] selections = {Integer.toString(id)};

        Cursor cursor = dbRead.query(
                BaseContrat.SiteContrat.TABLE_SITE,
                projection,  // colonnes à retourner
                selection,  // colonnes pour la clause WHERE (inactif)
                selections, // valeurs pour la clause WHERE (inactif)
                null, // GROUP BY (inactif)
                null,     // HAVING (inactif)
                tri,     // ordre de tri
                null); // LIMIT (inactif)
        List<Site> listSite = new ArrayList<>();
        if (cursor != null) {
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    // conversion des données remontées en un objet métier :
                    listSite.add(new Site(
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex(BaseContrat.SiteContrat.ID))),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.SiteContrat.COLONNE_URL)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.SiteContrat.COLONNE_NAME)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.SiteContrat.COLONNE_IDENTIFIANT)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.SiteContrat.COLONNE_PASSWORD)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.SiteContrat.COLONNE_ICONE))
                    ));
                    cursor.moveToNext();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                cursor.close();
            }
        }
        return listSite;
    }

    public Site getSiteById(Context context, int id){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        // accès en lecture (query) :
        SQLiteDatabase dbRead = databaseHelper.getReadableDatabase();

        // projection (colonnes utilisées après la requète) :
        String[] projection = {
                BaseContrat.SiteContrat.ID,
                BaseContrat.SiteContrat.COLONNE_URL,
                BaseContrat.SiteContrat.COLONNE_NAME,
                BaseContrat.SiteContrat.COLONNE_PASSWORD,
                BaseContrat.SiteContrat.COLONNE_IDENTIFIANT
        };

        Site site = new Site();

        String selection = BaseContrat.SiteContrat.ID + " = ? ";
        String[] selectionArgs = {Integer.toString(id)};

        Cursor cursor = dbRead.query(
                BaseContrat.SiteContrat.TABLE_SITE,

                projection,  // colonnes à retourner

                selection,  // colonnes pour la clause WHERE (inactif)

                selectionArgs, // valeurs pour la clause WHERE (inactif)

                null, // GROUP BY (inactif)

                null,     // HAVING (inactif)

                null,     // ordre de tri

                null); // LIMIT (inactif)
        if (cursor != null) {
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    site.setID(id);
                    site.setUrl(cursor.getString(cursor.getColumnIndex(BaseContrat.SiteContrat.COLONNE_URL)));
                    site.setName(cursor.getString(cursor.getColumnIndex(BaseContrat.SiteContrat.COLONNE_NAME)));
                    site.setIdentifiant(cursor.getString(cursor.getColumnIndex(BaseContrat.SiteContrat.COLONNE_NAME)));
                    site.setPassword(cursor.getString(cursor.getColumnIndex(BaseContrat.SiteContrat.COLONNE_PASSWORD)));

                    Log.i("TEST", site.toString());

                    return site;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                return null;
            } finally {
                cursor.close();
            }
        }
        return null;
    }

    public void insertSite(Context context, Site site){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        // accès en lecture (query) :
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(BaseContrat.SiteContrat.COLONNE_NAME, site.getName());
        values.put(BaseContrat.SiteContrat.COLONNE_URL, site.getUrl());
        values.put(BaseContrat.SiteContrat.COLONNE_IDENTIFIANT, site.getIdentifiant());
        values.put(BaseContrat.SiteContrat.COLONNE_PASSWORD, site.getPassword());
        values.put(BaseContrat.SiteContrat.COLONNE_USER, site.getUser().getID());
        values.put(BaseContrat.SiteContrat.COLONNE_ICONE, site.getImageBase64());

        db.beginTransaction();

        try {
            db.insert(BaseContrat.SiteContrat.TABLE_SITE, null, values);
            db.setTransactionSuccessful() ;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
            db.close();
        }
    }

    public int updateSite(Context context, Site site){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        // accès en lecture (query) :
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // objet de valeurs :
        ContentValues values = new ContentValues();
        values.put(BaseContrat.SiteContrat.COLONNE_URL, site.getUrl());
        values.put(BaseContrat.SiteContrat.COLONNE_NAME, site.getName());
        values.put(BaseContrat.SiteContrat.COLONNE_IDENTIFIANT, site.getIdentifiant());
        values.put(BaseContrat.SiteContrat.COLONNE_PASSWORD, site.getPassword());
        values.put(BaseContrat.SiteContrat.COLONNE_USER, site.getUser().getID());

        // filtre (clause WHERE) :
        String selection = BaseContrat.CryptoContrat.COLONNE_USER + " = ? ";
        String[] selectionArgs = {Integer.toString(site.getUser().getID())};
        // requête :
        int count = db.update(
                BaseContrat.SiteContrat.TABLE_SITE,
                values,
                selection,
                selectionArgs);

        return count;
    }

    public boolean deleteSite(Context context, int id){

        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        // accès en lecture (query) :
        SQLiteDatabase dbRead = databaseHelper.getReadableDatabase();

        try{
            dbRead.delete(BaseContrat.SiteContrat.TABLE_SITE,BaseContrat.SiteContrat.ID+"=?",new String[]{Integer.toString(id)});
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
