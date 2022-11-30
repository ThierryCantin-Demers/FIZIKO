package modele.moteurdephysique;

import java.util.Timer;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.scene.shape.QuadCurve;
import javafx.util.Duration;

/**
 * Cette classe permet de déplacer une ImageView sur une trajectoire
 * parabollique suivant une animation sur un temps défini.
 * 
 * @author Thierry Cantin-Demers, Alexandre Khuong, Tommy Audet et Nicolas
 *         St-Laurent
 *
 */
public class DeplacementObjet
{
	/**
	 * Le projectile que l'on souhaite déplacé
	 */
	Projectile projectileDeplace;

	/**
	 * L'animation du déplacement de l'objet
	 */
	PathTransition animationDard;

	/**
	 * La courbe représantant la trajectoire parabollique du projectile
	 */
	QuadCurve trajectoire;

	/**
	 * Constructeur de la classe DeplacementObjet qui initialise le
	 * PathTransition avec les valeurs en paramètres (projectile, trajectoire,
	 * dureeAnimation) et place le projectile à sa position initiale.
	 * 
	 * @param projectile Le projectile déplacé par le PathTransition
	 * @param trajectoire La parabole sur laquelle le projectile se déplace
	 * @param dureeAnimation Le temps total de l'animation
	 */
	public DeplacementObjet(Projectile projectile, QuadCurve trajectoire,
			double dureeAnimation)
	{

		setProjectileDeplace(projectile);
		setTrajectoire(trajectoire);

		// Setup de l'animation du dard
		animationDard = new PathTransition();
		animationDard.setPath(trajectoire);
		animationDard.setNode(projectile.getImageViewProjectile());
		animationDard.setCycleCount(1);
		animationDard.setDuration(Duration.millis(dureeAnimation));

		animationDard.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);

		getProjectileDeplace().getImageViewProjectile()
				.setX(trajectoire.getStartX()
						- getProjectileDeplace().getLargeurHitbox() / 2);
		getProjectileDeplace().getImageViewProjectile()
				.setY(trajectoire.getStartY()
						- getProjectileDeplace().getHauteurHitbox() / 2);

	}

	/**
	 * Retourne le projectile déplacé par la parabole
	 * 
	 * @return le projectile déplacé
	 */
	public Projectile getProjectileDeplace()
	{
		return projectileDeplace;
	}

	/**
	 * Modifie le projectile déplacé par la parabole par celui en paramètre
	 * 
	 * @param projectile Le nouveau projectile
	 */
	public void setProjectileDeplace(Projectile projectile)
	{
		this.projectileDeplace = projectile;
	}

	/**
	 * Retourne la parabole de l'animation (trajectoire du projectile).
	 * 
	 * @return La parabole de l'animation
	 */
	public QuadCurve getTrajectoire()
	{
		return trajectoire;
	}

	/**
	 * Modifie la parabole de l'animation (trajectoire du projectile) par celle
	 * en paramètre
	 * 
	 * @param forme2 La nouvelle parabole de l'animation
	 */
	public void setTrajectoire(QuadCurve forme2)
	{
		this.trajectoire = forme2;
	}

	/**
	 * Retourne l'animation en cours
	 * 
	 * @return L'animation actuelle
	 */
	public PathTransition getAnimation()
	{
		return animationDard;
	}

	/**
	 * Modifie l'animation en cours par celle entrée en paramètre
	 * 
	 * @param animation La nouvelle animation
	 */
	public void setAnimation(PathTransition animation)
	{
		this.animationDard = animation;
	}

	/**
	 * Retourne le nombre de cycle de l'animation
	 * 
	 * @return Le nombre de cycle
	 */
	public int getNbCycle()
	{
		return animationDard.getCycleCount();
	}

	/**
	 * Modifie le nombre de cycle de l'animation par celui entré en paramètre.
	 * 
	 * @param nbCycles Le nouveau nombre de cycle
	 */
	public void setNbCycle(int nbCycles)
	{
		getAnimation().setCycleCount(nbCycles);
	}

	/**
	 * Commence à jouer l'animation de déplacement du dard après 1.1s
	 */
	public void deplacerProjectile()
	{
		Timer t = new java.util.Timer();
		t.schedule(new java.util.TimerTask()
		{
			@Override
			public void run()
			{

				projectileDeplace.getImageViewProjectile().setVisible(true);
				animationDard.playFromStart();

				t.cancel();
			}
		}, 1300);

	}

}
