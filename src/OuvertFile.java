import java.util.*;

import static java.lang.Thread.sleep;

public class OuvertFile extends Ouvert {
    //Queue<EtatTaquin> FILE = new ArrayDeque<>();

    public OuvertFile(EtatTaquin etatFinal, Heuristique heuristique,CoutDeplacement coutDeplacement) {
        super(etatFinal, heuristique,coutDeplacement);
        this.etatsCollection = new ArrayDeque<>();
    }


    @Override
    public void ajouterEtat(EtatTaquin etat) {
        ( (ArrayDeque<EtatTaquin> )etatsCollection ).offer(etat);
    }

    @Override
    public void supprimerEtat(EtatTaquin etat) {
        ( (ArrayDeque<EtatTaquin> )etatsCollection ).pop();
    }

    @Override
    public EtatTaquin teteCollectionOuvert() {
        return ( (ArrayDeque<EtatTaquin> )etatsCollection ).getFirst();
    }

    @Override
    public void ajoutEtatsVoisinsValables(EtatTaquin etatTaquinGrille) {
        System.out.println("permutations");
        //List<EtatTaquin> etatsVoisins = etatTaquinGrille.getEtatVoisin();
        List<EtatTaquin> etatsVoisins = UsineVoisins.getEtatVoisin(etatTaquinGrille,coutDeplacement);
        etatsVoisins.removeIf(Objects::isNull);
        Collections.sort(etatsVoisins, new Comparator<EtatTaquin>() {
            @Override
            public int compare(EtatTaquin o1, EtatTaquin o2) {
                // Comparer les objets en utilisant la méthode calc()
                return ( o1.getCoutDeplacement()+heuristique.calculHeuristique(o1) )
                        - ( o2.getCoutDeplacement()+heuristique.calculHeuristique(o2) );
            }
        });
        System.out.println("ETAT VOISINS=>");
        for ( EtatTaquin etatTaquin : etatsVoisins ){
            System.out.print(etatTaquin);
            System.out.print(etatTaquin.getCoutDeplacement()+" ");
            System.out.println(heuristique.calculHeuristique(etatTaquin));
        }
        System.out.println("<==FIN");
        for ( EtatTaquin etat : etatsVoisins){
            if (!Fermer.estDansFermer(etat)){
                ajouterEtat(etat);
            }
            else {
                System.out.println( "Etat n°"+etat.getIdEtat()+" déjà dans fermer");
            }
        }
    }

}
