package fiziko.modele.jeuflechette.obstacles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Classe qui permet de créer des obstacles de type Caisse
 *
 * @author Thierry Cantin-Demers, Alexandre Khuong, Tommy Audet et Nicolas
 * St-Laurent
 */
public class Caisse extends ImageView implements Obstacle {
    /**
     * Largeur, en pixel, de la caisse
     */
    private final int LARGEUR_CAISSE = 47;

    /**
     * Hauteur, en pixel, de la caisse
     */
    private final int HAUTEUR_CAISSE = 47;

    /**
     * Chemin d'accès de l'image de base d'une caisse
     */
    private final String IMAGE_DEFAUT_PATH = "/images/boite.png";

    /**
     * La grosseur de l'image de la caisse par rapport à sa grosseur originale
     */
    private double scale;

    /**
     * Constructeur de base d'une caisse. Essaie de lui donner l'image par
     * défaut et donne quelques attributs à cette image.
     */
    public Caisse() {
        scale = 1.0;

        this.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(IMAGE_DEFAUT_PATH))));

        this.setPreserveRatio(true);
        this.setSmooth(true);
        this.setCache(true);

        this.setFitHeight(HAUTEUR_CAISSE * scale);
        this.setFitWidth(LARGEUR_CAISSE * scale);
    }

    /**
     * Donne l'image passée en paramètre à la caisse et donne quelques attributs
     * à cette image.
     *
     * @param image : l'image que l'on veut donner à la caisse
     */
    public Caisse(Image image) {
        scale = 1.0;

        this.setImage(image);

        this.setPreserveRatio(true);
        this.setSmooth(true);
        this.setCache(true);

        this.setFitHeight(HAUTEUR_CAISSE * scale);
        this.setFitWidth(LARGEUR_CAISSE * scale);
    }

    /**
     * Change la grosseur de l'image en changer son << scale >> par rapport à
     * l'image d'origine
     *
     * @param scale : le nouvelle grosseur de la caisse par rapport à son image
     *              d'origine
     */
    @Override
    public void setScale(double scale) {
        this.scale = scale;

        this.setFitHeight(HAUTEUR_CAISSE * scale);
        this.setFitWidth(LARGEUR_CAISSE * scale);
    }

    /**
     * Retourne la valeur de grossissement de la caisse par rapport à l'image
     * d'origine
     *
     * @return la valeur de grossissement
     */
    public double getScale() {
        return this.scale;
    }

}
