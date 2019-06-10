package com.pierre.cryptosecure;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pierre.cryptosecure.dao.DAOSite;
import com.pierre.cryptosecure.dao.DAOUser;
import com.pierre.cryptosecure.model.Site;
import com.pierre.cryptosecure.model.User;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    private Button buttonAddWebsite = null;
    private String userId;
    private String password;
    private LinearLayoutManager layoutManager= null;
    private SiteAdapter siteAdapter = null;
    private GestureDetector gestureDetector = null;
    private List<Site> siteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        if(savedInstanceState != null){
            this.password = savedInstanceState.getString("password");
            this.userId = savedInstanceState.getString("User");
        }
        else{
            this.userId = getIntent().getExtras().getString("User");
            this.password = getIntent().getExtras().getString("Password");
        }
        this.buttonAddWebsite = findViewById(R.id.buttonAddWebsite);

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        DAOSite daoSite = new DAOSite();
        siteList = daoSite.getAllSiteByUser(HomePageActivity.this, Integer.parseInt(this.userId));

        this.siteAdapter = new SiteAdapter(siteList);
        recycler.setAdapter(this.siteAdapter);

        recycler.addOnItemTouchListener(this);
        gestureDetector = new GestureDetector(this,
                new GestureDetector.SimpleOnGestureListener()
                {
                    @Override
                    public boolean onSingleTapUp(MotionEvent event)
                    {
                        return true;
                    }
                });

        buttonAddWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, AddWebsite.class);
                intent.putExtra("User", HomePageActivity.this.userId);
                intent.putExtra("Password", HomePageActivity.this.password);
                startActivityForResult(intent, 200);
            }
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putString("password" , this.password);
        saveInstanceState.putString("User" , this.userId);
        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("Essai", savedInstanceState.getString("Password"));
        this.password = savedInstanceState.getString("password");
        this.userId = savedInstanceState.getString("User");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void onStart(){
        super.onStart();
        Log.i("TEST","onStart" );
    }

    protected void onResume(){
        this.actualiserListe();
        super.onResume();
        Log.i("TEST","onResume" );
    }

    protected void onPause(){
        super.onPause();
        Log.i("TEST","onPause" );
    }

    protected void onStop(){
        super.onStop();
        Log.i("TEST","onStop" );
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.i("TEST","onDestroy" );
    }

    private void actualiserListe(){
        DAOSite daoSite = new DAOSite();
        List<Site> siteList = daoSite.getAllSiteByUser(HomePageActivity.this, Integer.parseInt(this.userId));
        this.siteAdapter.actualiserListe(siteList);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        // position dans la liste d'objets métier (position à partir de zéro !) :
        if (child != null)
        {
            int position = rv.getChildAdapterPosition(child);
            Intent intent = new Intent(this, IdentifiantsActivity.class);
            intent.putExtra("siteId", this.siteList.get(position).getID());
            startActivityForResult(intent, 200);
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
