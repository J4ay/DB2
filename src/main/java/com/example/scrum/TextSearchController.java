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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class TextSearchController {
    @FXML
    private Button searchButton;
    @FXML
    private Button searchButton1;
    @FXML
    private Label title;
    @FXML
    private Label title1;
    @FXML
    private Label title2;
    @FXML
    private Label title3;
    @FXML
    private Label title4;
    @FXML
    private Label title5;
    @FXML
    private Label title6;
    @FXML
    private Label title7;
    @FXML
    private Label title8;
    @FXML
    private Label title9;
    @FXML
    private Label availableBooks;
    @FXML
    private Label availableBooks1;
    @FXML
    private Label availableBooks2;
    @FXML
    private Label availableBooks3;
    @FXML
    private Label availableBooks4;
    @FXML
    private Label availableBooks5;
    @FXML
    private Label availableBooks6;
    @FXML
    private Label availableBooks7;
    @FXML
    private Label availableBooks8;
    @FXML
    private Label availableBooks9;
    @FXML
    private Label rating;
    @FXML
    private Label rating1;
    @FXML
    private Label rating2;
    @FXML
    private Label rating3;
    @FXML
    private Label rating4;
    @FXML
    private Label rating5;
    @FXML
    private Label rating6;
    @FXML
    private Label rating7;
    @FXML
    private Label rating8;
    @FXML
    private Label rating9;
    @FXML
    private Pane card;
    @FXML
    private Pane card1;
    @FXML
    private Pane card2;
    @FXML
    private Pane card3;
    @FXML
    private Pane card4;
    @FXML
    private Pane card5;
    @FXML
    private Pane card6;
    @FXML
    private Pane card7;
    @FXML
    private Pane card8;
    @FXML
    private Pane card9;
    @FXML
    private TextField titleSearchText;
    @FXML
    private TextField isbnSearchText;
    @FXML
    private Label isbnErrorLabel;
    @FXML
    private Label home;
    @FXML
    private Button addBookButton;

    Database db = new Database();
    private GUI gui = new GUI();
    ArrayList<Label> titleLabels = new ArrayList<Label>();
    ArrayList<Label> availableBooksLabels = new ArrayList<Label>();
    ArrayList<Label> ratingLabels = new ArrayList<Label>();
    ArrayList<Pane> cards = new ArrayList<Pane>();
    int numberOfCards = 10;

    @FXML
    public void initialize() {      //executes AFTER the FXML elements are initialized

        // ********** adding labels to ArrayLists **********
        titleLabels.add(title);
        titleLabels.add(title1);
        titleLabels.add(title2);
        titleLabels.add(title3);
        titleLabels.add(title4);
        titleLabels.add(title5);
        titleLabels.add(title6);
        titleLabels.add(title7);
        titleLabels.add(title8);
        titleLabels.add(title9);
        availableBooksLabels.add(availableBooks);
        availableBooksLabels.add(availableBooks1);
        availableBooksLabels.add(availableBooks2);
        availableBooksLabels.add(availableBooks3);
        availableBooksLabels.add(availableBooks4);
        availableBooksLabels.add(availableBooks5);
        availableBooksLabels.add(availableBooks6);
        availableBooksLabels.add(availableBooks7);
        availableBooksLabels.add(availableBooks8);
        availableBooksLabels.add(availableBooks9);
        ratingLabels.add(rating);
        ratingLabels.add(rating1);
        ratingLabels.add(rating2);
        ratingLabels.add(rating3);
        ratingLabels.add(rating4);
        ratingLabels.add(rating5);
        ratingLabels.add(rating6);
        ratingLabels.add(rating7);
        ratingLabels.add(rating8);
        ratingLabels.add(rating9);
        cards.add(card);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        cards.add(card8);
        cards.add(card9);


        // ********** getting search result **********
        titleSearchText.setText(HelloApplication.last_search_text);
        if (HelloApplication.last_search_text == null){
            db.getBooksByIsbn(HelloApplication.last_search_isbn);
        }
        if (HelloApplication.last_search_isbn == 0)
            db.getBooksByName(HelloApplication.last_search_text);       //saves search result in global variable HelloApplication.last_search

        // ********** set all cards invisible **********
        for(int i = 0; i < numberOfCards; i++) {
            cards.get(i).setVisible(false);
        }

        isbnErrorLabel.setVisible(false);

        if(HelloApplication.librarian) {
            addBookButton.setVisible(true);
        } else {
            addBookButton.setVisible(false);
        }


        // ********** putting results into result cards **********
        putResultsIntoCards();
    }

    public void putResultsIntoCards() {
        for(int i = 0; i < HelloApplication.last_search.size() && i < numberOfCards; i++) {
            cards.get(i).setVisible(true);             //set as many cards visible as the last search returned results

            titleLabels.get(i).setText(HelloApplication.last_search.get(i).getTitle());

            int total_books = 0, borrowed_books = 0;
            String available_books = null;
            total_books = HelloApplication.last_search.get(i).getNumber_of_books();
            borrowed_books = HelloApplication.last_search.get(i).getNumber_borrowed_books();
            available_books = "" + (total_books - borrowed_books);
            availableBooksLabels.get(i).setText(available_books);

            String ratingAsString = "" + HelloApplication.last_search.get(i).getAverage_rating();
            ratingLabels.get(i).setText(ratingAsString);
        }
    }

    public void searchButtonPressed(ActionEvent event) throws IOException {
        // ********** emptying all result cars **********
        for(int i = 0; i < HelloApplication.last_search.size() && i < numberOfCards; i++) {
            titleLabels.get(i).setText("");
            availableBooksLabels.get(i).setText("");
            ratingLabels.get(i).setText("");
            cards.get(i).setVisible(false);
        }
        // ********** getting new search result **********
        db.getBooksByName(titleSearchText.getText());       //saves search result in global variable HelloApplication.last_search

        // ********** putting results into result cards **********
        putResultsIntoCards();
    }

    // ********** making the result cards clickable **********
    public void bookCard0Pressed(MouseEvent event) throws IOException {
        bookCardPressed(event, 0);
    }
    public void bookCard1Pressed(MouseEvent event) throws IOException {
        bookCardPressed(event, 1);
    }
    public void bookCard2Pressed(MouseEvent event) throws IOException {
        bookCardPressed(event, 2);
    }
    public void bookCard3Pressed(MouseEvent event) throws IOException {
        bookCardPressed(event, 3);
    }
    public void bookCard4Pressed(MouseEvent event) throws IOException {
        bookCardPressed(event, 4);
    }
    public void bookCard5Pressed(MouseEvent event) throws IOException {
        bookCardPressed(event, 5);
    }
    public void bookCard6Pressed(MouseEvent event) throws IOException {
        bookCardPressed(event, 6);
    }
    public void bookCard7Pressed(MouseEvent event) throws IOException {
        bookCardPressed(event, 7);
    }
    public void bookCard8Pressed(MouseEvent event) throws IOException {
        bookCardPressed(event, 8);
    }
    public void bookCard9Pressed(MouseEvent event) throws IOException {
        bookCardPressed(event, 9);
    }

    public void bookCardPressed(MouseEvent event, int card_number) throws IOException {
        HelloApplication.current_book = HelloApplication.last_search.get(card_number);
        gui.switchToBookPage(event);
    }

    public void isbnButtonPressed(ActionEvent event) throws IOException {
        try{
        if (isbnSearchText.getText() != ""){
            if (isbnSearchText.getText().length() == 4){

                HelloApplication.current_book = db.getBookByIsbn(Integer.parseInt(isbnSearchText.getText()));
                gui.switchToBookPage(event);}
            else {
                for(int i = 0; i < HelloApplication.last_search.size() && i < numberOfCards; i++) {
                    titleLabels.get(i).setText("");
                    availableBooksLabels.get(i).setText("");
                    ratingLabels.get(i).setText("");
                    cards.get(i).setVisible(false);
                }
                // ********** getting new search result **********
                db.getBooksByIsbn(Integer.parseInt(isbnSearchText.getText()));       //saves search result in global variable HelloApplication.last_search

                // ********** putting results into result cards **********
                putResultsIntoCards();
            }
        }
        } catch (NumberFormatException e) {
            isbnErrorLabel.setVisible(true);
        }
    }


    public void homeClicked(MouseEvent event) throws IOException {
        gui.switchToHomePage(event);
    }

    public void addBookButtonPressed(ActionEvent event) throws IOException {
        gui.switchToAddBookPage(event);
    }
}
