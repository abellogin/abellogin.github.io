/**
 *
 * @author Alejandro
 */
public class EjemploInformes {

    public static void main(String[] args) {
        System.out.println(new InformeExpedienteCompleto().getInforme());
        System.out.println(new InformeExpedienteAlumno().getInforme());
    }

    // Definimos la interfaz
    public static interface Informe {

        public String getInforme();
    }

    // Definimos la clase abstracta.
    // Ojo! Sólo definimos un método abstracto, ya que de esa manera
    // en esta clase podemos definir el método getInforme, evitando
    // tener código duplicado en varias clases.
    public static abstract class InformeAlumno implements Informe {

        public String getInforme() {
            return getCabecera() + getInformeEspecifico();
        }

        public String getCabecera() {
            return "Esto es una cabecera\n";
        }

        protected abstract String getInformeEspecifico();
    }

    public static class InformeExpedienteAlumno extends InformeAlumno {

        @Override
        protected String getInformeEspecifico() {
            return "Esto es el expendiente de un alumno";
        }
    }

    public static class InformeExpedienteCompleto extends InformeAlumno {

        @Override
        protected String getInformeEspecifico() {
            return "Esto es el expendiente completo de un alumno";
        }
    }
}
