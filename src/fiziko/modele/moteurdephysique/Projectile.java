package fiziko.modele.moteurdephysique;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Cette classe stocke toutes les données relatives à un projectile et permet
 * d'en modifier quelques propriétés.
 *
 * @author Thierry Cantin-Demers, Alexandre Khuong, Tommy Audet et Nicolas
 * St-Laurent
 */
public class Projectile {
    /**
     * ImageView du dard qui sera affiché à l'écran
     */
    private ImageView imageViewProjectile;

    /**
     * Image du dard qui est attribué à l'ImageView
     */
    private Image imageProjectile;

    /**
     * Hauteur du projectile
     */
    private final int HAUTEUR_PROJECTILE = 19;

    /**
     * Largeur du projectile
     */
    private final int LARGEUR_PROJECTILE = 66;

    /**
     * Crée un nouveau projectile avec l'image d'un dard
     */
    public Projectile() {

        imageProjectile = new Image(
                Objects.requireNonNull(getClass().getResourceAsStream("/images/dard.png")));

        imageViewProjectile = new ImageView(imageProjectile);

        imageViewProjectile.setPreserveRatio(true);
        imageViewProjectile.setSmooth(true);
        imageViewProjectile.setCache(true);

        imageViewProjectile.setFitHeight(HAUTEUR_PROJECTILE);
        imageViewProjectile.setFitWidth(LARGEUR_PROJECTILE);

    }

    /**
     * Retourne la hauteur du projectile
     *
     * @return la hauteur du projectile
     */
    public int getHauteurHitbox() {
        return HAUTEUR_PROJECTILE;

    }

    /**
     * Retourne la largeur du projectile
     *
     * @return la largeur du projectile
     */
    public int getLargeurHitbox() {
        return LARGEUR_PROJECTILE;
    }

    /**
     * Retourne l'ImageView qui représente le projectile
     *
     * @return l'ImageView qui représente le projectile
     */
    public ImageView getImageViewProjectile() {
        return imageViewProjectile;
    }

}
