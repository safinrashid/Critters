/* CRITTERS GUI <MyClass.java>
 * EE422C Project 5 submission by
 * Replace <...> with your actual data.
 * <Student1 Name> Safin Rashid
 * <Student1 EID> srr3288
 * <Student1 5-digit Unique No.> 17155
 * Slip days used: <0>
 * Spring 2023
 */

package assignment5;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.*;

/*
 * See the PDF for descriptions of the methods and fields in this
 * class.
 * You may add fields, methods or inner classes to Critter ONLY
 * if you make your additions private; no new public, protected or
 * default-package code or data can be added to Critter.
 */

public abstract class Critter {

    /* START --- NEW FOR PROJECT 5 */

    public static int spotSize = Math.min(800/Params.WORLD_WIDTH, 900/Params.WORLD_HEIGHT);

    public enum CritterShape {
        CIRCLE,
        SQUARE,
        TRIANGLE,
        DIAMOND,
        STAR,
        SQUAREalt
    }

    public static int cloverCount = 0, goblinCount = 0, critter1Count = 0,
            critter2Count = 0, critter3Count = 0, critter4Count = 0;

    public static boolean newcircle = true, newsquare = true, newdiamond = true, newstar = true, newtriangle = true, newsquarealt = true;
    public static int startRow = 14, currRow = startRow;

    /* the default color is white, which I hope makes critters invisible by default
     * If you change the background color of your View component, then update the default
     * color to be the same as you background
     * critters must override at least one of the following three methods, it is not
     * proper for critters to remain invisible in the view
     * If a critter only overrides the outline color, then it will look like a non-filled
     * shape, at least, that's the intent. You can edit these default methods however you
     * need to, but please preserve that intent as you implement them.
     */
    public javafx.scene.paint.Color viewColor() {
        return UI.worldColorRGB;
    }

    public javafx.scene.paint.Color viewOutlineColor() {
        return viewColor();
    }

    public javafx.scene.paint.Color viewFillColor() {
        return viewColor();
    }

    public abstract CritterShape viewShape();

    //TODO : test and implement look() in Critters
    protected final String look(int direction, boolean steps) {
        energy -= assignment5.Params.LOOK_ENERGY_COST;
        int dist = 1, x = x_coord, y = y_coord;
        if(steps) dist++;

        if(direction == 0 || direction == 1 || direction == 7){ //west
            x += dist;
            if(direction == 1) y -= dist; //sw
            if(direction == 7) y += dist; //nw
        }
        else if(direction == 3 || direction == 4 || direction == 5){ //east
            x -= dist;
            if(direction == 3) y -= dist; //se
            if(direction == 5) y += dist; //ne
        }else if(direction == 2){ //south
            y -= dist;
        }else if(direction == 6){ //north
            y += dist;
        }

        for(int i = 0; i < population.size(); i++){
            if(population.get(i).x_coord == x &&
               population.get(i).y_coord == y){
                return population.get(i).toString();
            }
        }

        return null;
    }

    public static String runStats(List<Critter> critters) {

        if(critters.isEmpty()) return "0 critters as follows";
        String crit_string = critters.get(0).viewShape().name().toLowerCase();

        return critters.size() + " " + crit_string + "s of "
                + population.size() + " total critters ";
    }

    public static String[][] makeWorld(){
        int h = Params.WORLD_HEIGHT;
        int w = Params.WORLD_WIDTH;
        String[][] map = new String[w][h];

        for(int i = 0; i < w; i++){
            for(int j = 0; j < h; j++){
                map[i][j] = " ";
            }
        }

        for (Critter critter : population) {
            map[critter.x_coord][critter.y_coord] = critter.toString();
        }

        for (int i = 0; i < population.size(); i++) {
            map[population.get(i).x_coord][population.get(i).y_coord] = population.get(i).viewShape().name();
        }

        return map;
    }

