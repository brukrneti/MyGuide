package hr.foi.myguide;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText teKorisnickoImeP = (EditText) findViewById(R.id.idKorimelogin);
        final EditText teLozinkaP= (EditText) findViewById(R.id.idLozinkalogin);

        final Button prijaviSe = (Button) findViewById(R.id.idPrijava);

        final TextView registracija = (TextView) findViewById(R.id.idRegistracija);

        registracija.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v)  {
            Intent registracijaIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            LoginActivity.this.startActivity(registracijaIntent);
            }
        });
        prijaviSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String korisnickoIme = teKorisnickoImeP.getText().toString();
                final String lozinka = teLozinkaP.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                String ime = jsonResponse.getString("ime");
                                String email = jsonResponse.getString("email");
                                Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
                                intent.putExtra("ime", ime);
                                intent.putExtra("korisnicko_ime", korisnickoIme);
                                intent.putExtra("email",email);
                                LoginActivity.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Prijava neuspješna")
                                        .setNegativeButton("Ponovno",null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ZahtjevZaPrijavu zahtjevZaPrijavu = new ZahtjevZaPrijavu(korisnickoIme,lozinka,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(zahtjevZaPrijavu);
            }
        });
    }
}
