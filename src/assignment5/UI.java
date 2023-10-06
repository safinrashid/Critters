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

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static assignment5.Critter.world;

public class UI {

    public static String basicText_big = "-fx-text-fill: #ecf0f1; -fx-font-size: 150%; -fx-font-family: Roboto; ",
            basicText = "-fx-text-fill: #ecf0f1; -fx-font-size: 110%; -fx-font-family: Roboto; ",
            errorText = "-fx-text-fill: #f14646; -fx-font-size: 120%; -fx-font-family: Roboto; ",
            statsText = "-fx-text-fill: #b7b7b7; -fx-font-size: 120%; -fx-font-family: Roboto; ",
            backgroundColor = "-fx-background-color: #2b2b2b; ",
            buttonColor = "-fx-text-fill: #f0ffaf; -fx-background-color: #575757; ";

    public static Color worldColorRGB = Color.rgb(100, 100, 100),
                        backgroundColorRGB = Color.rgb(43, 43, 43);

    static int cr = 0, cr1 = 1, tmst = 3, ani = 5, sta = 7, sd = 9, clr = 11, prm = 8;
    static int speed = 1;
    static boolean running = true, cloverDisplayed = false, goblinDisplayed = false, c1Displayed = false,
            c2Displayed = false, c3Displayed = false, c4Displayed = false, statsWindowShow = false;
    private static final GridPane grid = Main.grid;
    static ComboBox<String> critterChoices = new ComboBox<>(), statsChoices = new ComboBox<>();
    static Button createButton = new Button("➠"), stepButton = new Button("➠"), animateButton = new Button("▶‖"),
            statsButton = new Button("＋"), seedButton = new Button("➠"), clearButton = new Button("  ↻  "),
            quitButton = new Button(" ✘ ");

    static TextField numInput = new TextField(), stepInput = new TextField(),
            animateInput = new TextField(), seedInput = new TextField();

    static TextField p1Input = new TextField(), p2Input = new TextField(), p3Input = new TextField(),
            p4Input = new TextField(), p5Input = new TextField(), p6Input = new TextField(),
            p7Input = new TextField(), p8Input = new TextField(), p9Input = new TextField(),
            p10Input = new TextField();

    static Button p1Button = new Button("➯"), p2Button = new Button("➯"), p3Button = new Button("➯"),
            p4Button = new Button("➯"), p5Button = new Button("➯"), p6Button = new Button("➯"),
            p7Button = new Button("➯"), p8Button = new Button("➯"), p9Button = new Button("➯"),
            p10Button = new Button("➯"), resetButton = new Button("  ↻  ");

    static final int WALK_ENERGY_COST = Params.WALK_ENERGY_COST, RUN_ENERGY_COST = Params.RUN_ENERGY_COST, REST_ENERGY_COST = Params.REST_ENERGY_COST,
            MIN_REPRODUCE_ENERGY = Params.MIN_REPRODUCE_ENERGY, REFRESH_CLOVER_COUNT = Params.REFRESH_CLOVER_COUNT, PHOTOSYNTHESIS_ENERGY_AMOUNT = Params.PHOTOSYNTHESIS_ENERGY_AMOUNT,
            START_ENERGY = Params.START_ENERGY, LOOK_ENERGY_COST = Params.LOOK_ENERGY_COST,
            WORLD_WIDTH = Params.WORLD_WIDTH, WORLD_HEIGHT = Params.WORLD_HEIGHT;

    static Label errorLabel = new Label("ERROR!");

    static GridPane statsGrid = new GridPane();

    static String cloverStats = "", goblinStats = "", c1Stats = "", c2Stats = "", c3Stats = "", c4Stats = "";

    static Stage statsWindow = new Stage();
    public static String selection = "";

    public static void generate(){

        Label signature = new Label("by Safin Rashid srr3288");
        signature.setStyle("-fx-text-fill: #ecf0f1; -fx-font-size: 90%; -fx-font-family: Roboto; ");
        grid.add(signature, 1, 30);
        GridPane.setConstraints(quitButton, 0, 30);

        menu();
        create();
        step();
        animate();
        stats();
        seed();
        params();
        clear_quit();

        Critter.displayWorld(world);
    }

    private static void menu(){
        for(int r = Critter.startRow; r < Critter.startRow+6; r++){
            Shape blankname = new Rectangle(60,35);
            blankname.setFill(backgroundColorRGB);
            blankname.setStroke(backgroundColorRGB);
            grid.add(blankname, 0, r);
            Shape blank = new Rectangle(35,35);
            blank.setFill(backgroundColorRGB);
            blank.setStroke(backgroundColorRGB);
            grid.add(blank, 1, r);
        }
        Critter.currRow = Critter.startRow;
        Critter.newcircle = Critter.newsquare = Critter.newdiamond = Critter.newstar = Critter.newtriangle = Critter.newsquarealt = true;
    }

