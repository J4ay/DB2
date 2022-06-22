package com.example.scrum;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HelloController {          //start_search controller!
    @FXML
    private Button isbnButton1;
    @FXML
    private Button nameButton2;
    @FXML
    private Label testLabel;
    @FXML
    private Label isbnErrorLabel;
    @FXML
    private TextField isbnText;
    @FXML
    private TextField nameText;
    @FXML
    private Button addBookButton;

    Database db = new Database();
    private GUI gui = new GUI();

    @FXML
    public void initialize() {
        isbnErrorLabel.setVisible(false);
        if(HelloApplication.librarian) {
            addBookButton.setVisible(true);
        } else {
            addBookButton.setVisible(false);
        }
    }

    public void isbnButtonPressed(ActionEvent event) throws IOException {
        try {
            if (isbnText.getText() != "") {
                if (isbnText.getText().length() == 4) {

                    HelloApplication.current_book = db.getBookByIsbn(Integer.parseInt(isbnText.getText()));
                    gui.switchToBookPage(event);
                } else {
                    db.getBooksByIsbn(Integer.parseInt(isbnText.getText()));
                    gui.switchToTextSearch(event);
                }
            }
        } catch (NumberFormatException e) {
            isbnErrorLabel.setVisible(true);
        }
    }

    public void nameButtonPressed(ActionEvent event) throws IOException {
        db.getBooksByName(nameText.getText());
        gui.switchToTextSearch(event);

    }

    public void addBookButtonPressed(ActionEvent event) throws IOException {
        gui.switchToAddBookPage(event);
    }

}