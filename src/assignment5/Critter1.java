package assignment5;

/** Safin critter 1:
 *
 * indecisive and randomly sprinting for clovers
 *
 */

public class Critter1 extends Critter {

    @Override
    public CritterShape viewShape() {
        return CritterShape.TRIANGLE;
    }

    public javafx.scene.paint.Color viewColor() {
        return javafx.scene.paint.Color.rgb(238, 141, 67);
    }

    @Override
    public void doTimeStep() {
        //run(Critter.getRandomInt(7)); //sprints a random direction
        if (Critter.getRandomInt(10) > 8) {
            Critter1 child = new Critter1();
            look(Critter.getRandomInt(8), child.fight("G")); //look implement
        }
    }

    @Override
    public boolean fight(String opponent) {
        //return opponent.equals("@") && Critter.getRandomInt(1) == 1; //randomly goes for Clovers
        if (opponent.equals("@")) {
            return true;
        } else {
            if (getEnergy() > Params.START_ENERGY / 2) {
                return true;
            }

            boolean found = false;
            while (!found) {
                int dir = Critter.getRandomInt(7);
                if (look(dir, false) == null) {
                    found = true;
                    walk(dir);
                } else if (look(dir, true) == null) {
                    found = true;
                    run(dir);
                }
            }
            return false;
        }
    }

    @Override
    public String toString() {
        return "#";
    }
}
