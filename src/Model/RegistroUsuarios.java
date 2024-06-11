package Model;

import java.util.HashMap;

public class RegistroUsuarios {
    public HashMap<String,String> tutores;
    public RegistroUsuarios() {
        tutores = new HashMap<>();
        tutores.put("John Doe","AAAA");
        tutores.put("Jane Smith","BBBB");
        tutores.put("Michael Johnson","CCCC");
        tutores.put("Emily Davis","DDDD");
        tutores.put("Daniel Garcia","EEEE");
    }
}
