import java.util.Collection;

/*
    Structure modifiant le type de parcours
 */
public abstract class Ouvert {
    protected CoutDeplacement coutDeplacement;
    protected Heuristique heuristique;
    protected EtatTaquin etatFinal; // etat final arretant le parcours si decouvert

    public Ouvert(EtatTaquin etatFinal, Heuristique heuristique,CoutDeplacement coutDeplacement) {
        this.etatFinal = etatFinal;
        this.heuristique = heuristique;
        this.coutDeplacement = coutDeplacement;
    }

    Collection<EtatTaquin> etatsCollection;
    public void parcours(EtatTaquin etatInit){
        int nbAnalyseEtatsDuGraphe = 0;
        ajouterEtat(etatInit);//Ouvert <-- {eInit}
        while ( !etatsCollection.isEmpty() ){//TANT QUE Ouvert != vide

            EtatTaquin etat = teteCollectionOuvert();
            nbAnalyseEtatsDuGraphe++;

            if (etat.estEquivalentA(this.etatFinal)) { // Si e est final on s'arrete
                System.out.println(etat);
                System.out.println("Trouvé en " + nbAnalyseEtatsDuGraphe + " analyses d'etats");
                CheminAfficheur.printResultParcours(etat);
                break;
            }

            supprimerEtat(etat);
            Fermer.ajouterDansFermer(etat);

            ajoutEtatsVoisinsValables(etat);
        }
    }
    public abstract void ajouterEtat(EtatTaquin etat);
    public abstract void supprimerEtat(EtatTaquin etat);
    public abstract EtatTaquin teteCollectionOuvert();
    public abstract void ajoutEtatsVoisinsValables(EtatTaquin etat);

}
