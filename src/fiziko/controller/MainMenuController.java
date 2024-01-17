package fiziko.controller;

import fiziko.application.SceneManager;
import fiziko.modele.controller.Controller;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * Classe permettant de contrôler la fenêtre « Menu Principal » de l'application
 * F1Z1K0.
 *
 * @author Nicolas St-Laurent, Alexandre Khuong, Tommy Audet et Thierry
 * Cantin-Demers
 */

public class MainMenuController implements Controller {
    /**
     * Le gestionnaire de scènes
     */
    SceneManager sceneManager;

    /**
     * L'image foncée du bouton « Jouer »
     */
    @FXML
    private ImageView boutonJouerDark;

    /**
     * L'image foncée du bouton « Quitter »
     */
    @FXML
    private ImageView boutonQuitterDark;

    /**
     * Animation « fade » du bouton « Jouer »
     */
    FadeTransition fadeTransitionJouer;

    /**
     * Animation « fade » du bouton « Quitter »
     */
    FadeTransition fadeTransitionQuitter;

    /**
     * Cette méthode change la scène de la fenêtre pour le menu de choix de jeu
     *
     * @param event
     */
    @FXML
    void jouer(MouseEvent event) {
        fadeTransitionJouer.play();
    }

    /**
     * Cette méthode ferme le programme
     *
     * @param event
     */
    @FXML
    void quitter(MouseEvent event) {
        fadeTransitionQuitter.play();
    }

    @FXML
    void initialize() {
        fadeTransitionJouer = createFadeTransition(400, 0, 1, boutonJouerDark);
        fadeTransitionJouer.setOnFinished((e) -> {
            sceneManager.changeCurrentScene("Flechettes Niveaux Menu");
            resetMenu();

        });

        fadeTransitionQuitter = createFadeTransition(400, 0, 1,
                boutonQuitterDark);
        fadeTransitionQuitter.setOnFinished((e) -> {
            Platform.exit();
        });
    }

    /**
     * Donne accès au gestionnaire de scènes de la fenêtre
     *
     * @param manager
     */
    public void setSceneManager(SceneManager manager) {
        this.sceneManager = manager;
    }

    /**
     * Réinitialise les animations de fade
     */
    @Override
    public void resetMenu() {
        boutonJouerDark.setOpacity(0);
        boutonQuitterDark.setOpacity(0);
    }

    /**
     * Cette méthode crée et retourne un service d'animation de fade
     *
     * @param dureeMillis : durée de l'animation
     * @param debut       : valeur d'opacité de début
     * @param fin         : valeur d'opacité de fin
     * @param image       : image sur laquelle on veut mettre l'animation
     * @return le service d'animation créé
     */
    private FadeTransition createFadeTransition(int dureeMillis, double debut,
                                                double fin, ImageView image) {
        FadeTransition ft = new FadeTransition(new Duration(dureeMillis));

        ft.setNode(image);

        ft.setFromValue(debut);
        ft.setToValue(fin);

        return ft;
    }

    @Override
    public void initMenu() {

    }
}
