package hr.foi.myguide;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import hr.foi.webservice.ZahtjevZaRegistraciju;

public class Registracija extends AppCompatActivity {
    RadioGroup rg;
    RadioButton rb;
    Integer userType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);
        final EditText etFirstName = (EditText) findViewById(R.id.etFirstNameReg);
        final EditText etLastName = (EditText) findViewById(R.id.etLastNameReg);
        final EditText etUsername = (EditText) findViewById(R.id.etUsernameReg);
        final EditText etEmail = (EditText) findViewById(R.id.etEmailReg);
        final EditText etPassword = (EditText) findViewById(R.id.etPasswordReg) ;
        final Button btnRegister = (Button) findViewById(R.id.btnRegister);
        rg = (RadioGroup) findViewById(R.id.ergRadioGroup);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String firstName = etFirstName.getText().toString();
                final String lastName = etLastName.getText().toString();
                final String username = etUsername.getText().toString();
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();

                //final Integer userType = Integer.parseInt(etUserType.getText().toString());
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            //boolean success = jsonResponse.getBoolean("success");
                            JSONObject dataJSON = jsonResponse.getJSONObject("data");
                            boolean success = dataJSON.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(Registracija.this, Prijava.class);
                                Registracija.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Registracija.this);
                                builder.setMessage("Registracija neuspješna")
                                        .setNegativeButton("Ponovno", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                ZahtjevZaRegistraciju zahtjevZaRegistraciju = new ZahtjevZaRegistraciju(firstName, lastName, email, username, password,userType , responseListener);
                RequestQueue queue = Volley.newRequestQueue(Registracija.this);
                queue.add(zahtjevZaRegistraciju);
            }
        });

    }
    public void rbclick(View v){
        int radioButtonid = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radioButtonid);

        if(rb.getText().equals("Guide")){
            userType = 2;

        }
        if(rb.getText().equals("Tourist")){
            userType = 3;

        }
    }
}
