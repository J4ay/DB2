package com.example.scrum;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class BookPageController {

    @FXML
    private Label bookPageLabel;
    @FXML
    private Label home;
    @FXML
    private Button backButton;
    @FXML
    private Button borrowButton;
    @FXML
    private Button returnButton;
    @FXML
    private Button ratingButton;
    @FXML
    private Label titleLabel;
    @FXML
    private Label borrowedLabel;
    @FXML
    private Label borrowSuccessLabel;
    @FXML
    private Label returnedLabel;
    @FXML
    private Label returnSuccessLabel;
    @FXML
    private Label ratingEmptyLabel;
    @FXML
    private Label ratingSuccessLabel;
    @FXML
    private ChoiceBox ratingChoiceBox;
    @FXML
    private Button editButton;
    @FXML
    private Button addBookButton;
    @FXML
    private Button deleteButton;

    Database db = new Database();
    private GUI gui = new GUI();

    @FXML
    public void initialize() {      //executes AFTER the FXML elements are initialized
        titleLabel.setText(HelloApplication.current_book.getTitle());
        bookPageLabel.setText(HelloApplication.current_book.getBookAsString());
        ObservableList<Integer> availableChoices = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5);
        ratingChoiceBox.setItems(availableChoices);

        // ****************************** setting all warning/success labels invisible ******************************
        borrowedLabel.setVisible(false);
        returnedLabel.setVisible(false);
        borrowSuccessLabel.setVisible(false);
        returnSuccessLabel.setVisible(false);
        ratingEmptyLabel.setVisible(false);
        ratingSuccessLabel.setVisible(false);



        if(HelloApplication.librarian) {
            editButton.setVisible(true);
            addBookButton.setVisible(true);
            deleteButton.setVisible(true);
        } else {
            editButton.setVisible(false);
            addBookButton.setVisible(false);
            deleteButton.setVisible(false);
        }


    }

    public void homeClicked(MouseEvent event) throws IOException {
        gui.switchToHomePage(event);
    }

    public void backButtonPressed(ActionEvent event) throws IOException {
        gui.switchToTextSearch(event);
    }

    public void borrowButtonPressed(ActionEvent event) throws IOException {

        // ****************************** calculating books left in library ******************************
        int total_books = HelloApplication.current_book.getNumber_of_books();
        int borrowed_books = HelloApplication.current_book.getNumber_borrowed_books();
        int books_left = total_books - borrowed_books;

        if(books_left > 0) {
            db.borrowBook();
            this.initialize();
            borrowSuccessLabel.setVisible(true);
        } else {
            borrowSuccessLabel.setVisible(false);
            borrowedLabel.setVisible(true);
        }
    }

    public void returnButtonPressed(ActionEvent event) throws IOException {
        int borrowed_books = HelloApplication.current_book.getNumber_borrowed_books();

        if(borrowed_books > 0) {
            db.returnBook();
            this.initialize();
            returnSuccessLabel.setVisible(true);
        } else {
            returnSuccessLabel.setVisible(false);
            returnedLabel.setVisible(true);
        }
    }

    public void ratingButtonPressed(ActionEvent event) throws IOException {
        if(ratingChoiceBox.getValue() != null) {
            int rating = (int) ratingChoiceBox.getValue();
            db.rateBook(rating);
            this.initialize();
            ratingSuccessLabel.setVisible(true);
        } else {
            ratingSuccessLabel.setVisible(false);
            ratingEmptyLabel.setVisible(true);
        }
    }

    public void editButtonPressed(ActionEvent event) throws IOException {
        gui.switchToEditPage(event);
    }

    public void addBookButtonPressed(ActionEvent event) throws IOException {
        gui.switchToAddBookPage(event);
    }

    public void deleteButtonPressed(ActionEvent event) throws IOException {
        gui.switchToDeletePrompt(event);

    }

}
