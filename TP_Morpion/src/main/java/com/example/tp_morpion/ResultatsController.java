package com.example.tp_morpion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ResultatsController {

    //////////////
    //References//
    //////////////

    private Stage stage;

    private Stage aideWindow;
    private Scene sceneAide;

    private JeuController jeuController;

    private Stage quitterWindow;
    private Scene sceneQuitter;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setAideWindow(Stage aideWindow) {
        this.aideWindow = aideWindow;
    }

    public void setSceneAide(Scene sceneAide) {
        this.sceneAide = sceneAide;
    }

    public void setjeuController(JeuController jeuController){
        this.jeuController = jeuController;
    }

    public void setQuitterWindow(Stage quitterWindow ){
        this.quitterWindow = quitterWindow;
    }

    public void setSceneQuitter(Scene sceneQuitter ){
        this.sceneQuitter = sceneQuitter;
    }


    /////////////////////////////
    //Initialisation de la page//
    /////////////////////////////
    @FXML
    private Label annonceGagnantTextField;
    @FXML
    private Label annonceMatchNulTextField;

    @FXML
    private Label nomPremierJoueur;
    @FXML
    private Label scorePremierJoueur;

    @FXML
    private Label nomDeuxiemeJoueur;
    @FXML
    private Label scoreDeuxiemeJoueur;


    public void initialisationResultats() { //Permet d'afficher les resultats

        nomPremierJoueur.setText(jeuController.getNomJoueur1());
        nomPremierJoueur.setTextFill(Color.rgb(64, 221, 202));

        nomDeuxiemeJoueur.setText(jeuController.getNomJoueur2());
        nomDeuxiemeJoueur.setTextFill(Color.rgb(215, 162, 63));

        scorePremierJoueur.setText(String.valueOf(jeuController.getScoreJoueur1()));
        scoreDeuxiemeJoueur.setText(String.valueOf(jeuController.getScoreJoueur2()));

        if (!jeuController.getMatchNul()) {
            annonceGagnantTextField.setText(jeuController.getNomJoueurGagnant() + " gagne");
        }
        else {
            annonceMatchNulTextField.setText("Match nul");
        }
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
    void onRejouerClick(ActionEvent event) {
        //Non fait
    }

    @FXML
    void onTerminerClick(ActionEvent event) {
        //Non fait
    }


}
