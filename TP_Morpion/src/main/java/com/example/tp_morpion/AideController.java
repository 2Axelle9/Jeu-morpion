package com.example.tp_morpion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class AideController {
    @FXML
    private Label helpEnum;

    @FXML
    public void initialize() {
        helpEnum.setText("• Deux joueurs s'affrontent, l'un utilisant les symboles X et l'autre O (ou autre si    modifié).\n" +
                "• Les joueurs alternent pour placer leur symbole sur la grille en choisissant une    case libre à chaque tour.\n" +
                "• Le joueur qui parvient à aligner au moins trois de ses symboles en premier    remporte la partie.\n" +
                "• Si la grille est remplie sans qu'aucun joueur n'ait aligné au moins trois symboles,    la partie se termine par un match nul.");
    }

    @FXML
    void onFAQClick(MouseEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.epicgames.com/site/fr/epic-games-store-faq")); // ou showDocument() avec HostServices dans la documentation javaFX ?
    }
}
