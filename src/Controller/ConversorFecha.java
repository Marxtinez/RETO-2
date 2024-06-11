package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

public class ConversorFecha {
    public static Date convertToDate(String dateString) {
        // Define el formato de la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");

        // Parsear la cadena de fecha a LocalDate
        LocalDate localDate = LocalDate.parse(dateString, formatter);

        // Convertir LocalDate a java.sql.Date
        return Date.valueOf(localDate);
    }
}
