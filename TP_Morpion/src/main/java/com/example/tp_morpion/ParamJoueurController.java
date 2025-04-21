package com.example.tp_morpion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Stage;

public class ParamJoueurController {

    //////////////
    //References//
    //////////////
    private Stage stage;
    private Scene sceneJeu;
    private JeuController jeuController;

    private Stage aideWindow;
    private Scene sceneAide;

    private ParamJeuController paramJeuController;

    private int premierJoueur;

    private Stage quitterWindow;
    private Scene sceneQuitter;

    public void setJeuController(JeuController jeuController){
        this.jeuController = jeuController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setSceneJeu(Scene sceneJeu) { //Ici mettre en parametre les scenes que nous voulons utiliser  (3 possibles : une pour la grille3x3, une pour la grille 5x5 et une pour la grille 7x7 )
        this.sceneJeu = sceneJeu;
    }

    public void setAideWindow(Stage aideWindow ){
        this.aideWindow = aideWindow;
    }

    public void setSceneAide(Scene sceneAide ){
        this.sceneAide = sceneAide;
    }

    public void setParamJeuController(ParamJeuController paramJeuController) {
        this.paramJeuController = paramJeuController;
    }

    public int getPremierJoueur(){
        return this.premierJoueur;
    }

    public void setPremierJoueur(int premierJoueur ){
        this.premierJoueur = premierJoueur;
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

    //Nom des joueurs

    @FXML
    private TextField joueur1;

    @FXML
    private TextField joueur2;

    @FXML
    private void onNextClick(ActionEvent event) { //Ici mettre les trois conditions possibles en fonction des scenes vers lesquels nous voulons etre redirige (3 possibles : une pour la grille3x3, une pour la grille 5x5 et une pour la grille 7x7 )

        premierJoueur();

        String nomJoueur1 = joueur1.getText();
        String nomJoueur2 = joueur2.getText();

        jeuController.setNomJoueur1(nomJoueur1);
        jeuController.setNomJoueur2(nomJoueur2);

        if (paramJeuController.getGrilleChoisie() == 3 && paramJeuController.getModeChoisie().equals("deux joueurs")) {
            stage.setScene(sceneJeu);
            jeuController.debutJeu();
            stage.show();
        }
    }

    //Joueur qui débute

    @FXML
    private ToggleGroup premierJoueurToggleGroup;

    @FXML
    private RadioButton aleaPremier;

    @FXML
    private RadioButton j1Premier;

    @FXML
    private RadioButton j2Premier;


    private void premierJoueur(){ //Peremet de recuperer le resultat lors du choix du premier joueur
        Toggle src = premierJoueurToggleGroup.getSelectedToggle();
        if (src.equals(j1Premier)) {
            premierJoueur = 1;
        } else if (src.equals(j2Premier)) {
            premierJoueur = 2;
        } else if (src.equals(aleaPremier)) {
            premierJoueur = 3;
        }
    }

}
