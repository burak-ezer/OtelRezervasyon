package Model;

import Helper.DBBaglanti;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Oda {
    private int oda_no;
    private String oda_tipi;
    private int oda_m2;
    private boolean klima;
    private boolean minibar;
    private boolean tv;
    private double oda_fiyat;

    public Oda(int oda_no, String oda_tipi, int oda_m2, boolean klima, boolean minibar, boolean tv,double oda_fiyat) {
        this.oda_no = oda_no;
        this.oda_tipi = oda_tipi;
        this.oda_m2 = oda_m2;
        this.klima = klima;
        this.minibar = minibar;
        this.tv = tv;
        this.oda_fiyat = oda_fiyat;
    }

    public int getOda_no() {
        return oda_no;
    }

    public void setOda_no(int oda_no) {
        this.oda_no = oda_no;
    }

    public String getOda_tipi() {
        return oda_tipi;
    }

    public void setOda_tipi(String oda_tipi) {
        this.oda_tipi = oda_tipi;
    }

    public boolean isKlima() {
        return klima;
    }

    public void setKlima(boolean klima) {
        this.klima = klima;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public int getOda_m2() {
        return oda_m2;
    }

    public void setOda_m2(int oda_m2) {
        this.oda_m2 = oda_m2;
    }

    public double getOda_fiyat() {
        return oda_fiyat;
    }

    public void setOda_fiyat(double oda_fiyat) {
        this.oda_fiyat = oda_fiyat;
    }

    public static ArrayList<Oda> odaList(){
        ArrayList<Oda> odaListesi = new ArrayList<>();
        Oda oda;
        String query= "SELECT * FROM public.oda_tbl";
        try {
            Statement statement = DBBaglanti.baglantiKur().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                int oda_no = rs.getInt("oda_no");
                String oda_tipi = rs.getString("oda_tipi");
                int oda_m2 = rs.getInt("oda_m2");
                Boolean klima = rs.getBoolean("klima");
                Boolean minibar = rs.getBoolean("minibar");
                Boolean tv = rs.getBoolean("tv");
                double oda_fiyat = rs.getDouble("oda_fiyat");
                oda = new Oda(oda_no,oda_tipi,oda_m2,klima,minibar,tv,oda_fiyat);
                odaListesi.add(oda);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return odaListesi;
    }

    public static void odaEkle(int oda_no,String oda_tipi,int oda_m2,boolean klima,boolean minibar,boolean tv,double oda_fiyat){
        String query = "INSERT INTO public.oda_tbl(oda_no, oda_tipi, oda_m2, klima, minibar, tv, oda_fiyat) VALUES (?, ?, ?, ?, ?, ?,?)";
        try {
            PreparedStatement pr = DBBaglanti.baglantiKur().prepareStatement(query);
            pr.setInt(1,oda_no);
            pr.setString(2,oda_tipi);
            pr.setInt(3,oda_m2);
            pr.setBoolean(4,klima);
            pr.setBoolean(5,minibar);
            pr.setBoolean(6,tv);
            pr.setDouble(7,oda_fiyat);
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void odaSil(int id){
        String query = "DELETE FROM public.oda_tbl WHERE oda_no = ?;";
        try {
            PreparedStatement pr = DBBaglanti.baglantiKur().prepareStatement(query);
            pr.setInt(1,id);
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
