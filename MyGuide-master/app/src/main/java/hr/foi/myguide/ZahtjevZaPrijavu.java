package hr.foi.myguide;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mateo on 28.10.2017..
 */

public class ZahtjevZaPrijavu extends StringRequest{
    private static final String LOGIN_REQUEST_URL = "http://192.168.56.1/Registracija/prijava.php";
    private Map<String, String> params;

    public ZahtjevZaPrijavu(String korisnicko_ime, String lozinka, Response.Listener<String> listener){
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener,null);
        params = new HashMap<>();
        params.put("korisnicko_ime",korisnicko_ime);
        params.put("lozinka", lozinka);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
