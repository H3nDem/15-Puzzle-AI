public class SimpleCoutDeplacement  implements CoutDeplacement{

    @Override
    public int augmenterCoutDeplacement(EtatTaquin etatInitial, char[][] etatSuivant,Direction direction) {
        return etatInitial.getCoutDeplacement()+1;
    }
}
