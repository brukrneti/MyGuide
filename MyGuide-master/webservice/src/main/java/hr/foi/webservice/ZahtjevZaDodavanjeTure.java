package hr.foi.webservice;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mateo on 8.12.2017..
 */

public class ZahtjevZaDodavanjeTure extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://yeloo.hr/AiR/MyGuideWebServices/Korisnik/register";
    private Map<String, String> params;

    public ZahtjevZaDodavanjeTure(String naziv, String opis, String cijena, String img_name, String img_path, Integer id_korisnik, Response.Listener<String> listener){
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener,null);
        params = new HashMap<>();
        params.put("naziv", naziv);
        params.put("opis",opis);
        params.put("cijena", cijena);
        params.put("img_name",img_name);
        params.put("img_path", img_path);
        params.put("id_korisnik", id_korisnik + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
