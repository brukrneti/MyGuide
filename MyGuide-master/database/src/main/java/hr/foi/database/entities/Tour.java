package hr.foi.database.entities;

/**
 * Created by Mateo on 6.12.2017..
 */

public class Tour {
    public Integer id_tura;
    public String naziv;
    public String opis;
    public String img_name;
    public String img_path;
    public Integer id_korisnik;
    public Integer aktivan;

    public Tour(Integer id_tura, String naziv, String opis, String img_name, String img_path, Integer id_korisnik, Integer aktivan) {
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

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
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
}