    private static void create(){

        Label critterLabel = new Label("Create: ");
        GridPane.setConstraints(critterLabel, 0, cr);
        critterLabel.setStyle(basicText_big);
        critterChoices.setPromptText("Select Object");
        critterChoices.getItems().addAll("Clover","Goblin","Critter1","Critter2","Critter3","Critter4");
        GridPane.setConstraints(critterChoices, 1, cr);
        GridPane.setConstraints(numInput, 1, cr1);
        numInput.setPromptText("amount        (default = 1)");
        numInput.setTooltip(new Tooltip("non-negative integer"));
        createButton.setStyle(buttonColor);
        createButton.setTooltip(new Tooltip("click to insert critters!"));
        GridPane.setConstraints(createButton, 2, cr1);
        grid.getChildren().addAll(critterLabel, critterChoices, numInput, createButton);
        Label errorLabel = new Label("ERROR!");
        errorLabel.setStyle(errorText);
        createButton.setOnAction(b -> {
            try {
                selection = critterChoices.getValue();
                int quantity;
                if(numInput.getText().isEmpty()) quantity = 1;
                else quantity = Integer.parseInt(numInput.getText());
                if(quantity < 0){
                    throw new IndexOutOfBoundsException();
                }
                for (int i = 0; i < quantity; i++) {
                    Critter.createCritter(selection);
                }
                Critter.displayWorld(world);
                updateStats();
                grid.getChildren().remove(errorLabel);
            } catch (IndexOutOfBoundsException | NoClassDefFoundError | IllegalArgumentException | InvalidCritterException e) {
                System.out.println("ERROR: " + e);
                GridPane.setConstraints(errorLabel, 1, cr1+1);
                if (! grid.getChildren().contains(errorLabel)) grid.getChildren().add(errorLabel);
            }
        });
    }

    private static void step(){

        Label stepLabel = new Label("Time Step: ");
        GridPane.setConstraints(stepLabel, 0, tmst);
        stepLabel.setStyle(basicText_big);
        GridPane.setConstraints(stepInput, 1, tmst);
        stepInput.setPromptText("steps            (default = 1)");
        stepInput.setTooltip(new Tooltip("non-negative integer"));
        stepButton.setStyle(buttonColor);
        stepButton.setTooltip(new Tooltip("click to step in time!"));
        GridPane.setConstraints(stepButton, 2, tmst);
        grid.getChildren().addAll(stepLabel, stepInput, stepButton);
        Label errorLabel = new Label("ERROR!");
        errorLabel.setStyle(errorText);
        stepButton.setOnAction(b -> {
            try {
                selection = "Clover";
                int quantity;
                if(stepInput.getText().isEmpty()) quantity = 1;
                else quantity = Integer.parseInt(stepInput.getText());
                if(quantity < 0){
                    throw new IndexOutOfBoundsException();
                }
                for (int i = 0; i < quantity; i++) {
                    Critter.worldTimeStep();
                }
                Critter.displayWorld(world);
                updateStats();
                grid.getChildren().remove(errorLabel);
            } catch (IndexOutOfBoundsException | NoClassDefFoundError | IllegalArgumentException e) {
                System.out.println("ERROR: " + e);
                GridPane.setConstraints(errorLabel, 1, tmst+1);
                if (! grid.getChildren().contains(errorLabel)) grid.getChildren().add(errorLabel);
            }
        });
    }

    private static void animate(){

        Label animateLabel = new Label("Animate: ");
        GridPane.setConstraints(animateLabel, 0, ani);
        animateLabel.setStyle(basicText_big);
        GridPane.setConstraints(animateInput, 1, ani);
        animateInput.setPromptText("rate              (default = 1)");
        animateInput.setTooltip(new Tooltip("non-negative integer"));
        animateButton.setStyle(buttonColor);
        animateButton.setTooltip(new Tooltip("click to play/pause animation!"));
        GridPane.setConstraints(animateButton, 2, ani);
        grid.getChildren().addAll(animateLabel, animateInput, animateButton);
        Label errorLabel = new Label("ERROR!");
        errorLabel.setStyle(errorText);
        animateButton.setOnAction(b -> {
            try {
                selection = "Clover";
                grid.getChildren().remove(errorLabel);
                if(running) {
                    buttonDisable(true);
                    if (! animateInput.getText().isEmpty()) {
                        speed = Integer.parseInt(animateInput.getText());
                        if(speed < 0){
                            throw new IndexOutOfBoundsException();
                        }
                    }
                    animationTimeline.setCycleCount(Timeline.INDEFINITE);
                    animationTimeline.play();
                    running = false;
                }else{
                    animationTimeline.stop();
                    buttonDisable(false);
                    running = true;
                }
            } catch (IndexOutOfBoundsException | NoClassDefFoundError | IllegalArgumentException e) {
                buttonDisable(false);
                System.out.println("ERROR: " + e);
                GridPane.setConstraints(errorLabel, 1, ani+1);
                if (! grid.getChildren().contains(errorLabel)) grid.getChildren().add(errorLabel);
            }
        });

    }

