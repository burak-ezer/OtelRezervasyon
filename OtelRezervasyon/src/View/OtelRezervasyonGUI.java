package View;

import Helper.Config;
import Helper.Helper;
import Model.Musteri;
import Model.Oda;
import Model.Rezervasyon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;

public class OtelRezervasyonGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane main_tabs;
    private JPanel main_pnl;
    private JPanel musteri_pnl;
    private JScrollPane musteri_scroll;
    private JTable musteri_tbl;
    private JPanel musteri_tbl_pnl;
    private JTextField musteriAdi_fld;
    private JTextField musteriSoyadi_fld;
    private JTextField musteriTc_fld;
    private JTextField musteriDogumyili_fld;
    private JTextField musteriTel_fld;
    private JTextField musteriAdres_fld;
    private JButton musteriEkle_btn;
    private JTextField musteriSil_fld;
    private JButton musteriSil_btn;
    private JPanel muteriIslem_pnl;
    private JTextField musteriAra_fld;
    private JButton musteriAra_btn;
    private JPanel oda_pnl;
    private JTable oda_tbl;
    private JPanel oda_tbl_pnl;
    private JScrollPane oda_scroll;
    private JPanel odaIslem_pnl;
    private JTextField odaNo_fld;
    private JComboBox odaTip_cmb;
    private JCheckBox klima_cbx;
    private JCheckBox minibar_cbx;
    private JCheckBox tv_cbx;
    private JRadioButton m2_15;
    private JRadioButton m2_20;
    private JRadioButton m2_25;
    private JButton odaEkle_btn;
    private JTextField odaFiyat_fld;
    private JTextField odaSil_fld;
    private JButton odaSil_btn;
    private JPanel rezervasyon_pnl;
    private JTable rezervasyon_tbl;
    private JScrollPane rezervasyon_scroll;
    private JPanel rezervasyonIslem_pnl;
    private JComboBox musteri_cbx;
    private JComboBox oda_cbx;
    private JTextField girisTarihi_fld;
    private JTextField cikisTarihi_fld;
    private JTextField kalmaSuresi_fld;
    private JTextField toplamUcret_fld;
    private JButton rezervasyonEkle_btn;
    private JTextField rezervasyonSil_fld;
    private JButton rezervasyonSil_btn;
    private DefaultTableModel musteri_mdl;
    private Object[] row_musteri;
    private DefaultTableModel oda_mdl;
    private Object[] row_oda;
    private DefaultTableModel rezervasyon_mdl;
    private Object[] row_rezervasyon;


    public OtelRezervasyonGUI(){
        //Frame ayarlar
        add(wrapper);
        setSize(900,500);
        setLocation(Helper.pencereKonum("x",getSize()),Helper.pencereKonum("y",getSize()));
        setVisible(true);
        setTitle(Config.PROGRAM_BASLIGI);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Müşteri Model
        musteri_mdl = new DefaultTableModel();
        Object[] col_musteri = {"ID","İsim","Soyisim","TC","Doğum Yılı","Telefon","Adres"};
        musteri_mdl.setColumnIdentifiers(col_musteri);
        musteri_tbl.setModel(musteri_mdl);
        row_musteri = new Object[col_musteri.length];
        musteri_tbl.getTableHeader().setReorderingAllowed(false);
        musteriListele();
        musteriEkle_cbx();

        //Oda Model
        oda_mdl = new DefaultTableModel();
        Object [] col_oda = {"No","Tip","M2","Klima","Minibar","TV","Fiyat"};
        oda_mdl.setColumnIdentifiers(col_oda);
        row_oda = new Object[col_oda.length];
        oda_tbl.getTableHeader().setReorderingAllowed(false);
        oda_tbl.setModel(oda_mdl);
        odaListele();
        placeHolder();
        odaEkle_cbx();

        //Rezervasyon Model
        rezervasyon_mdl = new DefaultTableModel();
        Object[] col_rezervasyon = {"ID","Müşteri TC","Oda No","Giriş Tarihi","Çıkış Tarihi","Kalma Süresi","Toplam Ücret"};
        row_rezervasyon = new Object[col_rezervasyon.length];
        rezervasyon_mdl.setColumnIdentifiers(col_rezervasyon);
        rezervasyon_tbl.setModel(rezervasyon_mdl);
        rezervasyonListele();


        musteriEkle_btn.addActionListener(e -> {
            if(Helper.bosKontrol(musteriAdi_fld)&&Helper.bosKontrol(musteriSoyadi_fld)&&Helper.bosKontrol(musteriDogumyili_fld)&&Helper.bosKontrol(musteriAdres_fld)&&Helper.bosKontrol(musteriTel_fld)&&Helper.bosKontrol(musteriTc_fld)){
                try{
                    Musteri.addMusteri(musteriAdi_fld.getText(),musteriSoyadi_fld.getText(),musteriTc_fld.getText(),Integer.valueOf(musteriDogumyili_fld.getText()),musteriTel_fld.getText(),musteriAdres_fld.getText());
                    musteriListele();
                    placeHolder();
                    Helper.uyariPenceresi("basariliEkleme");
                    musteriEkle_cbx();
                }catch (NumberFormatException numberFormatException){
                    Helper.uyariPenceresi("formatHatasi");
                    placeHolder();
                }

            }
        });

        musteriSil_btn.addActionListener(e -> {
            try {
                Musteri.deleteMusteri(Integer.valueOf(musteriSil_fld.getText()));
                Helper.uyariPenceresi("basariliSilme");
                placeHolder();
                musteriListele();
            }catch (NumberFormatException numberFormatException){
                Helper.uyariPenceresi("formatHatasi");
                placeHolder();
            }
        });

        musteriAra_btn.addActionListener(e -> {
            String query = Musteri.aramaMusteri(musteriAra_fld.getText(),musteriAra_fld.getText(),musteriAra_fld.getText());
            musteriListele(query);
        });
        m2_15.setSelected(true);
        odaEkle_btn.addActionListener(e -> {
            ButtonGroup odaOzellik_btng = new ButtonGroup();
            odaOzellik_btng.add(m2_15);
            odaOzellik_btng.add(m2_20);
            odaOzellik_btng.add(m2_25);
            int m2=15;
            if(m2_15.isSelected()){
                m2 = 15;
            }
            if(m2_20.isSelected()){
                m2 = 20;
            }
            if(m2_25.isSelected()){
                m2 = 25;
            }
            try{
                Oda.odaEkle(Integer.valueOf(odaNo_fld.getText()),odaTip_cmb.getSelectedItem().toString(),m2,klima_cbx.isSelected(),minibar_cbx.isSelected(),tv_cbx.isSelected(),Double.valueOf(odaFiyat_fld.getText()));
                Helper.uyariPenceresi("basariliEkleme");
                placeHolder();
                odaListele();
                odaEkle_cbx();
            }catch (NumberFormatException numberFormatException){
                Helper.uyariPenceresi("formatHatasi");
                placeHolder();
            }
        });
        musteriAdi_fld.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                    musteriAdi_fld.setText("");
            }
        });
        musteriSoyadi_fld.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                musteriSoyadi_fld.setText("");
            }
        });
        musteriTc_fld.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                musteriTc_fld.setText("");
            }
        });
        musteriAdres_fld.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                musteriAdres_fld.setText("");
            }
        });
        musteriDogumyili_fld.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                musteriDogumyili_fld.setText("");
            }
        });
        musteriTel_fld.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                musteriTel_fld.setText("");
            }
        });
        musteriAra_fld.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                musteriAra_fld.setText("");
            }
        });
        musteriSil_fld.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                musteriSil_fld.setText("");
            }
        });

        odaNo_fld.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                odaNo_fld.setText("");
            }
        });
        odaFiyat_fld.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                odaFiyat_fld.setText("");
            }
        });
        odaSil_fld.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                odaSil_fld.setText("");
            }
        });
        odaSil_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Oda.odaSil(Integer.valueOf(odaSil_fld.getText()));
                    Helper.uyariPenceresi("basariliSilme");
                    placeHolder();
                    odaListele();
                }catch (NumberFormatException numberFormatException){
                    Helper.uyariPenceresi("formatHatasi");
                    placeHolder();
                }
            }
        });
        rezervasyonEkle_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Rezervasyon.rezervasyonEkle(musteri_cbx.getSelectedItem().toString(),Integer.valueOf(oda_cbx.getSelectedItem().toString()), Date.valueOf(girisTarihi_fld.getText()),Date.valueOf(cikisTarihi_fld.getText()),Integer.valueOf(kalmaSuresi_fld.getText()),Double.valueOf(toplamUcret_fld.getText()));
                    rezervasyonListele();
                    Helper.uyariPenceresi("basariliEkleme");
                    placeHolder();
                }catch (Exception exception){
                    Helper.uyariPenceresi("formatHatasi");
                    placeHolder();
                }
            }
        });

        girisTarihi_fld.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                girisTarihi_fld.setText("");
            }
        });

        cikisTarihi_fld.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                cikisTarihi_fld.setText("");
            }
        });
        kalmaSuresi_fld.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                kalmaSuresi_fld.setText("");
            }
        });
        toplamUcret_fld.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                toplamUcret_fld.setText("");
            }
        });
        rezervasyonSil_fld.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                rezervasyonSil_fld.setText("");
            }
        });
        rezervasyonSil_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Rezervasyon.rezervasyonSil(Integer.valueOf(rezervasyonSil_fld.getText()));
                    rezervasyonListele();
                    Helper.uyariPenceresi("basariliSilme");
                    placeHolder();
                }catch (NumberFormatException exception){
                    Helper.uyariPenceresi("formatHatasi");
                    placeHolder();
                }
            }
        });
        rezervasyonSil_fld.addFocusListener(new FocusAdapter() {
        });

    }

    public void musteriEkle_cbx(){
        musteri_cbx.removeAllItems();
        for (Musteri musteri:Musteri.musteriList()){
            musteri_cbx.addItem(musteri.getMusteri_tc().toString());
        }
    }
    public void odaEkle_cbx(){
        oda_cbx.removeAllItems();
        for (Oda oda:Oda.odaList()){
            oda_cbx.addItem(String.valueOf(oda.getOda_no()));
        }
    }
    public void musteriListele(){
        DefaultTableModel temizle = (DefaultTableModel) musteri_tbl.getModel();
        temizle.setRowCount(0);
        for(Musteri musteri:Musteri.musteriList()){
            int i = 0;
            row_musteri[i++] = musteri.getId();
            row_musteri[i++] = musteri.getMusteri_adi();
            row_musteri[i++] = musteri.getMusteri_soyadi();
            row_musteri[i++] = musteri.getMusteri_tc();
            row_musteri[i++] = musteri.getMusteri_dogumyili();
            row_musteri[i++] = musteri.getMusteri_tel();
            row_musteri[i++] = musteri.getMusteri_adres();
            musteri_mdl.addRow(row_musteri);
        }
    }
    public void musteriListele(String query){
        DefaultTableModel temizle = (DefaultTableModel) musteri_tbl.getModel();
        temizle.setRowCount(0);
        for(Musteri musteri:Musteri.musteriList(query)){
            int i = 0;
            row_musteri[i++] = musteri.getId();
            row_musteri[i++] = musteri.getMusteri_adi();
            row_musteri[i++] = musteri.getMusteri_soyadi();
            row_musteri[i++] = musteri.getMusteri_tc();
            row_musteri[i++] = musteri.getMusteri_dogumyili();
            row_musteri[i++] = musteri.getMusteri_tel();
            row_musteri[i++] = musteri.getMusteri_adres();
            musteri_mdl.addRow(row_musteri);
        }
    }

    public void odaListele(){
        DefaultTableModel temizle = (DefaultTableModel) oda_tbl.getModel();
        temizle.setRowCount(0);
        for(Oda oda:Oda.odaList()){
            int i = 0;
            row_oda[i++] = oda.getOda_no();
            row_oda[i++] = oda.getOda_tipi();
            row_oda[i++] = oda.getOda_m2();
            row_oda[i++] = oda.isKlima();
            row_oda[i++] = oda.isMinibar();
            row_oda[i++] = oda.isTv();
            row_oda[i++] = oda.getOda_fiyat();
            oda_mdl.addRow(row_oda);
        }
    }

    public void rezervasyonListele(){
        DefaultTableModel temizle = (DefaultTableModel) rezervasyon_tbl.getModel();
        temizle.setRowCount(0);
        for (Rezervasyon rezervasyon:Rezervasyon.rezervasyonList()){
            int i = 0;
            row_rezervasyon[i++] = rezervasyon.getId();
            row_rezervasyon[i++] = rezervasyon.getMusteri_tc();
            row_rezervasyon[i++] = rezervasyon.getOda_no();
            row_rezervasyon[i++] = rezervasyon.getGiris_tarihi();
            row_rezervasyon[i++] = rezervasyon.getCikis_tarihi();
            row_rezervasyon[i++] = rezervasyon.getKalma_suresi();
            row_rezervasyon[i++] = rezervasyon.getToplam_ucret();
            rezervasyon_mdl.addRow(row_rezervasyon);
        }
    }

    public void placeHolder(){
        musteriAdi_fld.setText("İsim");
        musteriSoyadi_fld.setText("Soyisim");
        musteriTc_fld.setText("TC");
        musteriAdres_fld.setText("Adres");
        musteriTel_fld.setText("Telefon");
        musteriDogumyili_fld.setText("Doğum Yılı");
        musteriSil_fld.setText("Silinecek Müşteri ID");
        musteriAra_fld.setText("İsim, Soyisim, TC Ara");
        odaNo_fld.setText("Oda No");
        odaFiyat_fld.setText("Fiyat - 100.00");
        odaSil_fld.setText("Silinecek Oda No");
        girisTarihi_fld.setText("Giriş Tarihi : 2000-01-01");
        cikisTarihi_fld.setText("Çıkış Tarihi : 2000-01-01");
        kalmaSuresi_fld.setText("Kalma Süresi");
        toplamUcret_fld.setText("Toplam Ücret");
        rezervasyonSil_fld.setText("Silinecek Rezervasyon ID");
    }




    public static void main(String[] args) {
        OtelRezervasyonGUI otelRezervasyonGUI = new OtelRezervasyonGUI();
    }
}
