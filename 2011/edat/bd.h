/*-----------------------------------------------------------------------
 * Fichero:     BD.H
 *
 * Descripcion: Definiciones para el manejo de ficheros de base de datos.
 *
 * Copyright:   Escuela de Ingenieria Informatica
 * Fecha:       01/04/2008
 *
 * NOTAS:
 *  - Los ficheros constaran de un registro de cabecera
 *    seguido de los registros de datos
 *  - Las funciones devolveran un codigo de error en caso que haya un error
 *  - BDAbre exige que el fichero exista
 *  - La escritura del registro de cabecera puede hacerse solo en BDCierra
 *
 *------------------------------------------------------------------------*/

#ifndef _BDH
#define _BDH

#include <stdio.h>


/*------------------------------------------------------------
 * TIPOS DE DATOS
 *------------------------------------------------------------*/



/* Estructura auxiliar para datos de fichero: */

typedef struct tagDatosFichero {
        char *pszNombreFichero;       	/* Nombre del fichero */
        FILE *f;                      	/* Descriptor del fichero */
        int  permisoAcceso;  		/* Permisos de acceso (lectura, lectura+escritura)*/
} DatosFichero;



/* Estructura con la definicion del registro de cabecera de los
 ficheros de datos */

typedef struct tagBDCabecera {
        unsigned short int tamanioCabecera; 	/* tamanio de Cabecera*/
        int lNumeroRegistros;  			/* numero total de registros */
        int lNumeroActivos;  			/* numero de registros activos (es decir: no borrados) */
        int  tamanioRegistro;  			/* tamanio de registro */
        char pszFechaCreacion[9];  		/* fecha de creacion de la BD - DD/MM/YY */
        char pszFechaActualizacion[9]; 		/* fecha de ultima modificacion de la BD - DD/MM/YY */
        char pszHoraActualizacion[9];  		/* hora de utima modificacion de la BD - hh/mm/ss */
        char sRelleno[128]; 			/*campo auxiliar */
} BDCabecera;



/* Estructura con la informacion sobre el registro */
typedef struct tagRegistro {
        void *pRegistroActual;  	/* Registro actual (a modo de buffer) */
        long lPosicionActual;  		/* Posicion del registro actual (-1 si invalido) */
} BDRegistro;


/* Estructura con el descriptor de la base de datos: 
 es el analogo al FILE al abrir ficheros como stream */
typedef struct tagBDFichero {
        DatosFichero *pFichero;  	/* Datos del fichero */
        BDCabecera *pCabecera;   	/* Registro de cabecera del fichero */
        BDRegistro *pRegistro;   	/* Datos del registro */
} BDFichero;




/*-----------------------------------------------------------------
 * Codigos definidos
 *-----------------------------------------------------------------*/

/* codigos de flag de actualizacion del fichero*/
#define NO_ACTUALIZADO 0
#define ACTUALIZADO 1

/* Codigos de tipo de acceso */
#define BD_LECT           0  /* BD abierta con permiso de lectura  */
#define BD_LECT_ESCRIT    1  /* BD abierta con permiso de lectura y escritura */

/* IMPORTANTE: En ficheros abiertos para lectura y escritura, es necesario 
   realizar una llamada alguna de las siguientes funciones: fseek, fsetpos, 
   fflush o rewind antes de cualquier paso de lectura a escritura, o viceversa */


/*-----------------------------------------------------------------
 * Prototipos de funciones generales de acceso a ficheros de datos
 * - NO saben estructura de los campos del registro
 *-----------------------------------------------------------------*/

/*
     I M P O R T A N T E
     -------------------

     Estas funciones representan las funciones minimas que hay que
     implementar. Es posible que para realizar la segunda parte de la
     practica se necesiten mas funciones. En este caso PODEIS DEFINIR
     VUESTRAS FUNCIONES y simplemente declararlas en este fichero e
     implementarlas en el fochero bd.c Este NO ES un fichero sagrado
     que no se puede tocar: es un instrumento de trabajo que sirve
     para proporcionar indicaciones, pero que se puede modificar segun
     lo necesiteis.
*/


