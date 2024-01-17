package fiziko.controller;

import fiziko.application.SceneManager;
import fiziko.modele.controller.Controller;
import fiziko.modele.jeuflechette.NiveauFlechettes;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur de la scène du choix de niveau du jeu de fléchettes
 *
 * @author Thierry Cantin-Demers, Alexandre Khuong, Tommy Audet et Nicolas
 * St-Laurent
 */
public class FlechettesMenuController implements Controller {

    /**
     * ImageView foncée permettant de faire la « fade transition » du niveau 1.
     */
    @FXML
    private ImageView boutonNiveau1Dark;

    /**
     * ImageView foncée permettant de faire la « fade transition » du niveau 2.
     */
    @FXML
    private ImageView boutonNiveau2Dark;

    /**
     * ImageView foncée permettant de faire la « fade transition » du niveau 3.
     */
    @FXML
    private ImageView boutonNiveau3Dark;

    /**
     * ImageView foncée permettant de faire la « fade transition » du niveau 4.
     */
    @FXML
    private ImageView boutonNiveau4Dark;

    /**
     * ImageView foncée permettant de faire la « fade transition » du niveau 5.
     */
    @FXML
    private ImageView boutonNiveau5Dark;

    /**
     * ImageView foncée permettant de faire la « fade transition » du niveau 6.
     */
    @FXML
    private ImageView boutonNiveau6Dark;

    /**
     * ImageView foncée permettant de faire la « fade transition » du niveau 7.
     */
    @FXML
    private ImageView boutonNiveau7Dark;

    /**
     * ImageView foncée permettant de faire la « fade transition » du niveau 8.
     */
    @FXML
    private ImageView boutonNiveau8Dark;

    /**
     * ImageView foncée permettant de faire la « fade transition » du niveau 9.
     */
    @FXML
    private ImageView boutonNiveau9Dark;

    /**
     * ImageView foncée permettant de faire la « fade transition » du niveau 10.
     */
    @FXML
    private ImageView boutonNiveau10Dark;

    /**
     * ImageView permettant de faire la « fade transition » du niveau 1.
     */
    @FXML
    private ImageView boutonNiveau1;

    /**
     * ImageView permettant de faire la « fade transition » du niveau 2.
     */
    @FXML
    private ImageView boutonNiveau2;

    /**
     * ImageView permettant de faire la « fade transition » du niveau 3.
     */
    @FXML
    private ImageView boutonNiveau3;

    /**
     * ImageView permettant de faire la « fade transition » du niveau 4.
     */
    @FXML
    private ImageView boutonNiveau4;

    /**
     * ImageView permettant de faire la « fade transition » du niveau 5.
     */
    @FXML
    private ImageView boutonNiveau5;

    /**
     * ImageView permettant de faire la « fade transition » du niveau 6.
     */
    @FXML
    private ImageView boutonNiveau6;

    /**
     * ImageView permettant de faire la « fade transition » du niveau 7.
     */
    @FXML
    private ImageView boutonNiveau7;

    /**
     * ImageView permettant de faire la « fade transition » du niveau 8.
     */
    @FXML
    private ImageView boutonNiveau8;

    /**
     * ImageView permettant de faire la « fade transition » du niveau 9.
     */
    @FXML
    private ImageView boutonNiveau9;

    /**
     * ImageView permettant de faire la « fade transition » du niveau 10.
     */
    @FXML
    private ImageView boutonNiveau10;

    /**
     * Image foncé du bouton retour qui apparait en animation lorsque le bouton
     * retour est cliqué
     */
    @FXML
    private ImageView boutonRetourDark;

    /**
     * Le gestionnaire de scène de la fenêtre principale
     */
    private SceneManager sceneManager;

    /**
     * La transition de fade du bouton retour
     */
    private FadeTransition retourBoutonFadeTransition;

