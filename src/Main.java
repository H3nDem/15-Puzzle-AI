import java.io.FileNotFoundException;

/*
    Programme principal permettant de lancer les differents
    parcours pour resoudre les grilles du jeu de taquin
    en un temps et nombre de coups differents
*/

public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        String filename = "src/grilles/taquin_2x3.grid";
//        parcoursLargeur(filename);
//        parcoursProfondeur(filename);
        parcoursMeilleurDabord(filename);
    }

    //3x3  :      80 coups /  0.034s
    //3x3c : 150 382 coups / 269.49s
    public static void parcoursLargeur(String filename) throws FileNotFoundException, InterruptedException {
        JeuTaquin jeuTaquin = new JeuTaquin(filename);
        jeuTaquin.setHeuristique(new ManhattanHeuristique(jeuTaquin.getEtatFinal()));
        jeuTaquin.setCoutDeplacement(new GratuitCoutDeplacement());
        jeuTaquin.setOuvert(new OuvertFile(jeuTaquin.getEtatFinal() , jeuTaquin.getHeuristique() , jeuTaquin.getCoutDeplacement() ));

        long begin = System.nanoTime();
        jeuTaquin.faireParcours();
        long end = System.nanoTime();
        System.out.println("BFS Execution time : " + (end-begin) / 1e9 + "s");
    }

    //3x3  :      38 coups /  0.025s
    //3x3c :  65 662 coups /  99.15s
    public static void parcoursProfondeur(String filename) throws FileNotFoundException, InterruptedException {
        JeuTaquin jeuTaquin = new JeuTaquin(filename);
        jeuTaquin.setHeuristique(new ManhattanHeuristique(jeuTaquin.getEtatFinal()));
        jeuTaquin.setCoutDeplacement(new GratuitCoutDeplacement());
        jeuTaquin.setOuvert(new OuvertPile(jeuTaquin.getEtatFinal() , jeuTaquin.getHeuristique() ,jeuTaquin.getCoutDeplacement() ));

        long begin = System.nanoTime();
        jeuTaquin.faireParcours();
        long end = System.nanoTime();
        System.out.println("DFS Execution time : " + (end-begin) / 1e9 + "s");
    }

    //3x3  :       7 coups /  0.015s
    //3x3c :     152 coups /    0.1s
    public static void parcoursMeilleurDabord(String filename) throws FileNotFoundException, InterruptedException {
        JeuTaquin jeuTaquin = new JeuTaquin(filename);
        jeuTaquin.setHeuristique(new ManhattanHeuristique(jeuTaquin.getEtatFinal()));
        //jeuTaquin.setCoutDeplacement(new GratuitCoutDeplacement());
        jeuTaquin.setCoutDeplacement(new SimpleCoutDeplacement());
        jeuTaquin.setOuvert(new OuvertMeilleurDabord(jeuTaquin.getEtatFinal() , jeuTaquin.getHeuristique() ,jeuTaquin.getCoutDeplacement() ));

        long begin = System.nanoTime();
        jeuTaquin.faireParcours();
        long end = System.nanoTime();
        System.out.println("MDA Execution time : " + (end-begin) / 1e9 + "s");
    }

}