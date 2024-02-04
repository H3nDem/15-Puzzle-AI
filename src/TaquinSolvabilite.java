import java.util.ArrayList;
import java.util.HashMap;

public class TaquinSolvabilite {
    /****** Classe permettant de determiner si un jeu de taquin est solvable c'est à dire si il peut aller de la congifuration initial à la configuration final ******/
    /****** 📜📜📜📜📜⬇️DOCUMENTATION Déplier ci-dessous⬇️📜📜📜📜📜******/
    /*
    VIDEO YOUTUBE Utilisé : https://youtu.be/YI1WqYKHi78 - Pourquoi ce puzzle est-il impossible? — Numberphile
    Wikipedia : https://fr.wikipedia.org/wiki/Taquin

    Voici aussi ce que l'o
     Lorsque la taille du jeu de taquin n'est pas fixée à 3, la méthode pour déterminer la solvabilité peut être légèrement différente.
     Voici une approche générale pour les jeux de taquin de taille n x m où n peut être différent de 3 :
        1.Comptez le nombre d'inversions dans l'état initial du jeu de taquin, tout comme dans la méthode précédente.
        Une inversion se produit lorsqu'un nombre est situé à un emplacement différent de sa position finale souhaitée et
        qu'un nombre plus petit que lui se trouve entre eux.

        2.Vérifiez si la somme des inversions dans l'état initial et la distance (en nombre de déplacements) entre
        la case vide et sa position finale souhaitée ont la même parité (c'est-à-dire qu'ils sont soit tous les deux pairs,
        soit tous les deux impairs).

        3.Si la parité des inversions et la parité de la distance entre la case vide et sa position finale sont les mêmes,
        alors le jeu de taquin est solvable. Sinon, s'ils sont différents, le jeu de taquin est insolvable.

     Il existe plusieurs méthodes pour calculer la distance entre la case vide et sa position finale,
     telles que la distance de Manhattan ou la distance de Hamming. La distance de Manhattan est généralement utilisée pour les jeux de taquin,
     et elle est calculée comme la somme des distances horizontale et verticale entre la position actuelle de chaque nombre
     et sa position finale souhaitée. La parité de cette distance est utilisée pour déterminer la solvabilité du jeu.

     Il est important de noter que pour les jeux de taquin de taille n x m où n et m sont tous les deux pairs,
     il peut exister des configurations qui ont la même parité d'inversions et de distance, mais qui ne sont pas solvables.
     Dans de tels cas, le jeu de taquin est insolvable même si les parités des inversions et de la distance sont les mêmes.
     Cependant, ces configurations sont rares et la méthode générale décrite ci-dessus est valable pour la plupart des jeux de taquin de taille n x m.
     */
    /** ATTRIBUTS **/
    private EtatTaquin etatInit;
    private EtatTaquin etatFinal;
    private char[][] etatInitTabCells;
    private char[][] etatFinalTabCells;
    private ArrayList<Character> listMelange = new ArrayList<>();
    private ArrayList<Character> listeOrdonne = new ArrayList<>();

    private int nbInversions;
    //private int nbColonnes;
    private int distanceManhattanAvecPosFinal;

    private boolean isNbInversionsEven;
    //private boolean isNbColonnesEven;
    private boolean isDistanceManhattanEven;
    private boolean isSolvable;
    /** CONSTRUCTEUR **/
    public TaquinSolvabilite(EtatTaquin etatInit, EtatTaquin etatFinal) {
        this.etatInit = etatInit;
        this.etatFinal = etatFinal;
        calculateIfSolvable();
    }
    private boolean calculateIfSolvable(){
        //ORDRE IMPORTANT !!!
        initTabCells();
        initArrayLists();
        System.out.println(listMelange);
        System.out.println(listeOrdonne);
        calculateNbInversions();
        System.out.println(nbInversions);
        //calculateNbColonnes(); //NE fonctionne que quand n = 3 sur un taquin n x m
        calculDistanceDeManhattanDeCaseVideAvecLaPosFinal();
        System.out.println("distance de manhattan"+distanceManhattanAvecPosFinal);
        fillBoolean();

        isSolvable = ( isNbInversionsEven && isDistanceManhattanEven )
                || ( !isNbInversionsEven && !isDistanceManhattanEven );
        return isSolvable;
    }

    /** Methodes lié à calculateIfSolvable **/
    private void calculDistanceDeManhattanDeCaseVideAvecLaPosFinal() {
        //Positions de la case vide dans l'état initial et l'état final :
        int posXInit = etatInit.getPosXCaseVide();
        int posYInit = etatInit.getPosYCaseVide();
        int posXFinal = etatFinal.getPosXCaseVide();
        int posYFinal= etatFinal.getPosYCaseVide();

        distanceManhattanAvecPosFinal =
                Math.abs(posXFinal-posXInit) + Math.abs(posYFinal - posYInit);

    }

    private void initTabCells() {
        this.etatInitTabCells = DeepCopyMatrix.deepCopyCharMatrix(this.etatInit.getTabCells());
        this.etatFinalTabCells = DeepCopyMatrix.deepCopyCharMatrix(this.etatFinal.getTabCells());
    }
    private void initArrayLists() {
        for (int i = 0; i < etatInitTabCells.length; i++) {
            for (int j = 0; j < etatInitTabCells[0].length; j++) {
                listMelange.add(etatInitTabCells[i][j]);
                listeOrdonne.add(etatFinalTabCells[i][j]);
            }
        }
    }

    private void calculateNbInversions(){
        HashMap<Character,Integer> mapCharPos = new HashMap<Character,Integer>();
        for (int i = 0; i < listeOrdonne.size(); i++) {
            mapCharPos.put(listeOrdonne.get(i),i);
        }
        //System.out.println(mapCharPos);
        int nbInversions = 0;
        for (int i = 0; i < listMelange.size(); i++) {
            Character characterCourant = listMelange.get(i);
            //System.out.println("characterCourant: " + characterCourant);
            for (int j = i; j < listMelange.size(); j++) {
                Character characterCourant2 = listMelange.get(j);
                //System.out.println("secondeBoucle: " + characterCourant2);
                if ( mapCharPos.get(characterCourant) > mapCharPos.get(characterCourant2) ){
                    nbInversions++;
                    //System.out.println("mapCharPos.get(charCourant)="+mapCharPos.get(characterCourant));
                    //System.out.println("mapCharPos.get(charCourant2)="+mapCharPos.get(characterCourant2));
                    //System.out.println("nbInversions"+nbInversions);
                }
            }
        }
        this.nbInversions = nbInversions;
        //System.out.println(nbInversions);
    }

    //NE FONCTIONNE QUE QUAND N = 3 sur un taquin n x m
//    private void calculateNbColonnes(){
//        this.nbColonnes = this.etatFinalTabCells[0].length;
//    }

    private void fillBoolean(){
        isNbInversionsEven = this.nbInversions % 2 == 0;
        //isNbColonnesEven = this.distanceManhattanAvecPosFinal % 2 == 0;
        isDistanceManhattanEven = this.distanceManhattanAvecPosFinal % 2 == 0;

    }

    public boolean isSolvable(){
        return isSolvable;
    }



}
