package hr.foi.myguide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import hr.foi.database.entities.Tour;
import hr.foi.myguide.Adapters.TourAdapter;
import hr.foi.webservice.ZahtjevZaPrijavu;
import hr.foi.webservice.ZahtjevZaTuru;

public class PocetnaStranica extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    RecyclerView recyclerView;
    TourAdapter adapter;
    List<Tour> listTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna_stranica);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //final PocetnaStranica p = new PocetnaStranica();

        listTour = new ArrayList<>();

        //creating recyclerview adapter
        final TourAdapter adapter = new TourAdapter(this, listTour);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
        //adapter = new TourAdapter(this, listTour);
        //recyclerView.setAdapter(adapter);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONObject dataJSON = jsonResponse.getJSONObject("data");
                    boolean success = dataJSON.getBoolean("success");
                    if(success){
                        JSONArray tours= dataJSON.getJSONArray("rows");
                        //List<Tour> toursList = new ArrayList<Tour>();
                        Tour tourInstance = new Tour();
                        tourInstance.fetchTours(tours);
                        adapter.updateToursList(tourInstance.toursList);
                        //listTour = tourInstance.toursList;
                        //adapter.notifyDataSetChanged();

                        //List<Tour> toursList = new ArrayList<Tour>();
                        //toursList = tourInstance.toursList;

//                        for (int i=0; i<tourInstance.toursList.size();i++) {
//                            Tour object = new Tour();
//
//                            object = tourInstance.toursList.get(i);
//
//                            // Initialize a new CardView
//                            CardView card = new CardView(mContext);
//
//                            // Set the CardView layoutParams
//                            LayoutParams params = new LayoutParams(
//                                    LayoutParams.WRAP_CONTENT,
//                                    LayoutParams.WRAP_CONTENT
//                            );
//                            params.setMargins(1000,100,40,20);
//                            card.setLayoutParams(params);
//
//                            // Set CardView corner radius
//                            card.setRadius(9);
//
//                            // Set cardView content padding
//                            //card.setContentPadding(15+(i*5), 15+(i*5), 15+(i*5), 15+(i*5));
//
//                            // Set a background color for CardView
//                            //card.setCardBackgroundColor(Color.parseColor("#FFC6D6C3"));
//
//                            // Set the CardView maximum elevation
//                           // card.setMaxCardElevation(15);
//
//                            // Set CardView elevation
//                           // card.setCardElevation(9);
//
//                            // Initialize a new TextView to put in CardView
//                            TextView tv = new TextView(mContext);
//                            tv.setLayoutParams(params);
//                            tv.setText(object.opis);
//                            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
//                            tv.setTextColor(Color.RED);
//
//                            // Put the TextView in CardView
//                            card.addView(tv);
//
//                            // Finally, add the CardView in root layout
//                            mRelativeLayout.addView(card);
//                        }


//                        for (int i = 0; i < tours.length(); i++) {
//                            //JSONObject currentRow = tours.getJSONObject(i).getInt("id_tura");
//                            JSONObject tour =  tours.getJSONObject(i);
//
//                            Tour tura = new Tour(tour.getInt("id_tura"),
//                                    tour.getString("naziv"),
//                                    tour.getString("opis"),
//                                    tour.getString("img_name"),
//                                    tour.getString("img_path"),
//                                    tour.getInt("id_korisnik"),
//                                    tour.getInt("aktivan"));
//                            toursList.add(tura);
//                        }
                       // JSONObject dadata = jsonResponse.getJSONObject("data");


                        //Tour tura = new Tour();

                        //List<Tour> tourList= new Gson().fromJson(tours.toString(), new TypeToken<ArrayList<Tour>>(){}.getType());
                    }else{

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        ZahtjevZaTuru zahtjevZaTuru = new ZahtjevZaTuru(responseListener);
        RequestQueue queue = Volley.newRequestQueue(PocetnaStranica.this);
        queue.add(zahtjevZaTuru);










        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu;ems to the a this adds itction bar if it is present.
        getMenuInflater().inflate(R.menu.pocetna_stranica, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_login) {
        } else if (id == R.id.nav_signup) {

        } else if (id == R.id.nav_about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