    static Timeline animationTimeline = new Timeline
            (new KeyFrame(Duration.millis(250), (ActionEvent event) -> {
                for (int i = 0; i < speed; i++) {
                    Critter.worldTimeStep();
                }
                Critter.displayWorld(world);
                updateStats();
            }));

    private static void stats(){

        Label statsLabel = new Label("Stats: ");
        GridPane.setConstraints(statsLabel, 0, sta);
        statsLabel.setStyle(basicText_big);
        statsChoices.setPromptText("Select Object");
        statsChoices.getItems().addAll("Clover","Goblin","Critter1","Critter2","Critter3","Critter4");
        GridPane.setConstraints(statsChoices, 1, sta);
        statsButton.setStyle(buttonColor);
        statsButton.setTooltip(new Tooltip("click to pop up stats!"));
        GridPane.setConstraints(statsButton, 2, sta);
        grid.getChildren().addAll(statsLabel, statsChoices, statsButton);
        Label errorLabel = new Label("ERROR!");
        errorLabel.setStyle(errorText);
        statsButton.setOnAction(b -> {
            boolean ready = true;
            try {
                String selection = statsChoices.getValue();
                String myPackage = "assignment5";
                String output = (String) Class.forName( myPackage + "." + selection)
                        .getMethod("runStats", List.class)
                        .invoke(null, Critter.getInstances(selection));
                if(selection.equals("Clover") && ! cloverDisplayed){
                    Label label = new Label("Clovers:");
                    label.setStyle(basicText_big);
                    statsGrid.add(label, 0, 0);
                    cloverDisplayed = true;
                    cloverStats = output;
                    Label stats = new Label(cloverStats);
                    stats.setStyle(statsText);
                    statsGrid.add(stats, 0, 1);
                }
                if(selection.equals("Critter1") && ! c1Displayed){
                    Label label = new Label("Critter1:");
                    label.setStyle(basicText_big);
                    statsGrid.add(label, 0, 2);
                    c1Displayed = true;
                    c1Stats = output;
                    Label stats = new Label(c1Stats);
                    stats.setStyle(statsText);
                    statsGrid.add(stats, 0, 3);
                }
                if(selection.equals("Critter2") && ! c2Displayed){
                    Label label = new Label("Critter2:");
                    label.setStyle(basicText_big);
                    statsGrid.add(label, 0, 4);
                    c2Displayed = true;
                    c2Stats = output;
                    Label stats = new Label(c2Stats);
                    stats.setStyle(statsText);
                    statsGrid.add(stats, 0, 5);
                }
                if(selection.equals("Critter3") && ! c3Displayed){
                    Label label = new Label("Critter3:");
                    label.setStyle(basicText_big);
                    statsGrid.add(label, 0, 6);
                    c3Displayed = true;
                    c3Stats = output;
                    Label stats = new Label(c3Stats);
                    stats.setStyle(statsText);
                    statsGrid.add(stats, 0, 7);
                }
                if(selection.equals("Critter4") && ! c4Displayed){
                    Label label = new Label("Critter4:");
                    label.setStyle(basicText_big);
                    statsGrid.add(label, 0, 8);
                    c4Displayed = true;
                    c4Stats = output;
                    Label stats = new Label(c4Stats);
                    stats.setStyle(statsText);
                    statsGrid.add(stats, 0, 9);
                }
                if(selection.equals("Goblin") && ! goblinDisplayed){
                    Label label = new Label("Goblins:");
                    label.setStyle(basicText_big);
                    statsGrid.add(label, 0, 10);
                    goblinDisplayed = true;
                    goblinStats = output;
                    Label stats = new Label(goblinStats);
                    stats.setStyle(statsText);
                    statsGrid.add(stats, 0, 11);
                }
                grid.getChildren().remove(errorLabel);
            } catch (NoClassDefFoundError | NoSuchMethodException |
                     IllegalArgumentException | InvocationTargetException |
                     IllegalAccessException | ClassNotFoundException |
                     InvalidCritterException e){
                ready = false;
                System.out.println("ERROR: " + e);
                GridPane.setConstraints(errorLabel, 1, sta+1);
                if (! grid.getChildren().contains(errorLabel)) grid.getChildren().add(errorLabel);
            }

            if(! statsWindowShow && ready && (cloverDisplayed || c1Displayed || c2Displayed || c3Displayed || c4Displayed || goblinDisplayed)){
                statsWindowShow = true;
                statsWindow.setTitle("Critter Stats");
                statsWindow.setResizable(false);
                statsGrid.setPadding(new Insets(20,10,10,20));
                statsGrid.setVgap(10);
                statsGrid.setHgap(10);
                statsGrid.setStyle("-fx-background-color: #2b2b2b;");
                Scene statsScene = new Scene(statsGrid, 400, 700);
                statsWindow.setScene(statsScene);
            }
            statsWindow.show();
        });
    }

