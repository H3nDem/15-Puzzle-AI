import java.util.ArrayList;
import java.util.List;

public class UsineVoisins {
    /***** Cette classe permet la création des voisins d'un etatTaquin ******/

    /***** Elle a été rajouté car ces méthodes présentes dans la classe EtatTaquin imposait
     * alors de devoir spécifier dans un taquin le type de CoutDeplacement utilisé ******/
    public static List<EtatTaquin> getEtatVoisin(EtatTaquin etatTaquin,CoutDeplacement coutDeplacement) {
        ArrayList<EtatTaquin> voisins = new ArrayList<>();
        List<Character> voisinsCaseVide = etatTaquin.getVoisinsCaseVide();
        for (int i = 0; i < voisinsCaseVide.size(); i++) {
            if (voisinsCaseVide.get(i) == ' ') {
                voisins.add(null);
            }
            else {
                switch (i) {
                    case 0 -> { voisins.add(permuteNord(etatTaquin,coutDeplacement)); }
                    case 1 -> { voisins.add(permuteSud(etatTaquin,coutDeplacement)); }
                    case 2 -> { voisins.add(permuteOuest(etatTaquin,coutDeplacement)); }
                    case 3 -> { voisins.add(permuteEst(etatTaquin,coutDeplacement)); }
                }
            }
        }
        return voisins;
    }
    private static EtatTaquin permuteNord(EtatTaquin etatTaquin,CoutDeplacement coutDeplacement) {
        char[][] nouveauTabCell = DeepCopyMatrix.deepCopyCharMatrix(etatTaquin.getTabCells());
        char[][] ancientTabCell = etatTaquin.getTabCells();
        int posXCaseVide = etatTaquin.getPosXCaseVide();
        int posYCaseVide = etatTaquin.getPosYCaseVide();
        nouveauTabCell[posXCaseVide][posYCaseVide] = ancientTabCell[posXCaseVide-1][posYCaseVide];
        nouveauTabCell[posXCaseVide-1][posYCaseVide] = ' ';

        int coutDeplacementAugmenter = coutDeplacement.augmenterCoutDeplacement(
                etatTaquin,nouveauTabCell, CoutDeplacement.Direction.NORD);
        return new EtatTaquin(nouveauTabCell,etatTaquin, coutDeplacementAugmenter);
    }
    private static EtatTaquin permuteSud(EtatTaquin etatTaquin,CoutDeplacement coutDeplacement) {
        char[][] nouveauTabCell = DeepCopyMatrix.deepCopyCharMatrix(etatTaquin.getTabCells());
        char[][] ancientTabCell = etatTaquin.getTabCells();
        int posXCaseVide = etatTaquin.getPosXCaseVide();
        int posYCaseVide = etatTaquin.getPosYCaseVide();
        nouveauTabCell[posXCaseVide][posYCaseVide] = ancientTabCell[posXCaseVide+1][posYCaseVide];
        nouveauTabCell[posXCaseVide+1][posYCaseVide] = ' ';

        int coutDeplacementAugmenter = coutDeplacement.augmenterCoutDeplacement(
                etatTaquin,nouveauTabCell, CoutDeplacement.Direction.SUD);
        return new EtatTaquin(nouveauTabCell,etatTaquin, coutDeplacementAugmenter);
    }
    private static EtatTaquin permuteOuest(EtatTaquin etatTaquin,CoutDeplacement coutDeplacement) {
        char[][] nouveauTabCell = DeepCopyMatrix.deepCopyCharMatrix(etatTaquin.getTabCells());
        char[][] ancientTabCell = etatTaquin.getTabCells();
        int posXCaseVide = etatTaquin.getPosXCaseVide();
        int posYCaseVide = etatTaquin.getPosYCaseVide();
        nouveauTabCell[posXCaseVide][posYCaseVide] = ancientTabCell[posXCaseVide][posYCaseVide-1];
        nouveauTabCell[posXCaseVide][posYCaseVide-1] = ' ';

        int coutDeplacementAugmenter = coutDeplacement.augmenterCoutDeplacement(
                etatTaquin,nouveauTabCell, CoutDeplacement.Direction.OUEST);
        return new EtatTaquin(nouveauTabCell,etatTaquin, coutDeplacementAugmenter);
    }
    private static EtatTaquin permuteEst(EtatTaquin etatTaquin, CoutDeplacement coutDeplacement) {
        char[][] nouveauTabCell = DeepCopyMatrix.deepCopyCharMatrix(etatTaquin.getTabCells());
        char[][] ancientTabCell = etatTaquin.getTabCells();
        int posXCaseVide = etatTaquin.getPosXCaseVide();
        int posYCaseVide = etatTaquin.getPosYCaseVide();
        nouveauTabCell[posXCaseVide][posYCaseVide] = ancientTabCell[posXCaseVide][posYCaseVide+1];
        nouveauTabCell[posXCaseVide][posYCaseVide+1] = ' ';

        int coutDeplacementAugmenter = coutDeplacement.augmenterCoutDeplacement(
                etatTaquin,nouveauTabCell, CoutDeplacement.Direction.EST);
        return new EtatTaquin(nouveauTabCell,etatTaquin, coutDeplacementAugmenter);
    }


}
