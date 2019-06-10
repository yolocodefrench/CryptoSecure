package com.pierre.cryptosecure;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.pierre.cryptosecure.dao.DAOSite;

import org.parceler.Parcels;

/**
 * Created by Utilisateur on 10/06/2019.
 */

public class DialogFragmentShower extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.MyDialogTheme);
        final int siteId = getArguments().getInt("siteId");
        builder.setMessage("Voulez vous vraiment supprimer ces identifiants")
        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int id) {

                Dialog dialog  = (Dialog) dialogInterface;
                Context context = dialog.getContext();
                DAOSite dao = new DAOSite();
                final boolean b = dao.deleteSite(context, siteId);
                if(b) {
                    Log.i("Essai", Integer.toString(siteId));
                    ((IdentifiantsActivity)getActivity()).finish();
                }
                else {
                    Log.i("Essai", "Loupé");
                }

            }
        }).setNegativeButton("annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.i("Essai", "annuler");
                dialog.dismiss();
            }
        });;
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
