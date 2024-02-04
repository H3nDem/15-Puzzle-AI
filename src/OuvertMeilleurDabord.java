import java.util.*;

public class OuvertMeilleurDabord extends Ouvert {
    //Possibles améliorations
    //	Une énorme amélioration en gain de temps serait à faire dans la classe OuvertMeilleurDabord où au lieu d'utiliser
    //	une ArrayList il faudrait utilisé une hashMap. Puisque là nous ajoutons un élément dans l'arrayList à une position
    //	donné et cela décale tous les éléments. Ce qui coute très cher pour un très grand tableau.
    //	Cela doit probablement impacté le 5x5d.

    public OuvertMeilleurDabord(EtatTaquin etatFinal, Heuristique heuristique,CoutDeplacement coutDeplacement) {
        super(etatFinal, heuristique,coutDeplacement);
        this.etatsCollection = new ArrayList<>();
    }



    @Override
    public void ajouterEtat(EtatTaquin etatTaquin) {
        boolean isAdded = false;
        for (int i = 0; i < etatsCollection.size(); i++) { // placer par ordre d'heuristique dans la liste
            //Auterment dit : si e CoutDepla + heuristique < e CoutDepla + heuristique de la liste.get(i) alors ...
            if ( ( etatTaquin.getCoutDeplacement()
                    +this.heuristique.calculHeuristique(etatTaquin) )
                    < ( etatTaquin.getCoutDeplacement()
                    +this.heuristique.calculHeuristique( ( ( ArrayList<EtatTaquin> ) etatsCollection ).get(i)) ) ){
                //Ajoute l'état taquin à la position i mais n'écrase pas la valeur qui était déjà à cette position
                //Décale toutes les autres valeurs
                ((ArrayList<EtatTaquin>)etatsCollection).add(i, etatTaquin);
                isAdded = true;
                break;
            }
        }
        if (!isAdded) {
            ((ArrayList<EtatTaquin>)etatsCollection).add(etatTaquin);
        }
    }
    @Override
    public void supprimerEtat(EtatTaquin etat) {
        ((ArrayList<EtatTaquin>)etatsCollection).remove(0);
    }
    @Override
    public EtatTaquin teteCollectionOuvert() {
        return ((ArrayList<EtatTaquin>)etatsCollection).get(0);
    }
    @Override
    public void ajoutEtatsVoisinsValables(EtatTaquin etatTaquinGrille) {

        for (EtatTaquin etatTaquinGrille1 : UsineVoisins.getEtatVoisin(etatTaquinGrille,coutDeplacement)){
            if (etatTaquinGrille1 != null && !Fermer.estDansFermer(etatTaquinGrille1)){
                System.out.print("N°"+etatTaquinGrille1.getIdEtat()+" ");
                System.out.print("Cout="+etatTaquinGrille1.getCoutDeplacement()+" ");
                System.out.println("H="+this.heuristique.calculHeuristique(etatTaquinGrille1));
                ajouterEtat(etatTaquinGrille1);
            }
        }

    }

    public void AfficherList(){
        System.out.print("[");
        for ( EtatTaquin etatTaquin : ((ArrayList<EtatTaquin>)etatsCollection) ){
            System.out.print(etatTaquin.getIdEtat()+",");
        }
        System.out.println("]");
    }


}
