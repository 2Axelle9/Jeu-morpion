package com.example.tp_morpion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {


        ////////////////////////////////////////////////////
        //Creation de la premiere scene, la page d'accueil//
        ////////////////////////////////////////////////////
        FXMLLoader fxmlAccueilLoader = new FXMLLoader(MainApplication.class.getResource("accueil.fxml"));
        Scene scene = new Scene(fxmlAccueilLoader.load());
        scene.getStylesheets().add(getClass().getResource("style.css").toString()); //on associe la feuille de style a la scene


        //////////////////////////////////////////
        //Configuration de la fenetre principale//
        //////////////////////////////////////////
        stage.setTitle("Tic-Tac-Toe The Ultimate Game");
        stage.setResizable(false); //empeche le redimenssionnement de la fenetre
        stage.setScene(scene); //on associe la fenetre a la scene creee precedemment, la page d'accueil


        ///////////////////////////////////////////////////////////////////////////////
        //Creation de la deuxieme scene, la page de parametrage d'une nouvelle partie//
        ///////////////////////////////////////////////////////////////////////////////
        FXMLLoader fxmlParamJeuLoader = new FXMLLoader(MainApplication.class.getResource("paramJeu.fxml"));
        Scene sceneParamJeu = new Scene(fxmlParamJeuLoader.load());
        sceneParamJeu.getStylesheets().add(getClass().getResource("style.css").toString());


        ///////////////////////////////////////////////
        //Creation de la scene d'aide, la page d'aide//
        ///////////////////////////////////////////////
        FXMLLoader fxmlAideLoader = new FXMLLoader(MainApplication.class.getResource("aide.fxml"));
        Scene sceneAide = new Scene(fxmlAideLoader.load());
        sceneAide.getStylesheets().add(getClass().getResource("style.css").toString());


        ////////////////////////////////////////////////////////
        //Creation d'une fenetre non modale, la fenetre d'aide//
        ////////////////////////////////////////////////////////
        Stage aideWindow = new Stage();
        aideWindow.setTitle("Tic-Tac-Toe The Ultimate Game - Aide");
        aideWindow.setResizable(false);


        //////////////////////////////////////////////////////////////////////
        //Creation de la troisieme scene, la page de parametrage des joueurs//
        //////////////////////////////////////////////////////////////////////
        FXMLLoader fxmlParamJoueurLoader = new FXMLLoader(MainApplication.class.getResource("paramJoueur.fxml"));
        Scene sceneParamJoueur = new Scene(fxmlParamJoueurLoader.load());
        sceneParamJoueur.getStylesheets().add(getClass().getResource("style.css").toString());


        //////////////////////////////////////////////////////////////////////
        //Creation de la quatrieme scene, la page de jeu contenant la grille//
        //////////////////////////////////////////////////////////////////////
        FXMLLoader fxmlJeuLoader = new FXMLLoader(MainApplication.class.getResource("jeu.fxml"));
        Scene sceneJeu = new Scene(fxmlJeuLoader.load());
        sceneJeu.getStylesheets().add(getClass().getResource("style.css").toString());


        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Creation de la cinquieme scene, la page affichant les resultats de la partie precedente et les scores//
        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        FXMLLoader fxmlResultatsLoader = new FXMLLoader(MainApplication.class.getResource("resultats.fxml"));
        Scene sceneResultats = new Scene(fxmlResultatsLoader.load());
        sceneResultats.getStylesheets().add(getClass().getResource("style.css").toString());


        /////////////////////////////////////////////////////////////////////////////////////////
        //Creation de la scene pour quitter, la page demandant la confirmation avant de quitter//
        /////////////////////////////////////////////////////////////////////////////////////////
        FXMLLoader fxmlQuitterLoader = new FXMLLoader(MainApplication.class.getResource("quitter.fxml"));
        Scene sceneQuitter = new Scene(fxmlQuitterLoader.load());
        sceneQuitter.getStylesheets().add(getClass().getResource("style.css").toString());


        //////////////////////////////////////////////////////////
        //Creation d'une fenetre modale, la fenetre pour quitter//
        //////////////////////////////////////////////////////////
        Stage quitterWindow = new Stage(StageStyle.DECORATED);
        quitterWindow.initModality(Modality.WINDOW_MODAL);
        quitterWindow.initOwner(stage);
        quitterWindow.setTitle("Attention");
        quitterWindow.setResizable(false);


        //////////////////////////////////////////////
        //Passage des references à la page d'accueil//
        //////////////////////////////////////////////
        AccueilController accueilController = (AccueilController) fxmlAccueilLoader.getController();
        accueilController.setStage(stage);
        accueilController.setSceneParamJeu(sceneParamJeu);
        accueilController.setAideWindow(aideWindow);
        accueilController.setSceneAide(sceneAide);
        accueilController.setQuitterWindow(quitterWindow);
        accueilController.setSceneQuitter(sceneQuitter);


        /////////////////////////////////////////////////////////////////////////
        //Passage des references a la page de parametrage d'une nouvelle partie//
        /////////////////////////////////////////////////////////////////////////
        ParamJeuController paramJeuController = (ParamJeuController) fxmlParamJeuLoader.getController();
        paramJeuController.setStage(stage);
        paramJeuController.setAideWindow(aideWindow);
        paramJeuController.setSceneAide(sceneAide);
        accueilController.setParamJeuController(paramJeuController);
        paramJeuController.setSceneParamJoueur(sceneParamJoueur);
        paramJeuController.setQuitterWindow(quitterWindow);
        paramJeuController.setSceneQuitter(sceneQuitter);


        ///////////////////////////////////////////////////////////////
        //Passage des references a la page de parametrage des joueurs//
        ///////////////////////////////////////////////////////////////
        ParamJoueurController paramJoueurController = (ParamJoueurController) fxmlParamJoueurLoader.getController();
        paramJoueurController.setStage(stage);
        paramJoueurController.setAideWindow(aideWindow);
        paramJoueurController.setSceneAide(sceneAide);
        paramJoueurController.setParamJeuController(paramJeuController);
        paramJoueurController.setSceneJeu(sceneJeu);
        paramJoueurController.setQuitterWindow(quitterWindow);
        paramJoueurController.setSceneQuitter(sceneQuitter);


        ///////////////////////////////////////////
        //Passage des references a la page de jeu//
        ///////////////////////////////////////////
        JeuController jeuController = (JeuController) fxmlJeuLoader.getController();
        jeuController.setStage(stage);
        jeuController.setAideWindow(aideWindow);
        jeuController.setSceneAide(sceneAide);
        paramJoueurController.setJeuController(jeuController);
        jeuController.setParamJoueurController(paramJoueurController);
        jeuController.setSceneResultats(sceneResultats);
        jeuController.setQuitterWindow(quitterWindow);
        jeuController.setSceneQuitter(sceneQuitter);


        /////////////////////////////////////////////////
        //Passage des references a la page de resultats//
        /////////////////////////////////////////////////
        ResultatsController resultatsController = (ResultatsController) fxmlResultatsLoader.getController();
        resultatsController.setStage(stage);
        resultatsController.setAideWindow(aideWindow);
        resultatsController.setSceneAide(sceneAide);
        resultatsController.setjeuController(jeuController);
        jeuController.setResultatsController(resultatsController);
        resultatsController.setQuitterWindow(quitterWindow);
        resultatsController.setSceneQuitter(sceneQuitter);


        /////////////////////////////////////////////////
        //Passage des references a la page pour quitter//
        /////////////////////////////////////////////////
        QuitterController quitterController = (QuitterController) fxmlQuitterLoader.getController();
        quitterController.setQuitterWindow(quitterWindow);
        quitterController.setStage(stage);
        quitterController.setAideWindow(aideWindow);




        stage.show(); //on affiche la fenetre principale
    }


    public static void main(String[] args) {
        launch();
    }
}