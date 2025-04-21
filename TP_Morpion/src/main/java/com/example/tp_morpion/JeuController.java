package com.example.tp_morpion;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JeuController {

    //////////////
    //References//
    //////////////

    private String nomJoueur1;
    private String nomJoueur2;

    private Stage aideWindow;
    private Scene sceneAide;

    private ParamJoueurController paramJoueurController;

    private Stage stage;
    private Scene sceneResultats;
    private ResultatsController resultatsController;

    private String nomJoueurGagnant;
    private Boolean matchNul = false;
    private int scoreJoueur1;
    private int scoreJoueur2;

    private Stage quitterWindow;
    private Scene sceneQuitter;


    public String getNomJoueur1(){
        return this.nomJoueur1;
    }

    public void setNomJoueur1(String nomJoueur1) {
        this.nomJoueur1 = nomJoueur1;
    }

    public String getNomJoueur2(){
        return this.nomJoueur2;
    }

    public void setNomJoueur2(String nomJoueur2) {
        this.nomJoueur2 = nomJoueur2;
    }

    public void setAideWindow(Stage aideWindow) {
        this.aideWindow = aideWindow;
    }

    public void setSceneAide(Scene sceneAide) {
        this.sceneAide = sceneAide;
    }

    public void setParamJoueurController(ParamJoueurController paramJoueurController){
        this.paramJoueurController = paramJoueurController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setSceneResultats(Scene sceneResultats) {
        this.sceneResultats = sceneResultats;
    }

    public void setResultatsController(ResultatsController resultatsController){
        this.resultatsController = resultatsController;
    }

    public String getNomJoueurGagnant(){
        return this.nomJoueurGagnant;
    }

    public Boolean getMatchNul(){
        return this.matchNul;
    }

    public int getScoreJoueur1(){
        return this.scoreJoueur1;
    }

    public int getScoreJoueur2(){
        return this.scoreJoueur2;
    }

    public void setQuitterWindow(Stage quitterWindow ){
        this.quitterWindow = quitterWindow;
    }

    public void setSceneQuitter(Scene sceneQuitter ){
        this.sceneQuitter = sceneQuitter;
    }


    ///////////////////////////////
    //Représentation de la grille//
    ///////////////////////////////

    @FXML
    private Button button0_0;
    @FXML
    private Button button0_1;
    @FXML
    private Button button0_2;
    @FXML
    private Button button1_0;
    @FXML
    private Button button1_1;
    @FXML
    private Button button1_2;
    @FXML
    private Button button2_0;
    @FXML
    private Button button2_1;
    @FXML
    private Button button2_2;

    private List<List<Button>> grille =new ArrayList<>();

    public void debutJeu(){

        List<Button> ligne0 = new ArrayList<>();
        ligne0.add(button0_0);
        ligne0.add(button0_1);
        ligne0.add(button0_2);

        List<Button> ligne1 = new ArrayList<>();
        ligne1.add(button1_0);
        ligne1.add(button1_1);
        ligne1.add(button1_2);

        List<Button> ligne2 = new ArrayList<>();
        ligne2.add(button2_0);
        ligne2.add(button2_1);
        ligne2.add(button2_2);

        grille.add(ligne0);
        grille.add(ligne1);
        grille.add(ligne2);

        button0_0.setGraphic(new ImageView());
        button0_1.setGraphic(new ImageView());
        button0_2.setGraphic(new ImageView());
        button1_0.setGraphic(new ImageView());
        button1_1.setGraphic(new ImageView());
        button1_2.setGraphic(new ImageView());
        button2_0.setGraphic(new ImageView());
        button2_1.setGraphic(new ImageView());
        button2_2.setGraphic(new ImageView());

        nomJoueur();
    }

    private Button getButton(int ligne, int colonne){  //Permet de recuperer un bouton a partir de ses coordonees
        return grille.get(ligne).get(colonne);
    }

    private int getCoordLigne(Button btn) { //Permet d'obtenir la ligne ou se trouve un bouton
        for (int ligne = 0; ligne < grille.size(); ligne++)
            for (int colonne = 0; colonne < grille.get(0).size(); colonne++)
                if (grille.get(ligne).get(colonne) == btn)
                    return ligne;
        return 0;
    }

    private int getCoordColonne(Button btn){ //Permet d'obtenir la colonne ou se trouve un bouton
        for (int ligne = 0; ligne < grille.size(); ligne++)
            for (int colonne = 0; colonne < grille.get(0).size(); colonne++)
                if (grille.get(ligne).get(colonne) == btn)
                    return colonne;
        return 0;
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
    void onRecommencerClick(ActionEvent event) {
        tour = 0;
        for (List<Button> ligne : grille) {
            for (Button bouton : ligne) {
                bouton.setDisable(false);
                ((ImageView) bouton.getGraphic()).setImage(null);
            }
        }
        if(choixModeAleatoire){ //Permet de recommenecer avec un choix aleatoire du joueur qui commence si ce mode a ete choisie
            paramJoueurController.setPremierJoueur(3);
        }
        nomJoueur();
    }

    //Gestion de l'evenement liee a la grille

    @FXML
    private Label nomJoueur;
    @FXML
    private Label verbeJouerTour;

    private Image symboleJ1 = new Image(getClass().getResourceAsStream("symboleDefautX.png"), 90, 90, true, true);

    private Image symboleJ2 = new Image(getClass().getResourceAsStream("symboleDefautO.png"), 90, 90, true, true);

    private int tour = 0;


    @FXML
    void onCaseClick(ActionEvent event) { //Detecte la case sur laquelle le joueur a clique
        Button src = (Button)event.getSource();
        jouer(src);
        if (!verificationVictoire()) { //Verifie si la partie est gagnee et sinon verifie si c'est du a un match nul
            verificationMatchNul();
        }
        nomJoueur();
    }

    public void jouer(Button button) { //Ajout du symbole du joueur sur la case cliquee
            Image symbole = symboleJoueur();
            ((ImageView) button.getGraphic()).setImage(symbole);
            button.setDisable(true);
            tour++;
    }

    private boolean choixModeAleatoire;

    public void nomJoueur() { //Determine le joueur dont c'est le tour et modifie le nom en consequence
        int premierJoueur = paramJoueurController.getPremierJoueur();
        boolean tourJoueur1 = (premierJoueur == 1 && tour % 2 == 0) || (premierJoueur == 2 && tour % 2 != 0); //2 cas possibles selon que le joueur 1 ou 2 a commence
        if (!matchNul && nomJoueurGagnant == null) { //Le nom du joueur dont c'est le tout n'est affiche que si la partie n'est pas terminee
            nomJoueur.setText(tourJoueur1 ? nomJoueur1 : nomJoueur2);
            nomJoueur.setTextFill(tourJoueur1 ? Color.rgb(64, 221, 202) : Color.rgb(215, 162, 63));

            if (premierJoueur == 3) { //Dans le cas ou le choix du joueur qui commence est aleatoire
                choixModeAleatoire = true; //Utilisation d'une variable pour enregistrer le parametre aleatoire si le joueur decide de recommencer ou de rejouer
                Random random = new Random();
                paramJoueurController.setPremierJoueur(random.nextInt(2) + 1);
                nomJoueur();
            }
        }
        else {
            nomJoueur.setText("");
            verbeJouerTour.setText("");
        }
    }

    public Image symboleJoueur() { //Determine le joueur dont c'est le tour et modifie le symbole en consequence
        int premierJoueur = paramJoueurController.getPremierJoueur();
        boolean tourJoueur1 = (premierJoueur == 1 && tour % 2 == 0) || (premierJoueur == 2 && tour % 2 != 0); //2 cas possibles selon que le joueur 1 ou 2 a commence
        return tourJoueur1 ? symboleJ1 : symboleJ2;
    }


    public boolean verificationVictoire() { //Verifie s'il y a un gagnant en utilisant les differentes conditions de victoire

        // Regarde s'il y a une victoire sur la premiere ligne de la grille
        // [] [] []
        // () () ()
        // () () ()
        Image b1= ((ImageView) getButton(0, 0).getGraphic()).getImage();
        Image b2 = ((ImageView) getButton(0, 1).getGraphic()).getImage();
        Image b3= ((ImageView) getButton(0, 2).getGraphic()).getImage();

        if (b1 == b2 && b1 == b3 && (b1 != null && b2 != null && b1 != null)) {
            pausePuisResultat(2);
            return true;
        }

        //Regarde s'il y a une victoire sur la deuxieme ligne de la grille
        // () () ()
        // [] [] []
        // () () ()
        Image b4 = ((ImageView) getButton(1, 0).getGraphic()).getImage();
        Image b5 = ((ImageView) getButton(1, 1).getGraphic()).getImage();
        Image b6 = ((ImageView) getButton(1, 2).getGraphic()).getImage();

        if (b4 == b5 && b4 == b6 && (b4 != null && b5 != null && b6 != null)) {
            pausePuisResultat(2);
            return true;
        }

        //Regarde s'il y a une victoire sur la troisieme ligne de la grille
        // () () ()
        // () () ()
        // [] [] []
        Image b7 = ((ImageView) getButton(2, 0).getGraphic()).getImage();
        Image b8 = ((ImageView) getButton(2, 1).getGraphic()).getImage();
        Image b9 = ((ImageView) getButton(2, 2).getGraphic()).getImage();

        if (b7 == b8 && b7 == b9 && (b7 != null && b8 != null && b9 != null)) {
            pausePuisResultat(2);
            return true;
        }

        //Regarde s'il y a une victoire sur la premiere colonne de la grille
        // [] () ()
        // [] () ()
        // [] () ()
        Image b10 = ((ImageView) getButton(0, 0).getGraphic()).getImage();
        Image b11 = ((ImageView) getButton(1, 0).getGraphic()).getImage();
        Image b12 = ((ImageView) getButton(2, 0).getGraphic()).getImage();

        if (b10 == b11 && b10 == b12 && (b10 != null && b11 != null && b12 != null)) {
            pausePuisResultat(2);
            return true;
        }

        //Regarde s'il y a une victoire sur la deuxieme colonne de la grille
        // () [] ()
        // () [] ()
        // () [] ()
        Image b13 = ((ImageView) getButton(0, 1).getGraphic()).getImage();
        Image b14 = ((ImageView) getButton(1, 1).getGraphic()).getImage();
        Image b15 = ((ImageView) getButton(2, 1).getGraphic()).getImage();

        if (b13 == b14 && b13 == b15 && (b13 != null && b14 != null && b15 != null)) {
            pausePuisResultat(2);
            return true;
        }

        //Regarde s'il y a une victoire sur la troisieme colonne de la grille
        // () () []
        // () () []
        // () () []
        Image b16 = ((ImageView) getButton(0, 2).getGraphic()).getImage();
        Image b17 = ((ImageView) getButton(1, 2).getGraphic()).getImage();
        Image b18 = ((ImageView) getButton(2, 2).getGraphic()).getImage();

        if (b16 == b17 && b16 == b18 && (b16 != null && b17 != null && b18 != null)) {
            pausePuisResultat(2);
            return true;
        }

        //Regarde s'il y a une victoire sur la diagonale allant de la gauche vers la droite
        // [] () ()
        // () [] ()
        // () () []
        Image b19 = ((ImageView) getButton(0, 0).getGraphic()).getImage();
        Image b20 = ((ImageView) getButton(1, 1).getGraphic()).getImage();
        Image b21 = ((ImageView) getButton(2, 2).getGraphic()).getImage();

        if (b19 == b20 && b19 == b21 && (b19 != null && b20 != null && b21 != null)) {
            pausePuisResultat(2);
            return true;
        }

        //Regarde s'il y a une victoire sur la diagonale allant de la droite vers la gauche
        // () () []
        // () [] ()
        // [] () ()
        Image b22 = ((ImageView) getButton(0, 2).getGraphic()).getImage();
        Image b23 = ((ImageView) getButton(1, 1).getGraphic()).getImage();
        Image b24 = ((ImageView) getButton(2, 0).getGraphic()).getImage();

        if (b22 == b23 && b22 == b24 && (b22 != null && b23 != null && b24 != null)) {
            pausePuisResultat(2);
            return true;
        }

        return false; //S'il n'y a pas encore de gagnant, la fonction retourne faux
    }

    public void verificationMatchNul(){ //Verifie s'il y a match nul
        Boolean grilleComplete = true;
        for (List<Button> ligne : grille) {
            for (Button bouton : ligne) {
                if (((ImageView) bouton.getGraphic()).getImage() == null) { //Si une case est encore vide c'est que la grille n'est pas complete, il ne peut donc pas y avoir match nul
                    grilleComplete = false;
                    break;
                }
            }
        }
        if (grilleComplete) { //Si aucune case vide n'a ete detectee, il y a match nul
            matchNul = true;
            pausePuisResultat(2);
        }
    }

    @FXML
    private void pausePuisResultat(int temps) { // Recupere les resultats et permet d'attendre quelques secondes avant d'afficher la page de resultat
        PauseTransition pause = new PauseTransition(Duration.seconds(temps));

        for (List<Button> ligne : grille) { //Desactive tout les boutons pour que les joueurs ne puissent plus jouer
            for (Button bouton : ligne) {
                bouton.setDisable(true);
            }
        }

        if (!matchNul) { //Permet de recuperer le nom du gagnant et d'ajouter les points
            if (nomJoueur.getText().equals(nomJoueur1)) {
                nomJoueurGagnant = nomJoueur1;
                scoreJoueur1 ++;
            } else {
                nomJoueurGagnant = nomJoueur2;
                scoreJoueur2 ++;
            }
        }
        else { //Permet d'indiquer qu'il n'y a pas de gagnant
            nomJoueurGagnant = null;
        }

        pause.setOnFinished(event -> {
            stage.setScene(sceneResultats);
            resultatsController.initialisationResultats();
            stage.show();
        });
        pause.play();
    }

}
