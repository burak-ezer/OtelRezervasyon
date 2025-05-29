package Model;

import Helper.DBBaglanti;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Rezervasyon {
    private int id;
    private String musteri_tc;
    private int oda_no;
    private Date giris_tarihi;
    private Date cikis_tarihi;
    private int kalma_suresi;
    private Double toplam_ucret;

    public Rezervasyon(int id, String musteri_tc, int oda_no, Date giris_tarihi, Date cikis_tarihi, int kalma_suresi, Double toplam_ucret) {
        this.id = id;
        this.musteri_tc = musteri_tc;
        this.oda_no = oda_no;
        this.giris_tarihi = giris_tarihi;
        this.cikis_tarihi = cikis_tarihi;
        this.kalma_suresi = kalma_suresi;
        this.toplam_ucret = toplam_ucret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMusteri_tc() {
        return musteri_tc;
    }

    public void setMusteri_tc(String musteri_tc) {
        this.musteri_tc = musteri_tc;
    }

    public int getOda_no() {
        return oda_no;
    }

    public void setOda_no(int oda_no) {
        this.oda_no = oda_no;
    }

    public Date getGiris_tarihi() {
        return giris_tarihi;
    }

    public void setGiris_tarihi(Date giris_tarihi) {
        this.giris_tarihi = giris_tarihi;
    }

    public Date getCikis_tarihi() {
        return cikis_tarihi;
    }

    public void setCikis_tarihi(Date cikis_tarihi) {
        this.cikis_tarihi = cikis_tarihi;
    }

    public int getKalma_suresi() {
        return kalma_suresi;
    }

    public void setKalma_suresi(int kalma_suresi) {
        this.kalma_suresi = kalma_suresi;
    }

    public Double getToplam_ucret() {
        return toplam_ucret;
    }

    public void setToplam_ucret(Double toplam_ucret) {
        this.toplam_ucret = toplam_ucret;
    }

    public static ArrayList<Rezervasyon> rezervasyonList(){
        ArrayList<Rezervasyon> rezervasyonListe = new ArrayList<>();
        Rezervasyon rezervasyon;
        String query = "SELECT * FROM public.rezervasyon_tbl";
        try {
            Statement st = DBBaglanti.baglantiKur().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                int id = rs.getInt("id");
                String rezervasyon_musteri_tc = rs.getString("rezervasyon_musteri_tc");
                int rezervasyon_oda_no = rs.getInt("rezervasyon_oda_no");
                Date giris_tarihi = rs.getDate("giris_tarihi");
                Date cikis_tarihi = rs.getDate("cikis_tarihi");
                int kalma_suresi = rs.getInt("kalma_suresi");
                double toplam_ucret = rs.getDouble("toplam_ucret");
                rezervasyon = new Rezervasyon(id,rezervasyon_musteri_tc,rezervasyon_oda_no,giris_tarihi,cikis_tarihi,kalma_suresi,toplam_ucret);
                rezervasyonListe.add(rezervasyon);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rezervasyonListe;
    }

    public static void rezervasyonEkle(String rezervasyon_musteri_tc, int rezervasyon_oda_no, java.sql.Date giris_tarihi, java.sql.Date cikis_tarihi, int kalma_suresi, Double toplam_ucret){
        String query = "INSERT INTO public.rezervasyon_tbl(rezervasyon_musteri_tc, rezervasyon_oda_no, giris_tarihi, cikis_tarihi, kalma_suresi, toplam_ucret) VALUES (?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement pr = DBBaglanti.baglantiKur().prepareStatement(query);
            pr.setString(1,rezervasyon_musteri_tc);
            pr.setInt(2,rezervasyon_oda_no);
            pr.setDate(3,giris_tarihi);
            pr.setDate(4,cikis_tarihi);
            pr.setInt(5,kalma_suresi);
            pr.setDouble(6,toplam_ucret);
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void rezervasyonSil(int oda_no){
        String query = "DELETE FROM public.rezervasyon_tbl WHERE id = ?;";
        try {
            PreparedStatement pr = DBBaglanti.baglantiKur().prepareStatement(query);
            pr.setInt(1,oda_no);
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
