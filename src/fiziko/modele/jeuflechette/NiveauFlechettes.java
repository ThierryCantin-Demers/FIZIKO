package fiziko.modele.jeuflechette;

import com.sun.javafx.geom.Point2D;
import fiziko.modele.jeuflechette.obstacles.Bordure;
import fiziko.modele.jeuflechette.obstacles.Cible;
import fiziko.modele.jeuflechette.obstacles.Obstacle;
import fiziko.modele.moteurdephysique.DeplacementObjet;
import fiziko.modele.moteurdephysique.EquationsProjectile;
import fiziko.modele.moteurdephysique.Projectile;
import javafx.animation.Interpolator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.shape.QuadCurve;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un niveau du jeu de fléchettes
 *
 * @author Thierry Cantin-Demers, Alexandre Khuong, Tommy Audet et Nicolas
 * St-Laurent
 */
public class NiveauFlechettes {
    /**
     * Objet contenant les données mathématiques du niveau
     */
    private EquationsProjectile donneesNiveau;

    /**
     * Le projectile qui est lié à ce niveau
     */
    private Projectile projectile;

    /**
     * Objet qui à sert jouer l'animation du le projectile du niveau
     */
    private DeplacementObjet donneesAnimation;

    /**
     * La liste des obstacles du niveau
     */
    private List<Obstacle> obstacles;

    /**
     * La cible qui est attribuée au niveau
     */
    private Cible cible;

    /**
     * Position de la cible (x,y)
     */
    private Point2D coordCible;

    /**
     * Listener qui écoute les changements dans la position du projectile pour
     * détecter les collisions avec les obstacles
     */
    private ChangeListener<Number> collisionListener;

    /**
     * Le personnage qui sera dans le niveau
     */
    private Personnage personnage;

    /**
     * Propriété qui contient l'état actuel du niveau, est utilisé pour détecter
     * si le joueur a gagné ou perdu
     */
    private SimpleStringProperty etatDuNiveauProperty;

    /**
     * Numéro du niveau
     */
    private int numero;

    /**
     * Retourne la propriété qui représente l'état actuel du niveau
     *
     * @return la propriété qui représente l'état actuel du niveau
     */
    public SimpleStringProperty etatDuNiveauProperty() {
        return this.etatDuNiveauProperty;
    }

    /**
     * Attribue un numéro au niveau
     *
     * @param numero : le niveau à attribuer au niveau
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Retourne le numéro du niveau
     *
     * @return le numéro du niveau
     */
    public int getNumero() {
        return this.numero;
    }

    /**
     * Constructeur de la classe par défaut. Cette méthode initialise tous les
     * attributs de la classe à leur valeur par défaut.
     */
    public NiveauFlechettes() {
        donneesNiveau = new EquationsProjectile();

        projectile = new Projectile();

        donneesAnimation = null;

        obstacles = new ArrayList<Obstacle>();

        obstacles.add(new Bordure(true));
        obstacles.add(new Bordure(false));

        cible = new Cible();

        personnage = new Personnage();

        etatDuNiveauProperty = new SimpleStringProperty();

        changerCollisionListener();

    }

    /**
     * Constructeur de la classe avec paramètres. Cette méthode initialise tous
     * les attributs de la classe avec les valeurs entrées en paramètre.
     */
    public NiveauFlechettes(EquationsProjectile donneesNiveau,
                            Projectile projectile, ArrayList<Obstacle> obstacles) {
        this.donneesNiveau = donneesNiveau;

        this.projectile = projectile;

        this.obstacles = obstacles;

        for (int i = 0; i < obstacles.size() && cible != null; i++) {
            if (obstacles.get(i) instanceof Cible) {
                this.cible = (Cible) obstacles.get(i);
            }
        }

        for (Obstacle obstacle : obstacles) {
            if (obstacle instanceof Bordure) {
                obstacles.remove(obstacle);
            }
        }

        obstacles.add(new Bordure(true));
        obstacles.add(new Bordure(false));

        etatDuNiveauProperty = new SimpleStringProperty();

        changerCollisionListener();

        personnage = new Personnage();
    }

    /**
     * Retourne l'objet EquationsProjectile actuel contenant toutes les données
     * d'un niveau.
     *
     * @return l'objet EquationProjectile en cours
     */
    public EquationsProjectile getDonneesNiveau() {
        return donneesNiveau;
    }

    /**
     * Retourne l'objet Projectile actuel contenant toutes les données d'un
     * projectile.
     *
     * @return l'objet Projectile en cours
     */
    public Projectile getProjectile() {
        return projectile;
    }

    /**
     * Retourne l'objet DeplacementObjet actuel contenant toutes les données
     * concernant l'animation d'un projectile
     *
     * @return l'objet DeplacementObjet en cours
     */
    public DeplacementObjet getDonneesAnimation() {
        return donneesAnimation;
    }

