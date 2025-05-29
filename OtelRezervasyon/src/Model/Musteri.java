package Model;

import Helper.DBBaglanti;
import java.sql.*;
import java.util.ArrayList;

public class Musteri {
    private int id;
    private String musteri_adi;
    private String musteri_soyadi;
    private String musteri_tc;
    private int musteri_dogumyili;
    private String musteri_tel;
    private String musteri_adres;

    public Musteri(int id,String musteri_adi, String musteri_soyadi, String musteri_tc, int musteri_dogumyili, String musteri_tel, String musteri_adres) {
        this.id = id;
        this.musteri_adi = musteri_adi;
        this.musteri_soyadi = musteri_soyadi;
        this.musteri_tc = musteri_tc;
        this.musteri_dogumyili = musteri_dogumyili;
        this.musteri_tel = musteri_tel;
        this.musteri_adres = musteri_adres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMusteri_adi() {
        return musteri_adi;
    }

    public void setMusteri_adi(String musteri_adi) {
        this.musteri_adi = musteri_adi;
    }

    public String getMusteri_soyadi() {
        return musteri_soyadi;
    }

    public void setMusteri_soyadi(String musteri_soyadi) {
        this.musteri_soyadi = musteri_soyadi;
    }

    public String getMusteri_tc() {
        return musteri_tc;
    }

    public void setMusteri_tc(String musteri_tc) {
        this.musteri_tc = musteri_tc;
    }

    public int getMusteri_dogumyili() {
        return musteri_dogumyili;
    }

    public void setMusteri_dogumyili(int musteri_dogumyili) {
        this.musteri_dogumyili = musteri_dogumyili;
    }

    public String getMusteri_tel() {
        return musteri_tel;
    }

    public void setMusteri_tel(String musteri_tel) {
        this.musteri_tel = musteri_tel;
    }

    public String getMusteri_adres() {
        return musteri_adres;
    }

    public void setMusteri_adres(String musteri_adres) {
        this.musteri_adres = musteri_adres;
    }

    public static ArrayList<Musteri> musteriList(){
        ArrayList<Musteri> musteriListesi = new ArrayList<>();
        Musteri musteri;
        String query = "SELECT * FROM public.musteri_tbl";
        try {
            Statement st = DBBaglanti.baglantiKur().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                int id = rs.getInt("id");
                String musteri_adi = rs.getString("musteri_adi");
                String musteri_soyadi = rs.getString("musteri_soyadi");
                String musteri_tc = rs.getString("musteri_tc");
                int musteri_dogumyili = rs.getInt("musteri_dogumyili");
                String musteri_tel = rs.getString("musteri_tel");
                String musteri_adres = rs.getString("musteri_adres");
                musteri = new Musteri(id,musteri_adi,musteri_soyadi,musteri_tc,musteri_dogumyili,musteri_tel,musteri_adres);
                musteriListesi.add(musteri);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return musteriListesi;
    }

    public static ArrayList<Musteri> musteriList(String query){
        ArrayList<Musteri> musteriListesi = new ArrayList<>();
        Musteri musteri;
        try {
            Statement st = DBBaglanti.baglantiKur().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                int id = rs.getInt("id");
                String musteri_adi = rs.getString("musteri_adi");
                String musteri_soyadi = rs.getString("musteri_soyadi");
                String musteri_tc = rs.getString("musteri_tc");
                int musteri_dogumyili = rs.getInt("musteri_dogumyili");
                String musteri_tel = rs.getString("musteri_tel");
                String musteri_adres = rs.getString("musteri_adres");
                musteri = new Musteri(id,musteri_adi,musteri_soyadi,musteri_tc,musteri_dogumyili,musteri_tel,musteri_adres);
                musteriListesi.add(musteri);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return musteriListesi;
    }

    public static void addMusteri(String musteri_adi,String musteri_soyadi,String musteri_tc,int musteri_dogumyili,String musteri_tel,String musteri_adres){
        String query = "INSERT INTO public.musteri_tbl(musteri_adi, musteri_soyadi, musteri_tc, musteri_dogumyili, musteri_tel, musteri_adres) VALUES (?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = DBBaglanti.baglantiKur().prepareStatement(query);
            preparedStatement.setString(1,musteri_adi);
            preparedStatement.setString(2,musteri_soyadi);
            preparedStatement.setString(3,musteri_tc);
            preparedStatement.setInt(4,musteri_dogumyili);
            preparedStatement.setString(5,musteri_tel);
            preparedStatement.setString(6,musteri_adres);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteMusteri(int id){
        String query = "DELETE FROM public.musteri_tbl WHERE id = ?;";
        try {
            PreparedStatement preparedStatement = DBBaglanti.baglantiKur().prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String aramaMusteri(String musteri_adi,String musteri_soyadi,String musteri_tc){
        String query = "SELECT * FROM musteri_tbl WHERE musteri_adi LIKE '%{{musteri_adi}}%' OR musteri_soyadi LIKE '%{{musteri_soyadi}}%'";
        query = query.replace("{{musteri_adi}}",musteri_adi);
        query = query.replace("{{musteri_soyadi}}",musteri_soyadi);
        if (!musteri_tc.isEmpty()){
            query += "OR musteri_tc LIKE '%{{musteri_tc}}%'";
            query = query.replace("{{musteri_tc}}",musteri_tc);
        }
        return query;
    }
}
