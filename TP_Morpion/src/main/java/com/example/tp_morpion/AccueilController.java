package com.example.tp_morpion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Stage;

public class AccueilController {


    //////////////
    //References//
    //////////////
    private Stage stage;
    private Scene sceneParamJeu;
    private ParamJeuController paramJeuController;

    private Stage aideWindow;
    private Scene sceneAide;

    private Stage quitterWindow;
    private Scene sceneQuitter;

    public void setParamJeuController(ParamJeuController paramJeuController){
        this.paramJeuController = paramJeuController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setSceneParamJeu(Scene sceneParamJeu) {
        this.sceneParamJeu = sceneParamJeu;
    }

    public void setAideWindow(Stage aideWindow ){
        this.aideWindow = aideWindow;
    }

    public void setSceneAide(Scene sceneAide ){
        this.sceneAide = sceneAide;
    }

    public void setQuitterWindow(Stage quitterWindow ){
        this.quitterWindow = quitterWindow;
    }

    public void setSceneQuitter(Scene sceneQuitter ){
        this.sceneQuitter = sceneQuitter;
    }


    //////////////////////////
    //Gestion des evenements//
    //////////////////////////

    @FXML
    void onAideClick(ActionEvent event) {
        aideWindow.setScene(sceneAide);
        aideWindow.show();
    }

    private GaussianBlur blurEffect = new GaussianBlur(10);
    @FXML
    void onQuitterClick(ActionEvent event) {
        stage.getScene().getRoot().setEffect(blurEffect); //pour flouter la fenetre principale lorsque la page modale est activee
        quitterWindow.setOnCloseRequest(e -> stage.getScene().getRoot().setEffect(null)); //pour enelever l'effet flou si l'on quitte la fenetre modale sans passer pas les boutons de la page
        quitterWindow.setScene(sceneQuitter);
        quitterWindow.show();
    }

    @FXML
    public void onDemarrerPartieClick(ActionEvent actionEvent) {
        stage.setScene(sceneParamJeu);
        paramJeuController.initialisationParamJeu();
        stage.show();
    }
}