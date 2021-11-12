import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Alejandro
 */
public class EjemploOrdenar {

    public static void main(String[] args) {
        ArrayList<Simple> lista = new ArrayList<Simple>();
        lista.add(new Simple("a", "b"));
        lista.add(new Simple("b", "a"));
        lista.add(new Simple("c", "c"));
        Collections.sort(lista);
        System.out.println(lista);
        Collections.sort(lista, Collections.reverseOrder());
        System.out.println(lista);
        Collections.sort(lista, new ComparadorDeSimple());
        System.out.println(lista);
        Collections.sort(lista, Collections.reverseOrder(new ComparadorDeSimple()));
        System.out.println(lista);
        Collections.sort(lista, new OtroComparadorDeSimple());
        // la mayor diferencia entre los comparadores e implementar comparable es
        // que se pueden tener varios comparadores para una misma clase
        System.out.println(lista);
    }

    // hacemos que esta clase sea comparable (ojo, interfaz tipada!)
    public static class Simple implements Comparable<Simple> {

        private String nombre;
        private String apellido;

        public Simple(String nombre, String apellido) {
            this.nombre = nombre;
            this.apellido = apellido;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public int compareTo(Simple o) {
            if (getNombre().equals(o.getNombre())) {
                return getApellido().compareTo(o.getApellido());
            } else {
                return getNombre().compareTo(o.getNombre());
            }
        }

        @Override
        public String toString() {
            return getNombre() + " " + getApellido();
        }
    }

    public static class ComparadorDeSimple implements Comparator<Simple> {

        public int compare(Simple o1, Simple o2) {
            if (o1.compareTo(o2) == 0) {
                return 0;
            } else {
                if (o1.getNombre().equals(o2.getNombre())) {
                    return o1.getApellido().compareTo(o2.getApellido());
                } else {
                    return o1.getNombre().compareTo(o2.getNombre());
                }
            }
        }
    }

    public static class OtroComparadorDeSimple implements Comparator<Simple> {

        public int compare(Simple o1, Simple o2) {
            if (o1.compareTo(o2) == 0) {
                return 0;
            } else {
                return o1.getApellido().compareTo(o2.getApellido());
            }
        }
    }
}
