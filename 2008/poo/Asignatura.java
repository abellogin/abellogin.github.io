package es.uam.eps.poo.beans;

import es.uam.eps.poo.remote.AsignaturaRemota;
import java.io.Serializable;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Clase que representa la informaci�n com�n
 * (compartida por todos los alumnos)
 * de una asignatura
 *
 * @author Alejandro Bellogin
 */
public class Asignatura implements AsignaturaRemota, Serializable {

    public static final int TIPO_TRONCAL = 1;
    public static final int TIPO_OBLIGATORIA = 2;
    public static final int TIPO_OPTATIVA = 3;
    private int id;
    private String nombre;
    private int cursoAcademico;
    private int semestre;
    private double numeroDeCreditos;
    private Practica practica;
    private List<Asignatura> asignaturasLlave;
    private int tipoAsignatura;

    /**
     * Crea una asignatura completa
     *
     * @param id id de la asignatura
     * @param nombre nombre de la asignatura
     * @param cursoAcademico curso acad�mico de la asignatura
     * @param semestre semestre en que se imparte la asignatura
     * @param numeroDeCreditos n�mero de cr�ditos que se obtienen con esta asignatura
     * @param tipoAsignatura troncal, obligatoria u optativa
     * @param practica asignatura pr�ctica (si la tiene, null en otro caso)
     * @param asignaturasLlave conjunto de asignaturas llave (si las tiene, null en otro caso)
     */
    public Asignatura(int id, String nombre, int cursoAcademico, int semestre, double numeroDeCreditos, int tipoAsignatura, Practica practica, List<Asignatura> asignaturasLlave) {
        this.id = id;
        this.nombre = nombre;
        this.cursoAcademico = cursoAcademico;
        this.semestre = semestre;
        this.numeroDeCreditos = numeroDeCreditos;
        this.practica = practica;
        this.asignaturasLlave = asignaturasLlave;
        this.tipoAsignatura = tipoAsignatura;
		try{
			UnicastRemoteObject.exportObject(this,0);
		} catch (Exception e){
			e.printStackTrace();
		}
    }

    /**
     * Crea una asignatura sin pr�cticas ni asignaturas llave
     *
     * @param id id de la asignatura
     * @param nombre nombre de la asignatura
     * @param cursoAcademico curso acad�mico de la asignatura
     * @param semestre semestre en que se imparte la asignatura
     * @param numeroDeCreditos n�mero de cr�ditos que se obtienen con esta asignatura
     * @param tipoAsignatura troncal, obligatoria u optativa
     */
    public Asignatura(int id, String nombre, int cursoAcademico, int semestre, double numeroDeCreditos, int tipoAsignatura) {
        this(id, nombre, cursoAcademico, semestre, numeroDeCreditos, tipoAsignatura, null, null);
    }

    /**
     * Crea una asignatura sin asignaturas llave, pero con pr�ctica
     *
     * @param id id de la asignatura
     * @param nombre nombre de la asignatura
     * @param cursoAcademico curso acad�mico de la asignatura
     * @param semestre semestre en que se imparte la asignatura
     * @param numeroDeCreditos n�mero de cr�ditos que se obtienen con esta asignatura
     * @param tipoAsignatura troncal, obligatoria u optativa
     * @param practica asignatura pr�ctica (si la tiene, null en otro caso)
     */
    public Asignatura(int id, String nombre, int cursoAcademico, int semestre, double numeroDeCreditos, int tipoAsignatura, Practica practica) {
        this(id, nombre, cursoAcademico, semestre, numeroDeCreditos, tipoAsignatura, practica, null);
    }

    /**
     * Crea una asignatura sin pr�cticas, pero con asignaturas llave
     *
     * @param id id de la asignatura
     * @param nombre nombre de la asignatura
     * @param cursoAcademico curso acad�mico de la asignatura
     * @param semestre semestre en que se imparte la asignatura
     * @param numeroDeCreditos n�mero de cr�ditos que se obtienen con esta asignatura
     * @param tipoAsignatura troncal, obligatoria u optativa
     * @param asignaturasLlave conjunto de asignaturas llave (si las tiene, null en otro caso)
     */
    public Asignatura(int id, String nombre, int cursoAcademico, int semestre, double numeroDeCreditos, int tipoAsignatura, List<Asignatura> asignaturasLlave) {
        this(id, nombre, cursoAcademico, semestre, numeroDeCreditos, tipoAsignatura, null, asignaturasLlave);
    }

    /**
     * Devuelve un conjunto de {@link Asignatura}s que el alumno
     * debe haber aprobado para poder matricularse de esta asignatura.
     *
     * @return conjunto de asignaturas llave
     */
    public List<Asignatura> getAsignaturasLlave() {
        return asignaturasLlave;
    }

    /**
     * Devuelve el curso acad�mico en que aparece esta asignatura
     * en el plan de estudios.
     *
     * @return curso acad�mico
     */
    public int getCursoAcademico() {
        return cursoAcademico;
    }

    /**
     * Devuelve el nombre de esta asignatura
     *
     * @return nombre de la asignatura
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el n�mero de cr�ditos que se obtiene al cursar esta asignatura
     *
     * @return n�mero de cr�ditos
     */
    public double getNumeroDeCreditos() {
        return numeroDeCreditos;
    }

    /**
     * Devuelve un objeto {@link Practica} asociado a esta asignatura.
     * Puede ser null.
     *
     * @return la practica asociada a esta asignatura
     */
    public Practica getPractica() {
        return practica;
    }

    /**
     * Devuelve el semestre en que se imparte esta asignatura
     *
     * @return semestre
     */
    public int getSemestre() {
        return semestre;
    }

    /**
     * Dice si esta asignatura es optativa
     *
     * @return true si esta asignatura es optativa
     */
    public boolean esOptativa() {
        return tipoAsignatura == TIPO_OPTATIVA;
    }

    /**
     * Dice si esta asignatura es troncal
     *
     * @return true si esta asignatura es troncal
     */
    public boolean esTroncal() {
        return tipoAsignatura == TIPO_TRONCAL;
    }

    /**
     * Dice si esta asignatura es obligatoria
     *
     * @return true si esta asignatura es obligatoria
     */
    public boolean esObligatoria() {
        return tipoAsignatura == TIPO_OBLIGATORIA;
    }

    /**
     * Obtiene el identificador de esta asignatura
     *
     * @return el identificador
     */
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        String s = "";
        s += "Id=" + id + ", nombre=" + nombre + ",tipo=" + tipoAsignatura + "\n";
        s += "Curso=" + cursoAcademico + ",semestre=" + semestre + ",créditos=" + numeroDeCreditos + "\n";
        s += "Práctica=" + practica + "\n";
        s += "Asignaturas llave:" + asignaturasLlave;
        return s;
    }
}
