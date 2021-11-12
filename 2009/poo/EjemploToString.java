/**
 *
 * @author Alejandro
 */
public class EjemploToString {

    public static void main(String[] args) {
        ImprimeBien ib = new ImprimeBien("a");
        ImprimeMal im = new ImprimeMal("a");
        System.out.println(ib);
        // la diferencia entre las dos clases
        // es que el toString por defecto muestra
		// la dirección de memoria que usa ese objeto
        System.out.println(im);
    }

    public static class ImprimeBien {

        private String s;

        public ImprimeBien(String s) {
            this.s = s;
        }

        @Override
        public String toString() {
            return "ImprimeBien = " + s;
        }
    }

    public static class ImprimeMal {

        private String s;

        public ImprimeMal(String s) {
            this.s = s;
        }

    }
}
