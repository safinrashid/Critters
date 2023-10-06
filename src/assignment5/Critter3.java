package assignment5;

/** Harsha critter 1:
 *
 * Sprints in a random direction and always fights. This critter loves to fight random things. Sprints a random dist 7
 *
 */

public class Critter3 extends Critter {

    @Override
    public CritterShape viewShape() {
        return CritterShape.DIAMOND;
    }

    public javafx.scene.paint.Color viewColor() {
        return javafx.scene.paint.Color.rgb(140, 68, 140);
    }

    @Override
    public void doTimeStep() {
        run(Critter.getRandomInt(7)); //sprints a random direction
    }

    @Override
    public boolean fight(String opponent) {
        return true;
    }

    @Override
    public String toString() {
        return "!";
    }

}
