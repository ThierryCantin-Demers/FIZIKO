package fiziko.modele.jeuflechette.obstacles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Classe qui permet de créer des obstacles de type Cible
 *
 * @author Thierry Cantin-Demers, Alexandre Khuong, Tommy Audet et Nicolas
 * St-Laurent
 */
public class Cible extends ImageView implements Obstacle {
    /**
     * Largeur, en pixel, de la cible
     */
    private final int LARGEUR_CIBLE = 21 * 4;

    /**
     * Hauteur, en pixel, de la cible
     */
    private final int HAUTEUR_CIBLE = 48 * 4;

    /**
     * Chemin d'accès de l'image de base d'une cible
     */
    private final String IMAGE_DEFAUT_PATH = "/images/cible.png";

    /**
     * La grosseur de l'image de la cible par rapport à sa grosseur originale
     */
    private double scale;

    /**
     * Constructeur de base d'une cible. Essaie de lui donner l'image par défaut
     * et donne quelques attributs à cette image.
     */
    public Cible() {
        scale = 1.0;

        this.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(IMAGE_DEFAUT_PATH))));

        this.setPreserveRatio(true);
        this.setSmooth(true);
        this.setCache(true);
        this.setFitHeight(HAUTEUR_CIBLE * scale);
        this.setFitWidth(LARGEUR_CIBLE * scale);

        this.setPickOnBounds(false);
    }

    /**
     * Donne l'image passée en paramètre à la cible et donne quelques attributs
     * à cette image.
     *
     * @param image : l'image que l'on veut donner à la cible
     */
    public Cible(Image image) {
        scale = 1.0;

        this.setImage(image);
        this.setPreserveRatio(true);
        this.setSmooth(true);
        this.setCache(true);
        this.setFitHeight(HAUTEUR_CIBLE * scale);
        this.setFitWidth(LARGEUR_CIBLE * scale);
    }

    /**
     * Change la grosseur de l'image en changer son << scale >> par rapport à
     * l'image d'origine
     *
     * @param scale : le nouvelle grosseur de la cible par rapport à son image
     *              d'origine
     */
    @Override
    public void setScale(double scale) {
        this.scale = scale;
        this.setFitHeight(HAUTEUR_CIBLE * scale);
        this.setFitWidth(LARGEUR_CIBLE * scale);
    }

    /**
     * Retourne la valeur de grossissement de la cible par rapport à l'image
     * d'origine
     *
     * @return la valeur de grossissement
     */
    public double getScale() {
        return this.scale;
    }
}