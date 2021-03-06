package hr.foi.webservice;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mateo on 8.12.2017..
 */

public class ZahtjevZaUredjivanjeTure extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://yeloo.hr/AiR/MyGuideWebServices/Tura/edit";
    private Map<String, String> params;

    public ZahtjevZaUredjivanjeTure(Integer id_tura, String naziv, String opis, Double cijena, String img_name, String img_path, Integer id_korisnik, Response.Listener<String> listener){
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener,null);
        params = new HashMap<>();
        params.put("id_tura", id_tura +"");
        params.put("naziv", naziv);
        params.put("opis",opis);
        params.put("cijena", cijena +"");
        params.put("img_name",img_name);
        params.put("img_path", img_path);
        params.put("id_korisnik", id_korisnik + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
