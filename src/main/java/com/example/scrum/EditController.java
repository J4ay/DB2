package com.example.scrum;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class EditController {
    @FXML
    private Label home;
    @FXML
    private TextField isbnInput;
    @FXML
    private Label isbnCurrent;
    @FXML
    private Button isbnButton;
    @FXML
    private TextField titleInput;
    @FXML
    private Label titleCurrent;
    @FXML
    private Button titleButton;
    @FXML
    private TextField authorInput;
    @FXML
    private Label authorCurrent;
    @FXML
    private Button authorButton;
    @FXML
    private TextField yearInput;
    @FXML
    private Label yearCurrent;
    @FXML
    private Button yearButton;
    @FXML
    private TextField editionInput;
    @FXML
    private Label editionCurrent;
    @FXML
    private Button editionButton;
    @FXML
    private TextField publisherInput;
    @FXML
    private Label publisherCurrent;
    @FXML
    private Button publisherButton;
    @FXML
    private TextField numberInput;
    @FXML
    private Label numberCurrent;
    @FXML
    private Button numberButton;
    @FXML
    private Button submitButton;
    @FXML
    private Label successLabel;
    @FXML
    private Button ratingsButton;
    @FXML
    private Label ratingsCurrent;
    @FXML
    private Button backButton;
    @FXML
    private Label errorLabel;

    Database db = new Database();
    private GUI gui = new GUI();

    @FXML
    public void initialize() {
        isbnCurrent.setText("" + HelloApplication.current_book.getIsbn());
        titleCurrent.setText(HelloApplication.current_book.getTitle());
        authorCurrent.setText(HelloApplication.current_book.getAuthor());
        yearCurrent.setText("" + HelloApplication.current_book.getYear());
        editionCurrent.setText(HelloApplication.current_book.getEdition());
        publisherCurrent.setText(HelloApplication.current_book.getPublisher());
        numberCurrent.setText("" + HelloApplication.current_book.getNumber_of_books());
        ratingsCurrent.setText("Average: " + HelloApplication.current_book.getAverage_rating() + " (from " + HelloApplication.current_book.getNumber_ratings() + " ratings)");
        errorLabel.setVisible(false);
    }

    public void homeClicked(MouseEvent event) throws IOException {
        gui.switchToHomePage(event);
    }

    public void isbnButtonPressed(ActionEvent event) throws IOException {
        try {
            db.updateIsbn(Integer.parseInt(isbnInput.getText()));
            isbnCurrent.setText("" + HelloApplication.current_book.getIsbn());
        } catch (NumberFormatException e) {
            errorLabel.setVisible(true);
        }
    }

    public void titleButtonPressed(ActionEvent event) throws IOException {
            if(titleInput.getText().length() > 40 ) {
                errorLabel.setVisible(true);
            } else {
                db.updateTitle(titleInput.getText());
                titleCurrent.setText(HelloApplication.current_book.getTitle());
            }
    }

    public void authorButtonPressed(ActionEvent event) throws IOException {
        if(authorInput.getText().length() > 40 ) {
            errorLabel.setVisible(true);
        } else {
            db.updateAuthor(authorInput.getText());
            authorCurrent.setText(HelloApplication.current_book.getAuthor());
        }
    }

    public void yearButtonPressed(ActionEvent event) throws IOException {
        try {
            db.updateYear(Integer.parseInt(yearInput.getText()));
            yearCurrent.setText("" + HelloApplication.current_book.getYear());
        } catch (NumberFormatException e) {
            errorLabel.setVisible(true);
        }
    }

    public void editionButtonPressed(ActionEvent event) throws IOException {
        if(editionInput.getText().length() > 40 ) {
            errorLabel.setVisible(true);
        } else {
            db.updateEdition(editionInput.getText());
            editionCurrent.setText(HelloApplication.current_book.getEdition());
        }
    }

    public void publisherButtonPressed(ActionEvent event) throws IOException {
        if(publisherInput.getText().length() > 40 ) {
            errorLabel.setVisible(true);
        } else {
            db.updatePublisher(publisherInput.getText());
            publisherCurrent.setText(HelloApplication.current_book.getPublisher());
        }
    }

    public void numberButtonPressed(ActionEvent event) throws IOException {
        try {
            db.updateNumber(Integer.parseInt(numberInput.getText()));
            numberCurrent.setText("" + HelloApplication.current_book.getNumber_of_books());
        } catch (NumberFormatException e) {
            errorLabel.setVisible(true);
        }
    }

    public void ratingsButtonPressed(ActionEvent event) throws IOException {
        db.resetRatings();
        ratingsCurrent.setText("Average: " + HelloApplication.current_book.getAverage_rating() + " (from " + HelloApplication.current_book.getNumber_ratings() + " ratings)");
    }

    public void backButtonPressed(ActionEvent event) throws IOException {
        gui.switchToBookPage(event);
    }

}
