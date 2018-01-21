package hr.foi.myguide;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import hr.foi.database.entities.Termin;
import hr.foi.database.entities.Tour;
import hr.foi.webservice.ZahtjevZaBrisanjeTure;
import hr.foi.webservice.ZahtjevZaTermin;
import hr.foi.webservice.ZahtjevZaTuru;

public class TourSchedule extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_tour_schedule);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tour schedule");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONObject dataJSON = jsonResponse.getJSONObject("data");
                    boolean success = dataJSON.getBoolean("success");
                    // Integer idUserType = loggedUser.getId_tip_korisnika();
                    if (success) {
                        JSONArray termin = dataJSON.getJSONArray("rows");
                        Termin terminInstance = new Termin();
                        terminInstance.fetchTermin(termin);

                        //terminInstance.terminList.get(0).getVrijeme_od();
                        //adapter.updateToursList(terminInstance.terminList);
                    } else {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        ZahtjevZaTermin zahtjevZaTermin = new ZahtjevZaTermin(1, responseListener);
        RequestQueue queue = Volley.newRequestQueue(TourSchedule.this);
        queue.add(zahtjevZaTermin);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //Integer id = item.getItemId();
        // idTour = tour.getId_tura();
//        if(id == R.id.delete){
//            Response.Listener<String> responseListener = new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    try {
//                        JSONObject jsonResponse = new JSONObject(response);
//                        JSONObject dataJSON = jsonResponse.getJSONObject("data");
//                        boolean success = dataJSON.getBoolean("success");
//                        if (success) {
//
//                            Toast.makeText(getApplicationContext(), "Tour has been successfully deleted.",
//                                    Toast.LENGTH_LONG).show();
//
//                            Intent intent = new Intent(TourDetails.this, MyTour.class);
//                            TourDetails.this.startActivity(intent);
//                            finish();
//
//                        } else {
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
//            ZahtjevZaBrisanjeTure zahtjevZaBrisanjeTure = new ZahtjevZaBrisanjeTure(idTour,responseListener);
//            RequestQueue queue = Volley.newRequestQueue(TourDetails.this);
//            queue.add(zahtjevZaBrisanjeTure);
//        }
//        if(id == R.id.edit){
//            Intent intent = new Intent(this, EditTour.class);
//            intent.putExtra("PARCELABLE_OBJEKT", tour);
//            startActivity(intent);
//        }
        /*else*/ if(item.getItemId()== android.R.id.home){
            //Ako je kliknuto na back button, zatvara se trenutna aktivnost i otvara se ona s koje smo došli na ovu
            //Moguće je ovaj princip jer smo na ovu aktivnost došli sa startActivity metodom
            finish();
        }

        return super.onOptionsItemSelected(item);

    }

}
