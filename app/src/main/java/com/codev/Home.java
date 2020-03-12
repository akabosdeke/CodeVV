package com.codev;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private WebView webView;
    TextView tv;
    private boolean exit = false;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId ();

        if (id == R.id.facebook) {
            startActivity (new Intent (Intent.ACTION_VIEW, Uri.parse ("https://www.facebook.com/Knowledge-hub-101620281456006/?boosted_auto_open=boosted_pagelike&ref=notif&modal=composer&notif_id=1583859512210732&notif_t=aymt_page_like_generic_tip_notif")));

            return true;
        }

        return super.onOptionsItemSelected (item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_home);
        webView = findViewById (R.id.web);
        tv = findViewById (R.id.textview);
        checkConnection ();


        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
     /*   FloatingActionButton fab = findViewById (R.id.fab);
        fab.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Snackbar.make (view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction ("Action", null).show ();
            }
        });*/

        final DrawerLayout drawer = findViewById (R.id.drawer_layout);
        NavigationView navigationView = findViewById (R.id.nav_view);
        navigationView.setNavigationItemSelectedListener (this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setItemIconTintList (null); //for origional and same icon


    }

    protected boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo ();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting ()) {
            return true;
        } else {
            return false;

        }
    }

    private void checkConnection() {
        if (isOnline ()) {
            Toast.makeText (this, "You are connected to internet", Toast.LENGTH_SHORT).show ();
            webView = findViewById (R.id.web);
            webView.loadUrl ("https://www.youtube.com/channel/UCZ6rB7nI-QF6fftI62st02Q/featured?view_as=subscriber");

            WebSettings webSettings = webView.getSettings ();
            webSettings.setJavaScriptEnabled (true);
            webView.setWebViewClient (new WebViewClient ());
        } else {
            //   Toast.makeText (this, "You are  not connected to internet", Toast.LENGTH_SHORT).show ();
            tv.setText (" Can't Connected to the Server.\n Please check internet Connection");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ().inflate (R.menu.home, menu);
        return true;
    }


//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController (this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp (navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp ();
//    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack ()) {
            webView.goBack ();

        } else {
            if (exit) {
                finish ();
            } else {
                Toast.makeText (this, "Press Again to Exit", Toast.LENGTH_SHORT).show ();
            }
            Timer timer = new Timer ();
            timer.schedule (new TimerTask () {
                @Override
                public void run() {
                    exit = false;
                }
            }, 2000);
            exit = true;


            super.onBackPressed ();
        }

    }
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
//        Toast.makeText(getApplicationContext(),"hi",Toast.LENGTH_SHORT).show();
    switch (item.getItemId ())
    {
        case R.id.facebook:
            Toast.makeText (getApplicationContext(), "Facebook clicked", Toast.LENGTH_SHORT).show ();
            break;
        case R.id.instagram:
            Toast.makeText (this, "Insta is clicked", Toast.LENGTH_SHORT).show ();
            break;
        case R.id.twitter:
            Toast.makeText (this, "twitter is clicked", Toast.LENGTH_SHORT).show ();
            break;
        case R.id.linkdin :
            Toast.makeText (this, "linkdin is clicked", Toast.LENGTH_SHORT).show ();
                break;
        case R.id.request_Video:
            Toast.makeText (this, "Request video is clicked", Toast.LENGTH_SHORT).show ();
            break;
        case R.id.About_Us:
            Toast.makeText (this, "Aboutus is clicked", Toast.LENGTH_SHORT).show ();
            break;
        case R.id.nav_amazon:
            Toast.makeText (this, "Amazon is clicked", Toast.LENGTH_SHORT).show ();
            break;
        case R.id.nav_Flipkart:
            Toast.makeText (this, "Flipkart is clicked", Toast.LENGTH_SHORT).show ();
            break;
    }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}