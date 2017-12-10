package hr.foi.myguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import hr.foi.database.entities.Korisnik;
import hr.foi.database.entities.Tour;
import hr.foi.myguide.Adapters.TourAdapter;
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
                SessionManager sessionManager = new SessionManager(this);

                final Korisnik loggedUser = sessionManager.retrieveUser();
                recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                listTour = new ArrayList<>();
                //creating recyclerview adapter
                final TourAdapter adapter = new TourAdapter(this, listTour);
                //setting adapter to recyclerview
                recyclerView.setAdapter(adapter);

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                JSONObject dataJSON = jsonResponse.getJSONObject("data");
                                boolean success = dataJSON.getBoolean("success");
                                Integer idUserType = loggedUser.getId_tip_korisnika();
                                if (success) {
                                    JSONArray tours = dataJSON.getJSONArray("rows");
                                    Tour tourInstance = new Tour();
                                    tourInstance.fetchTours(tours);
                                    adapter.updateToursList(tourInstance.toursList);
                                } else {
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

//                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//                fab.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(PocetnaStranica.this, AddTour.class);
//                        PocetnaStranica.this.startActivity(intent);
////                        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                                .setAction("Action", null).show();
//                    }
//                });

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                drawer.setDrawerListener(toggle);
                toggle.syncState();

                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(this);
                Menu menu =navigationView.getMenu();
                menu.getItem(0).setChecked(true);
                if(loggedUser.getId_tip_korisnika()==1){
                    MenuItem confirm = menu.findItem(R.id.nav_confirmRes);
                    MenuItem myTours = menu.findItem(R.id.nav_myTours);
                    confirm.setVisible(false);
                    myTours.setVisible(false);
                }
                if(loggedUser.getId_tip_korisnika()==2){
                    MenuItem reservation = menu.findItem(R.id.nav_reservation);
                    reservation.setVisible(false);
                }
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

                if(id == R.id.nav_home) {

                }else if (id == R.id.nav_profile) {

                }else if (id == R.id.nav_reservation) {

                }else if(id==R.id.nav_myTours){
                    Intent intent = new Intent(PocetnaStranica.this, MyTour.class);
                    PocetnaStranica.this.startActivity(intent);
                }else if (id == R.id.nav_confirmRes) {

                }else if (id == R.id.nav_about) {

                }else if (id == R.id.nav_logout) {
                    SessionManager logOut = new SessionManager(this);
                    logOut.logoutUser();
                    finish();
                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
    }

}
