public class discoverIP_main {
    public static void main(String[] args) {
        long debut = System.currentTimeMillis();
        long fin;
        System.out.println("L'adresse IP de la machine est : "+discoverIP_util.obtenirBase());

        fin=System.currentTimeMillis();
        System.out.println("Temps d'execution pour obtenir l'IP du réseau : "+(fin-debut)+" ms");
        debut=System.currentTimeMillis();

        discoverIP_util.testIP(discoverIP_util.obtenirBase());

        fin=System.currentTimeMillis();
        System.out.println("Temps d'execution pour découvrir les appareils sur le réseau : "+(fin-debut)+" ms");
        debut=System.currentTimeMillis();

        discoverIP_util.obtenirNomMac();

        fin=System.currentTimeMillis();
        System.out.println("Temps d'execution de l'obtention des informations : "+(fin-debut)+" ms");
        debut=System.currentTimeMillis();

        discoverIP_util.affichageInformation();

        fin=System.currentTimeMillis();
        System.out.println("Temps d'execution de l'affichage : "+(fin-debut)+" ms");

        // FIN D'EXECUTION DE L'OBTENTION DES ADRESSES IP

        IP_util.IP_util_main();
    }
}
