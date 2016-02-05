package brugerautorisation.transport.rmi;

import brugerautorisation.data.Bruger;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Brugeradmin extends Remote {
    Bruger hentBruger(String brugernavn, String asgangskode) throws RemoteException;
}