    public static GridPane world = new GridPane();
    public static void displayWorld(Object pane) {

        GridPane world = (GridPane) pane;
        world.getChildren().clear();

        for (int i = 0; i < Params.WORLD_WIDTH; i++) {
            for (int j = 0; j < Params.WORLD_HEIGHT; j++) {

                Shape s = new Rectangle(spotSize, spotSize);
                s.autosize();
                s.setFill(UI.worldColorRGB);
                s.setStroke(UI.backgroundColorRGB);
                world.add(s, i, j);

            }
        }

        for (Critter critter : population) {
            int x = critter.x_coord;
            int y = critter.y_coord;
            CritterShape shape = critter.viewShape();
            Color fill = critter.viewFillColor();
            Color stroke = critter.viewOutlineColor();

            switch (shape) {
                case CIRCLE:
                    world.add(circle(fill, stroke), x, y);
                    if(newcircle) {
                        int temp = spotSize;
                        spotSize = 30;
                        Label name = new Label(UI.selection + ":");
                        name.setStyle(UI.basicText);
                        Main.grid.add(name, 0, currRow);
                        Main.grid.add(circle(fill, stroke), 1, currRow);
                        currRow++;
                        spotSize = temp;
                        newcircle = false;
                    }
                    continue;
                case SQUARE:
                    world.add(square(fill, stroke), x, y);
                    if(newsquare) {
                        int temp = spotSize;
                        spotSize = 30;
                        Label name = new Label(UI.selection + ":");
                        name.setStyle(UI.basicText);
                        Main.grid.add(name, 0, currRow);
                        Main.grid.add(square(fill, stroke), 1, currRow);
                        currRow++;
                        spotSize = temp;
                        newsquare = false;
                    }
                    continue;
                case TRIANGLE:
                    world.add(triangle(fill, stroke), x, y);
                    if(newtriangle) {
                        int temp = spotSize;
                        spotSize = 30;
                        Label name = new Label(UI.selection + ":");
                        name.setStyle(UI.basicText);
                        Main.grid.add(name, 0, currRow);
                        Main.grid.add(triangle(fill, stroke), 1, currRow);
                        currRow++;
                        spotSize = temp;
                        newtriangle = false;
                    }
                    continue;
                case DIAMOND:
                    world.add(diamond(fill, stroke), x, y);
                    if(newdiamond) {
                        int temp = spotSize;
                        spotSize = 30;
                        Label name = new Label(UI.selection + ":");
                        name.setStyle(UI.basicText);
                        Main.grid.add(name, 0, currRow);
                        Main.grid.add(diamond(fill, stroke), 1, currRow);
                        currRow++;
                        spotSize = temp;
                        newdiamond = false;
                    }
                    continue;
                case STAR:
                    world.add(star(fill, stroke), x, y);
                    if(newstar) {
                        int temp = spotSize;
                        spotSize = 30;
                        Label name = new Label(UI.selection + ":");
                        name.setStyle(UI.basicText);
                        Main.grid.add(name, 0, currRow);
                        Main.grid.add(star(fill, stroke), 1, currRow);
                        currRow++;
                        spotSize = temp;
                        newstar = false;
                    }
                    continue;
                case SQUAREalt:
                    world.add(squareAlt(fill, stroke), x, y);
                    if(newsquarealt) {
                        int temp = spotSize;
                        spotSize = 30;
                        Label name = new Label(UI.selection + ":");
                        name.setStyle(UI.basicText);
                        Main.grid.add(name, 0, currRow);
                        Main.grid.add(squareAlt(fill, stroke), 1, currRow);
                        currRow++;
                        spotSize = temp;
                        newsquarealt = false;
                    }
                    continue;
            }

        }
    }

    public static Shape circle(Color fill, Color stroke){
        Shape s = new Circle(spotSize / 2);
        s.setFill(fill);
        s.setStroke(stroke);
        return s;
    }

    public static Shape square(Color fill, Color stroke){
        Shape s = new Rectangle(spotSize-4, spotSize-4);
        s.setStrokeWidth(5);
        s.setFill(fill);
        s.setStroke(stroke);
        return s;
    }

    public static Shape triangle(Color fill, Color stroke){
        Shape s = new Path(new MoveTo(0, 0), new LineTo(-spotSize + 2, 0), new LineTo(-spotSize / 2, -spotSize + 2), new LineTo(0, 0));
        s.setFill(fill);
        s.setStroke(stroke);
        return s;
    }

    public static Shape diamond(Color fill, Color stroke){
        Shape s = new Path(new MoveTo(0, 0), new LineTo(spotSize / 2, spotSize / 2 - 2), new LineTo(spotSize - 2, 0), new LineTo(spotSize / 2, -spotSize / 2 + 2), new LineTo(0, 0));
        s.setFill(fill);
        s.setStroke(stroke);
        return s;
    }

    public static Shape star(Color fill, Color stroke){
        Shape s = new Path(new MoveTo(0, 0), new LineTo(spotSize / 3 + spotSize / 25, spotSize / 3 - spotSize / 6), new LineTo(spotSize / 2, spotSize / 2 - 2),
                new LineTo(3 * spotSize / 4.7, spotSize / 3 - spotSize / 6), new LineTo(spotSize - 3, 0), new LineTo(3 * spotSize / 4.7, -spotSize / 3 + spotSize / 6),
                new LineTo(spotSize / 2, -spotSize / 2 + 2), new LineTo(spotSize / 3 + spotSize / 25, -spotSize / 3 + spotSize / 6), new LineTo(0, 0));
        s.setFill(fill);
        s.setStroke(stroke);
        return s;
    }

