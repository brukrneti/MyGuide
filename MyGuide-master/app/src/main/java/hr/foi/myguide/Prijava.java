package hr.foi.myguide;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import hr.foi.webservice.ZahtjevZaPrijavu;

public class Prijava extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prijava);

        final EditText etUsername = (EditText) findViewById(R.id.etUsernameLogin);
        final EditText etPassword= (EditText) findViewById(R.id.etPasswordLogin);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final TextView tvSignUp = (TextView) findViewById(R.id.tvSignUp);

        tvSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)  {
                Intent registracijaIntent = new Intent(Prijava.this, Registracija.class);
                Prijava.this.startActivity(registracijaIntent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            //String result = jsonResponse.getString("data");
                            //boolean success = result.contains("success");
                            JSONObject dataJSON = jsonResponse.getJSONObject("data");
                            boolean success = dataJSON.getBoolean("success");
                            if(success){
                                //String name = jsonResponse.getString("ime");
                                //String email = jsonResponse.getString("email");
                                Intent intent = new Intent(Prijava.this, PocetnaStranica.class);
                               // intent.putExtra("ime", name);
                                intent.putExtra("korisnicko_ime", username);
                                //intent.putExtra("email",email);
                                Prijava.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Prijava.this);
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
                ZahtjevZaPrijavu zahtjevZaPrijavu = new ZahtjevZaPrijavu(username,password,responseListener);
                RequestQueue queue = Volley.newRequestQueue(Prijava.this);
                queue.add(zahtjevZaPrijavu);
            }
        });
    }
}