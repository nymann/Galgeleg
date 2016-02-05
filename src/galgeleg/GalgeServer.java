package galgeleg;

import java.rmi.Naming;

/**
 * Created by Nymann on 02-02-2016.
 */
public class GalgeServer {
    public static void main(String[] args) throws Exception {
        java.rmi.registry.LocateRegistry.createRegistry(1099); // Start i Server-JVM på port 1099.

        GalgeI g = new GalgeImp(); // woot.
        Naming.rebind("rmi://localhost/galgeleg", g);
        System.out.println("Galgeleg registreret.");
    }
}