    /**
     * La transition de fade du bouton retour
     */
    private FadeTransition niveauBoutonFadeTransition;

    /**
     * La liste des niveaux du jeu de fléchettes
     */
    private List<NiveauFlechettes> niveaux;

    /**
     * La liste de complétion des niveaux du jeu de fléchettes
     */
    private List<Boolean> niveauxCompletion;

    /**
     * Le contrôleur de la scène du jeu de fléchettes
     */
    private JeuFlechettesController jeuFlechettesController;

    /**
     * La Pane qui fait fonction de root à la scène
     */
    @FXML
    private AnchorPane root;

    /**
     * Initialisation du contrôleur lors du chargement du fichier fxml
     */
    @FXML
    void initialize() {

        // Initialisation de la transisiton de fade pour le bouton retour
        retourBoutonFadeTransition = new FadeTransition(new Duration(400));

        // Initialisation de la transisiton de fade pour les boutons de niveaux
        niveauBoutonFadeTransition = new FadeTransition(new Duration(400));

        // Opacité du bouton retour
        retourBoutonFadeTransition.setFromValue(0);
        retourBoutonFadeTransition.setToValue(1);

        // Opacité des boutons des niveaux
        niveauBoutonFadeTransition.setFromValue(0);
        niveauBoutonFadeTransition.setToValue(1);

        // ArrayList contenant les niveaux
        niveaux = new ArrayList<NiveauFlechettes>();
    }

    /**
     * Réinisitialise le contrôleur
     */
    @Override
    public void resetMenu() {
        boutonRetourDark.setOpacity(0);
        boutonNiveau1Dark.setOpacity(0);
        boutonNiveau2Dark.setOpacity(0);
        boutonNiveau3Dark.setOpacity(0);
        boutonNiveau4Dark.setOpacity(0);
        boutonNiveau5Dark.setOpacity(0);
        boutonNiveau6Dark.setOpacity(0);
        boutonNiveau7Dark.setOpacity(0);
        boutonNiveau8Dark.setOpacity(0);
        boutonNiveau9Dark.setOpacity(0);
        boutonNiveau10Dark.setOpacity(0);
    }

    /**
     * Donne accès au gestionnaire de scène de la fenêtre
     *
     * @param manager : le gestionnaire de scène de la fenêtre
     */
    @Override
    public void setSceneManager(SceneManager manager) {
        this.sceneManager = manager;
    }

    /**
     * Méthode qui permet de retourner au menu de choix de jeu
     *
     * @param event : lorsque le bouton retour est cliqué
     */
    @FXML
    void retourAuMenuChoixDeJeu(MouseEvent event) {

        retourBoutonFadeTransition.setOnFinished((e) -> {
            sceneManager.changeCurrentScene("Main menu");
        });
        retourBoutonFadeTransition.setNode(boutonRetourDark);
        retourBoutonFadeTransition.play();

    }

    /**
     * Méthode qui permet de lancer le niveau 1 et d'initialiser le « fade
     * transition ».
     *
     * @param event : lorsque le bouton niveau 1 est cliqué
     */
    @FXML
    void boutonNiveau1(MouseEvent event) {
        niveauBoutonFadeTransition.setOnFinished((e) -> {
            jeuFlechettesController.setNumero(1);
            sceneManager.changeCurrentScene("Jeu Flechettes");
        });
        niveauBoutonFadeTransition.setNode(boutonNiveau1Dark);
        niveauBoutonFadeTransition.play();
    }

    /**
     * Méthode qui permet de lancer le niveau 2 et d'initialiser le « fade
     * transition ».
     *
     * @param event : lorsque le bouton niveau 2 est cliqué
     */
    @FXML
    void boutonNiveau2(MouseEvent event) {
        niveauBoutonFadeTransition.setOnFinished((e) -> {
            jeuFlechettesController.setNumero(2);
            sceneManager.changeCurrentScene("Jeu Flechettes");
        });
        niveauBoutonFadeTransition.setNode(boutonNiveau2Dark);
        niveauBoutonFadeTransition.play();

    }

