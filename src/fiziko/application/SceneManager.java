package fiziko.application;

import fiziko.modele.controller.Controller;
import fiziko.modele.sauvegarde.GameState;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe facilitant la gestion de changement de scènes dans une certaine
 * fenêtre
 *
 * @author Thierry Cantin-Demers, Alexandre Khuong, Tommy Audet et Nicolas
 * St-Laurent
 */

public class SceneManager {
    /**
     * Les scènes que la fenêtre peut afficher
     */
    private Map<String, Scene> scenes;

    /**
     * Les controlleurs des scènes de la fenêtre
     */
    private Map<Scene, Controller> controllers;

    /**
     * La fenêtre qui est lié à ce manager
     */
    private Stage stage;

    /**
     * La scène qui est affichée en ce moment dans la fenêtre
     */
    private Scene currentScene;

    /**
     * Objet GameState qui représente l'état du jeu actuel
     */
    private GameState currentGameState;

    /**
     * Crée un nouveau gestionnaire de scènes pour un certain «stage». Ajoute un
     * listener sur le stage qui détecte les changements de scène et qui appelle
     * la méthode resetMenu() du controlleur de la nouvelle scène, ce qui remet
     * les controlleurs à leurs valeurs par défaut
     *
     * @param stage : le «stage» sur lequel on veut ajouter un manager de
     *              scènes.
     */
    public SceneManager(Stage stage) {
        scenes = new HashMap<String, Scene>();
        controllers = new HashMap<Scene, Controller>();
        this.stage = stage;

        if (stage.getScene() != null) {
            currentScene = stage.getScene();
        }

        stage.sceneProperty().addListener((a, o, n) -> {
            Controller controller;

            /**
             * On appele la méthode de réinitialisation de menu sur le
             * Controller que nous venons de quitter
             */
            if ((controller = controllers.get(o)) instanceof Controller) {
                ((Controller) controller).resetMenu();
            }

            /**
             * On appele la méthode d'initialisation de menu sur le Controller
             * dans lequel nous entrons
             */
            if ((controller = controllers.get(n)) instanceof Controller) {
                ((Controller) controller).initMenu();
            }
        });
    }

    /**
     * Ajoute une scène aux possibilités de scènes que la fenêtre peut prendre.
     * Si le stage n'avait aucune scène d'attribuée, elle prendra la nouvelle
     * scène.
     *
     * @param name  : le nom de la scène qui sera ajoutée
     * @param scene : l'objet Scene de la scène qui sera ajoutée
     */
    public void addScene(String name, Scene scene) {
        scenes.put(name, scene);

        if (stage.getScene() == null) {
            stage.setScene(scene);
            currentScene = scene;
        }
    }

    /**
     * Ajoute une scène aux possibilités de scènes que la fenêtre peut prendre.
     * Si le stage n'avait aucune scène d'attribuée, elle prendra la nouvelle
     * scène. Donne une référence au contrôleur de la scène.
     *
     * @param name       : le nom de la scène qui sera ajoutée
     * @param scene      : l'objet Scene de la scène qui sera ajoutée
     * @param controller : le contrôleur de la scène qui sera ajouter dans la
     *                   map des contrôleurs "controllers"
     */
    public void addScene(String name, Scene scene, Controller controller) {
        scenes.put(name, scene);
        controllers.put(scene, controller);

        if (stage.getScene() == null) {
            stage.setScene(scene);
            currentScene = scene;
        }
    }

    /**
     * Change la scène de la fenêtre pour la scène correspondant au nom donné
     * dans la liste de scènes.
     *
     * @param name : le nom de la scène que l'on veut afficher
     */
    public void changeCurrentScene(String name) {
        if (scenes.containsKey(name)) {
            currentScene = scenes.get(name);
            stage.setScene(currentScene);
        }
    }

    /**
     * Retourne la scène qui est actuellement affichée
     *
     * @return la scène qui est actuellement affichée
     */
    public Scene getCurrentScene() {
        return this.currentScene;
    }

    /**
     * Affiche la fenêtre si elle est instanciée et si elle possède une scène,
     * sinon lance une exception.
     *
     * @throws Exception
     */
    public void showStage() throws Exception {
        if (stage != null && stage.getScene() != null) {
            stage.show();
        } else {
            throw (new Exception(
                    "The stage has not been initialized or the stage doesn't have a scene yet."));
        }
    }

    /**
     * Permet de spécifier le controlleur d'un scène pour pouvoir appeler la
     * méthode resetMenu() du controlleur
     *
     * @param scene      : la scène dont on veut spécifier le controlleur
     * @param controller : le controlleur de la scène
     */
    public void addControllerToScene(Scene scene, Controller controller) {
        controllers.put(scene, controller);
    }

    /**
     * Donne une nouvelle référence à l'état du jeu actuel
     *
     * @param gameState : le nouvel état du jeu
     */
    public void setCurrentGameState(GameState gameState) {
        this.currentGameState = gameState;
    }

    /**
     * Retourne l'état actuel du jeu
     *
     * @return l'état actuel du jeu
     */
    public GameState getCurrentGameState() {
        return this.currentGameState;
    }

    /**
     * Retourne le Stage auquel le SceneManager est lié
     *
     * @return le Stage auquel le SceneManager est lié
     */
    public Stage getStage() {
        return this.stage;
    }

    /**
     * Retourne l'objet scène associé à la String passée en paramètre
     *
     * @param nom : le nom de la scène
     * @return l'objet Scene associé au nom donné en paramètre
     */
    public Scene getScene(String nom) {
        return scenes.get(nom);
    }

    /**
     * Retourne le Controller associé à une certaine scène donnée par la String
     * qui la représente
     *
     * @param nomScene : la String représentant la scène dont on veut le
     *                 Controller
     * @return le Controller de la scène donnée
     */
    public Controller getController(String nomScene) {
        Controller controller = null;

        if (scenes.containsKey(nomScene)
                && controllers.containsKey(scenes.get(nomScene))) {
            controller = controllers.get(scenes.get(nomScene));
        }

        return controller;
    }
}
