package brugerautorisation.transport.rmi;

import brugerautorisation.data.Bruger;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BrugeradminImpl extends UnicastRemoteObject implements Brugeradmin {
    public BrugeradminImpl() throws RemoteException {

    }

    public Bruger hentBruger(String brugernavn, String adgangskode) throws RemoteException {
        System.out.println("Hej fra Impl.");
        return hentBruger(brugernavn, adgangskode);
    }
}
