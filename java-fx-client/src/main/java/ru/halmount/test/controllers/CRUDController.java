package ru.halmount.test.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ru.halmount.test.DTO.*;
import ru.halmount.test.model.*;
import ru.halmount.test.services.HTTPService;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import static ru.halmount.test.JavaFXClient.primaryStage;

public class CRUDController implements Initializable {
    private static final TypeReference<List<Client>> LIST_CLIENT_TYPE_REFERENCE = new TypeReference<>() {
    };
    private static final TypeReference<List<Credit>> LIST_CREDIT_TYPE_REFERENCE = new TypeReference<>() {
    };
    private static final TypeReference<List<Bank>> LIST_BANK_TYPE_REFERENCE = new TypeReference<>() {
    };
    private static final TypeReference<List<CreditOffer>> LIST_CREDIT_OFFER_TYPE_REFERENCE = new TypeReference<>() {
    };
    private static final TypeReference<List<Payment>> LIST_PAYMENT_TYPE_REFERENCE = new TypeReference<>() {
    };
    @FXML
    TableView table;
    @FXML
    Button createButton;
    @FXML
    Button updateButton;
    @FXML
    Button deleteButton;
    @FXML
    Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switch (MainController.entityType) {

            case CREDIT -> {
                List<Credit> credit = HTTPService.get("http://localhost:8080/credits", LIST_CREDIT_TYPE_REFERENCE);
                initializeTable(Credit.class, credit);
                createButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/Create.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    GridPane createGrid = (GridPane) root.lookup("#createGrid");
                    int index = 0;
                    for (Field declaredField : CreateCreditDTO.class.getDeclaredFields()) {
                        Label fieldLabel = new Label(declaredField.getName());
                        if (declaredField.getType().equals(LocalDate.class)) {
                            DatePicker dField = new DatePicker();
                            dField.setId(declaredField.getName());
                            createGrid.addRow(index, fieldLabel, dField);
                            index++;
                        } else {
                            TextField textField = new TextField();
                            textField.setId(declaredField.getName());
                            createGrid.addRow(index, fieldLabel, textField);
                            index++;
                        }
                    }

                    Button back = (Button) root.lookup("#back");
                    Button create = (Button) root.lookup("#create");
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
                    Parent finalRoot = root;
                    create.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventNew -> {
                        CreateCreditDTO requestBody = new CreateCreditDTO();
                        for (Field declaredField : CreateCreditDTO.class.getDeclaredFields()) {
                            if (declaredField.getType().equals(LocalDate.class)) {
                                DatePicker dField = (DatePicker) finalRoot.lookup("#" + declaredField.getName());
                                setValue(declaredField, requestBody, dField);
                            } else {
                                TextField tField = (TextField) finalRoot.lookup("#" + declaredField.getName());
                                setValue(declaredField, requestBody, tField);
                            }

                        }
                        HTTPService.post("http://localhost:8080/credits", requestBody, null);
                    });
                    Scene scene = new Scene(root, 600, 400);

                    primaryStage.setScene(scene);
                });
                updateButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    Credit selectedItem = (Credit) table.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/Update.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        GridPane updateGrid = (GridPane) root.lookup("#updateGrid");
                        int index = 0;
                        for (Field declaredField : UpdateCreditDTO.class.getDeclaredFields()) {
                            Label fieldLabel = new Label(declaredField.getName());
                            if (declaredField.getType().equals(LocalDate.class)) {
                                DatePicker dField = new DatePicker();
                                dField.setId(declaredField.getName());
                                updateGrid.addRow(index, fieldLabel, dField);
                                index++;
                            } else {
                                TextField textField = new TextField();
                                textField.setId(declaredField.getName());
                                updateGrid.addRow(index, fieldLabel, textField);
                                index++;
                            }
                        }

                        Button back = (Button) root.lookup("#back");
                        Button update = (Button) root.lookup("#update");
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
                        Parent finalRoot = root;
                        update.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventNew -> {
                            UpdateCreditDTO requestBody = new UpdateCreditDTO();
                            for (Field declaredField : UpdateCreditDTO.class.getDeclaredFields()) {
                                if (declaredField.getType().equals(LocalDate.class)) {
                                    DatePicker dField = (DatePicker) finalRoot.lookup("#" + declaredField.getName());
                                    setValue(declaredField, requestBody, dField);
                                } else {
                                    TextField tField = (TextField) finalRoot.lookup("#" + declaredField.getName());
                                    setValue(declaredField, requestBody, tField);
                                }

                            }
                            HTTPService.put("http://localhost:8080/credits/" + selectedItem.getId(), requestBody);
                        });
                        Scene scene = new Scene(root, 600, 400);

                        primaryStage.setScene(scene);
                    }
                });
                deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    Credit selectedItem = (Credit) table.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        HTTPService.delete("http://localhost:8080/credits/" + selectedItem.getId());
                        table.getItems().remove(selectedItem);
                    }
                });
            }
            case CLIENT -> {
                List<Client> clients = HTTPService.get("http://localhost:8080/clients", LIST_CLIENT_TYPE_REFERENCE);
                initializeTable(Client.class, clients);
                createButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/Create.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    GridPane createGrid = (GridPane) root.lookup("#createGrid");
                    int index = 0;
                    for (Field declaredField : CreateClientDTO.class.getDeclaredFields()) {
                        Label fieldLabel = new Label(declaredField.getName());
                        if (declaredField.getType().equals(LocalDate.class)) {
                            DatePicker dField = new DatePicker();
                            dField.setId(declaredField.getName());
                            createGrid.addRow(index, fieldLabel, dField);
                            index++;
                        } else {
                            TextField textField = new TextField();
                            textField.setId(declaredField.getName());
                            createGrid.addRow(index, fieldLabel, textField);
                            index++;
                        }
                    }

                    Button back = (Button) root.lookup("#back");
                    Button create = (Button) root.lookup("#create");
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
                    Parent finalRoot = root;
                    create.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventNew -> {
                        CreateClientDTO requestBody = new CreateClientDTO();
                        for (Field declaredField : CreateClientDTO.class.getDeclaredFields()) {
                            if (declaredField.getType().equals(LocalDate.class)) {
                                DatePicker dField = (DatePicker) finalRoot.lookup("#" + declaredField.getName());
                                setValue(declaredField, requestBody, dField);
                            } else {
                                TextField tField = (TextField) finalRoot.lookup("#" + declaredField.getName());
                                setValue(declaredField, requestBody, tField);
                            }

                        }
                        HTTPService.post("http://localhost:8080/clients", requestBody, null);
                    });
                    Scene scene = new Scene(root, 600, 400);

                    primaryStage.setScene(scene);
                });
                updateButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    Client selectedItem = (Client) table.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/Update.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        GridPane updateGrid = (GridPane) root.lookup("#updateGrid");
                        int index = 0;
                        for (Field declaredField : UpdateClientDTO.class.getDeclaredFields()) {
                            Label fieldLabel = new Label(declaredField.getName());
                            if (declaredField.getType().equals(LocalDate.class)) {
                                DatePicker dField = new DatePicker();
                                dField.setId(declaredField.getName());
                                updateGrid.addRow(index, fieldLabel, dField);
                                index++;
                            } else {
                                TextField textField = new TextField();
                                textField.setId(declaredField.getName());
                                updateGrid.addRow(index, fieldLabel, textField);
                                index++;
                            }
                        }

                        Button back = (Button) root.lookup("#back");
                        Button update = (Button) root.lookup("#update");
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
                        Parent finalRoot = root;
                        update.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventNew -> {
                            UpdateClientDTO requestBody = new UpdateClientDTO();
                            for (Field declaredField : UpdateClientDTO.class.getDeclaredFields()) {
                                if (declaredField.getType().equals(LocalDate.class)) {
                                    DatePicker dField = (DatePicker) finalRoot.lookup("#" + declaredField.getName());
                                    setValue(declaredField, requestBody, dField);
                                } else {
                                    TextField tField = (TextField) finalRoot.lookup("#" + declaredField.getName());
                                    setValue(declaredField, requestBody, tField);
                                }

                            }
                            HTTPService.put("http://localhost:8080/clients/" + selectedItem.getId(), requestBody);
                        });
                        Scene scene = new Scene(root, 600, 400);

                        primaryStage.setScene(scene);
                    }
                });
                deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    Client selectedItem = (Client) table.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        HTTPService.delete("http://localhost:8080/clients/" + selectedItem.getId());
                        table.getItems().remove(selectedItem);
                    }
                });
            }
            case BANK -> {
                List<Bank> banks = HTTPService.get("http://localhost:8080/banks", LIST_BANK_TYPE_REFERENCE);
                initializeTable(Bank.class, banks);
                createButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/Create.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Button back = (Button) root.lookup("#back");
                    Button create = (Button) root.lookup("#create");
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
                    create.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventNew -> {
                        HTTPService.post("http://localhost:8080/banks", null, null);
                    });
                    Scene scene = new Scene(root, 600, 400);

                    primaryStage.setScene(scene);
                });
                updateButton.setVisible(false); // TODO Ребят, не покупайте сентри, плс)
                deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    Bank selectedItem = (Bank) table.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        HTTPService.delete("http://localhost:8080/banks/" + selectedItem.getId());
                        table.getItems().remove(selectedItem);
                    }
                });
            }
            case CREDIT_OFFER -> {
                List<CreditOffer> creditOffers = HTTPService.get("http://localhost:8080/creditOffers", LIST_CREDIT_OFFER_TYPE_REFERENCE);
                initializeTable(CreditOffer.class, creditOffers);
                createButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/Create.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    GridPane createGrid = (GridPane) root.lookup("#createGrid");
                    int index = 0;
                    for (Field declaredField : CreateCreditOfferDTO.class.getDeclaredFields()) {
                        Label fieldLabel = new Label(declaredField.getName());
                        if (declaredField.getType().equals(LocalDate.class)) {
                            DatePicker dField = new DatePicker();
                            dField.setId(declaredField.getName());
                            createGrid.addRow(index, fieldLabel, dField);
                            index++;
                        } else {
                            TextField textField = new TextField();
                            textField.setId(declaredField.getName());
                            createGrid.addRow(index, fieldLabel, textField);
                            index++;
                        }
                    }

                    Button back = (Button) root.lookup("#back");
                    Button create = (Button) root.lookup("#create");
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
                    Parent finalRoot = root;
                    create.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventNew -> {
                        CreateCreditOfferDTO requestBody = new CreateCreditOfferDTO();
                        for (Field declaredField : CreateCreditOfferDTO.class.getDeclaredFields()) {
                            if (declaredField.getType().equals(LocalDate.class)) {
                                DatePicker dField = (DatePicker) finalRoot.lookup("#" + declaredField.getName());
                                setValue(declaredField, requestBody, dField);
                            } else {
                                TextField tField = (TextField) finalRoot.lookup("#" + declaredField.getName());
                                setValue(declaredField, requestBody, tField);
                            }

                        }
                        HTTPService.post("http://localhost:8080/creditOffers", requestBody, null);
                    });
                    Scene scene = new Scene(root, 600, 400);

                    primaryStage.setScene(scene);
                });
                updateButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    CreditOffer selectedItem = (CreditOffer) table.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/Update.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        GridPane updateGrid = (GridPane) root.lookup("#updateGrid");
                        int index = 0;
                        for (Field declaredField : UpdateCreditOfferDTO.class.getDeclaredFields()) {
                            Label fieldLabel = new Label(declaredField.getName());
                            if (declaredField.getType().equals(LocalDate.class)) {
                                DatePicker dField = new DatePicker();
                                dField.setId(declaredField.getName());
                                updateGrid.addRow(index, fieldLabel, dField);
                                index++;
                            } else {
                                TextField textField = new TextField();
                                textField.setId(declaredField.getName());
                                updateGrid.addRow(index, fieldLabel, textField);
                                index++;
                            }
                        }

                        Button back = (Button) root.lookup("#back");
                        Button update = (Button) root.lookup("#update");
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
                        Parent finalRoot = root;
                        update.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventNew -> {
                            UpdateCreditOfferDTO requestBody = new UpdateCreditOfferDTO();
                            for (Field declaredField : UpdateCreditOfferDTO.class.getDeclaredFields()) {
                                if (declaredField.getType().equals(LocalDate.class)) {
                                    DatePicker dField = (DatePicker) finalRoot.lookup("#" + declaredField.getName());
                                    setValue(declaredField, requestBody, dField);
                                } else {
                                    TextField tField = (TextField) finalRoot.lookup("#" + declaredField.getName());
                                    setValue(declaredField, requestBody, tField);
                                }

                            }
                            HTTPService.put("http://localhost:8080/creditOffers/" + selectedItem.getId(), requestBody);
                        });
                        Scene scene = new Scene(root, 600, 400);

                        primaryStage.setScene(scene);
                    }
                });
                deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    CreditOffer selectedItem = (CreditOffer) table.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        HTTPService.delete("http://localhost:8080/creditOffers/" + selectedItem.getId());
                        table.getItems().remove(selectedItem);
                    }
                });
            }
            case PAYMENT -> {
                List<Payment> payments = HTTPService.get("http://localhost:8080/payments", LIST_PAYMENT_TYPE_REFERENCE);
                initializeTable(Payment.class, payments);
                createButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/Create.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    GridPane createGrid = (GridPane) root.lookup("#createGrid");
                    int index = 0;
                    for (Field declaredField : CreatePaymentDTO.class.getDeclaredFields()) {
                        Label fieldLabel = new Label(declaredField.getName());
                        if (declaredField.getType().equals(LocalDate.class)) {
                            DatePicker dField = new DatePicker();
                            dField.setId(declaredField.getName());
                            createGrid.addRow(index, fieldLabel, dField);
                            index++;
                        } else {
                            TextField textField = new TextField();
                            textField.setId(declaredField.getName());
                            createGrid.addRow(index, fieldLabel, textField);
                            index++;
                        }
                    }

                    Button back = (Button) root.lookup("#back");
                    Button create = (Button) root.lookup("#create");
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
                    Parent finalRoot = root;
                    create.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventNew -> {
                        CreatePaymentDTO requestBody = new CreatePaymentDTO();
                        for (Field declaredField : CreatePaymentDTO.class.getDeclaredFields()) {
                            if (declaredField.getType().equals(LocalDate.class)) {
                                DatePicker dField = (DatePicker) finalRoot.lookup("#" + declaredField.getName());
                                setValue(declaredField, requestBody, dField);
                            } else {
                                TextField tField = (TextField) finalRoot.lookup("#" + declaredField.getName());
                                setValue(declaredField, requestBody, tField);
                            }

                        }
                        HTTPService.post("http://localhost:8080/payments", requestBody, null);
                    });
                    Scene scene = new Scene(root, 600, 400);

                    primaryStage.setScene(scene);
                });
                updateButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    Payment selectedItem = (Payment) table.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/Update.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        GridPane updateGrid = (GridPane) root.lookup("#updateGrid");
                        int index = 0;
                        for (Field declaredField : UpdatePaymentDTO.class.getDeclaredFields()) {
                            Label fieldLabel = new Label(declaredField.getName());
                            if (declaredField.getType().equals(LocalDate.class)) {
                                DatePicker dField = new DatePicker();
                                dField.setId(declaredField.getName());
                                updateGrid.addRow(index, fieldLabel, dField);
                                index++;
                            } else {
                                TextField textField = new TextField();
                                textField.setId(declaredField.getName());
                                updateGrid.addRow(index, fieldLabel, textField);
                                index++;
                            }
                        }

                        Button back = (Button) root.lookup("#back");
                        Button update = (Button) root.lookup("#update");
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
                        Parent finalRoot = root;
                        update.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventNew -> {
                            UpdatePaymentDTO requestBody = new UpdatePaymentDTO();
                            for (Field declaredField : UpdatePaymentDTO.class.getDeclaredFields()) {
                                if (declaredField.getType().equals(LocalDate.class)) {
                                    DatePicker dField = (DatePicker) finalRoot.lookup("#" + declaredField.getName());
                                    setValue(declaredField, requestBody, dField);
                                } else {
                                    TextField tField = (TextField) finalRoot.lookup("#" + declaredField.getName());
                                    setValue(declaredField, requestBody, tField);
                                }

                            }
                            HTTPService.put("http://localhost:8080/payments/" + selectedItem.getId(), requestBody);
                        });
                        Scene scene = new Scene(root, 600, 400);

                        primaryStage.setScene(scene);
                    }
                });
                deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    Payment selectedItem = (Payment) table.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        HTTPService.delete("http://localhost:8080/payments/" + selectedItem.getId());
                        table.getItems().remove(selectedItem);
                    }
                });
            }
        }

        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/main.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root, 600, 362);

            primaryStage.setScene(scene);
        });
    }

    private <T> void initializeTable(Class<T> clazz, List<T> models) {
        table.getItems().clear();
        table.getColumns().clear();
        for (Field declaredField : clazz.getDeclaredFields()) {
            String name = declaredField.getName();
            if (name.equals("clients") || name.equals("credits") || name.equals("payGraph")) {
                continue;
            }
            if (name.equals("client") || name.equals("credit")) {
                TableColumn<T, String> column = new TableColumn<>(name + "id");
                column.setCellValueFactory(new PropertyValueFactory<>(name));
                table.getColumns().add(column);
            } else {
                TableColumn<T, String> column = new TableColumn<>(name);
                column.setCellValueFactory(new PropertyValueFactory<>(name));
                table.getColumns().add(column);
            }
        }
        table.getItems().addAll(models);
    }

    private void setValue(Field declaredField, Object requestBody, TextField tField) {
        try {
            Class<?> clazz = declaredField.getType();
            if (clazz.equals(Integer.class)) {
                declaredField.set(requestBody, Integer.parseInt(tField.getText()));
            } else if (clazz.equals(BigDecimal.class)) {
                declaredField.set(requestBody, new BigDecimal(tField.getText()));
            } else {
                declaredField.set(requestBody, tField.getText());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void setValue(Field declaredField, Object requestBody, DatePicker dField) {
        try {
            declaredField.set(requestBody, dField.getValue());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

