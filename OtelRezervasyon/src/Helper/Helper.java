package Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void uyariPenceresi(String uyariTip){
        switch (uyariTip){
            case "basariliEkleme":
                JOptionPane.showMessageDialog(null,"Ekleme başarılı.","Başarılı İşlem",JOptionPane.INFORMATION_MESSAGE);
                break;
            case "basariliSilme":
                JOptionPane.showMessageDialog(null,"Veri silindi.","Silme İşlemi",JOptionPane.WARNING_MESSAGE);
                break;
            case "bosKontrol":
                JOptionPane.showMessageDialog(null,"Tüm boşlukları doldurunuz!","Uyarı",JOptionPane.INFORMATION_MESSAGE);
                break;
            case "formatHatasi":
                JOptionPane.showMessageDialog(null,"Girilen format hatalı! Lütfen kontrol ediniz.","Uyarı",JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }
    public static int pencereKonum(String eksen, Dimension d){
        int point;
        switch (eksen){
            case "x":
                point = (Toolkit.getDefaultToolkit().getScreenSize().width-d.width)/2;
                break;
            case "y":
                point = (Toolkit.getDefaultToolkit().getScreenSize().height-d.height)/2;
                break;
            default:
                point = 0;
        }
        return point;
    }

    public static boolean bosKontrol(JTextField jTextField){
        if(jTextField.getText().length()==0){
            uyariPenceresi("bosKontrol");
            return false;
        }
        return true;
    }

}
