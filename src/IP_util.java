import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IP_util {
    private static final Logger LOGGER = Logger.getLogger(IP_util.class.getPackageName());

    /**
     * Algorithme de ping des adresses IP.
     * @param adresse a essayer
     * @throws IOException Exception Input Output
     */
    public static void envoiePaquet(InetAddress adresse) throws IOException {
        byte[] buffer = new byte[1024];
        DatagramPacket paquet = new DatagramPacket(buffer, buffer.length, adresse, 9);
        DatagramSocket socket = new DatagramSocket();
        socket.send(paquet);
        socket.close();
    }
    public static String testIP(String ip){
        try {
            InetAddress address = InetAddress.getByName(ip);
            if (address.isReachable(1000)) {
                return ip;
            }
        } catch (UnknownHostException e) {
            LOGGER.log(Level.SEVERE,"Adresse " + ip + " inconnue");
        } catch (IOException e) {
            LOGGER.log(Level.WARNING,"Erreur de connexion");
        }
        return "";
    }


    /**
     * Algorithme essayant d'envoyer un ou plusieurs paquets à une adresse IP, spécifié par l'utilisateur.
     */
    public static void IP_util_main() {
        while (true) {
            Scanner lecteur = new Scanner(System.in);
            System.out.println("Entrez l'adresse IP que vous souhaitez envoyer un paquet : [0 pour quitter]");
            String adresse = lecteur.nextLine();

            if(adresse.equals("0")){
                break;
            }

            String ip = testIP(adresse);

            while (ip.equals("")) {
                System.out.println("Adresse IP non valide, veuillez réessayer : ");
                adresse = lecteur.nextLine();
                ip = testIP(adresse);
            }
            try {
                envoiePaquet(InetAddress.getByName(ip));
                System.out.println("Paquet envoyé");
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE,"Erreur d'envoi du paquet");
            }
        }
        System.exit(666);
    }
}