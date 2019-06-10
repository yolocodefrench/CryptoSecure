package com.pierre.cryptosecure.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pierre.cryptosecure.BaseContrat;
import com.pierre.cryptosecure.DatabaseHelper;
import com.pierre.cryptosecure.model.Site;
import com.pierre.cryptosecure.model.User;

import java.util.ArrayList;
import java.util.List;

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
        Log.i("testPassword", "Get    : "+user.getIdentifiant()+" "+user.getHashedPassword());

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

                    if(cursor != null && cursor.moveToFirst()) {
                        userToGet.setID(cursor.getInt(cursor.getColumnIndex(BaseContrat.UserContrat.ID)));
                        userToGet.setIdentifiant(cursor.getString(cursor.getColumnIndex(BaseContrat.UserContrat.COLONNE_IDENTIFIANT)));
                        userToGet.setHashedPassword(cursor.getString(cursor.getColumnIndex(BaseContrat.UserContrat.COLONNE_PASSWORD)));
                        userToGet.setPassword(user.getPassword());
                        return userToGet;
                    }
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
    public User getUserById(Context context, int id){
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

        String selection = BaseContrat.UserContrat.ID + " = ? ";
        String[] selectionArgs = {Integer.toString(id)};
        Log.i("testPassword", "Get    : "+id);

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
            Log.i("Essai", "J'y quiq");
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {

                    if(cursor != null && cursor.moveToFirst()) {
                        Log.i("Essai", "Il y a un utilisateur");
                        userToGet.setID(cursor.getInt(cursor.getColumnIndex(BaseContrat.UserContrat.ID)));
                        userToGet.setIdentifiant(cursor.getString(cursor.getColumnIndex(BaseContrat.UserContrat.COLONNE_IDENTIFIANT)));
                        userToGet.setHashedPassword(cursor.getString(cursor.getColumnIndex(BaseContrat.UserContrat.COLONNE_PASSWORD)));
                        return userToGet;
                    }
                    Log.i("Essai", "Il y a un utilisateur");
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
                BaseContrat.UserContrat.COLONNE_IDENTIFIANT,
                BaseContrat.UserContrat.COLONNE_PASSWORD,
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

        Log.i("testPassword", "Insert : "+user.getIdentifiant()+" "+user.getHashedPassword());

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

    public List<User> getAllUsers(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] columns = {
                BaseContrat.UserContrat.ID,
                BaseContrat.UserContrat.COLONNE_IDENTIFIANT,
                BaseContrat.UserContrat.COLONNE_PASSWORD
        };
        String selection = null; // this will select all rows
        Cursor cursor = db.query(BaseContrat.UserContrat.TABLE_USER, columns, selection,
                null, null, null, null, null);

        List<User> users = new ArrayList<>();
        if (cursor != null)
        {
            try
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    // conversion des données remontées en un objet métier :
                    users.add(new User(
                            cursor.getInt(cursor.getColumnIndex(BaseContrat.UserContrat.ID)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.UserContrat.COLONNE_IDENTIFIANT)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.UserContrat.COLONNE_PASSWORD))
                            )
                    );
                    cursor.moveToNext();
                }
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
            finally
            {
                cursor.close();
            }
        }



        return users;
    }


}
