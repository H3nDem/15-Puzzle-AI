import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Fermer {



    /****** Ajout / Verification de presence d'un etat dans la structure Fermer ******/

    /**Amelioration de la rapidité avec une hashMap au lieu de parcourir toute la liste **/
    public static HashMap<Integer,ArrayList<EtatTaquin>> hashMapFERMER = new HashMap<>();
    public static final List<Integer> ID_FERMER = new ArrayList<>();

    public static void  ajouterDansFermer(EtatTaquin etatTaquin) {
        //ArrayList<Character> list = Fermer.convertToArrayList(etatTaquin.getTabCells());

        //ArrayList<Character> list = etatTaquin.getListFormats();
        //int hashValue = list.hashCode();
        int hashValue = etatTaquin.getHashCode();
        if ( !hashMapFERMER.containsKey(hashValue)){
            hashMapFERMER.put(hashValue, new ArrayList<EtatTaquin>());
            hashMapFERMER.get(hashValue).add(etatTaquin);
            //ID_FERMER.add(etatTaquin.getIdEtat());
        }else {
            ArrayList<EtatTaquin> arrayList = hashMapFERMER.get(hashValue);
            for (EtatTaquin etatTaquin1 : arrayList){
                if (etatTaquin1.estEquivalentA(etatTaquin)){
                    return;
                }
            }
            hashMapFERMER.get(hashValue).add(etatTaquin);
            //ID_FERMER.add(etatTaquin.getIdEtat());
        }
    }
    public static boolean estDansFermer(EtatTaquin etatTaquin){
        //ArrayList<Character> list = Fermer.convertToArrayList(etatTaquin.getTabCells());
        //ArrayList<Character> list = etatTaquin.getListFormats();
        //int hashValue = list.hashCode();
        int hashValue = etatTaquin.getHashCode();
        if ( !hashMapFERMER.containsKey(hashValue) ){
            return false;
        }else{
            ArrayList<EtatTaquin> arrayList = hashMapFERMER.get(hashValue);
            for (EtatTaquin etatTaquin1 : arrayList ){
                if (etatTaquin1.estEquivalentA(etatTaquin)){
                    return true;
                }
            }
            return false;
        }
    }
    public static ArrayList<Character> convertToArrayList(char[][] tab){
        ArrayList<Character> arrayList = new ArrayList<Character>(tab.length);

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                arrayList.add(tab[i][j]);
            }
        }
        return arrayList;
    }

}
