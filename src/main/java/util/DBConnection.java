package util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Datele de conectare se citesc din fisierul db.properties
 * (din directorul proiectului sau din classpath).
 * Vezi db.properties.example.
 */
public class DBConnection {
    private static final String CONFIG_FILE="db.properties";
    private static final Properties config=incarcaConfig();

    private static Properties incarcaConfig(){
        Properties p=new Properties();
        try(InputStream in=deschideConfig()){
            if(in==null){
                throw new IllegalStateException(
                        "Lipseste fisierul "+CONFIG_FILE+". Copiaza db.properties.example in "+CONFIG_FILE+" si completeaza datele.");
            }
            p.load(in);
        }catch(IOException e){
            throw new IllegalStateException("Nu se poate citi "+CONFIG_FILE, e);
        }
        return p;
    }
    private static InputStream deschideConfig() throws IOException{
        File f=new File(CONFIG_FILE);
        if(f.exists()){
            return new FileInputStream(f);
        }
        return DBConnection.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
    }
    public static Connection getConnection()
            throws SQLException{
        return DriverManager.getConnection(
                config.getProperty("db.url"),
                config.getProperty("db.user"),
                config.getProperty("db.password"));
    }
}