/* Crea una NUEVA base de datos. Si szNombreFichero corresponde a 
   una base de datos ya existente, la informacin previa se pierde.
   Devuelve un puntero a una estructura BDFichero que permite acceder 
   a la BD creada. La BD creada se abre siempre con permisos de 
   lectura y escritura. tamanioRegistro indica el tamano del registro.
*/
BDFichero  *BDCrea(const char *szNombreFichero, int tamanioRegistro, char *sRelleno);


/* abre una base de datos ya existente (en caso contrario, da error) 
   con permisos de lectura/escritura definidos por la variable permisoAcceso
   (valores: BD_LECT, BD_LECT_ESCRIT) */
BDFichero  *BDAbre(const char *szNombreFichero, int permisoAcceso);


/* Cierra la base de datos, liberando toda la memoria reservada */
int  BDCierra (BDFichero *bd);


/* escribe el registro de la cabecera en el fichero de la BD */
int  BDEscribeCabecera (BDFichero *bd);


/* lee registro de cabecera del fichero, lo guarda en pCabecera */
int  BDLeeCabecera     (BDFichero *bd);


/* lee registro actual (donde este posicionado el fichero) a
   pRegistroActual. El fichero no se desplaza, es decir, al final de
   la lectura, el fichero sigue posicionado en el registro en que
   estaba posicionado antes de la llamada.

   Si el fichero esta' posicionado al final, es decir, si se ha
   llamado la funcion BDNext hasta visitar todos los registros, esta
   funcion devuelvera 0 e pondra' el buffer interno a NULL (recordaros
   hacer el free del buffer antes de ponerlo a NULL...)
*/
int  BDLeeRegistro     (BDFichero *bd);


/* escribe el contenido del buffer pRegistroActual en la posicion
   actual del fichero. El fichero no se desplaza, es decir, al final de
   la lectura, el fichero sigue posicionado en el registro en que
   estaba posicionado antes de la llamada.
*/
int  BDEscribeRegistro (BDFichero *bd);

/* busca un lugar idoneo  en la BD (registro previamente borrado, o final 
   de fichero) y annade el registro al que apunta pRegistroActual. 
   Deja lPosicionActual que apunte al registro que se acaba de insertar.
*/
int  BDAniadeRegistro  (BDFichero *bd);

/*
  posiciona el fichero en el primer registro no borrado. Devuelve la
  posicion en el fichero del registro (este valor no debe usarse,
  pero es util para debugging. 
*/
long BDFirst (BDFichero *bd);

/*
  asumiendo que el fichero est\'a posicionado al principio de un
  registro, se posiciona en el registro no borrado siguiente. Devuelve
  la posicion en que el fichero se queda posicionado, o 0 si no hay
  mas registros no borrados en el fichero.
*/
int BDNext (BDFichero *bd);


/* borra el registro que esta en lPosicionActual: marca el registro
   como borrado. Deja lPosicionActual apuntando al final del registro
   actual en el fichero 
*/
int  BDBorraRegistro (BDFichero *bd);

/* actualiza el contenido del buffer interno en memoria central usando
   los datos de pRegistroNuevo.

   OJO: esta funcion no escribe nada en el fichero. Simplemente copia
   los datos que se le pasan en el buffer interno. Para escribir los
   datos en un registro sera' necesario, tras llamar esta funcion,
   llamar a la funcion EscribeRegistro.
*/
int  BDActualizaRegistro   (BDFichero *bd, void *pRegistroNuevo);

/*
  Devuelve un puntero a la cadena de datos del buffer actualmente en
  memoria. Devuelve NULL si no hay ningun registro en memoria, o si se ha efectuado 
  una llamada a BDNext que ha 

  Ejemplo:
  Para acceder a los registros uno a uno, tras haber abierto el fichero, se
  ejecutara' el bucle:

  BDFirst(bd);

  while ( (buf = BDData(bd)) != NULL) {
     mydata *d = (mydata *) buf;
     // trabaja aqui con los datos del registro 
     BDNext(bd);
     BDLeeRegistro(bd);
  }
*/
void *BDData(BDFichero *bd);  


/* devuelve la longitud del registro en esta base de datos */
int BDRegLen (BDFichero *bd);

/* devuelve el numero de registros en esta base de datos */
int BDRegNo (BDFichero *bd);

#endif


