package com.pierre.cryptosecure.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pierre.cryptosecure.BaseContrat;
import com.pierre.cryptosecure.DatabaseHelper;
import com.pierre.cryptosecure.model.Site;
import com.pierre.cryptosecure.model.User;

/**
 * Created by Utilisateur on 18/03/2019.
 */

public class DAOUser {
    public User getUserByIds(Context context, User user){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        // accès en lecture (query) :
        SQLiteDatabase dbRead = databaseHelper.getReadableDatabase();

        // projection (colonnes utilisées après la requète) :
        String[] projection = {
                BaseContrat.UserContrat.ID,
                BaseContrat.UserContrat.COLONNE_IDENTIFIANT,
                BaseContrat.UserContrat.COLONNE_PASSWORD,
        };

        User userToGet = new User();

        String selection = BaseContrat.UserContrat.COLONNE_IDENTIFIANT + " = ? AND " +
                BaseContrat.UserContrat.COLONNE_PASSWORD + " = ? ";
        String[] selectionArgs = {user.getIdentifiant(), user.getHashedPassword()};

        Cursor cursor = dbRead.query(
                BaseContrat.UserContrat.TABLE_USER,

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
                    cursor.moveToNext();

                    userToGet.setID(cursor.getInt(cursor.getColumnIndex(BaseContrat.UserContrat.ID)));
                    userToGet.setIdentifiant(cursor.getString(cursor.getColumnIndex(BaseContrat.UserContrat.COLONNE_IDENTIFIANT)));
                    userToGet.setHashedPassword(cursor.getString(cursor.getColumnIndex(BaseContrat.UserContrat.COLONNE_PASSWORD)));
                    userToGet.setPassword(user.getPassword());
                    return user;
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

    public boolean hasAUserThisId(Context context, String identifiant) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        // accès en lecture (query) :
        SQLiteDatabase dbRead = databaseHelper.getReadableDatabase();

        String[] projection = {
                BaseContrat.UserContrat.ID,
        };
        String selection = BaseContrat.UserContrat.COLONNE_IDENTIFIANT + " = ? " ;
        String[] selectionArgs = {identifiant};

        Cursor cursor = dbRead.query(
                BaseContrat.UserContrat.TABLE_USER,

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
                    cursor.moveToNext();
                    return true;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                return false;
            } finally {
                cursor.close();
            }
        }
        return false;
    }
    public void insertUser(Context context, User user){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        // accès en lecture (query) :
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(BaseContrat.UserContrat.COLONNE_IDENTIFIANT, user.getIdentifiant());
        values.put(BaseContrat.UserContrat.COLONNE_PASSWORD, user.getHashedPassword());

        db.beginTransaction();

        try {
            db.insert(BaseContrat.UserContrat.TABLE_USER, null, values);
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
}
