package hr.foi.myguide;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.Request;
import com.android.volley.Response;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mateo on 26.10.2017..
 */

public class ZahtjevZaRegistraciju extends StringRequest{
    private static final String REGISTER_REQUEST_URL = "http://192.168.56.1/Registracija/registracija.php";
    private Map<String, String> params;

    public ZahtjevZaRegistraciju(String ime, String prezime, String email, String korisnicko_ime, String lozinka, Integer tip_korisika, Response.Listener<String> listener){
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener,null);
        params = new HashMap<>();
        params.put("ime", ime);
        params.put("prezime",prezime);
        params.put("email", email);
        params.put("korisnicko_ime",korisnicko_ime);
        params.put("lozinka", lozinka);
        params.put("tip_korisnika", tip_korisika + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