    /**
     * Méthode qui permet de lancer le niveau 3 et d'initialiser le « fade
     * transition ».
     *
     * @param event : lorsque le bouton niveau 3 est cliqué
     */
    @FXML
    void boutonNiveau3(MouseEvent event) {
        niveauBoutonFadeTransition.setOnFinished((e) -> {
            jeuFlechettesController.setNumero(3);
            sceneManager.changeCurrentScene("Jeu Flechettes");
        });
        niveauBoutonFadeTransition.setNode(boutonNiveau3Dark);
        niveauBoutonFadeTransition.play();

    }

    /**
     * Méthode qui permet de lancer le niveau 4 et d'initialiser le « fade
     * transition ».
     *
     * @param event : lorsque le bouton niveau 4 est cliqué
     */
    @FXML
    void boutonNiveau4(MouseEvent event) {
        niveauBoutonFadeTransition.setOnFinished((e) -> {
            jeuFlechettesController.setNumero(4);
            sceneManager.changeCurrentScene("Jeu Flechettes");
        });
        niveauBoutonFadeTransition.setNode(boutonNiveau4Dark);
        niveauBoutonFadeTransition.play();
    }

    /**
     * Méthode qui permet de lancer le niveau 5 et d'initialiser le « fade
     * transition ».
     *
     * @param event : lorsque le bouton niveau 5 est cliqué
     */
    @FXML
    void boutonNiveau5(MouseEvent event) {
        niveauBoutonFadeTransition.setOnFinished((e) -> {
            jeuFlechettesController.setNumero(5);
            sceneManager.changeCurrentScene("Jeu Flechettes");
        });
        niveauBoutonFadeTransition.setNode(boutonNiveau5Dark);
        niveauBoutonFadeTransition.play();
    }

    /**
     * Méthode qui permet de lancer le niveau 6 et d'initialiser le « fade
     * transition ».
     *
     * @param event : lorsque le bouton niveau 6 est cliqué
     */
    @FXML
    void boutonNiveau6(MouseEvent event) {
        niveauBoutonFadeTransition.setOnFinished((e) -> {
            jeuFlechettesController.setNumero(6);
            sceneManager.changeCurrentScene("Jeu Flechettes");
        });
        niveauBoutonFadeTransition.setNode(boutonNiveau6Dark);
        niveauBoutonFadeTransition.play();
    }

    /**
     * Méthode qui permet de lancer le niveau 7 et d'initialiser le « fade
     * transition ».
     *
     * @param event : lorsque le bouton niveau 7 est cliqué
     */
    @FXML
    void boutonNiveau7(MouseEvent event) {
        niveauBoutonFadeTransition.setOnFinished((e) -> {
            jeuFlechettesController.setNumero(7);
            sceneManager.changeCurrentScene("Jeu Flechettes");
        });
        niveauBoutonFadeTransition.setNode(boutonNiveau7Dark);
        niveauBoutonFadeTransition.play();
    }

    /**
     * Méthode qui permet de lancer le niveau 8 et d'initialiser le « fade
     * transition ».
     *
     * @param event : lorsque le bouton niveau 8 est cliqué
     */
    @FXML
    void boutonNiveau8(MouseEvent event) {
        niveauBoutonFadeTransition.setOnFinished((e) -> {
            jeuFlechettesController.setNumero(8);
            sceneManager.changeCurrentScene("Jeu Flechettes");
        });
        niveauBoutonFadeTransition.setNode(boutonNiveau8Dark);
        niveauBoutonFadeTransition.play();
    }

