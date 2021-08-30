package ru.halmount.test.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import ru.halmount.test.EntityType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ru.halmount.test.JavaFXClient.primaryStage;

public class MainController implements Initializable {
    public static EntityType entityType;
    @FXML
    Button banksButton;
    @FXML
    Button clientsButton;
    @FXML
    Button creditsButton;
    @FXML
    Button paymentsButton;
    @FXML
    Button offersButton;
    @FXML
    Button takeCreditButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        banksButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            entityType = EntityType.BANK;
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/CRUD.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root, 600, 400);

            primaryStage.setScene(scene);
        });
        clientsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            entityType = EntityType.CLIENT;
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/CRUD.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root, 600, 400);

            primaryStage.setScene(scene);
        });
        creditsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            entityType = EntityType.CREDIT;
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/CRUD.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root, 600, 400);

            primaryStage.setScene(scene);
        });
        paymentsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            entityType = EntityType.PAYMENT;
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/CRUD.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root, 600, 400);

            primaryStage.setScene(scene);
        });
        offersButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            entityType = EntityType.CREDIT_OFFER;
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/CRUD.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root, 600, 400);

            primaryStage.setScene(scene);
        });
        takeCreditButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/AcceptOffer.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root, 600, 400);

            primaryStage.setScene(scene);
        });
    }
}
