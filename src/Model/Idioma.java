package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Idioma extends Properties {
    public static final int spanish = 0;
    public static final int english = 1;
    public Idioma(int lang){
        switch(lang){
            case spanish:
                getProperties("./src/Model/espanol.properties");
                break;
            case english:
                getProperties("./src/Model/ingles.properties");
                break;
            default:
                getProperties("./src/Model/espanol.properties");
        }

    }

    private void getProperties(String langFile) {
        try {
            this.load( new FileInputStream(new File(langFile)));
        } catch (IOException ex) {

        }
    }
}