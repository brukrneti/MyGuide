package hr.foi.myguide;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText teIme = (EditText) findViewById(R.id.idIme);
        final EditText tePrezime = (EditText) findViewById(R.id.idPrezime);
        final EditText tekorisnickoIme = (EditText) findViewById(R.id.idKorime);
        final EditText teEmail = (EditText) findViewById(R.id.idEmail);
        final EditText teLozinka = (EditText) findViewById(R.id.idLozinka);
        final EditText teTipKorisnika = (EditText) findViewById(R.id.id_TipKorisnika);
        final Button registirajSe = (Button) findViewById(R.id.idRegistrirajSe);
        registirajSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ime = teIme.getText().toString();
                final String prezime = tePrezime.getText().toString();
                final String korisnickoIme = tekorisnickoIme.getText().toString();
                final String email = teEmail.getText().toString();
                final String lozinka = teLozinka.getText().toString();
                final Integer tipKorisnika = Integer.parseInt(teTipKorisnika.getText().toString());



                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registracija neuspješna")
                                        .setNegativeButton("Ponovno",null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                ZahtjevZaRegistraciju zahtjevZaRegistraciju = new ZahtjevZaRegistraciju(ime,prezime,email,korisnickoIme,lozinka,tipKorisnika,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(zahtjevZaRegistraciju);
            }
        });
    }
}
