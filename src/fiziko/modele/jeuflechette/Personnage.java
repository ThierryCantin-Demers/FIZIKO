package fiziko.modele.jeuflechette;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Classe qui permet de créer un personnage
 *
 * @author Thierry Cantin-Demers, Alexandre Khuong, Tommy Audet et Nicolas
 * St-Laurent
 */
public class Personnage extends ImageView {
    /**
     * La proportion des dimensions de l'image originale du personnage qui sera
     * donnée à l'image dans le jeu
     */
    private final double SCALE = 0.4;

    /**
     * La largeur, en pixel, du personnage
     */
    private final int LARGEUR_PERSONNAGE = (int) (373 * SCALE);

    /**
     * La hauteur, en pixel, du personnage
     */
    private final int HAUTEUR_PERSONNAGE = (int) (676 * SCALE);

    /**
     * Chemin d'accès de l'image de base d'un personnage
     */
    private final String IMAGE_DEFAUT_PATH = "/images/personnage.png";

    /**
     * Proportion de la position en X du point de lancement du dard par rapport
     * à la largeur totale du personnage
     */
    private final double RATIO_POINT_DE_LANCEMENT_X = 0.77;

    /**
     * Proportion de la position en Y du point de lancement du dard par rapport
     * à la hauteur totale du personnage
     */
    private final double RATIO_POINT_DE_LANCEMENT_Y = 0.189;

    /**
     * Le point d'où sera lancé le dard
     */
    private Point2D pointDeLancement;

    /**
     * Constructeur de base d'un personnage. Essaie de lui donner l'image par
     * défaut et donne quelques attributs à cette image. Donne des coordonée par
     * défaut au point de lancement en considérant les coordonées comme étant
     * (0,0)
     */
    public Personnage() {

        this.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(IMAGE_DEFAUT_PATH))));

        this.setPreserveRatio(true);
        this.setSmooth(true);
        this.setCache(true);
        this.setFitHeight(HAUTEUR_PERSONNAGE);
        this.setFitWidth(LARGEUR_PERSONNAGE);

        pointDeLancement = new Point2D(
                RATIO_POINT_DE_LANCEMENT_X * LARGEUR_PERSONNAGE,
                RATIO_POINT_DE_LANCEMENT_Y * HAUTEUR_PERSONNAGE);
    }

    /**
     * Donne l'image passée en paramètre au personnage et donne quelques
     * attributs à cette image. Donne des coordonée par défaut au point de
     * lancement en considérant les coordonées comme étant (0,0)
     *
     * @param image : l'image que l'on veut donner au personnage
     */
    public Personnage(Image image) {
        this.setImage(image);
        this.setPreserveRatio(true);
        this.setSmooth(true);
        this.setCache(true);
        this.setFitHeight(HAUTEUR_PERSONNAGE);
        this.setFitWidth(LARGEUR_PERSONNAGE);

        pointDeLancement = new Point2D(
                RATIO_POINT_DE_LANCEMENT_X * LARGEUR_PERSONNAGE,
                RATIO_POINT_DE_LANCEMENT_Y * HAUTEUR_PERSONNAGE);
    }

    /**
     * Change la position du personnage dans l'écran et recalcule la position du
     * lancement du dard
     *
     * @param x : la nouvelle position en x du personnage
     * @param y : la nouvelle position en y du personnage
     */
    public void setPosition(double x, double y) {
        this.setX(x);
        this.setY(y);

        pointDeLancement = new Point2D(
                RATIO_POINT_DE_LANCEMENT_X * LARGEUR_PERSONNAGE + x,
                RATIO_POINT_DE_LANCEMENT_Y * HAUTEUR_PERSONNAGE + y);
    }

    /**
     * Retourne le point d'où sera lancé le dard
     *
     * @return le point d'où sera lancé le dard
     */
    public Point2D getPointDeLancement() {
        return this.pointDeLancement;
    }

    /**
     * Joue un gif du personnage qui lance le dard
     */
    public void jouerAnimationLancement() {
        Image gif = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/images/gif/lancementDard.gif")));
        this.setImage(gif);
    }

}
