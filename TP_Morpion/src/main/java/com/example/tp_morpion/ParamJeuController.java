package com.example.tp_morpion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Stage;

public class ParamJeuController {

    //////////////
    //References//
    //////////////
    private Stage stage;
    private Scene sceneParamJoueur;


    private Stage aideWindow;
    private Scene sceneAide;

    private int grilleChoisie;
    private String modeChoisie;

    private Stage quitterWindow;
    private Scene sceneQuitter;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setSceneParamJoueur(Scene sceneParamJoueur) { //Ici mettre en parametre les scenes que nous voulons utiliser  (2 possibles : une pour le mode 2 joueurs et une pour le mode ordinateur )
        this.sceneParamJoueur = sceneParamJoueur;
    }

    public void setAideWindow(Stage aideWindow ){
        this.aideWindow = aideWindow;
    }

    public void setSceneAide(Scene sceneAide ){
        this.sceneAide = sceneAide;
    }

    public int getGrilleChoisie(){
       return this.grilleChoisie;
    }

    public String getModeChoisie(){
        return this.modeChoisie;
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
    public void initialisationParamJeu(){
        suivantButton.setDisable(true);
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



    //Choix de la grille et du mode de jeu

    @FXML
    private ToggleGroup choixGrilleToggleGroup;
    @FXML
    private ToggleButton choixGrille3x3;
    @FXML
    private ToggleButton choixGrille5x5;
    @FXML
    private ToggleButton choixGrille7x7;

    @FXML
    private ToggleGroup choixModeToggleGroup;
    @FXML
    private ToggleButton choixMode2Joueurs;
    @FXML
    private ToggleButton choixModeOrdinateur;

    @FXML
    private Button suivantButton;



    @FXML
    void onNextClick(ActionEvent event) { //Ici mettre les deux conditions possibles en fonction des scenes vers lesquels nous voulons etre redirige (2 possibles : une pour le mode 2 joueurs et une pour le mode ordinateur )
        if (this.getGrilleChoisie() == 3 && this.getModeChoisie().equals("deux joueurs")) {
            stage.setScene(sceneParamJoueur);
            stage.show();
        }
    }


    @FXML
    private void choixGrille(ActionEvent event){ //permet de récupérer le résultat du choix de grille
        ToggleButton action = (ToggleButton) event.getSource();
        Toggle src = choixGrilleToggleGroup.getSelectedToggle();

        if (src.equals(choixGrille3x3)) {
            grilleChoisie = 3;
        } else if (src.equals(choixGrille5x5)) {
            grilleChoisie = 5;
        } else if (src.equals(choixGrille7x7)) {
            grilleChoisie = 7;
        }

        verificationParam();
    }


    @FXML
    private void choixMode(ActionEvent event) { //permet de récupérer le résultat du mode de jeu
        ToggleButton action = (ToggleButton) event.getSource();
        Toggle src = choixModeToggleGroup.getSelectedToggle();
        if (src == choixModeOrdinateur) {
            modeChoisie = "ordinateur" ;
        } else if (src == choixMode2Joueurs) {
            modeChoisie = "deux joueurs";
        }
        verificationParam();
    }

    private void verificationParam() { //Vérifie que 1 parametre de chaque categorie est selectionne avant de pouvoir cliquer sur suivant
        /*if (grilleChoisie != 0 && modeChoisie != null) { //Il n'y a pour le moment qu'une seule grille et un seul mode, cette condition n'est donc pas appropriee et nous contraint d'utiliser la condition suivante, mais lorqu'il y en aura davantage ce sera elle qui sera utilisee
            suivantButton.setDisable(false);
        }*/
         if (grilleChoisie == 3 && "deux joueurs".equals(modeChoisie)) {
            suivantButton.setDisable(false);
            }
        else {
            suivantButton.setDisable(true);
        }
    }


}
