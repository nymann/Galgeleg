package galgeleg;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Nymann on 02-02-2016.
 */
public interface GalgeI extends java.rmi.Remote {

    ArrayList<String> getBrugteBogstaver() throws RemoteException;

    String getSynligtOrd() throws RemoteException;

    String getOrdet() throws RemoteException;

    int getAntalForkerteBogstaver() throws RemoteException;

    boolean erSidsteBogstavKorrekt() throws RemoteException;

    boolean erSpilletVundet() throws RemoteException;

    boolean erSpilletTabt() throws RemoteException;

    boolean erSpilletSlut() throws RemoteException;

    void hentOrdFraOrdliste() throws RemoteException;

    void opdaterSynligtOrd() throws RemoteException; // Not sure about this. (was private)

    void logStatus() throws RemoteException;

    void gætBogstav(String bogstav) throws RemoteException;

    void nulstil() throws RemoteException;


}
