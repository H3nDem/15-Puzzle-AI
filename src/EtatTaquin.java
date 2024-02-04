import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Definit un etat de la grille du jeu de taquin
*/

public class EtatTaquin {
    private char[][] tabCells;     // La grille constitue des caracteres a deplacer jusqu'a une certaine configuration
    private int posXCaseVide;     // Position X de la case vide
    private int posYCaseVide;     // Position Y de la case vide
    private final int idEtat;     // Identifiant de l'etat
    public static int nbEtat = 0; // Attibue une valeur unique a chaque nouvel etat
    private EtatTaquin previousState;
    private int coutDeplacement;



    /****** Constructeur et initialisation de la case vide ******/


    public EtatTaquin(char[][] tabCells, EtatTaquin previousState, int coutDeplacement) {
        this.tabCells = tabCells;
        this.initCaseVide();
        this.idEtat = nbEtat;
        nbEtat++;
        this.coutDeplacement = coutDeplacement;
        this.previousState = previousState;
        System.out.println("EtatTaquin Crée avec n°"+this.idEtat);
        faciliterLaStructureFermer();//A utiliser pour améliorer la structure Fermer

    }
    // Initialise la caseVide à l' appel du constructeur
    private void initCaseVide() {
        for (int i = 0; i < tabCells.length; i++) {
            for (int j = 0; j < tabCells[0].length; j++) {
                if (tabCells[i][j] == ' '){
                    this.posXCaseVide= i;
                    this.posYCaseVide= j;
                }
            }
        }
    }


    /****** Recuperation des cases voisines ******/
    // Recupere les cases voisines de la case vide d'un etat
    public List<Character> getVoisinsCaseVide() {
        List<Character> voisins = new ArrayList<>();
        addVoisinNord(voisins);
        addVoisinSud(voisins);
        addVoisinOuest(voisins);
        addVoisinEst(voisins);
        return voisins;
    }
    private void addVoisinNord(List<Character> voisins) {
        if (posXCaseVide != 0) {
            voisins.add(getCell(posXCaseVide-1,posYCaseVide));
        }else {
            voisins.add(' ');
        }
    }
    private void addVoisinSud(List<Character> voisins) {
        if (posXCaseVide != JeuTaquin.LIGNES -1) {
            voisins.add(getCell(posXCaseVide+1,posYCaseVide));
        } else {
            voisins.add(' ');
        }
    }
    private void addVoisinOuest(List<Character> voisins) {
        if (posYCaseVide != 0) {
            voisins.add(getCell(posXCaseVide,posYCaseVide-1));
        }else {
            voisins.add(' ');
        }
    }
    private void addVoisinEst(List<Character> voisins) {
        if (posYCaseVide != JeuTaquin.COLONNES -1) {
            voisins.add(getCell(posXCaseVide,posYCaseVide+1));
        }else {
            voisins.add(' ');
        }
    }



    // Verifie qu'un etat et parfaitement egale a un autre
    public boolean estEquivalentA(EtatTaquin etatTaquinGrille) {
        return Arrays.deepEquals(this.tabCells, etatTaquinGrille.tabCells);
    }


    /****** Affichage d'un etat de la grille du taquin ******/
    @Override
    public String toString() {
        return printEtat();
    }
    // Afficahge detaille
    private String printEtat() {
        System.out.println("État n°" + idEtat);
        System.out.println("Case vide: X=" + posXCaseVide + ", Y=" + posYCaseVide);
        for (int i = 0; i < this.tabCells.length; i++) {
            System.out.print("[");
            for (int j = 0; j < this.tabCells[0].length; j++) {
                System.out.print(this.getCell(i,j));
                if (j != this.tabCells[0].length-1) System.out.print(",");
            }
            System.out.println("]");
        }
        return "";
    }
    // Affichage compact (gain 2 lignes)
    public String AfficherEtatCompact() {
        for (int i = 0; i < this.tabCells.length; i++) {
            System.out.print("[");
            for (int j = 0; j < this.tabCells[0].length; j++) {
                System.out.print(this.getCell(i,j));
                if (j != this.tabCells[0].length-1) System.out.print(",");
            }
            if ( i == 0 ){
                System.out.print("]");
                System.out.println("N°"+idEtat);
            }else {
                System.out.println("]");
            }
        }
        return "";
    }


