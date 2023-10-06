package assignment5;

/** Safin critter 2:
 *
 * slow but is ready to fight any goblins
 *
 */

public class Critter2 extends Critter {

    @Override
    public CritterShape viewShape() {
        return CritterShape.STAR;
    }

    public javafx.scene.paint.Color viewColor() {
        return javafx.scene.paint.Color.rgb(175, 175, 0);
    }

    @Override
    public void doTimeStep() {
        //walk(Critter.getRandomInt(7)); //walk a random direction
        int rand = Critter.getRandomInt(7);
        walk(rand);
        if(Critter.getRandomInt(100) > 95) {
            Critter2 child = new Critter2();
            reproduce(child, Critter.getRandomInt(8));
        }
    }

    @Override
    public boolean fight(String opponent) {
        return opponent.equals("G"); //has beef with Goblins
    }

    @Override
    public String toString() {
        return "^";
    }

}