    public static Shape squareAlt(Color fill, Color stroke){
        Shape s = new Rectangle(spotSize-4, spotSize-4);
        s.setStrokeWidth(5);
        s.setFill(fill);
        s.setStroke(stroke);
        return s;
    }



	/* END --- NEW FOR PROJECT 5
			rest is unchanged from Project 4 */

    private int energy = 0;

    private int x_coord;
    private int y_coord;

    private static List<Critter> population = new ArrayList<Critter>();
    private static List<Critter> babies = new ArrayList<Critter>();

    /* Gets the package name.  This assumes that Critter and its
     * subclasses are all in the same package. */
    private static String myPackage;

    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    private static Random rand = new Random();

    public static int getRandomInt(int max) {
        return rand.nextInt(max);
    }

    public static void setSeed(long new_seed) {
        rand = new Random(new_seed);
    }

    private static Map<Integer, Map<Integer, Critter>> critterMap = new HashMap<>();

    /**
     * create and initialize a Critter subclass.
     * critter_class_name must be the qualified name of a concrete
     * subclass of Critter, if not, an InvalidCritterException must be
     * thrown.
     *
     * @param critter_class_name
     * @throws InvalidCritterException
     */
    public static void createCritter(String critter_class_name)
            throws InvalidCritterException {
        try {
            Critter critter = (Critter) Class.forName(myPackage + "." + critter_class_name).newInstance();
            critter.x_coord = getRandomInt(Params.WORLD_WIDTH);
            critter.y_coord = getRandomInt(Params.WORLD_HEIGHT);
            critter.energy = Params.START_ENERGY;
            population.add(critter);
            Map<Integer, Critter> temp = new HashMap<>();
            temp.put(critter.y_coord, critter);
            critterMap.put(critter.x_coord, temp);

            if(critter_class_name.equals("Clover")) cloverCount++;
            else if(critter_class_name.equals("Goblin")) goblinCount++;
            else if(critter_class_name.equals("Critter1")) critter1Count++;
            else if(critter_class_name.equals("Critter2")) critter2Count++;
            else if(critter_class_name.equals("Critter3")) critter3Count++;
            else if(critter_class_name.equals("Critter4")) critter4Count++;

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new InvalidCritterException(critter_class_name);
        }
    }

