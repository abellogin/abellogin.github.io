
/**
 *
 * @author Alejandro
 */
public class EjemploArray {

    public static void main(String[] args) {
        String[] array = new String[]{"a", "b", "c", "d"};
        // también
        String[] array2 = {"a", "b", "c", "d"};

        // como en C
        for (int i = 0; i < array.length; i++) {
            String s = array[i];
            System.out.println(s);
        }

        // for-each
        for (String s : array) {
            System.out.println(s);
        }
    }
}
