package assignment5;

/** Harsha critter 2:
 *
 *This critter walks around and fights only goblins. He is a goblin fighter. He walks in smaller increments
 *
 */

public class Critter4 extends Critter {

    @Override
    public CritterShape viewShape() {
        return CritterShape.SQUAREalt;
    }

    public javafx.scene.paint.Color viewColor() {
        return javafx.scene.paint.Color.rgb(0, 50, 50);
    }
    public javafx.scene.paint.Color viewOutlineColor(){return UI.worldColorRGB;}

    @Override
    public void doTimeStep() {
        walk(Critter.getRandomInt(5));//He doesn't walk that much
    }

    @Override
    public boolean fight(String opponent) {
        return opponent.equals("G");//only fights goblins cuz he is like that
    }

    @Override
    public String toString() {
        return "*";
    }

}
