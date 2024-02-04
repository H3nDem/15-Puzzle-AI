public interface CoutDeplacement {
    /***** Direction permet d'indiquer la direction pour éventuellement modifier l'heuristique spécialement ******/
    /***** Direction N'est pas utiliser dans les classes Gratuit et Simple CoutDeplacement ******/
    public enum Direction {NORD,SUD,OUEST,EST,AUCUNE_IMPORTANCE}
    public int augmenterCoutDeplacement(EtatTaquin etatInitial, char[][] etatSuivant,Direction direction);


}
