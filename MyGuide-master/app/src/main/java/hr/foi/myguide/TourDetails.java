package hr.foi.myguide;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import hr.foi.database.entities.Tour;
import hr.foi.myguide.Adapters.TourAdapter;
import hr.foi.webservice.ZahtjevZaBrisanjeTure;
import hr.foi.webservice.ZahtjevZaPromjeneTure;

public class TourDetails extends AppCompatActivity {


    TextView textViewTitle, textViewShortDesc, textViewPrice, textViewTourId;
    ImageView imgView;
    private Tour tour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_tour_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        tour = getIntent().getParcelableExtra("PARCELABLE_OBJEKT");
        textViewTourId  = (TextView) findViewById(R.id.textViewTourId);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        textViewShortDesc = (TextView) findViewById(R.id.textViewShortDesc);
        textViewPrice = (TextView)  findViewById(R.id.textViewPrice);
        imgView = (ImageView)  findViewById(R.id.imageView);

        textViewTourId.setText(tour.getId_tura().toString());
        textViewTitle.setText(tour.getNaziv());
        textViewShortDesc.setText(tour.getOpis());
        textViewPrice.setText(tour.getCijena().toString());
        Picasso.with(this)
                .load(tour.getImg_path())
                .into(imgView);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Integer id = item.getItemId();
        Integer idTour = tour.getId_tura();
        if(id == R.id.delete){
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONObject dataJSON = jsonResponse.getJSONObject("data");
                        boolean success = dataJSON.getBoolean("success");
                        if (success) {
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Tour has been successfully deleted.",
                                    Toast.LENGTH_LONG).show();
                        } else {
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            ZahtjevZaBrisanjeTure zahtjevZaBrisanjeTure = new ZahtjevZaBrisanjeTure(idTour,responseListener);
            RequestQueue queue = Volley.newRequestQueue(TourDetails.this);
            queue.add(zahtjevZaBrisanjeTure);
        }

        return super.onOptionsItemSelected(item);

    }


}
