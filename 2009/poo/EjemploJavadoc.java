/**
 * Clase para ejemplificar el uso de <code>javadoc</code>.
 * 
 * Esto se incluir� al comienzo de la p�gina que define esta clase.
 * Como se puede ver, se puede incluir m�s de un autor, as� como
 * la versi�n de esta clase u otra informaci�n relevante.
 * <p>
 *
 *
 * @author autor1 autor1@a.com
 * @author autor2 autor2@b.org
 * @version X.0
 * @since SDK1.5
 */
public class EjemploJavadoc {

    /**
     * Se pueden poner comentarios a atributos de la clase,
     * aunque <code>javadoc</code> no procesar� comentarios
	 * sobre atributos privados por defecto.
	 *
     * Ver m�todo {@link #getDate} para m�s informaci�n.
     */
    private Date fecha;

    /**
     * Otro comentario sobre un atributo.
     * Este entero se incrementa en el constructor.
     */
    public static int numEjemplos = 0;

    /**
     * Constructor que se usa para crear un objeto.
	 *
     * @param d una fecha para saber cu�ndo se ha creado este objeto.
     * @see #numEjemplos
     */
    public EjemploJavadoc(Date d) {
      this.fecha = d;
      this.numEjemplos++;
    }

    /**
     * Constructor sin argumentos que pasar� la fecha actual al 
	 * constructor que recibe un <i>Date</i>.
     * @see #EjemploJavadoc(Date)
     */
    public EjemploJavadoc() {
      this(new Date());
    }

    /**
     * Devuelve la fecha en que este objeto fue creado.
	 * 
     * @return objeto de tipo <code>Date</code> indicando cu�ndo se cre� un objeto.
     * @see #EjemploJavadoc
     */
    public Date getDate() {
      return this.fecha;
    }
}
