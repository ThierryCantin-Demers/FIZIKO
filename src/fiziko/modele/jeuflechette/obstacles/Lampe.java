package fiziko.modele.jeuflechette.obstacles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Lampe extends ImageView implements Obstacle {

    /**
     * Largeur, en pixel, de la lampe
     */
    private final int LARGEUR_LAMPE = 38;

    /**
     * Hauteur, en pixel, de la lampe
     */
    private final int HAUTEUR_LAMPE = 93;

    /**
     * Chemin d'accès de l'image de base d'une lampe
     */
    private final String IMAGE_DEFAUT_PATH = "/images/lampe.png";

    /**
     * La grosseur de l'image de la lampe par rapport à sa grosseur originale
     */
    private double scale;

    /**
     * Constructeur de base d'une lampe. Essaie de lui donner l'image par défaut
     * et donne quelques attributs à cette image.
     */
    public Lampe() {
        scale = 1.0;

        this.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(IMAGE_DEFAUT_PATH))));

        this.setPreserveRatio(true);
        this.setSmooth(true);
        this.setCache(true);
        this.setFitHeight(HAUTEUR_LAMPE * scale);
        this.setFitWidth(LARGEUR_LAMPE * scale);
    }

    /**
     * Donne l'image passée en paramètre à la lampe et donne quelques attributs
     * à cette image.
     *
     * @param image : l'image que l'on veut donner à la lampe
     */
    public Lampe(Image image) {
        scale = 1.0;

        this.setImage(image);

        this.setPreserveRatio(true);
        this.setSmooth(true);
        this.setCache(true);

        this.setFitHeight(HAUTEUR_LAMPE * scale);
        this.setFitWidth(LARGEUR_LAMPE * scale);
    }

    /**
     * Change la grosseur de l'image en changer son << scale >> par rapport à
     * l'image d'origine
     *
     * @param scale : le nouvelle grosseur de la lampe par rapport à son image
     *              d'origine
     */
    @Override
    public void setScale(double scale) {
        this.scale = scale;
        this.setFitHeight(HAUTEUR_LAMPE * scale);
        this.setFitWidth(LARGEUR_LAMPE * scale);
    }

    /**
     * Retourne la valeur de grossissement de la lampe par rapport à l'image
     * d'origine
     *
     * @return la valeur de grossissement
     */
    public double getScale() {
        return this.scale;
    }
}
