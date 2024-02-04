public class CheminAfficheur {

    public static void printResultParcours(EtatTaquin etatTaquin) {
        System.out.println("AFFICHAGE PARCOURS : (à lire de bas en haut)");
        //System.out.println(etatTaquin);
        etatTaquin.AfficherEtatCompact();
        EtatTaquin etatTaquin1 = etatTaquin;
        int nbCoups = 0;
        while (etatTaquin1.getPreviousState()!=null){
            nbCoups++;
            etatTaquin1 = etatTaquin1.getPreviousState();
            etatTaquin1.AfficherEtatCompact();
            //System.out.println(etatTaquin1);
        }
        System.out.println("NB COUPS : "+nbCoups);
    }
}
