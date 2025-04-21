package com.example.tp_morpion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class QuitterController {

    //////////////
    //References//
    //////////////
    private Stage quitterWindow;

    private Stage stage;

    private Stage aideWindow;

    public void setQuitterWindow(Stage quitterWindow ){
        this.quitterWindow = quitterWindow;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setAideWindow(Stage aideWindow ){
        this.aideWindow = aideWindow;
    }


    //////////////////////////
    //Gestion des evenements//
    //////////////////////////
    @FXML
    void noQuitClick(ActionEvent event) {
        quitterWindow.close();
        stage.getScene().getRoot().setEffect(null); //pour enelever l'effet flou lorsque l'on quitte la fenetre modale en passant par les boutons de la page
    }

    @FXML
    void yesQuitClick(ActionEvent event) {
        quitterWindow.close();
        aideWindow.close();
        stage.close();

    }
}
