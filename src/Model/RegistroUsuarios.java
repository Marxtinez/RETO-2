package Model;

import java.util.HashMap;

public class RegistroUsuarios {
    public HashMap<String,String> tutores,profesores;
    public RegistroUsuarios() {
        tutores = new HashMap<>();
        tutores.put("John Doe","AAAA");
        tutores.put("Jane Smith","BBBB");
        tutores.put("Michael Johnson","CCCC");
        tutores.put("Emily Davis","DDDD");
        tutores.put("Daniel Garcia","EEEE");

        profesores = new HashMap<>();
        profesores.put("Maria Rodriguez","FFFF");
        profesores.put("Pablo Fernandez","GGGG");
        profesores.put("Laura Gomez","HHHH");
        profesores.put("Alejandro Breva","IIII");
        profesores.put("Andrea Perez","JJJJ");
    }
}
