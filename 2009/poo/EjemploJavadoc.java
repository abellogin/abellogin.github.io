/**
 * Clase para ejemplificar el uso de <code>javadoc</code>.
 * 
 * Esto se incluirá al comienzo de la página que define esta clase.
 * Como se puede ver, se puede incluir más de un autor, así como
 * la versión de esta clase u otra información relevante.
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
     * aunque <code>javadoc</code> no procesará comentarios
	 * sobre atributos privados por defecto.
	 *
     * Ver método {@link #getDate} para más información.
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
     * @param d una fecha para saber cuándo se ha creado este objeto.
     * @see #numEjemplos
     */
    public EjemploJavadoc(Date d) {
      this.fecha = d;
      this.numEjemplos++;
    }

    /**
     * Constructor sin argumentos que pasará la fecha actual al 
	 * constructor que recibe un <i>Date</i>.
     * @see #EjemploJavadoc(Date)
     */
    public EjemploJavadoc() {
      this(new Date());
    }

    /**
     * Devuelve la fecha en que este objeto fue creado.
	 * 
     * @return objeto de tipo <code>Date</code> indicando cuándo se creó un objeto.
     * @see #EjemploJavadoc
     */
    public Date getDate() {
      return this.fecha;
    }
}
