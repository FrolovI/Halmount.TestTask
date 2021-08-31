package ru.halmount.test.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import ru.halmount.test.DTO.CreateUserCreditOfferDTO;
import ru.halmount.test.model.CreditOffer;
import ru.halmount.test.services.HTTPService;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import static ru.halmount.test.JavaFXClient.primaryStage;

public class AcceptOfferController implements Initializable {
    private static final TypeReference<CreditOffer> CREDIT_OFFER_TYPE_REFERENCE = new TypeReference<>() {
    };
    @FXML
    TextField sumCredit;
    @FXML
    TextField monthCount;
    @FXML
    TextField client;
    @FXML
    TextField credit;
    @FXML
    Button accept;
    @FXML
    Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        accept.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventNew -> {
            CreateUserCreditOfferDTO requestBody = new CreateUserCreditOfferDTO();
            requestBody.idClient = Integer.valueOf(client.getText());
            requestBody.idCredit = Integer.valueOf(credit.getText());
            requestBody.sumCredit = new BigDecimal(sumCredit.getText());
            requestBody.monthCount = Integer.valueOf(monthCount.getText());
            CreditOffer response = HTTPService.post("http://localhost:8080/users/creditOffers", requestBody, CREDIT_OFFER_TYPE_REFERENCE);
            JOptionPane.showMessageDialog(null, "You accepted credit on: " + response.sumCredit + "RUB; \nOn: " + requestBody.monthCount + " month");
        });

        back.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventNew -> {
            Parent rootMain = null;
            try {
                rootMain = FXMLLoader.load(getClass().getResource("/main.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(rootMain, 600, 362);

            primaryStage.setScene(scene);
        });
    }
}
