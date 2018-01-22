package hr.foi.myguide;

import android.location.Address;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import hr.foi.database.entities.Korisnik;
import hr.foi.database.entities.Tour;
import hr.foi.webservice.ZahtjevZaDodavanjeTure;
import hr.foi.webservice.ZahtjevZaRezervaciju;

public class AddReservation extends AppCompatActivity {
    EditText etPerson;
    Button btnSubmit;
    private Tour tour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reservation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etPerson = (EditText) findViewById(R.id.etPerson) ;
        SessionManager sessionManager = new SessionManager(this);
        final Korisnik loggedUser = sessionManager.retrieveUser();
        tour = getIntent().getParcelableExtra("PARCELABLE_OBJEKT");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer person = Integer.parseInt(etPerson.getText().toString());
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            //boolean success = jsonResponse.getBoolean("success");
                            JSONObject dataJSON = jsonResponse.getJSONObject("data");
                            boolean success = dataJSON.getBoolean("success");
                            if (success) {
                                Toast.makeText(getApplicationContext(), "Reservation has been successfully submitted.",
                                        Toast.LENGTH_LONG).show();
                                etPerson.setText("");
                            } else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddReservation.this);
                                builder.setMessage("Error.")
                                        .setNegativeButton("Try again", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ZahtjevZaRezervaciju zahtjevZaRezervaciju = new ZahtjevZaRezervaciju(loggedUser.getId_korisnik(), tour.getId_tura(),person,responseListener);
                RequestQueue queue = Volley.newRequestQueue(AddReservation.this);
                queue.add(zahtjevZaRezervaciju);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
