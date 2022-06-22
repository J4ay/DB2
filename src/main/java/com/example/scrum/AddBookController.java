package com.example.scrum;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AddBookController {

    @FXML
    private Label home;
    @FXML
    private TextField isbnInput;
    @FXML
    private TextField titleInput;
    @FXML
    private TextField authorInput;
    @FXML
    private TextField yearInput;
    @FXML
    private TextField editionInput;
    @FXML
    private TextField publisherInput;
    @FXML
    private TextField numberInput;
    @FXML
    private Button submitButton;
    @FXML
    private Label successLabel;

    Database db = new Database();
    private GUI gui = new GUI();

    @FXML
    public void initialize() {
        successLabel.setVisible(false);
    }

    public void homeClicked(MouseEvent event) throws IOException {
        gui.switchToHomePage(event);
    }

    public void submitButtonPressed(ActionEvent event) throws IOException {
        db.addBook(Integer.parseInt(isbnInput.getText()), titleInput.getText(), authorInput.getText(), Integer.parseInt(yearInput.getText()), editionInput.getText(), publisherInput.getText(), Integer.parseInt(numberInput.getText()));
        successLabel.setVisible(true);
    }
}
