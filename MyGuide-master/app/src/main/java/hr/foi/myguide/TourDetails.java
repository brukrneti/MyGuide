package hr.foi.myguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import hr.foi.database.entities.Tour;
import hr.foi.myguide.Adapters.TourAdapter;

public class TourDetails extends AppCompatActivity {
    TextView textViewTitle, textViewShortDesc, textViewPrice, textViewTourId;
    ImageView imgView;
    private Tour tour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

//        Toast.makeText(this, tour.naziv + " " + tour.opis, Toast.LENGTH_SHORT).show();
//        Log.e("TAG", tour.getNaziv());
//        Log.e("TAG", tour.getOpis());


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
