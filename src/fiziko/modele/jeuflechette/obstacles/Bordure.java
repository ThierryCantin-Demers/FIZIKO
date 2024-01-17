package fiziko.modele.jeuflechette.obstacles;

import javafx.scene.shape.Line;

/**
 * Classe représentant les bordures à la droite et en bas de l'écran pour les
 * collisions avec le dard
 *
 * @author Thierry Cantin-Demers, Alexandre Khuong, Nicolas St-Laurent et Tommy
 * Audet
 */
public class Bordure extends Line implements Obstacle {

    /**
     * La hauteur d'une bordure verticale
     */
    private static final double HAUTEUR = 1017;

    /**
     * La largeur d'une bordure horizontale
     */
    private static final double LARGEUR = 1920;

    /**
     * Crée une nouvelle bordure en bas de l'écran allant de gauche à droite de
     * l'écran si la bordure est horizontale et une nouvelle bordure partant du
     * haut de l'écran vers le bas placé à la droite de l'écran si elle est
     * verticale
     *
     * @param horizontal : détermine si la bordure sera horizontale ou verticale
     */
    public Bordure(boolean horizontal) {
        if (horizontal) {
            this.setStartX(0);
            this.setStartY(HAUTEUR);
            this.setEndX(LARGEUR);
            this.setEndY(HAUTEUR);
        } else {
            this.setStartX(LARGEUR);
            this.setStartY(-HAUTEUR);
            this.setEndX(LARGEUR);
            this.setEndY(HAUTEUR);
        }
    }

    @Override
    public void setScale(double scale) {

    }

}
