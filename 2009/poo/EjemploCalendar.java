import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Alejandro
 */
public class EjemploCalendar {

    public static void main(String[] args) throws Exception {
        // distintas maneras de obtener un objeto Calendar
        // con una fecha determinada, p.e. 14:00 23/03/2010

        // Opción 1: con set
        Calendar calendar1 = Calendar.getInstance();
        calendar1.clear();
        calendar1.set(2010, 03 - 1, 23, 14, 00); // mes is 0-based
        // Opción 2: con varios set
        Calendar calendar2 = Calendar.getInstance();
        calendar2.clear();
        calendar2.set(Calendar.YEAR, 2010);
        calendar2.set(Calendar.MONTH, 03 - 1);
        calendar2.set(Calendar.DATE, 23);
        calendar2.set(Calendar.HOUR_OF_DAY, 14);
        calendar2.set(Calendar.MINUTE, 00);
        // Opción 3: a partir de un String
        Calendar calendar3 = Calendar.getInstance();
        // el primer String es una expresión regular, así que
        // distintas opciones serían válidas
        calendar3.setTime(new SimpleDateFormat("yyyy.MM.dd HH:mm").parse("2010.03.23 14:00"));

        System.out.println("Opción 1 = " + calendar1);
        System.out.println("Opción 2 = " + calendar2);
        System.out.println("Opción 3 = " + calendar3);
        // Nota: la última opción es la única capaz de rellenar correctamente el resto de campos
    }
}
