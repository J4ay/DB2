package com.example.scrum;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;


public class RoleSelectController {

    @FXML
    private Button customerButton;
    @FXML
    private Button librarianButton;

    Database db = new Database();

    private GUI gui = new GUI();

    public void customerButtonPressed(ActionEvent event) throws IOException {
        HelloApplication.librarian = false;
       gui.switchToHomePage(event);
    }

    public void librarianButtonPressed(ActionEvent event) throws IOException {
        HelloApplication.librarian = true;
        gui.switchToHomePage(event);
    }
}
