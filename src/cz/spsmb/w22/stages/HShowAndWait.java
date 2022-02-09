package cz.spsmb.w22.stages;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

// Často je potřeba použít tzv. dialog okno a pozastavit činnost programu, dokud není dialog zavřen. Např. dialog
// typu yes/no. JavaFX narozdíl od Swing neposkytuje přímo dialog okno.
// Metoda stage.showAndWait() slouží přesně k tomuto účelu.
public class HShowAndWait extends Application {
    protected static int counter = 0;
    protected Stage lastOpenStage;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        Button openButton = new Button("Open");
        openButton.setOnAction(e -> open(++counter));
        root.getChildren().add(openButton);
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("The Primary Stage");
        stage.show();

        this.lastOpenStage = stage;
    }

    private void open(int stageNumber) {
        Stage stage = new Stage();
        stage.setTitle("#" + stageNumber);

        Button sayHelloButton = new Button("Say Hello");
        sayHelloButton.setOnAction(e -> System.out.println("Hello from #" + stageNumber));

        Button openButton = new Button("Open");
        openButton.setOnAction(e -> open(++counter));

        VBox root = new VBox();
        root.getChildren().addAll(sayHelloButton, openButton);
        Scene scene = new Scene(root, 200, 200);
        stage.setScene(scene);
        stage.setX(this.lastOpenStage.getX() + 50);
        stage.setY(this.lastOpenStage.getY() + 50);
        this.lastOpenStage = stage;

        System.out.println("Before stage.showAndWait(): " + stageNumber);

        // Show the stage and wait for it to close
        stage.showAndWait();
        //stage.show();
        //URL url = getClass().getClassLoader().getResource("C:\\Users\\stemberk\\IdeaProjects\\JavaFX\\cur.png");
        //Cursor myCur = Cursor.cursor(url.toExternalForm());
        //scene.setCursor(myCur);
        System.out.println("After stage.showAndWait(): " + stageNumber);
    }
}