    /****** GETTERS / SETTERS ******/
    public char[][] getTabCells() {
        return tabCells;
    }
    public char getCell(int x, int y ){
        return this.tabCells[x][y];
    }
    public int getPosXCaseVide() {
        return posXCaseVide;
    }
    public int getPosYCaseVide() {
        return posYCaseVide;
    }
    public int getIdEtat() {
        return idEtat;
    }

    public EtatTaquin getPreviousState() {
        return previousState;
    }
    public int getCoutDeplacement() {
        return coutDeplacement;
    }

    /****** Convertir le tabCells sous forme de ArrayList<Character> pour faciliter la structure Fermer ***** */
    //Explication : les hashCode() pour char[][] ne fonctionne pas bien.
    //Ces deux attributs servent pour la rapidité dans la classe Fermer.java :
    private ArrayList<Character> listFormats =new ArrayList<>();
    private int hashValue;
    private void faciliterLaStructureFermer(){
        this.listFormats = Fermer.convertToArrayList(this.getTabCells());
        this.hashValue = listFormats.hashCode();
    }
    public ArrayList<Character> getListFormats() {
        return this.listFormats;
    }
    public int getHashCode() {
        return hashValue;
    }




    private EtatTaquin(char[][] tabCells, EtatTaquin previousState) {
        this.tabCells = tabCells;
        this.initCaseVide();
        this.idEtat = nbEtat;
        nbEtat++;
        this.coutDeplacement = 0;
        this.previousState = previousState;
        System.out.println("EtatTaquin Crée avec  n°"+this.idEtat);
        //faciliterLaStructureFermer();
    }

    /****** Recuperation et construction des etats voisins ******/
    // Recupere les etats voisins de l'etat, utile pour les parcours

    /** NE SERT PLUS A RIEN CAR ON A CREER LA CLASSE UsineVoisins qui permet de prendre en plus en compte le cout de deplacement
     * Elle est placé en private **/
    private List<EtatTaquin> getEtatVoisin() {
        ArrayList<EtatTaquin> voisins = new ArrayList<>();
        List<Character> voisinsCaseVide = getVoisinsCaseVide();
        for (int i = 0; i < voisinsCaseVide.size(); i++) {
            if (voisinsCaseVide.get(i) == ' ') {
                voisins.add(null);
            }
            else {
                switch (i) {
                    case 0 -> { voisins.add(permuteNord()); }
                    case 1 -> { voisins.add(permuteSud()); }
                    case 2 -> { voisins.add(permuteOuest()); }
                    case 3 -> { voisins.add(permuteEst()); }
                }
            }
        }
        return voisins;
    }

    private EtatTaquin permuteNord() {
        char[][] nouveauTabCell = DeepCopyMatrix.deepCopyCharMatrix(this.tabCells);
        nouveauTabCell[posXCaseVide][posYCaseVide] = tabCells[posXCaseVide-1][posYCaseVide];
        nouveauTabCell[posXCaseVide-1][posYCaseVide] = ' ';
        return new EtatTaquin(nouveauTabCell,this);
    }
    private EtatTaquin permuteSud() {
        char[][] nouveauTabCell = DeepCopyMatrix.deepCopyCharMatrix(this.tabCells);
        nouveauTabCell[posXCaseVide][posYCaseVide] = tabCells[posXCaseVide+1][posYCaseVide];
        nouveauTabCell[posXCaseVide+1][posYCaseVide] = ' ';
        return new EtatTaquin(nouveauTabCell,this);
    }
    private EtatTaquin permuteOuest() {
        char[][] nouveauTabCell = DeepCopyMatrix.deepCopyCharMatrix(this.tabCells);
        nouveauTabCell[posXCaseVide][posYCaseVide] = tabCells[posXCaseVide][posYCaseVide-1];
        nouveauTabCell[posXCaseVide][posYCaseVide-1] = ' ';
        return new EtatTaquin(nouveauTabCell,this);
    }
    private EtatTaquin permuteEst() {
        char[][] nouveauTabCell = DeepCopyMatrix.deepCopyCharMatrix(this.tabCells);
        nouveauTabCell[posXCaseVide][posYCaseVide] = tabCells[posXCaseVide][posYCaseVide+1];
        nouveauTabCell[posXCaseVide][posYCaseVide+1] = ' ';
        return new EtatTaquin(nouveauTabCell,this);
    }

}
