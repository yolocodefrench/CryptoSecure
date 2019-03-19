package com.pierre.cryptosecure.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pierre.cryptosecure.BaseContrat;
import com.pierre.cryptosecure.DatabaseHelper;
import com.pierre.cryptosecure.model.Crypto;

/**
 * Created by Utilisateur on 18/03/2019.
 */

public class DAOCrypto {

    public Crypto getCryptoByUserId(Context context, int id){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        // accès en lecture (query) :
        SQLiteDatabase dbRead = databaseHelper.getReadableDatabase();

        String[] projection = {
                BaseContrat.CryptoContrat.ID,
                BaseContrat.CryptoContrat.COLONNE_PUBLIC_KEY,
                BaseContrat.CryptoContrat.COLONNE_PRIVATE_KEY,
        };
        Crypto crypto = new Crypto();

        String selection = BaseContrat.CryptoContrat.COLONNE_USER + " = ? ";
        String[] selectionArgs = {Integer.toString(id)};

        Cursor cursor = dbRead.query(
                BaseContrat.CryptoContrat.TABLE_CRYPTO,

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

                    crypto.setID(cursor.getInt(cursor.getColumnIndex(BaseContrat.CryptoContrat.ID)));
                    crypto.setPublicKey(cursor.getString(cursor.getColumnIndex(BaseContrat.CryptoContrat.COLONNE_PUBLIC_KEY)));
                    crypto.setPrivateKey(cursor.getString(cursor.getColumnIndex(BaseContrat.CryptoContrat.COLONNE_PRIVATE_KEY)));
                    return crypto;
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
    public void insertCrypto(Context context, Crypto crypto){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        // accès en lecture (query) :
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(BaseContrat.CryptoContrat.COLONNE_PUBLIC_KEY, crypto.getPublicKey());
        values.put(BaseContrat.CryptoContrat.COLONNE_PRIVATE_KEY, crypto.getPrivateKey());
        values.put(BaseContrat.CryptoContrat.COLONNE_PRIVATE_KEY, crypto.getUser().getID());
        db.beginTransaction();

        try {
            db.insert(BaseContrat.CryptoContrat.TABLE_CRYPTO, null, values);
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

    private int updateCrypto(Context context, Crypto crypto){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        // accès en lecture (query) :
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // objet de valeurs :
        ContentValues values = new ContentValues();
        values.put(BaseContrat.CryptoContrat.COLONNE_PUBLIC_KEY, crypto.getPublicKey());
        values.put(BaseContrat.CryptoContrat.COLONNE_PRIVATE_KEY, crypto.getPrivateKey());
        values.put(BaseContrat.CryptoContrat.COLONNE_USER, crypto.getUser().getID());
        // filtre (clause WHERE) :
        String selection = BaseContrat.CryptoContrat.COLONNE_USER + " = ? ";
        String[] selectionArgs = {Integer.toString(crypto.getUser().getID())};
        // requête :
        int count = db.update(
                BaseContrat.CryptoContrat.TABLE_CRYPTO,
                values,
                selection,
                selectionArgs);

        return count;
    }
}
