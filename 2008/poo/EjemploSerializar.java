import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author Alejandro
 */
public class EjemploSerializar {

    public static void main(String[] args) {
        try {
            // probamos clase serializable
            FileOutputStream fos1 = new FileOutputStream("fichero.obj");
            ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
            oos1.writeObject(new ClaseSerializable("hola"));
            oos1.close();
        } catch (Exception e) {
            System.out.println("Falló al guardar la clase serializable");
        }
        try {
            // probamos clase no serializable
            FileOutputStream fos2 = new FileOutputStream("fichero.obj");
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            oos2.writeObject(new ClaseNoSerializable("hola"));
            oos2.close();
        } catch (Exception e) {
            System.out.println("Falló al guardar la clase no serializable");
        }
    }

    public static class ClaseSerializable implements Serializable {

        private String string;

        public ClaseSerializable(String string) {
            this.string = string;
        }
    }

    public static class ClaseNoSerializable {

        private String string;

        public ClaseNoSerializable(String string) {
            this.string = string;
        }
    }
}
