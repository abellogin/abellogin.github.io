import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alejandro
 */
public class EjemploMap {

    // Este método es el que hemos comentado en clase.
    // No hace uso de métodos propios del map, y siempre llama a put
    private static void a(Map<String, List<String>> tabla, String clave, String valor) {
        List<String> valores = tabla.get(clave);
        if (valores == null) {
            valores = new ArrayList<String>();
        }
        // dado que lo que queremos es añadir, llamamos a add en la parte común del código
        valores.add(valor);
        tabla.put(clave, valores);
    }

    // Este método es una variación del anterior, donde se hace utiliza que
    // los objetos son referencias a memoria, y por tanto, no hace falta volver
    // a insertar el objeto en la tabla
    private static void b(Map<String, List<String>> tabla, String clave, String valor) {
        List<String> valores = tabla.get(clave);
        if (valores == null) {
            valores = new ArrayList<String>();
            tabla.put(clave, valores);
        }
        valores.add(valor);
    }

    // Similar al anterior pero haciendo uso del método contains de la tabla
    private static void c(Map<String, List<String>> tabla, String clave, String valor) {
        List<String> valores = null;
        if (tabla.containsKey(clave)) {
            valores = tabla.get(clave);
        } else {
            valores = new ArrayList<String>();
            tabla.put(clave, valores);
        }
        valores.add(valor);
    }

    private static void recorrer(Map<String, List<String>> tabla) {
        // Primera opción: las claves
        for (String clave : tabla.keySet()) {
            System.out.println(clave);
            // Dada la clave se puede acceder al valor: tabla.get(clave)
        }
        // Segunda opción: los valores
        for (List<String> valor : tabla.values()) {
            System.out.println(valor);
            // No podemos saber cuál es la clave asociada de manera directa
            // (necesitaríamos hacer otra consulta a la tabla)
        }
        // Tercera opción: el par (clave, valor)
        for (Map.Entry<String, List<String>> entrada : tabla.entrySet()) {
            System.out.println(entrada.getKey() + " --> " + entrada.getValue());
        }
    }

    public static void main(String[] args) {
        Map<String, List<String>> mapa = new HashMap<String, List<String>>();
        // primer caso
        a(mapa, "a", "a");
        b(mapa, "b", "b");
        c(mapa, "c", "c");
        recorrer(mapa);
        // añadir a la lista ya existente
        a(mapa, "a", "a");
        b(mapa, "b", "b");
        c(mapa, "c", "c");
        recorrer(mapa);
    }
}
