import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class discover_IP_world_main {
    static ArrayList<String> listeIP = new ArrayList<>();
    static ArrayList<String> listeNom = new ArrayList<>();

    /**
     * Algorithme de ping des adresses IP.
     * @param address a essayer
     * @throws IOException Exception Input Output
     */
    public static void testIp(String address) throws IOException {
        try {
            InetAddress ip = InetAddress.getByName(address);
            if (ip.isReachable(1000)) {
                if(!ip.getHostName().equals(address)) {
                    listeIP.add(address);
                    listeNom.add(ip.getHostAddress());
                    System.out.println("Adresse IP : " + address + " - Nom : " + ip.getHostName());
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("Adresse " + address + " inconnue");
        } catch (IOException e) {
            System.out.println("Erreur de connexion");
        }
    }
    public static void main(String[] args) throws IOException {
        testIp("82.65.20.23");
        int n1=1,n2=0,n3=0,n4=0;
        while(n1<255 && n2<255 && n3<255 && n4<255){
            String ip = n1 + "." + n2 + "." + n3 + "." + n4;
            System.out.println("Test de l'adresse : " + ip);
            try {
                testIp(ip);
            } catch (IOException e) {
                e.printStackTrace();
            }
            n4++;
            if(n4==255){
                n3++;
                n4=0;
            }
            if(n3==255){
                n2++;
                n3=0;
            }
            if(n2==255){
                n1++;
                n2=0;
            }
        }
        System.out.println("\nListe des adresses IP trouvées :");
        for(String ip : listeIP){
            System.out.println(ip + " - " + listeNom.get(listeIP.indexOf(ip)));
        }
    }
}
