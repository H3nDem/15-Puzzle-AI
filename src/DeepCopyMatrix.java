/*
    Permet de faire une copie d'un tableau en deux dimension

    Si on fait "char[][] chars2 = chars1", cela ne fait qu'une reference
    vers l'objet chars1 mais n'est pas une copie, du coup lorqu'on
    modifie chars2 cela modifie egalement chars1 vu que c'est le même objet.

    Ce qui posait probleme avec les methodes de permutation
    de la case vide avec son voisin
*/

public class DeepCopyMatrix {
    // Pour int[][]
    public static int[][] deepCopyIntMatrix(int[][] input) {
        if (input == null)
            return null;
        int[][] result = new int[input.length][];
        for (int r = 0; r < input.length; r++) {
            result[r] = input[r].clone();
        }
        return result;
    }
    // Pour char[][]
    public static char[][] deepCopyCharMatrix(char[][] input) {
        if (input == null)
            return null;
        char[][] result = new char[input.length][];
        for (int r = 0; r < input.length; r++) {
            result[r] = input[r].clone();
        }
        return result;
    }
}
