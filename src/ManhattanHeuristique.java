/*
    Heuristique qui calcule la distance entre la case contenant "char"
    d'un etat et la case contenant ce meme char de l'etat final
*/

public class ManhattanHeuristique implements Heuristique {
    private EtatTaquin etatFinal;
    public ManhattanHeuristique(EtatTaquin etatFinal) {
        this.etatFinal = etatFinal;
    }

    public int calculHeuristique(EtatTaquin etat) {
        //System.out.println("nouveau calculheuristique");
        int valeurH = 0;
        char[][] etatTabCell = etat.getTabCells();
        for (int i = 0; i < etat.getTabCells().length; i++) {
            for (int j = 0; j < etat.getTabCells()[i].length; j++) {
                char charDansEtat = etat.getCell(i,j); // CHAR dans ETAT a la position i,j
                //System.out.println("charDansEtat "+charDansEtat+" "+i+" "+j);
                if (charDansEtat == ' ') continue;
                int[] posDansEtatFinal = getPosition(etatFinal.getTabCells(),charDansEtat); // Coords equivalente de CHAR dans ETAT FINAL
                //System.out.println("char dans l'Etat: " + charDansEtat + ", pos: X=" + i + " Y=" + j);
                //System.out.println("char dans l'Etat Final: " + etatFinal.getCell(posDansEtatFinal[0],posDansEtatFinal[1]) + ", pos: X=" + posDansEtatFinal[0] + " Y=" + posDansEtatFinal[1]);
                //System.out.println("Distance entre les 2 cases: " + distance(etat.getTabCell(), etat.getCell(i,j), etatFinal.getCell(posDansEtatFinal[0],posDansEtatFinal[1])) + "\n");
                valeurH += distance(etat.getTabCells(), charDansEtat, etatFinal.getCell(posDansEtatFinal[0],posDansEtatFinal[1]));
                //System.out.println("Valeur = " + valeurH);
            }
        }
        return valeurH;
    }

    private int distance(char[][] e, char c, char cFinal) {
        int[] pos = getPosition(e,c);
        int[] posFinal = getPosition(etatFinal.getTabCells(),cFinal);
        return Math.abs(posFinal[0]-pos[0]) + Math.abs(posFinal[1]-pos[1]);
    }

    private int[] getPosition(char[][] etat, char c) {
        int[] pos = new int[2];
        for (int x = 0; x < etat.length; x++) {
            for (int y = 0; y < etat[0].length; y++) {
                if (etat[x][y] == c) {
                    pos[0] = x; pos[1] = y;
                    return pos;
                }
            }
        }
        return pos;
    }
}