    private static void seed(){

        Label seedLabel = new Label("Seed: ");
        GridPane.setConstraints(seedLabel, 0, sd);
        seedLabel.setStyle(basicText_big);
        GridPane.setConstraints(seedInput, 1, sd);
        seedInput.setPromptText("rng number");
        seedInput.setTooltip(new Tooltip("non-negative integer"));
        seedButton.setStyle(buttonColor);
        seedButton.setTooltip(new Tooltip("click to set seed!"));
        GridPane.setConstraints(seedButton, 2, sd);
        grid.getChildren().addAll(seedLabel, seedInput, seedButton);
        Label errorLabel = new Label("ERROR!");
        errorLabel.setStyle(errorText);
        seedButton.setOnAction(b -> {
            try {
                int rng = Integer.parseInt(seedInput.getText());
                Critter.setSeed(rng);
                grid.getChildren().remove(errorLabel);
            } catch (ArrayIndexOutOfBoundsException | NoClassDefFoundError | IllegalArgumentException e) {
                System.out.println("ERROR: " + e);
                GridPane.setConstraints(errorLabel, 1, sd+1);
                if (! grid.getChildren().contains(errorLabel)) grid.getChildren().add(errorLabel);
            }
        });
    }

    private static void clear_quit(){

        GridPane.setConstraints(clearButton, 0, clr);
        clearButton.setStyle(buttonColor);
        clearButton.setTooltip(new Tooltip("click to clear/reset world!"));
        grid.getChildren().addAll(clearButton);
        Label errorLabel = new Label("ERROR!");
        errorLabel.setStyle(errorText);
        clearButton.setOnAction(b -> {
            try {
                Critter.clearWorld();
                menu();
                Critter.displayWorld(world);
                grid.getChildren().remove(errorLabel);
            }catch(IllegalArgumentException e){
                GridPane.setConstraints(errorLabel, 1, clr+1);
                if (! grid.getChildren().contains(errorLabel)) grid.getChildren().add(errorLabel);
            }
        });

        quitButton.setTooltip(new Tooltip("exit simulation"));
        grid.getChildren().addAll(quitButton);
        quitButton.setStyle("-fx-text-fill: #ff6d6d; -fx-background-color: #575757; ");
        quitButton.setOnAction(b -> System.exit(0));
    }

    private static void buttonDisable(boolean b){

        createButton.setDisable(b);
        statsButton.setDisable(b);
        seedButton.setDisable(b);
        stepButton.setDisable(b);
        clearButton.setDisable(b);
        quitButton.setDisable(false);

        critterChoices.setDisable(b);
        statsChoices.setDisable(b);

        numInput.setDisable(b);
        stepInput.setDisable(b);
        animateInput.setDisable(b);
        seedInput.setDisable(b);

        p1Input.setDisable(b);
        p2Input.setDisable(b);
        p3Input.setDisable(b);
        p4Input.setDisable(b);
        p5Input.setDisable(b);
        p6Input.setDisable(b);
        p7Input.setDisable(b);
        p8Input.setDisable(b);
        p9Input.setDisable(b);
        p10Input.setDisable(b);

        p1Button.setDisable(b);
        p2Button.setDisable(b);
        p3Button.setDisable(b);
        p4Button.setDisable(b);
        p5Button.setDisable(b);
        p6Button.setDisable(b);
        p7Button.setDisable(b);
        p8Button.setDisable(b);
        p9Button.setDisable(b);
        p10Button.setDisable(b);
        resetButton.setDisable(b);
    }