    /**
     * Retourne la liste contenant tous les obstacles du niveau actuel.
     *
     * @return la liste d'obstacles en cours
     */
    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * AJoute un obstacles à la liste d'obstacles du niveau
     *
     * @param obstacle : l'obstacle à ajouter au niveau
     */
    public void ajouterObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    /**
     * Retourne la cible du niveau actuel.
     *
     * @return la cible en cours
     */
    public Cible getCible() {
        return cible;
    }

    /**
     * Modifie la cible du niveau actuel pour celle en paramètre
     *
     * @param la nouvelle version de la cible
     */
    public void setCible(Cible cible) {
        this.cible = cible;

        for (Obstacle obstacle : obstacles) {
            if (obstacle instanceof Cible) {
                obstacles.remove(obstacle);
                obstacles.add(cible);
            }
        }
    }

    /**
     * Modifie l'objet EquationsProjectile contenant toutes les données du
     * niveau actuel.
     *
     * @param la nouvelle version de l'objet EquationsProjectile
     */
    public void setDonneesNiveau(EquationsProjectile donneesNiveau) {
        this.donneesNiveau = donneesNiveau;
    }

    /**
     * Modifie l'objet Projectile contenant toutes les données du projectile
     * actuel.
     *
     * @param la nouvelle version du projectile
     */
    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
        changerCollisionListener();
    }

    /**
     * Modifie l'objet DeplacementObjet contenant toutes les données de
     * l'animation du projectile en cours.
     *
     * @param la nouvelle version de l'objet DeplacementObjet
     */
    public void setDonneesAnimation(DeplacementObjet donneesAnimation) {
        if (donneesAnimation != null) {
            this.donneesAnimation = donneesAnimation;
            changerCollisionListener();
        }
    }

    /**
     * Modifie la liste contenant tous les obstacles du niveau en cours.
     *
     * @param la nouvelle liste d'obstacles
     */
    public void setObstacles(List<Obstacle> obstacles) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle instanceof Bordure) {
                obstacles.remove(obstacle);
            }
        }

        obstacles.add(new Bordure(true));
        obstacles.add(new Bordure(false));

        this.obstacles = obstacles;

    }

    /**
     * Cette méthode s'occupe de placer un listener sur le projectile du niveau
     * en cours d'exécution. Lorsque le listener détecte que le projectile est
     * entré dans la zone d'un des obstacles, l'animation s'arrête, et on
     * affiche un message de réussite/ d'échec.
     */

    /**
     * Retourne les coordonnées de la cible actuelle dans un objet Point2D
     *
     * @return les coordonnées de la cible
     */
    public Point2D getCoordCible() {
        return coordCible;
    }

    /**
     * Modifie les coordonnées de la cible actuelle
     *
     * @param les nouvelles coordonnées de la cible en cours
     */
    public void setCoordCible(Point2D coordCible) {
        this.coordCible = coordCible;
    }

    /**
     * Retourne l'ImageView contenant les informations du personnage.
     *
     * @return L'ImageView du personnage
     */
    public Personnage getPersonnage() {
        return this.personnage;
    }

    /**
     * Génère un nouveau Listener de collision entre le projectile et les
     * obstacles
     *
     * @return le ChangeListener créé
     */
    public ChangeListener<Number> genererCollisionListener() {
        ChangeListener<Number> listener = new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                for (Obstacle obstacle : obstacles) {
                    if (((Node) obstacle).getBoundsInParent()
                            .intersects(projectile.getImageViewProjectile()
                                    .getBoundsInParent())) {
                        if (obstacle instanceof Cible) {

                            donneesAnimation.getAnimation().stop();
                            etatDuNiveauProperty.set("gagné");
                        } else {
                            donneesAnimation.getAnimation().stop();
                            etatDuNiveauProperty.set("perdu");
                        }
                    }
                }
            }
        };

        return listener;
    }

    /**
     * Cette méthode change le collision listener du projectile dans le niveau
     * en cours. Il est réinitialisé à chaque fois afin de prévenir les bugs
     * concernant la détection du projectile.
     */
    public void changerCollisionListener() {
        if (collisionListener != null) {
            projectile.getImageViewProjectile().translateXProperty()
                    .removeListener(collisionListener);
        }
        ChangeListener<Number> listener = genererCollisionListener();
        collisionListener = listener;
        projectile.getImageViewProjectile().translateXProperty()
                .addListener(listener);
    }

    /**
     * Méthode qui enlève le collision listener du projectile pour éviter les
     * collisions non voulues
     */
    public void removeCollisionListener() {
        if (collisionListener != null) {
            projectile.getImageViewProjectile().translateXProperty()
                    .removeListener(collisionListener);
            collisionListener = null;
        }
    }

    /**
     * Cette méthode change l'animation de la fléchette dans le niveau en cours.
     *
     * @param parabole  La parabole de la nouvelle animation
     * @param tempsAnim La durée de la nouvelle animation en millisecondes
     */
    public void changerAnimation(QuadCurve parabole, double tempsAnimSeconde) {
        donneesAnimation = new DeplacementObjet(projectile, parabole,
                tempsAnimSeconde * 1000);
        donneesAnimation.getAnimation().setInterpolator(Interpolator.EASE_OUT);
    }
}
