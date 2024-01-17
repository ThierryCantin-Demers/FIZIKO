package fiziko.application;

import fiziko.controller.FlechettesMenuController;
import fiziko.controller.JeuFlechettesController;
import fiziko.controller.MainMenuController;
import fiziko.modele.sauvegarde.GameState;
import fiziko.modele.sauvegarde.SaveManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Classe permettant de lancer l'application F1Z1K0. Cette application comporte
 * différents jeux interractifs liés à des concepts physiques et mathématiques.
 *
 * @author Thierry Cantin-Demers, Alexandre Khuong, Tommy Audet et Nicolas
 * St-Laurent
 * @version 1.0
 */
public class App extends Application {
    /**
     * Le gestionnionaire de scène qui sera utilisé dans le programme
     */
    private SceneManager sceneManager;

    /**
     * La dernière partie jouée
     */
    private GameState loadedGame;

    /**
     * Le contrôleur du jeu de fléchettes
     */
    private JeuFlechettesController jeuFlechettesController;

    /**
     * Méthode qui est appelée au lancement de l'application
     *
     * @param args
     */
    public static void main(String args[]) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Instancie le controlleur de scènes
        sceneManager = new SceneManager(primaryStage);

        // Charge la dernière partie ouverte
        loadedGame = SaveManager.loadLastGame();

        // Si le fichier de la derniere partie est introuvable ou un problème
        // est survenu lors de l'ouverture du fichier, on crée une nouvelle
        // partie
        if (loadedGame == null) {
            loadedGame = new GameState();
            loadedGame.setFromFile(false);
        } else {
            loadedGame.setFromFile(true);
            SaveManager.saveGame(loadedGame);
        }

        sceneManager.setCurrentGameState(loadedGame);

        // Instancie la scène du menu principal
        instanciationSceneMenuPrincipal();

        // Instancie la scène du jeu de fléchettes
        instanciationSceneJeuFlechettes();

        // Instancie la scène du menu de choix de niveaux du jeu de fléchettes
        instanciationSceneChoixNiveauJeuFlechettes();

        // Donne quelques propriétés à la fenêtre et l'affiche

        primaryStage.setMaximized(true);
        primaryStage.setWidth(1936);
        primaryStage.setHeight(1056);

        primaryStage.setTitle("FIZIKO");

        sceneManager.showStage();
    }

    /**
     * Instancie la scène du menu principal
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void instanciationSceneMenuPrincipal()
            throws FileNotFoundException, IOException {
        FXMLLoader mainMenuLoader = new FXMLLoader();
        Pane rootMainMenu = mainMenuLoader
                .load(getClass().getResourceAsStream("/fxml/main_menu.fxml"));
        MainMenuController mainMenuController = mainMenuLoader.getController();
        mainMenuController.setSceneManager(sceneManager);

        Scene mainMenuScene = new Scene(rootMainMenu);

        sceneManager.addScene("Main menu", mainMenuScene);
    }

    /**
     * Instancie la scène du jeu de fléchettes
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void instanciationSceneJeuFlechettes()
            throws FileNotFoundException, IOException {
        FXMLLoader flechettesJeuLoader = new FXMLLoader();
        Pane flechettesJeuRoot = flechettesJeuLoader.load(
                getClass().getResourceAsStream("/fxml/jeu_flechettes.fxml"));
        jeuFlechettesController = flechettesJeuLoader.getController();
        jeuFlechettesController.setSceneManager(sceneManager);

        Scene flechettesJeuScene = new Scene(flechettesJeuRoot);

        sceneManager.addScene("Jeu Flechettes", flechettesJeuScene,
                jeuFlechettesController);
    }

    /**
     * Instancie la scène du menu de choix de niveaux du jeu de fléchettes
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void instanciationSceneChoixNiveauJeuFlechettes()
            throws FileNotFoundException, IOException {
        FXMLLoader flechettesMenuLoader = new FXMLLoader();
        Pane flechettesMenuRoot = flechettesMenuLoader.load(getClass().getResourceAsStream(
                "/fxml/flechettes_niveaux_menu.fxml"));
        FlechettesMenuController flechettesMenuController = flechettesMenuLoader
                .getController();
        flechettesMenuController
                .setJeuFlechettesController(jeuFlechettesController);
        flechettesMenuController.setSceneManager(sceneManager);
        flechettesMenuController.setNiveauxJeuFlechettes();

        // Si la partie a effectivement été chargée du fichier, on prend les
        // valeurs de la dernière partie pour la complétion des niveaux.
        if (loadedGame.getFromFile()) {
            flechettesMenuController.setNiveauxCompletion(
                    loadedGame.getNiveauxFlechettesCompletion());
        }

        Scene flechettesMenuScene = new Scene(flechettesMenuRoot);

        sceneManager.addScene("Flechettes Niveaux Menu", flechettesMenuScene,
                flechettesMenuController);
    }

}