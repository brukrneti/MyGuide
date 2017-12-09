package hr.foi.myguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

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
import hr.foi.webservice.ZahtjevZaPromjeneTure;
import hr.foi.webservice.ZahtjevZaTuru;

public class EditTour extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Tour> listTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna_stranica);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
        Integer idUser = loggedUser.getId_korisnik();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONObject dataJSON = jsonResponse.getJSONObject("data");
                    boolean success = dataJSON.getBoolean("success");
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
        ZahtjevZaPromjeneTure zahtjevZaPromjeneTure = new ZahtjevZaPromjeneTure(idUser,responseListener);
        RequestQueue queue = Volley.newRequestQueue(EditTour.this);
        queue.add(zahtjevZaPromjeneTure);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditTour.this, AddTour.class);
                EditTour.this.startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            Intent intent = new Intent(EditTour.this, PocetnaStranica.class);
            EditTour.this.startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