    /**
     * Gets a list of critters of a specific type.
     *
     * @param critter_class_name What kind of Critter is to be listed.
     *                           Unqualified class name.
     * @return List of Critters.
     * @throws InvalidCritterException
     */
    public static List<Critter> getInstances(String critter_class_name)
            throws InvalidCritterException {
        ArrayList<Critter> critters = new ArrayList<Critter>();
        try {
            if (critter_class_name == null) {
                return population;
            }
            Critter cr = (Critter) Class.forName(myPackage + "." + critter_class_name).newInstance();
            for(int i = 0; i < population.size(); i++){
                if(! cr.getClass().isInstance(population.get(i))) continue;
                critters.add(population.get(i));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new InvalidCritterException(critter_class_name);
        }

        return critters;
    }

    /**
     * Clear the world of all critters, dead and alive
     */
    public static void clearWorld() {
        population.clear(); critterMap.clear();
    }

    public static void worldTimeStep() {
        for(int i = 0; i < population.size(); i++) {
            Critter critter = population.get(i);
            critter.doTimeStep();
            critter.energy -= Params.REST_ENERGY_COST;
            if (critter.energy <= 0) {
                population.remove(i);
                i -= 1;
            }
        }

        doEncounters(); //check pdf
        refresh(); //refresh clovers and babies
    }

    /**
     * Refresh clovers respectively
     * Promote babies to real Critters in worldTimeStep
     */
    private static void refresh(){

        for(int i = 0; i < Params.REFRESH_CLOVER_COUNT; i++){
            try {createCritter("Clover");}
            catch (InvalidCritterException ignored) {}
        }

        population.addAll(babies);
        babies.clear();
    }

    /**
     * Handles encounters when critters run into another critter
     * Invokes fight() if location is shared to determine outcomes
     */
    private static void doEncounters(){
        Critter c1, c2;
        int c1x, c2x, c1y, c2y, c1p, c2p, temp;
        boolean sameSpot, dead1, dead2, alive;

        for(int i = 0; i < population.size()-1; i++){
            c1 = population.get(i);
            c1x = c1.x_coord; c1y = c1.y_coord;

            for(int j = i+1; j < population.size(); j++){
                c2 = population.get(j);
                c2x = c2.x_coord; c2y = c2.y_coord;
                sameSpot = c1x == c2x && c1y == c2y;
                alive = c1.getEnergy() > 0 && c2.getEnergy() > 0;
                dead1 = c1.getEnergy() < 1; dead2 = c2.getEnergy() < 1;

                //same spot, alive
                if(sameSpot && alive){

                    c1p = 0; c2p = 0;
                    if(c1.fight(c2.toString())) c1p = getRandomInt(c1.getEnergy());
                    if(c2.fight(c1.toString())) c2p = getRandomInt(c2.getEnergy());

                    if(c1p < c2p){ //c1 loses
                        c2.energy += c1.getEnergy() /2;
                        population.remove(i); i -= 1;
                        j = population.size();
                    }
                    else{ //c2 loses
                        c1.energy += c2.getEnergy() /2;
                        population.remove(j); j -= 1;
                    }
                }

                //remove if encounter dead
                if(sameSpot && dead1){
                    population.remove(i);
                    i -= 1;
                    j = population.size();
                }
                if(sameSpot && dead2){
                    population.remove(j);
                    j -= 1;
                }
            }
        }
    }


    public abstract void doTimeStep();

    public abstract boolean fight(String oponent);

    /* a one-character long string that visually depicts your critter
     * in the ASCII interface */
    public String toString() {
        return "";
    }

    protected int getEnergy() {
        return energy;
    }

    protected final void walk(int direction) {
        this.energy -= Params.WALK_ENERGY_COST;
        moveCritter(1, direction);
    }

    protected final void run(int direction) {
        this.energy -= Params.RUN_ENERGY_COST;
        moveCritter(3, direction);
    }

    /**
     * Moves critter 3 spaces in direction
     * @param direction
     */
    protected final void sprint(int direction) {
        this.energy -= Params.RUN_ENERGY_COST;
        moveCritter(2, direction);
    }

    /**
     * handles critter movement with
     * respective distance and direction
     *
     * @param distance
     * @param direction
     */
    private void moveCritter(int distance, int direction){

        //adjust direction
        if(direction == 0 || direction == 1 || direction == 7){ //west
            x_coord += distance;
            if(direction == 1) y_coord -= distance; //sw
            if(direction == 7) y_coord += distance; //nw
        }
        else if(direction == 3 || direction == 4 || direction == 5){ //east
            x_coord -= distance;
            if(direction == 3) y_coord -= distance; //se
            if(direction == 5) y_coord += distance; //ne
        }else if(direction == 2){ //south
            y_coord -= distance;
        }else if(direction == 6){ //north
            y_coord += distance;
        }

        outOfWorld();

    }

    /**
     * Process any critters that fall out of the world after moving
     */
    private  void outOfWorld(){

        //out of bounds X
        if (x_coord > Params.WORLD_WIDTH - 1) x_coord -= Params.WORLD_WIDTH - 1;
        if (x_coord < 0) x_coord += Params.WORLD_WIDTH - 1;

        //out of bounds Y
        if (y_coord > Params.WORLD_HEIGHT - 1) y_coord -= Params.WORLD_HEIGHT - 1;
        if (y_coord < 0) y_coord += Params.WORLD_HEIGHT - 1;

    }

    protected final void reproduce(Critter offspring, int direction) {
        if (energy < Params.MIN_REPRODUCE_ENERGY) {
            return;
        }
        offspring.energy = (int) Math.floor(energy / 2);
        energy = (int) Math.ceil(energy / 2);
        offspring.x_coord = x_coord;
        offspring.y_coord = y_coord;
        offspring.moveCritter(1, direction);
        babies.add(offspring);
    }




    protected int getX_coord() {
        return x_coord;
    }

    protected int getY_coord() {
        return y_coord;
    }

    /**
     * The TestCritter class allows some critters to "cheat". If you
     * want to create tests of your Critter model, you can create
     * subclasses of this class and then use the setter functions
     * contained here.
     * <p>
     * NOTE: you must make sure that the setter functions work with
     * your implementation of Critter. That means, if you're recording
     * the positions of your critters using some sort of external grid
     * or some other data structure in addition to the x_coord and
     * y_coord functions, then you MUST update these setter functions
     * so that they correctly update your grid/data structure.
     */
    static abstract class TestCritter extends Critter {

        protected void setEnergy(int new_energy_value) {
            super.energy = new_energy_value;
        }

        protected void setX_coord(int new_x_coord) {
            super.x_coord = new_x_coord;
        }

        protected void setY_coord(int new_y_coord) {
            super.y_coord = new_y_coord;
        }


        /**
         * This method getPopulation has to be modified by you if you
         * are not using the population ArrayList that has been
         * provided in the starter code.  In any case, it has to be
         * implemented for grading tests to work.
         */
        protected static List<Critter> getPopulation() {
            return population;
        }

        /**
         * This method getBabies has to be modified by you if you are
         * not using the babies ArrayList that has been provided in
         * the starter code.  In any case, it has to be implemented
         * for grading tests to work.  Babies should be added to the
         * general population at either the beginning OR the end of
         * every timestep.
         */
        protected static List<Critter> getBabies() {
            return babies;
        }
    }
}
