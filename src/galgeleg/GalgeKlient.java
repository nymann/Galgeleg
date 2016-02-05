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

        playTheGame();
    }

    private static boolean loginToServer() {
        String username = "";
        String password = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("To proceed, login to the server.");
            System.out.print("Username: ");
            username = bufferedReader.readLine();
            System.out.print("\nPassword: ");
            password = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // Bruger login.
            Brugeradmin ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
            Bruger b = ba.hentBruger(username, password);
            System.out.println("Fik bruger " + b);
            return true;
            //
        } catch (RemoteException e) {
            return false;
        } catch (NotBoundException e) {
            return false;
        } catch (MalformedURLException e) {
            return false;
        } catch (IllegalArgumentException a) {
            System.out.println("Forkert brugernavn eller adgangskode, proev igen!");
            return false;
        }
    }

    private static void playTheGame() throws IOException, NotBoundException {
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
        if (g.erSpilletVundet()) {
            System.out.println("Tillykke, du har vundet! :-)");
        } else {
            System.out.println("Woops, du tabte! :-(\n");
            System.out.println("Det rigtige ord var: " + g.getOrdet());
        }
        System.out.println("Vil du proeve igen? (Ja/Nej)");
        s = bufferedReader.readLine();
        if (s.toUpperCase().equals("JA")) {
            playTheGame();
        } else {
            System.out.println("Tak for nu!");
        }
    }
}
