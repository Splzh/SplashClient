package net.fabricmc.splash;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.minecraft.client.MinecraftClient;

//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

public class ExternalMenu extends Application {


    @Override
    public void start(final Stage primaryStage) {

        //Platform.setImplicitExit(false);


        Label splash = new Label("Splash Client by Splzh");
        splash.setTextFill(Color.WHITE);
        splash.setFont(new Font("Arial", 10));
        Label splash2 = new Label("SPLASH CLIENT");
        splash2.setTextFill(Color.WHITE);
        splash2.setFont(new Font("Chiller", 40));
        Label modulestext = new Label("Splash Client v2.2");
        modulestext.setTextFill(Color.WHITE);
        modulestext.setFont(new Font("Arial", 30));
        Label space = new Label("");
        Label space2 = new Label("");
        modulestext.setWrapText(true);
        Button button = new Button();
        button.setText("Config");
        BackgroundFill buttonC = new BackgroundFill(Color.SLATEGRAY, new CornerRadii(11), new Insets(-3.0, -3.0, -3.0, -3.0));
        button.setBackground(new Background(buttonC));
        BackgroundFill buttonB = new BackgroundFill(Color.DARKRED, new CornerRadii(11), new Insets(-3.0, -3.0, -3.0, -3.0));
        Button button2 = new Button();
        button2.setText("Config2");

        // MODULES //

        Button module1 = new Button();
        module1.setText("Time HUD");
        module1.setTextFill(Color.WHITE);
        module1.setBackground(new Background(buttonC));
        Button module2 = new Button();
        module2.setText("Player HUD");
        module2.setTextFill(Color.WHITE);
        module2.setBackground(new Background(buttonC));
        Button module3 = new Button();
        module3.setText("LowFire");
        module3.setTextFill(Color.WHITE);
        module3.setBackground(new Background(buttonC));
        Button module4 = new Button();
        module4.setText("NoHurtCam");
        module4.setTextFill(Color.WHITE);
        module4.setBackground(new Background(buttonC));
        Button module5 = new Button();
        module5.setText("ArmourHUD");
        module5.setTextFill(Color.WHITE);
        module5.setBackground(new Background(buttonC));
        Button module6 = new Button();
        module6.setText("Potion Glint");
        module6.setTextFill(Color.WHITE);
        module6.setBackground(new Background(buttonC));
        Button module7 = new Button();
        module7.setText("Health HUD");
        module7.setTextFill(Color.WHITE);
        module7.setBackground(new Background(buttonC));
        Button module8 = new Button();
        module8.setText("Totem Counter");
        module8.setTextFill(Color.WHITE);
        module8.setBackground(new Background(buttonC));

        Button eject = new Button();
        eject.setText("EJECT CORRECTLY");
        eject.setTextFill(Color.WHITE);
        eject.setBackground(new Background(buttonB));





        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Label secondLabel = new Label("Armour HUD Position");
                secondLabel.setTextFill(Color.WHITE);

                StackPane secondaryLayout = new StackPane();
                secondaryLayout.getChildren().add(secondLabel);

                Scene secondScene = new Scene(secondaryLayout, 230, 100, Color.DARKORANGE);

                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("Config");
                newWindow.setScene(secondScene);
                BackgroundFill myBF = new BackgroundFill(Color.web("#383838"), new CornerRadii(1), new Insets(0.0, 0.0, 0.0, 0.0));
                secondaryLayout.setBackground(new Background(myBF));

                // Set position of second window, related to primary window.
                newWindow.setX(primaryStage.getX() + 200);
                newWindow.setY(primaryStage.getY() + 100);

                newWindow.show();
            }
        });
        eject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
        module1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (ModConfig.enableTime == false) {
                    ModConfig.enableTime = true;
                } else {
                    ModConfig.enableTime = false;
                }
            }
        });

        TilePane root = new TilePane(Orientation.VERTICAL);
        //root.getChildren().add(splash2);
        BackgroundFill myBF = new BackgroundFill(Color.web("#383838"), new CornerRadii(1), new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(myBF));

        root.setAlignment(Pos.TOP_CENTER);
        root.setPrefRows(2);
        root.getChildren().add(space2);
        root.getChildren().add(modulestext);
        Scene scene = new Scene(root, 350, 500);
        primaryStage.setTitle("Splash Client v2.2");
        primaryStage.setScene(scene);
        primaryStage.show();
        root.getChildren().add(space);
        root.getChildren().add(module1);
        root.getChildren().add(module2);
        root.getChildren().add(module3);
        root.getChildren().add(module4);
        root.getChildren().add(module5);
        root.getChildren().add(module6);
        root.getChildren().add(module7);
        root.getChildren().add(module8);
        root.getChildren().add(button);
        root.getChildren().add(eject);

    }

    public static void main(String[] args) {
        launch(args);
        MinecraftClient.getInstance().isPaused();
    }
}