    private static void params(){

        Label label = new Label("Parameters: ");
        GridPane.setConstraints(label, prm, 0);
        label.setStyle(basicText_big);
        grid.getChildren().add(label);

        errorLabel.setStyle(errorText);
        GridPane.setConstraints(errorLabel, prm, 0);

        {
            p1Button.setTooltip(new Tooltip("update WALK_ENERGY_COST!"));
            p2Button.setTooltip(new Tooltip("update RUN_ENERGY_COST!"));
            p3Button.setTooltip(new Tooltip("update REST_ENERGY_COST!"));
            p4Button.setTooltip(new Tooltip("update MIN_REPRODUCE_ENERGY!"));
            p5Button.setTooltip(new Tooltip("update REFRESH_CLOVER_COUNT!"));
            p6Button.setTooltip(new Tooltip("update PHOTOSYNTHESIS_ENERGY_AMOUNT!"));
            p7Button.setTooltip(new Tooltip("update START_ENERGY!"));
            p8Button.setTooltip(new Tooltip("update LOOK_ENERGY_COST!"));
            p9Button.setTooltip(new Tooltip("update WORLD_WIDTH!"));
            p10Button.setTooltip(new Tooltip("update WORLD_HEIGHT!"));
            p1Input.setTooltip(new Tooltip("non-negative integer"));
            p2Input.setTooltip(new Tooltip("non-negative integer"));
            p3Input.setTooltip(new Tooltip("non-negative integer"));
            p4Input.setTooltip(new Tooltip("non-negative integer"));
            p5Input.setTooltip(new Tooltip("non-negative integer"));
            p6Input.setTooltip(new Tooltip("non-negative integer"));
            p7Input.setTooltip(new Tooltip("non-negative integer"));
            p8Input.setTooltip(new Tooltip("non-negative integer"));
            p9Input.setTooltip(new Tooltip("no insane sizes & non-negative integer"));
            p10Input.setTooltip(new Tooltip("no insane sizes & non-negative integer"));
        }

        {
            Label paramLabel = new Label("Walk Energy Cost: ");
            GridPane.setConstraints(paramLabel, prm, 1);
            paramLabel.setStyle(basicText);
            p1Button.setStyle(buttonColor);
            GridPane.setConstraints(p1Input, prm + 1, 1);
            p1Input.setPromptText("" + Params.WALK_ENERGY_COST);
            GridPane.setConstraints(p1Button, prm + 2, 1);
            grid.getChildren().addAll(paramLabel, p1Input, p1Button);
            p1Button.setOnAction(b -> {
                try {
                    if(Integer.parseInt(p1Input.getText()) < 0){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    Params.WALK_ENERGY_COST = Integer.parseInt(p1Input.getText());
                    grid.getChildren().remove(errorLabel);
                } catch (ArrayIndexOutOfBoundsException | NoClassDefFoundError | IllegalArgumentException e) {
                    System.out.println("ERROR: " + e);
                    if (! grid.getChildren().contains(errorLabel)) grid.add(errorLabel, prm+1, 11);
                }
            });
        }

        {
            Label paramLabel = new Label("Run Energy Cost: ");
            GridPane.setConstraints(paramLabel, prm, 2);
            paramLabel.setStyle(basicText);
            p2Button.setStyle(buttonColor);
            GridPane.setConstraints(p2Input, prm + 1, 2);
            p2Input.setPromptText("" + Params.RUN_ENERGY_COST);
            GridPane.setConstraints(p2Button, prm + 2, 2);
            grid.getChildren().addAll(paramLabel, p2Input, p2Button);
            p2Button.setOnAction(b -> {
                try {
                    if(Integer.parseInt(p2Input.getText()) < 0){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    Params.RUN_ENERGY_COST = Integer.parseInt(p2Input.getText());
                    grid.getChildren().remove(errorLabel);
                } catch (ArrayIndexOutOfBoundsException | NoClassDefFoundError | IllegalArgumentException e) {
                    System.out.println("ERROR: " + e);
                    if (! grid.getChildren().contains(errorLabel)) grid.add(errorLabel, prm+1, 11);
                }
            });
        }

        {
            Label paramLabel = new Label("Rest Energy Cost: ");
            GridPane.setConstraints(paramLabel, prm, 3);
            paramLabel.setStyle(basicText);
            p3Button.setStyle(buttonColor);
            GridPane.setConstraints(p3Input, prm + 1, 3);
            p3Input.setPromptText("" + Params.REST_ENERGY_COST);
            GridPane.setConstraints(p3Button, prm + 2, 3);
            grid.getChildren().addAll(paramLabel, p3Input, p3Button);
            p3Button.setOnAction(b -> {
                try {
                    if(Integer.parseInt(p3Input.getText()) < 0){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    Params.REST_ENERGY_COST = Integer.parseInt(p3Input.getText());
                    grid.getChildren().remove(errorLabel);
                } catch (ArrayIndexOutOfBoundsException | NoClassDefFoundError | IllegalArgumentException e) {
                    System.out.println("ERROR: " + e);
                    if (! grid.getChildren().contains(errorLabel)) grid.add(errorLabel, prm+1, 11);
                }
            });
        }

        {
            Label paramLabel = new Label("Min Reproduce: ");
            GridPane.setConstraints(paramLabel, prm, 4);
            paramLabel.setStyle(basicText);
            p4Button.setStyle(buttonColor);
            GridPane.setConstraints(p4Input, prm + 1, 4);
            p4Input.setPromptText("" + Params.MIN_REPRODUCE_ENERGY);
            GridPane.setConstraints(p4Button, prm + 2, 4);
            grid.getChildren().addAll(paramLabel, p4Input, p4Button);
            p4Button.setOnAction(b -> {
                try {
                    if(Integer.parseInt(p4Input.getText()) < 0){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    Params.MIN_REPRODUCE_ENERGY = Integer.parseInt(p4Input.getText());
                    grid.getChildren().remove(errorLabel);
                } catch (ArrayIndexOutOfBoundsException | NoClassDefFoundError | IllegalArgumentException e) {
                    System.out.println("ERROR: " + e);
                    if (! grid.getChildren().contains(errorLabel)) grid.add(errorLabel, prm+1, 11);
                }
            });
        }

        {
            Label paramLabel = new Label("Refresh Clover Count: ");
            GridPane.setConstraints(paramLabel, prm, 5);
            paramLabel.setStyle(basicText);
            p5Button.setStyle(buttonColor);
            GridPane.setConstraints(p5Input, prm + 1, 5);
            p5Input.setPromptText("" + Params.REFRESH_CLOVER_COUNT);
            GridPane.setConstraints(p5Button, prm + 2, 5);
            grid.getChildren().addAll(paramLabel, p5Input, p5Button);
            p5Button.setOnAction(b -> {
                try {
                    if(Integer.parseInt(p5Input.getText()) < 0){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    Params.REFRESH_CLOVER_COUNT = Integer.parseInt(p5Input.getText());
                    grid.getChildren().remove(errorLabel);
                } catch (ArrayIndexOutOfBoundsException | NoClassDefFoundError | IllegalArgumentException e) {
                    System.out.println("ERROR: " + e);
                    if (! grid.getChildren().contains(errorLabel)) grid.add(errorLabel, prm+1, 11);
                }
            });
        }

        {
            Label paramLabel = new Label("Photosynthesis: ");
            GridPane.setConstraints(paramLabel, prm, 6);
            paramLabel.setStyle(basicText);
            p6Button.setStyle(buttonColor);
            GridPane.setConstraints(p6Input, prm + 1, 6);
            p6Input.setPromptText("" + Params.PHOTOSYNTHESIS_ENERGY_AMOUNT);
            GridPane.setConstraints(p6Button, prm + 2, 6);
            grid.getChildren().addAll(paramLabel, p6Input, p6Button);
            p6Button.setOnAction(b -> {
                try {
                    if(Integer.parseInt(p6Input.getText()) < 0){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    Params.PHOTOSYNTHESIS_ENERGY_AMOUNT = Integer.parseInt(p6Input.getText());
                    grid.getChildren().remove(errorLabel);
                } catch (ArrayIndexOutOfBoundsException | NoClassDefFoundError | IllegalArgumentException e) {
                    System.out.println("ERROR: " + e);
                    if (! grid.getChildren().contains(errorLabel)) grid.add(errorLabel, prm+1, 11);
                }
            });
        }

        {
            Label paramLabel = new Label("Start Energy: ");
            GridPane.setConstraints(paramLabel, prm, 7);
            paramLabel.setStyle(basicText);
            p7Button.setStyle(buttonColor);
            GridPane.setConstraints(p7Input, prm + 1, 7);
            p7Input.setPromptText("" + Params.START_ENERGY);
            GridPane.setConstraints(p7Button, prm + 2, 7);
            grid.getChildren().addAll(paramLabel, p7Input, p7Button);
            p7Button.setOnAction(b -> {
                try {
                    if(Integer.parseInt(p7Input.getText()) < 0){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    Params.START_ENERGY = Integer.parseInt(p7Input.getText());
                    grid.getChildren().remove(errorLabel);
                } catch (ArrayIndexOutOfBoundsException | NoClassDefFoundError | IllegalArgumentException e) {
                    System.out.println("ERROR: " + e);
                    if (! grid.getChildren().contains(errorLabel)) grid.add(errorLabel, prm+1, 11);
                }
            });
        }

        {
            Label paramLabel = new Label("Look Energy Cost: ");
            GridPane.setConstraints(paramLabel, prm, 8);
            paramLabel.setStyle(basicText);
            p8Button.setStyle(buttonColor);
            GridPane.setConstraints(p8Input, prm + 1, 8);
            p8Input.setPromptText("" + Params.LOOK_ENERGY_COST);
            GridPane.setConstraints(p8Button, prm + 2, 8);
            grid.getChildren().addAll(paramLabel, p8Input, p8Button);
            p8Button.setOnAction(b -> {
                try {
                    if(Integer.parseInt(p8Input.getText()) < 0){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    Params.LOOK_ENERGY_COST = Integer.parseInt(p8Input.getText());
                    grid.getChildren().remove(errorLabel);
                } catch (ArrayIndexOutOfBoundsException | NoClassDefFoundError | IllegalArgumentException e) {
                    System.out.println("ERROR: " + e);
                    if (! grid.getChildren().contains(errorLabel)) grid.add(errorLabel, prm+1, 11);
                }
            });
        }

        {
            Label paramLabel = new Label("World Width: ");
            GridPane.setConstraints(paramLabel, prm, 9);
            paramLabel.setStyle(basicText);
            p9Button.setStyle(buttonColor);
            GridPane.setConstraints(p9Input, prm + 1, 9);
            p9Input.setPromptText("" + Params.WORLD_WIDTH);
            GridPane.setConstraints(p9Button, prm + 2, 9);
            grid.getChildren().addAll(paramLabel, p9Input, p9Button);
            p9Button.setOnAction(b -> {
                try {
                    if(Integer.parseInt(p9Input.getText()) < 0){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    Params.WORLD_WIDTH = Integer.parseInt(p9Input.getText());
                    Critter.spotSize = Math.min(800/Params.WORLD_WIDTH, 750/Params.WORLD_HEIGHT);
                    world.getChildren().clear();
                    Critter.clearWorld();
                    menu();
                    Critter.displayWorld(world);
                    grid.getChildren().remove(errorLabel);
                } catch (ArithmeticException | ArrayIndexOutOfBoundsException | NoClassDefFoundError | IllegalArgumentException e) {
                    System.out.println("ERROR: " + e);
                    if (! grid.getChildren().contains(errorLabel)) grid.add(errorLabel, prm+1, 11);
                }
            });
        }

        {
            Label paramLabel = new Label("World Height: ");
            GridPane.setConstraints(paramLabel, prm, 10);
            paramLabel.setStyle(basicText);
            p10Button.setStyle(buttonColor);
            GridPane.setConstraints(p10Input, prm + 1, 10);
            p10Input.setPromptText("" + Params.WORLD_HEIGHT);
            GridPane.setConstraints(p10Button, prm + 2, 10);
            grid.getChildren().addAll(paramLabel, p10Input, p10Button);
            p10Button.setOnAction(b -> {
                try {
                    if(Integer.parseInt(p10Input.getText()) < 0){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    Params.WORLD_HEIGHT = Integer.parseInt(p10Input.getText());
                    grid.getChildren().remove(errorLabel);
                    Critter.spotSize = Math.min(800/Params.WORLD_WIDTH, 750/Params.WORLD_HEIGHT);
                    Critter.clearWorld();
                    menu();
                    world.getChildren().clear();
                    Critter.displayWorld(world);
                } catch (ArithmeticException | ArrayIndexOutOfBoundsException | NoClassDefFoundError | IllegalArgumentException e) {
                    System.out.println("ERROR: " + e);
                    if (! grid.getChildren().contains(errorLabel)) grid.add(errorLabel, prm+1, 11);
                }
            });
        }

        reset();
    }

    private static void reset(){
        resetButton.setStyle(buttonColor);
        resetButton.setTooltip(new Tooltip("click to restore default parameters!"));
        GridPane.setConstraints(resetButton, prm, 11);
        grid.getChildren().add(resetButton);

        resetButton.setOnAction(b -> {
            grid.getChildren().remove(errorLabel);

            Params.WALK_ENERGY_COST = WALK_ENERGY_COST;
            Params.RUN_ENERGY_COST = RUN_ENERGY_COST;
            Params.REST_ENERGY_COST = REST_ENERGY_COST;
            Params.MIN_REPRODUCE_ENERGY = MIN_REPRODUCE_ENERGY;
            Params.REFRESH_CLOVER_COUNT = REFRESH_CLOVER_COUNT;
            Params.PHOTOSYNTHESIS_ENERGY_AMOUNT = PHOTOSYNTHESIS_ENERGY_AMOUNT;
            Params.START_ENERGY = START_ENERGY;
            Params.LOOK_ENERGY_COST = LOOK_ENERGY_COST;
            Params.WORLD_HEIGHT = WORLD_HEIGHT;
            Params.WORLD_WIDTH = WORLD_WIDTH;

            p1Input.clear();
            p2Input.clear();
            p3Input.clear();
            p4Input.clear();
            p5Input.clear();
            p6Input.clear();
            p7Input.clear();
            p8Input.clear();
            p9Input.clear();
            p10Input.clear();
        });
    }

    private static void updateStats(){
        Label errorLabel = new Label("ERROR!");
        errorLabel.setStyle(errorText);
        String myPackage = "assignment5";
        try {
            if (cloverDisplayed) {
                String selection = "Clover";
                String output = (String) Class.forName(myPackage + "." + selection)
                        .getMethod("runStats", List.class)
                        .invoke(null, Critter.getInstances(selection));
                statsGrid.getChildren().removeIf( node -> GridPane.getColumnIndex(node) == 0 && GridPane.getRowIndex(node) == 1);
                Label stats = new Label();
                stats.setStyle(statsText);
                stats.setText(output);
                statsGrid.add(stats, 0, 1);
            }
            if (c1Displayed) {
                String selection = "Critter1";
                String output = (String) Class.forName(myPackage + "." + selection)
                        .getMethod("runStats", List.class)
                        .invoke(null, Critter.getInstances(selection));
                statsGrid.getChildren().removeIf( node -> GridPane.getColumnIndex(node) == 0 && GridPane.getRowIndex(node) == 3);
                Label stats = new Label();
                stats.setStyle(statsText);
                stats.setText(output);
                statsGrid.add(stats, 0, 3);
            }
            if (c2Displayed) {
                String selection = "Critter2";
                String output = (String) Class.forName(myPackage + "." + selection)
                        .getMethod("runStats", List.class)
                        .invoke(null, Critter.getInstances(selection));
                statsGrid.getChildren().removeIf( node -> GridPane.getColumnIndex(node) == 0 && GridPane.getRowIndex(node) == 5);
                Label stats = new Label();
                stats.setStyle(statsText);
                stats.setText(output);
                statsGrid.add(stats, 0, 5);
            }
            if (c3Displayed) {
                String selection = "Critter3";
                String output = (String) Class.forName(myPackage + "." + selection)
                        .getMethod("runStats", List.class)
                        .invoke(null, Critter.getInstances(selection));
                statsGrid.getChildren().removeIf( node -> GridPane.getColumnIndex(node) == 0 && GridPane.getRowIndex(node) == 7);
                Label stats = new Label();
                stats.setStyle(statsText);
                stats.setText(output);
                statsGrid.add(stats, 0, 7);
            }
            if (c4Displayed) {
                String selection = "Critter4";
                String output = (String) Class.forName(myPackage + "." + selection)
                        .getMethod("runStats", List.class)
                        .invoke(null, Critter.getInstances(selection));
                statsGrid.getChildren().removeIf( node -> GridPane.getColumnIndex(node) == 0 && GridPane.getRowIndex(node) == 9);
                Label stats = new Label();
                stats.setStyle(statsText);
                stats.setText(output);
                statsGrid.add(stats, 0, 9);
            }
            if (goblinDisplayed) {
                String selection = "Goblin";
                String output = (String) Class.forName(myPackage + "." + selection)
                        .getMethod("runStats", List.class)
                        .invoke(null, Critter.getInstances(selection));
                statsGrid.getChildren().removeIf( node -> GridPane.getColumnIndex(node) == 0 && GridPane.getRowIndex(node) == 11);
                Label stats = new Label();
                stats.setStyle(statsText);
                stats.setText(output);
                statsGrid.add(stats, 0, 11);
            }
        }
        catch (NoClassDefFoundError | NoSuchMethodException |
                IllegalArgumentException | InvocationTargetException |
                IllegalAccessException | ClassNotFoundException |
                InvalidCritterException e){
            System.out.println("ERROR: " + e);
            GridPane.setConstraints(errorLabel, 1, sta+1);
            if (! grid.getChildren().contains(errorLabel)) grid.getChildren().add(errorLabel);
        }
    }

}