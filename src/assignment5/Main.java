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

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage stage;
    public static GridPane grid = new GridPane();

    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;
        stage.setResizable(false);
        stage.setTitle("Project 5: Critters Simulation");
        grid.setPadding(new Insets(20,10,10,20));
        grid.setVgap(7);
        grid.setHgap(7);
        grid.setStyle(UI.backgroundColor);
        UI.generate();
        SubScene menu = new SubScene(grid, 850, 900);
        SubScene world = new SubScene(Critter.world, 950, 900);
        Critter.world.setPadding(new Insets(20,20,10,0));
        Critter.world.setStyle(UI.backgroundColor);
        HBox root = new HBox();
        root.getChildren().addAll(menu,world);
        Scene mainScene = new Scene(root,1800,900);
        stage.setScene(mainScene);
        stage.show();
    }

}