package com.example.scrum;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class DeletePromptController {

    @FXML
    private Label bookNameLabel;
    @FXML
    private Button noButton;
    @FXML
    private Button yesButton;

    private Database db = new Database();
    private GUI gui = new GUI();

    @FXML
    public void initialize() {
        bookNameLabel.setText(HelloApplication.current_book.getTitle());
    }

    public void noButtonPressed(ActionEvent event) throws IOException {
        gui.switchToBookPage(event);
    }

    public void yesButtonPressed(ActionEvent event) throws IOException {
        HelloApplication.last_search.remove(HelloApplication.current_book);
        db.deleteBook();
        HelloApplication.current_book = null;
        gui.switchToTextSearch(event);
    }

}
