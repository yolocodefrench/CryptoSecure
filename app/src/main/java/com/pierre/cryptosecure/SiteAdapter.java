package com.pierre.cryptosecure;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pierre.cryptosecure.model.Site;

import java.util.List;

/**
 * Created by Utilisateur on 20/03/2019.
 */

public class SiteAdapter extends RecyclerView.Adapter<SiteViewHolder> {

    private List<Site> listSite = null;

    public SiteAdapter(List<Site> listSite) {
        this.listSite = listSite;
    }

    @Override
    public SiteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewSite = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_site, parent, false);
        return new SiteViewHolder(viewSite);
    }

    @Override
    public void onBindViewHolder(SiteViewHolder holder, int position) {
        holder.getTextView().setText(listSite.get(position).getUrl());
        if(listSite.get(position).getImage() != null) {
            holder.getImageView().setImageBitmap(listSite.get(position).getImage());
            Log.i("Image", "Content : "+ listSite.get(position).getImage());
        }

    }

    @Override
    public int getItemCount() {
        return listSite.size();
    }

    public void actualiserListe(List<Site> listeSite)
    {
        this.listSite = listeSite;
        notifyDataSetChanged();
    }
}
