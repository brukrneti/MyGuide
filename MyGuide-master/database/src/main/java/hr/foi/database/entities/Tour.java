package hr.foi.database.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateo on 6.12.2017..
 */


public class Tour implements Parcelable{
    public Integer id_tura;
    public String naziv;
    public String opis;
    public Double cijena;
    public String img_name;
    public String img_path;
    public Integer id_korisnik;
    public Integer aktivan;

    public List<Tour> toursList = new ArrayList<Tour>();

    public Tour () {

    }

    public Tour(String naziv, String opis,Double cijena, String img_name, String img_path, Integer id_korisnik) {
        //this.id_tura = id_tura;
        this.naziv = naziv;
        this.opis = opis;
        this.cijena = cijena;
        this.img_name = img_name;
        this.img_path = img_path;
        this.id_korisnik = id_korisnik;
        //this.aktivan = aktivan;
    }

    protected Tour(Parcel in) {
        id_tura = in.readInt();
        naziv = in.readString();
        opis = in.readString();
        cijena = in.readDouble();
        img_name = in.readString();
        img_path = in.readString();
        id_korisnik = in.readInt();
        aktivan = in.readInt();
        toursList = in.createTypedArrayList(Tour.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_tura);
        dest.writeString(naziv);
        dest.writeString(opis);
        dest.writeDouble(cijena);
        dest.writeString(img_name);
        dest.writeString(img_path);
        dest.writeInt(id_korisnik);
        dest.writeInt(aktivan);
        dest.writeTypedList(toursList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Tour> CREATOR = new Creator<Tour>() {
        @Override
        public Tour createFromParcel(Parcel in) {
            return new Tour(in);
        }

        @Override
        public Tour[] newArray(int size) {
            return new Tour[size];
        }
    };

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

    public Double getCijena() { return cijena; }

    public void setCijena(Double cijena) { this.cijena = cijena; }

    public String getImg_name() {
        return img_name;
    }

    public void setImg_name(String img_name) {
        this.img_name = img_name;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String  img_path) {
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
                tourInstance.id_tura = currentTour.getInt("id_tura");
                tourInstance.naziv = currentTour.getString("naziv");
                tourInstance.opis = currentTour.getString("opis");
                tourInstance.cijena = currentTour.getDouble("cijena");
                tourInstance.img_name = currentTour.getString("img_name");
                tourInstance.img_path = currentTour.getString("img_path");
                tourInstance.id_korisnik = currentTour.getInt("id_korisnik");
                tourInstance.aktivan = currentTour.getInt("aktivan");

                toursList.add(tourInstance);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
