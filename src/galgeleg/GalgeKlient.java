package galgeleg;

import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class GalgeKlient {

    public static void main(String[] args) throws Exception {

        Boolean isUserLoggedIn = loginToServer();
        while (!isUserLoggedIn) {
            isUserLoggedIn = loginToServer();
        }

        GalgeI g = (GalgeI) Naming.lookup("rmi://localhost/galgeleg");
        g.nulstil();

        try {
            g.hentOrdFraOrdliste();
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!g.erSpilletSlut()) {
            System.out.println("Brugte bogstaver: " + g.getBrugteBogstaver() + ".");
            System.out.print("Gaet et bogstav: ");
            s = bufferedReader.readLine();
            g.gætBogstav(s);
            g.logStatus();
            System.out.println("" + g.getAntalForkerteBogstaver());
            System.out.println("" + g.getSynligtOrd());
        }
        if(g.erSpilletVundet()) {
            System.out.println("Tillykke, du har vundet! :-)");
        }
        else {
            System.out.println("Woops, du tabte! :-(\n");
            System.out.println("Det rigtige ord var: " + g.getOrdet());
        }
        System.out.println("Vil du proeve igen? (Ja/Nej)");
        s = bufferedReader.readLine();
        if (s.toUpperCase().equals("JA")) {
            g.nulstil();
        } else {
            System.out.println("Tak for nu!");
        }
    }

    private static boolean loginToServer() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("To proceed, login to the server.");
        System.out.print("Username: ");
        String username = bufferedReader.readLine();
        System.out.print("\nPassword: ");
        String password = bufferedReader.readLine();

        try {
            // Bruger login.
            Brugeradmin ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
            Bruger b = ba.hentBruger(username, password);
            System.out.println("Fik bruger " + b);
            return true;
            //
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        } catch (NotBoundException e) {
            e.printStackTrace();
            return false;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