    /**
     * Méthode qui permet de lancer le niveau 9 et d'initialiser le « fade
     * transition ».
     *
     * @param event : lorsque le bouton niveau 9 est cliqué
     */
    @FXML
    void boutonNiveau9(MouseEvent event) {
        niveauBoutonFadeTransition.setOnFinished((e) -> {
            jeuFlechettesController.setNumero(9);
            sceneManager.changeCurrentScene("Jeu Flechettes");
        });
        niveauBoutonFadeTransition.setNode(boutonNiveau9Dark);
        niveauBoutonFadeTransition.play();
    }

    /**
     * Méthode qui permet de lancer le niveau 10 et d'initialiser le « fade
     * transition ».
     *
     * @param event : lorsque le bouton niveau 10 est cliqué
     */
    @FXML
    void boutonNiveau10(MouseEvent event) {
        niveauBoutonFadeTransition.setOnFinished((e) -> {
            jeuFlechettesController.setNumero(10);
            sceneManager.changeCurrentScene("Jeu Flechettes");
        });
        niveauBoutonFadeTransition.setNode(boutonNiveau10Dark);
        niveauBoutonFadeTransition.play();
    }

    /**
     * Donne accès au contrôleur du jeu de fléchettes
     *
     * @param jeuFlechettesController : le contrôleur de la scène du jeu de
     *                                fléchettes
     */
    public void setJeuFlechettesController(
            JeuFlechettesController jeuFlechettesController) {
        this.jeuFlechettesController = jeuFlechettesController;
    }

    /**
     * Change la valeur de complétions de niveaux du jeu de fléchettes
     *
     * @param niveauxCompletion : le nouveaux niveau de complétion des niveau du
     *                          jeu de fléchettes
     */
    public void setNiveauxCompletion(List<Boolean> niveauxCompletion) {
        this.niveauxCompletion = niveauxCompletion;
    }

    public void setNiveauxJeuFlechettes() {
        jeuFlechettesController.setNiveaux(niveaux);
    }

    /**
     * Retourne la liste des niveaux du jeu de fléchettes
     *
     * @return la liste des niveaux du jeu de fléchettes
     */
    public List<NiveauFlechettes> getNiveaux() {
        return this.niveaux;
    }

    /**
     * Initialise le contrôleur lorsque qu'on arrive sur sa scène. Rend les
     * boutons de niveaux visibles ou invisibles dépendamment de si ces niveaux
     * sont complétés ou non.
     */
    @Override
    public void initMenu() {
        setNiveauxCompletion(sceneManager.getCurrentGameState()
                .getNiveauxFlechettesCompletion());

        boutonNiveau1.setVisible(true);
        boutonNiveau1Dark.setVisible(true);

        boutonNiveau2.setVisible(niveauxCompletion.get(0));
        boutonNiveau2Dark.setVisible(niveauxCompletion.get(0));

        boutonNiveau3.setVisible(niveauxCompletion.get(1));
        boutonNiveau3Dark.setVisible(niveauxCompletion.get(1));

        boutonNiveau4.setVisible(niveauxCompletion.get(2));
        boutonNiveau4Dark.setVisible(niveauxCompletion.get(2));

        boutonNiveau5.setVisible(niveauxCompletion.get(3));
        boutonNiveau5Dark.setVisible(niveauxCompletion.get(3));

        boutonNiveau6.setVisible(niveauxCompletion.get(4));
        boutonNiveau6Dark.setVisible(niveauxCompletion.get(4));

        boutonNiveau7.setVisible(niveauxCompletion.get(5));
        boutonNiveau7Dark.setVisible(niveauxCompletion.get(5));

        boutonNiveau8.setVisible(niveauxCompletion.get(6));
        boutonNiveau8Dark.setVisible(niveauxCompletion.get(6));

        boutonNiveau9.setVisible(niveauxCompletion.get(7));
        boutonNiveau9Dark.setVisible(niveauxCompletion.get(7));

        boutonNiveau10.setVisible(niveauxCompletion.get(8));
        boutonNiveau10Dark.setVisible(niveauxCompletion.get(8));
    }
}