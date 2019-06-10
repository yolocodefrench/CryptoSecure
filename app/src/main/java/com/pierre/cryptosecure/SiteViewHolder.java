package com.pierre.cryptosecure;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Utilisateur on 20/03/2019.
 */

public class SiteViewHolder extends ViewHolder {

    private TextView text = null;
    private ImageView image = null;
    public SiteViewHolder(View itemView) {
        super(itemView);
        this.text = (TextView) itemView.findViewById(R.id.site_text);
        this.image = (ImageView) itemView.findViewById(R.id.imageView);
    }

    public TextView getTextView()
    {
        return this.text;
    }

    public ImageView getImageView()
    {
        return this.image;
    }
}
