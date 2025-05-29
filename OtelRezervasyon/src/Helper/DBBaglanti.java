package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBBaglanti {
    Connection connection = null;
    public Connection baglanti(){
        try {
            connection = DriverManager.getConnection(Config.DB_URL,Config.DB_AD,Config.DB_SIFRE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    public static Connection baglantiKur(){
        DBBaglanti dbBaglanti = new DBBaglanti();
        return dbBaglanti.baglanti();
    }
}
