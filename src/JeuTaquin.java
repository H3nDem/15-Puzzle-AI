import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
    Creer le jeu du taquin a partir duquel on lance les parcours
*/

public class JeuTaquin {
    private EtatTaquin etatInit;                                  // Etat initial servant de départ aux parcours
    private EtatTaquin etatFinal;                                 // Etat final marquant la fin du parcours si decouvert
    public static int LIGNES;                                    // Nombre de lignes de la grille donnee au depart
    public static int COLONNES;                                  // Nombre de conlonnes de la grille donnee au depart
    private Ouvert ouvert;                                        // Structure influancant la facon de parcourir l'arbre
    private Heuristique heuristique;                              // Heuristique influancant la facon de parcourir l'arbre

    private CoutDeplacement coutDeplacement;


    /****** Initialisation de la partie ******/
    public JeuTaquin(String filename) throws FileNotFoundException {
        initialiser(filename);
        System.out.println("Dimensions du taquin: Lignes= " + LIGNES + ", Colonnes: " + COLONNES + "\n");
        System.out.println(etatInit);
        System.out.println(etatFinal);
        TaquinSolvabilite taquinSolvabilite = new TaquinSolvabilite(etatInit,etatFinal);
        System.out.println("TaquinSolvable : "+taquinSolvabilite.isSolvable());
        if (!taquinSolvabilite.isSolvable()){
            System.out.println("LE TAQUIN N'EST PAS SOLVABLE!!! Voir classe TaquinSolvabilite");
            System.exit(0);
        }
    }
    private void initNbLigneEtColonne(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner( new File(filename));
        LIGNES = Integer.parseInt(scanner.nextLine());
        COLONNES = scanner.nextLine().length();
        int tmp;
        if((tmp = scanner.nextLine().length()) > COLONNES){
            COLONNES = tmp;
        }
//        this.etatInit = new EtatTaquin(new char[LIGNES][COLONNES],null);
//        this.etatFinal = new EtatTaquin(new char[LIGNES][COLONNES],null);
        scanner.close();
    }
    private void initialiser(String filename) throws FileNotFoundException{
        initNbLigneEtColonne(filename);
        System.out.println("NB ligne: " + LIGNES + " NB Colonne: " + COLONNES);
        Scanner scanner = new Scanner(new File(filename));
        scanner.nextLine(); // Sauter la première ligne
        char[][] tabEtatInit = new char[LIGNES][COLONNES];
        // Creation EtatInit
        for (int i = 0; i < LIGNES; i++) {
            String line = scanner.nextLine();
            char[] lineSplit = line.toCharArray();
            // Remplissage des cases de Etat Init
            for (int j = 0; j < line.length(); j++) {
                tabEtatInit[i][j] = lineSplit[j];
            }
            // Si la ligne termine par la case vide :
            if (line.length() < COLONNES) {
                tabEtatInit[i][COLONNES -1] = ' ';
            }
        }
        this.etatInit = new EtatTaquin(tabEtatInit,null,0);
        char[][] tabEtatfinal = new char[LIGNES][COLONNES];
        // Creation Etat Final
        for (int i = 0; i < LIGNES; i++) {
            String line = scanner.nextLine();
            char[] lineSplit = line.toCharArray();
            // Remplissage des cases de Etat Final
            for (int j = 0; j < line.length(); j++) {
                tabEtatfinal[i][j] = lineSplit[j];
            }
            // Si la ligne termine par la case vide :
            if (line.length() < COLONNES) {
                tabEtatfinal[i][COLONNES -1] = ' ';
            }
        }
        this.etatFinal = new EtatTaquin(tabEtatfinal,null,-1);//Le cout n'a pas d'importance ici.
    }

    public void faireParcours() throws InterruptedException {
        System.out.println("FAISONS LE PARCOURS LETS GO !!!!");
        if (this.ouvert == null){
            System.out.println("Ne peut pas faire le parcours tant que la structure Ouverte n'est pas initialiser");
        }
        else {
            ouvert.parcours(etatInit);
        }
    }


    /****** Getters / Setters ******/
    public EtatTaquin getEtatInit() {
        return this.etatInit;
    }
    public EtatTaquin getEtatFinal() {
        return this.etatFinal;
    }

    public Ouvert getOuvert() {
        return this.ouvert;
    }
    public Heuristique getHeuristique() {
        return this.heuristique;
    }
    public CoutDeplacement getCoutDeplacement() {
        return coutDeplacement;
    }

    public void setOuvert(Ouvert OUVERT) {
        this.ouvert = OUVERT;
        if (heuristique == null){
            this.heuristique = new h0Heuristique();
        }
        if (this.coutDeplacement == null){
            this.coutDeplacement = new GratuitCoutDeplacement();
        }
    }
    public void setHeuristique(Heuristique heuristique) {
        this.heuristique = heuristique;
    }
    public void setCoutDeplacement(CoutDeplacement coutDeplacement) {
        this.coutDeplacement = coutDeplacement;
    }
}
