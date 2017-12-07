package hr.foi.database.entities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateo on 6.12.2017..
 */

public class Tour {
    public Integer id_tura;
    public String naziv;
    public String opis;
    public String img_name;
    public Integer img_path;
    public Integer id_korisnik;
    public Integer aktivan;

    public List<Tour> toursList = new ArrayList<Tour>();

    public Tour () {

    }
//    public Tour(Integer id_tura, String naziv, String opis, String img_name, String img_path, Integer id_korisnik, Integer aktivan) {
//        this.id_tura = id_tura;
//        this.naziv = naziv;
//        this.opis = opis;
//        this.img_name = img_name;
//        this.img_path = img_path;
//        this.id_korisnik = id_korisnik;
//        this.aktivan = aktivan;
//    }
    public Tour(String naziv, String opis, int img_path) {
        this.id_tura = id_tura;
        this.naziv = naziv;
        this.opis = opis;
        this.img_name = img_name;
        this.img_path = img_path;
        this.id_korisnik = id_korisnik;
        this.aktivan = aktivan;
    }

    public Integer getId_tura() {
        return id_tura;
    }

    public void setId_tura(Integer id_tura) {
        this.id_tura = id_tura;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getImg_name() {
        return img_name;
    }

    public void setImg_name(String img_name) {
        this.img_name = img_name;
    }

    public int getImg_path() {
        return img_path;
    }

    public void setImg_path(int img_path) {
        this.img_path = img_path;
    }

    public Integer getId_korisnik() {
        return id_korisnik;
    }

    public void setId_korisnik(Integer id_korisnik) {
        this.id_korisnik = id_korisnik;
    }

    public Integer getAktivan() {
        return aktivan;
    }

    public void setAktivan(Integer aktivan) {
        this.aktivan = aktivan;
    }

    public void fetchTours(JSONArray jsonData) {
        for (int i = 0; i < jsonData.length(); i++) {
            //JSONObject currentRow = tours.getJSONObject(i).getInt("id_tura");
            JSONObject currentTour = null;
            try {
                currentTour = jsonData.getJSONObject(i);
                Tour tourInstance = new Tour();
                tourInstance.id_korisnik = currentTour.getInt("id_tura");
                tourInstance.naziv = currentTour.getString("naziv");
                tourInstance.opis = currentTour.getString("opis");
                tourInstance.img_name = currentTour.getString("img_name");
                tourInstance.img_path = currentTour.getInt("img_path");
                tourInstance.id_korisnik = currentTour.getInt("id_korisnik");
                tourInstance.aktivan = currentTour.getInt("aktivan");

                toursList.add(tourInstance);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
