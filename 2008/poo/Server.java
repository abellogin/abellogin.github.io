/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.uam.eps.poo.remote.test;

import es.uam.eps.poo.beans.Asignatura;
import es.uam.eps.poo.beans.CargarInformacion;
import es.uam.eps.poo.remote.SistemaRemoto;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

/**
 *
 * @author mora
 */
public class Server {
    public static SistemaRemoto cargaSistemaRemoto() throws RemoteException{
        Map<Integer, Asignatura> asignaturas=
                CargarInformacion.obtenerListaAsignaturasTeoria();
        SistemaRemoto s= new ImplSistemaRemoto(asignaturas.values());
        return s;
    }

    public static Registry obtenerRegistro() throws RemoteException{
        Registry r=null;
        try {
            r = LocateRegistry.getRegistry("127.0.0.1");
        } catch (RemoteException ex) {
            System.err.println("Registro no encontrado");
        }
        return r;
    }

    public static void main(String[] args){        
        try{            
            SistemaRemoto s= cargaSistemaRemoto();
            //exportamos el objeto remoto para su registro, ya no hereda de UnicastRemoteObject 
            UnicastRemoteObject.exportObject(s,0);
            Registry registry = LocateRegistry.createRegistry(SistemaRemoto.Port);
            registry.rebind(SistemaRemoto.ID, s);
            System.out.println("Sistema remoto registrado");
        } catch (Exception e) {
            System.err.println("Errorr al registrar servidor");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
