import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
public class discoverIP_util {
    private static final ArrayList<String> listeIP = new ArrayList<>();
    private static final ArrayList<String> listeNom = new ArrayList<>();

    /**
     * Algorithme de ping des adresses IP.
     * @param base l'adresse IP contenant uniquement les 3 octets fixes.
     */
    public static void testIP(String base) {
        System.out.println("Test de la base : " + base + ".*");
        int i = 0;
        while (i <= 255) {
            String ip = base + "." + i;
            System.out.println("Test de l'adresse : " + ip);
            try {
                InetAddress address = InetAddress.getByName(ip);
                if (address.isReachable(1000)) {
                    setListeIP(ip);
                }
            } catch (UnknownHostException e) {
                System.out.println("Adresse " + ip + " inconnue");
            } catch (IOException e) {
                System.out.println("Erreur de connexion");
            }
            i++;
        }
    }

    /**
     * Algorithme récupérant les 3 octets de l'adresse IP grâce au localhost en éspérant que ce soit en DHCP
     * @return l'adresse IP contenant uniquement les 3 octets fixes.
     */
    public static String obtenirBase() {
        String base = "";
        try {
            InetAddress address = InetAddress.getLocalHost();
            String ip = address.getHostAddress();
            String[] tab = ip.split("\\.");
            base = tab[0] + "." + tab[1] + "." + tab[2];
        } catch (UnknownHostException e) {
            System.out.println("Adresse inconnue");
        }
        return base;
    }

    /**
     * ArrayList contenant les adresses IP des appareils trouvés.
     * @param IP l'adresse IP à ajouter à la liste.
     */
    public static void setListeIP(String IP) {
        listeIP.add(IP);
    }

    /**
     * L'appareil est identifié par son adresse IP, on récupère son nom.
     * @param nom le nom de l'appareil.
     */
    public static void setListeNom(String nom) {
        listeNom.add(nom);
    }

    /**
     * Obtenir l'ArrayList récupérant les adresses IP des appareils qui sont atteignables.
     * @return l'ArrayList contenant les adresses IP des appareils trouvés.
     */
    public static ArrayList<String> getListeIP() {
        return listeIP;
    }

    /**
     * Obtenir l'ArrayList récupérant les noms des appareils qui sont atteignables.
     * @return l'ArrayList contenant les noms des appareils.
     */
    public static ArrayList<String> getListeNom() {
        return listeNom;
    }

    /**
     * Algorithme récupérant le nom de l'appareil grâce à son adresse IP.
     */
    public static void obtenirNomMac() {
        for (String ip : getListeIP()) {
            try {
                InetAddress address = InetAddress.getByName(ip);
                setListeNom(address.getHostName());
            } catch (UnknownHostException e) {
                System.out.println("Adresse " + ip + " inconnue");
            }
        }
    }

    /**
     * Algorithme permettant l'affichage des adresses ip, ainsi que le nom de l'appareil.
     */
    public static void affichageInformation() {
        System.out.println("\n");
        for (int i = 0; i < getListeIP().size() - 1; i++) {
            System.out.println("Adresse IP : " + getListeIP().get(i) + "\tNom : " + getListeNom().get(i));
        }
        System.out.println("\n");
    }
}
