package hr.foi.webservice;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mateo on 9.1.2018..
 */

public class ZahtjevZaUredenjeProfila extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://yeloo.hr/AiR/MyGuideWebServices/Tura/edit";
    private Map<String, String> params;

    public ZahtjevZaUredenjeProfila(Integer id_korisnik, String korisnicko_ime, String lozinka, String img_name, String img_path,Response.Listener<String> listener){
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener,null);
        params = new HashMap<>();
        params.put("id_korisnik", id_korisnik +"");
        params.put("korisnicko_ime", korisnicko_ime);
        params.put("lozinka",lozinka);
        params.put("img_name",img_name);
        params.put("img_path", img_path);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}