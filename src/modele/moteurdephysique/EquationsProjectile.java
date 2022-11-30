package modele.moteurdephysique;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;

/**
 * Classe qui implémente plusieurs notions et équations de la cinématique
 * notamment les équations du MRU (Mouvement rectiligne uniforme) et du MRUA
 * (Mouvement rectiligne uniformément accéléré). Elle stocke toutes les
 * variables de ces équations et permet d'effectuer tous les calculs nécessaire
 * lorsque l'on déplace un projectile.
 * 
 * @author Thierry Cantin-Demers, Alexandre Khuong, Tommy Audet et Nicolas
 *         St-Laurent
 */
public class EquationsProjectile
{

	/*
	 * Attributs pour les équations du mouvement rectiligne uniforme et
	 * uniformément accéléré. Toutes les valeurs sont stockées dans des objets
	 * "Property".
	 */

	/**
	 * L'intensité du champ gravitationnel terrestre (accélération
	 * gravitationnelle) en N/kg
	 * 
	 */
	private static final double ACCELERATION_GRAVITATIONNELLE_TERRE = -9.80665;

	/**
	 * La position initiale du projectile en X, en mètre.
	 */
	private SimpleDoubleProperty positionInitX;
	/**
	 * La position initiale du projectile en Y, en mètre.
	 */
	private SimpleDoubleProperty positionInitY;
	/**
	 * La position finale du projectile en X, en mètre.
	 */
	private SimpleDoubleProperty positionFinX;
	/**
	 * La position finale du projectile en Y, en mètre.
	 */
	private SimpleDoubleProperty positionFinY;
	/**
	 * La deplacement effectué par le projectile en X, en mètre.
	 */
	private SimpleDoubleProperty variationPositionX;
	/**
	 * La deplacement effectué par le projectile en Y, en mètre.
	 */
	private SimpleDoubleProperty variationPositionY;
	/**
	 * La vitesse initiale combinée du projectile en m/s.
	 */
	private SimpleDoubleProperty vitesseInit;
	/**
	 * La vitesse initiale du projectile en X, en m/s.
	 */
	private SimpleDoubleProperty vitesseInitX;
	/**
	 * La vitesse initiale du projectile en Y, en m/s.
	 */
	private SimpleDoubleProperty vitesseInitY;
	/**
	 * La vitesse finale combinée du projectile en m/s.
	 */
	private SimpleDoubleProperty vitesseFin;
	/**
	 * La vitesse finale du projectile en X, en m/s.
	 */
	private SimpleDoubleProperty vitesseFinX;
	/**
	 * La vitesse finale du projectile en Y, en m/s.
	 */
	private SimpleDoubleProperty vitesseFinY;
	/**
	 * La variation de la vitesse du projectile en X, en m/s.
	 */
	private SimpleDoubleProperty variationVitesseX;
	/**
	 * La variation de la vitesse du projectile en Y, en m/s.
	 */
	private SimpleDoubleProperty variationVitesseY;
	/**
	 * La variation de la vitesse combinée du projectile, en m/s.
	 */
	private SimpleDoubleProperty variationVitesse;
	/**
	 * L'intensité du champ gravitationnel actuel (de base il s'agit de celui de
	 * la Terre)
	 */
	private SimpleDoubleProperty champGravitationnel;
	/**
	 * Le temps marquant le début du déplacement, en s
	 */
	private SimpleDoubleProperty tempsInit;
	/**
	 * Le temps marquant la fin du déplacement, en s
	 */
	private SimpleDoubleProperty tempsFin;
	/**
	 * La variation de temps marquant la durée totale du déplacement, en s
	 */
	private SimpleDoubleProperty variationTemps;
	/**
	 * L'angle que fait initialement le projectile par rapport au sol, en degré
	 */
	private SimpleDoubleProperty angleInit;
	/**
	 * L'angle que fait le projectile par rapport au sol à la toute fin, en
	 * degré
	 */
	private SimpleDoubleProperty angleFin;
	/**
	 * L'accélération combinée du projectile, en m/s2
	 */
	private SimpleDoubleProperty acceleration;
	/**
	 * L'accélération en X du projectile, en m/s2
	 */
	private SimpleDoubleProperty accelerationX;
	/**
	 * L'accélération en Y du projectile, en m/s2
	 */
	private SimpleDoubleProperty accelerationY;
	/**
	 * La vitesse moyenne du projectile tout au long du déplacement en X, en m/s
	 */
	private SimpleDoubleProperty vitesseMoyenneX;
	/**
	 * La vitesse moyenne du projectile tout au long du déplacement en Y, en m/s
	 */
	private SimpleDoubleProperty vitesseMoyenneY;

	/**
	 * Constructeur de la classe EquationsProjectile. Cette méthode instantie
	 * tous les attributs à leurs valeurs par défaut et surtout, s'occupe de
	 * bind de nombreux attributs/property entre-eux afin d'automatiser certains
	 * calculs.
	 */
	public EquationsProjectile()
	{

		/*
		 * Instantiation des attributs
		 */
		positionInitX = new SimpleDoubleProperty(0);
		positionInitY = new SimpleDoubleProperty(0);
		positionFinX = new SimpleDoubleProperty(0);
		positionFinY = new SimpleDoubleProperty(0);
		variationPositionX = new SimpleDoubleProperty(0);
		variationPositionY = new SimpleDoubleProperty(0);
		vitesseInit = new SimpleDoubleProperty(0);
		vitesseInitX = new SimpleDoubleProperty(0);
		vitesseInitY = new SimpleDoubleProperty(0);
		vitesseFin = new SimpleDoubleProperty(0);
		vitesseFinX = new SimpleDoubleProperty(0);
		vitesseFinY = new SimpleDoubleProperty(0);
		variationVitesse = new SimpleDoubleProperty(0);
		variationVitesseX = new SimpleDoubleProperty(0);
		variationVitesseY = new SimpleDoubleProperty(0);
		champGravitationnel = new SimpleDoubleProperty(
				ACCELERATION_GRAVITATIONNELLE_TERRE);
		tempsInit = new SimpleDoubleProperty(0);
		tempsFin = new SimpleDoubleProperty(0);
		variationTemps = new SimpleDoubleProperty(0);

		Bindings.bindBidirectional(tempsFin, variationTemps);

		angleInit = new SimpleDoubleProperty(0);
		angleFin = new SimpleDoubleProperty(0);
		acceleration = new SimpleDoubleProperty(0);
		accelerationX = new SimpleDoubleProperty(0);
		accelerationY = new SimpleDoubleProperty(0);
		vitesseMoyenneX = new SimpleDoubleProperty(0);
		vitesseMoyenneY = new SimpleDoubleProperty(0);
	}

	/**
	 * Constructeur de la classe EquationsProjectile. Cette méthode instantie
	 * tous les attributs à leurs valeurs par défaut, sauf la vitesse initiale
	 * vectorielle, et surtout, s'occupe de bind de nombreux attributs/property
	 * entre-eux afin d'automatiser certains calculs.
	 */
	public EquationsProjectile(double vitesseInitialeVectorielle)
	{

		/*
		 * Instantiation des attributs
		 */
		positionInitX = new SimpleDoubleProperty(0);
		positionInitY = new SimpleDoubleProperty(0);
		positionFinX = new SimpleDoubleProperty(0);
		positionFinY = new SimpleDoubleProperty(0);
		variationPositionX = new SimpleDoubleProperty(0);
		variationPositionY = new SimpleDoubleProperty(0);
		vitesseInit = new SimpleDoubleProperty(vitesseInitialeVectorielle);
		vitesseInitX = new SimpleDoubleProperty(0);
		vitesseInitY = new SimpleDoubleProperty(0);
		vitesseFin = new SimpleDoubleProperty(0);
		vitesseFinX = new SimpleDoubleProperty(0);
		vitesseFinY = new SimpleDoubleProperty(0);
		variationVitesse = new SimpleDoubleProperty(0);
		variationVitesseX = new SimpleDoubleProperty(0);
		variationVitesseY = new SimpleDoubleProperty(0);
		champGravitationnel = new SimpleDoubleProperty(
				ACCELERATION_GRAVITATIONNELLE_TERRE);
		tempsInit = new SimpleDoubleProperty(0);
		tempsFin = new SimpleDoubleProperty(0);
		variationTemps = new SimpleDoubleProperty(0);

		Bindings.bindBidirectional(tempsFin, variationTemps);

		angleInit = new SimpleDoubleProperty(0);
		angleFin = new SimpleDoubleProperty(0);
		acceleration = new SimpleDoubleProperty(0);
		accelerationX = new SimpleDoubleProperty(0);
		accelerationY = new SimpleDoubleProperty(
				ACCELERATION_GRAVITATIONNELLE_TERRE);
		vitesseMoyenneX = new SimpleDoubleProperty(0);
		vitesseMoyenneY = new SimpleDoubleProperty(0);

		// Simple binding qui calcule automatiquement la variation du temps

	}

	public EquationsProjectile(double vitesseInitialeVectorielle,
			double accelerationY)
	{

		/*
		 * Instantiation des attributs
		 */
		positionInitX = new SimpleDoubleProperty(0);
		positionInitY = new SimpleDoubleProperty(0);
		positionFinX = new SimpleDoubleProperty(0);
		positionFinY = new SimpleDoubleProperty(0);
		variationPositionX = new SimpleDoubleProperty(0);
		variationPositionY = new SimpleDoubleProperty(0);
		vitesseInit = new SimpleDoubleProperty(vitesseInitialeVectorielle);
		vitesseInitX = new SimpleDoubleProperty(0);
		vitesseInitY = new SimpleDoubleProperty(0);
		vitesseFin = new SimpleDoubleProperty(0);
		vitesseFinX = new SimpleDoubleProperty(0);
		vitesseFinY = new SimpleDoubleProperty(0);
		variationVitesse = new SimpleDoubleProperty(0);
		variationVitesseX = new SimpleDoubleProperty(0);
		variationVitesseY = new SimpleDoubleProperty(0);
		champGravitationnel = new SimpleDoubleProperty(
				ACCELERATION_GRAVITATIONNELLE_TERRE);
		tempsInit = new SimpleDoubleProperty(0);
		tempsFin = new SimpleDoubleProperty(0);
		variationTemps = new SimpleDoubleProperty(0);

		Bindings.bindBidirectional(tempsFin, variationTemps);

		angleInit = new SimpleDoubleProperty(0);
		angleFin = new SimpleDoubleProperty(0);
		acceleration = new SimpleDoubleProperty(0);
		accelerationX = new SimpleDoubleProperty(0);
		this.accelerationY = new SimpleDoubleProperty(accelerationY);
		vitesseMoyenneX = new SimpleDoubleProperty(0);
		vitesseMoyenneY = new SimpleDoubleProperty(0);
	}

	/**
	 * Cette méthode calcule la position finale d'un projectile, en X. Elle
	 * utilise la position initiale, la vitesse initiale et la variation du
	 * temps.
	 * 
	 * @return La position finale du projectile en X.
	 */
	public double calculerPositionFinX()
	{
		positionFinX.set(positionInitX.getValue()
				+ vitesseInitX.getValue() * variationTemps.getValue());
		return positionFinX.get();
	}

	/**
	 * Cette méthode calcule la position finale d'un projectile, en Y. Elle
	 * utilise la position initiale, la vitesse initiale et la variation du
	 * temps.
	 * 
	 * @return La position finale du projectile en Y.
	 */
	public double calculerPositionFinY()
	{
		positionFinY.set(positionInitY.get()
				+ (vitesseInitY.get() * variationTemps.get())
				+ (0.5 * champGravitationnel.get()
						* Math.pow(getVariationTemps(), 2)));
		return positionFinY.get();
	}

	/**
	 * Cette méthode calcule la vitesse finale d'un projectile, en X. Elle
	 * n'utilise que la vitesse initiale.
	 * 
	 * @return La vitesse finale du projectile en X.
	 */
	public double calculerVitesseFinX()
	{
		vitesseFinX.set(vitesseInitX.get());
		return vitesseFinX.get();
	}

	/**
	 * Cette méthode calcule la vitesse finale d'un projectile, en Y. Elle
	 * utilise la vitesse initiale, le champ gravitationnel et la variation du
	 * temps.
	 * 
	 * @return La vitesse finale du projectile en Y.
	 */
	public double calculerVitesseFinY()
	{
		vitesseFinY.set(vitesseInitY.get()
				+ champGravitationnel.get() * variationTemps.get());
		return vitesseFinY.get();
	}

	/**
	 * Cette méthode calcule la vitesse moyenne d'un projectile, en X. Elle
	 * utilise la variation de position et la variation du temps
	 * 
	 * @return La vitesse moyenne du projectile en X.
	 */
	public double calculerVitesseMoyenneX()
	{
		vitesseMoyenneX.set(variationPositionX.get() / variationTemps.get());
		return vitesseMoyenneX.get();
	}

	/**
	 * Cette méthode calcule la vitesse moyenne d'un projectile, en Y. Elle
	 * utilise la variation de position et la variation du temps
	 * 
	 * @return La vitesse moyenne du projectile en Y.
	 */
	public double calculerVitesseMoyenneY()
	{
		vitesseMoyenneY.set(variationPositionY.get() / variationTemps.get());
		return vitesseMoyenneY.get();
	}

	/**
	 * Cette méthode calcule la vitesse finale en X ou en Y d'un projectile
	 * grâce au temps. Elle utilise la vitesse initiale, l'accélération et la
	 * variation du temps.
	 * 
	 * @param String xY La string comprenant l'axe sur lequel on souhaite
	 *            travailler (X ou Y)
	 * @return La vitesse finale du projectile en X ou en Y ou NaN si la String
	 *         ne contient pas X ou Y.
	 */
	public double calculerVitesseFinTemps(String xY)
	{
		switch (xY.toUpperCase())
		{
			case "X":
			{
				vitesseFinX.set(vitesseInitX.get());
				return vitesseFinX.get();
			}
			case "Y":
			{
				vitesseFinY.set(vitesseInitY.get()
						+ accelerationY.get() * variationTemps.get());
				return vitesseFinY.get();
			}
			default:
				break;
		}
		return Double.NaN;
	}

	/**
	 * Cette méthode calcule la vitesse à un temps donné en X ou en Y d'un
	 * projectile grâce au temps. Elle utilise la vitesse initiale,
	 * l'accélération et le temps donné.
	 * 
	 * @param String xY La string comprenant l'axe sur lequel on souhaite
	 *            travailler (X ou Y)
	 * @param tempsDonne Le temps pour lequel nous voulons avoir la vitesse en X
	 *            ou Y.
	 * @return La vitesse à un temps donné du projectile en X ou en Y ou NaN si
	 *         la String ne contient pas X ou Y.
	 */
	public double calculerVitesseTempsDonne(String xY, double tempsDonne)
	{
		switch (xY.toUpperCase())
		{
			case "X":
			{
				return vitesseInitX.get() + accelerationX.get() * tempsDonne;
			}
			case "Y":
			{
				return vitesseInitY.get() + accelerationY.get() * tempsDonne;
			}
			default:
				break;
		}
		return Double.NaN;
	}

	/**
	 * Cette méthode calcule la vitesse vectorielle à un temps donné d'un
	 * projectile. Elle utilise la vitesse initiale, l'accélération, le temps
	 * donné et le théorème de Pythagore.
	 * 
	 * @param tempsDonne : Le temps pour lequel nous voulons avoir la vitesse
	 *            vectorielle.
	 * @return la vitesse vectorielle
	 */
	public double calculerVitesseVectorielleTempsDonne(double tempsDonne)
	{
		return Math.sqrt(Math.pow(calculerVitesseTempsDonne("x", tempsDonne), 2)
				+ Math.pow(calculerVitesseTempsDonne("y", tempsDonne), 2));
	}

	/**
	 * Cette méthode calcule le déplacement d'un projectile. Elle utilise la
	 * vitesse initiale, la vitesse finale et la variation du temps.
	 * 
	 * @param String xY La string comprenant l'axe sur lequel on souhaite
	 *            travailler (X ou Y)
	 * @return Le déplacement total du projectile en X ou Y ou NaN si la String
	 *         ne contient pas X ou Y.
	 */
	public double calculerDeplacement(String xY)
	{
		switch (xY.toUpperCase())
		{
			case "X":
			{
				variationPositionX.set(vitesseInitX.get()
						+ vitesseFinX.get() * variationTemps.get() / 2);
				return variationPositionX.get();
			}
			case "Y":
			{
				variationPositionY.set(((vitesseInitY.get() + vitesseFinY.get())
						* variationTemps.get()) / 2);
				return variationPositionY.get();
			}
			default:
				break;
		}
		return Double.NaN;
	}

	/**
	 * Cette méthode calcule la vitesse finale d'un projectile en se basant sur
	 * le déplacement. Elle utilise la vitesse initiale, l'accélération et la
	 * variation de position.
	 * 
	 * @param String xY La string comprenant l'axe sur lequel on souhaite
	 *            travailler (X ou Y)
	 * @return La vitesse finale du projectile en X ou Y ou NaN si la String ne
	 *         contient pas X ou Y.
	 */
	public double calculerVitesseFinDeplacement(String xY)
	{

		switch (xY.toUpperCase())
		{
			case "X":
			{
				vitesseFinX.set(Math.sqrt(Math.pow(vitesseInitX.get(), 2)
						+ 2 * accelerationX.get() * variationPositionX.get()));
				return vitesseFinX.get();

			}
			case "Y":
			{
				vitesseFinY.set(Math.sqrt(Math.pow(vitesseInitY.get(), 2)
						+ 2 * accelerationY.get() * variationPositionY.get()));
				return vitesseFinY.get();
			}

			default:
				break;
		}

		return Double.NaN;

	}

	/**
	 * Cette méthode calcule l'accélération d'un projectile en se basant sur la
	 * variation de vitesse. Elle utilise la variation de vitesse et la
	 * variation de temps.
	 * 
	 * @param String xY La string comprenant l'axe sur lequel on souhaite
	 *            travailler (X ou Y) ou "" lorsque l'on travaille avec
	 *            l'accélération combinée.
	 * @return L'accélération du projectile en X,Y ou celle combinée ou NaN si
	 *         la String ne contient pas X,Y ou "".
	 */
	public double calculerAcceleration(String xY)
	{
		switch (xY.toUpperCase())
		{
			case "X":
			{
				accelerationX
						.set(variationVitesseX.get() / variationTemps.get());
				return accelerationX.get();
			}
			case "Y":
			{
				accelerationY
						.set(variationVitesseY.get() / variationTemps.get());
				return accelerationY.get();
			}
			case "":
			{
				acceleration.set(variationVitesse.get() / variationTemps.get());
				return acceleration.get();
			}

		}
		return Double.NaN;
	}

	/**
	 * Cette méthode calcule le déplacement d'un projectile en se basant sur la
	 * vitesse initiale. Elle utilise la vitesse initiale, l'accélération et la
	 * variation du temps.
	 * 
	 * @param String xY La string comprenant l'axe sur lequel on souhaite
	 *            travailler (X ou Y)
	 * @return Le déplacement total du projectile en X ou Y ou NaN si la String
	 *         ne contient pas X ou Y.
	 */
	public double calculerPositionFinVitesseInitiale(String xY)
	{
		switch (xY.toUpperCase())
		{
			case "X":
			{
				positionFinX.set(positionInitX.get()
						+ vitesseInitX.get() * variationTemps.get());
				return variationPositionX.get();
			}
			case "Y":
			{
				positionFinY.set(positionInitY.get()
						+ vitesseInitY.get() * variationTemps.get()
						+ 0.5 * accelerationY.get()
								* Math.pow(variationTemps.get(), 2));
				return variationPositionY.get();
			}

		}
		return Double.NaN;
	}

	/**
	 * Cette méthode calcule le déplacement d'un projectile en se basant sur la
	 * vitesse finale. Elle utilise la vitesse finale, l'accélération et la
	 * variation du temps.
	 * 
	 * @param String xY La string comprenant l'axe sur lequel on souhaite
	 *            travailler (X ou Y)
	 * @return Le déplacement total du projectile en X ou Y ou NaN si la String
	 *         ne contient pas X ou Y.
	 */
	public double calculerPositionFinVitesseFinale(String xY)
	{
		switch (xY.toUpperCase())
		{
			case "X":
			{
				variationPositionX.set(vitesseFinX.get() * variationTemps.get()
						- 0.5 * accelerationX.get()
								* Math.pow(variationTemps.get(), 2));
				return variationPositionX.get();
			}
			case "Y":
			{
				variationPositionY.set(positionInitY.get()
						+ vitesseFinY.get() * variationTemps.get()
						- 0.5 * accelerationY.get()
								* Math.pow(variationTemps.get(), 2));
				return variationPositionY.get();
			}

		}
		return Double.NaN;
	}

	/**
	 * Calcule l'angle finale avec les vitesses finale en x et y.
	 * 
	 * @return L'angle final
	 */
	public double calculerAngleFin()
	{
		this.setAngleFin(Math.atan(
				Math.abs(this.vitesseFinY.get()) / this.vitesseFinX.get()));
		return this.angleFin.get();
	}

	/**
	 * Modifie la position initiale en X et en y avec les valeurs entrées en
	 * paramètres
	 * 
	 * @param positionFinX La position initiale en x
	 * @param positionFinY La position initiale en y
	 */
	public void setPositionInit(double positionInitX, double positionInitY)
	{
		this.positionInitX.set(positionInitX);
		this.positionInitY.set(positionInitY);
	}

	/**
	 * Retourne la position initiale en X
	 * 
	 * @return la position initiale en X
	 */
	public double getPositionInitX()
	{
		return positionInitX.get();
	}

	/**
	 * Modifie la position initiale en X par celle entrée en paramètre
	 * 
	 * @param positionInitX La nouvelle position initiale en X
	 */
	public void setPositionInitX(double positionInitX)
	{
		this.positionInitX.set(positionInitX);
		;
	}

	/**
	 * Retourne la position initiale en Y
	 * 
	 * @return la position initiale en Y
	 */
	public double getPositionInitY()
	{
		return positionInitY.get();
	}

	/**
	 * Modifie la position initiale en Y par celle entrée en paramètre
	 * 
	 * @param positionInitY La nouvelle position initiale en Y
	 */
	public void setPositionInitY(double positionInitY)
	{
		this.positionInitY.set(positionInitY);
		;
	}

	/**
	 * Modifie la position finale en X et en y avec les valeurs entrées en
	 * paramètres
	 * 
	 * @param positionFinX La position finale en x
	 * @param positionFinY La position finale en y
	 */
	public void setPositionFin(double positionFinX, double positionFinY)
	{
		this.positionFinX.set(positionFinX);
		this.positionFinY.set(positionFinY);
	}

	/**
	 * Retourne la position finale en X
	 * 
	 * @return la position finale en X
	 */
	public double getPositionFinX()
	{
		return positionFinX.get();
	}

	/**
	 * Modifie la position finale en X par celle entrée en paramètre
	 * 
	 * @param positionFinX La nouvelle position finale en X
	 */
	public void setPositionFinX(double positionFinX)
	{
		this.positionFinX.set(positionFinX);
		;
	}

	/**
	 * Retourne la position finale en Y
	 * 
	 * @return la position finale en Y
	 */
	public double getPositionFinY()
	{
		return positionFinY.get();
	}

	/**
	 * Modifie la position finale en Y par celle entrée en paramètre
	 * 
	 * @param positionFinX La nouvelle position finale en Y
	 */
	public void setPositionFinY(double positionFinY)
	{
		this.positionFinY.set(positionFinY);
		;
	}

	/**
	 * Retourne la vitesse initiale en X
	 * 
	 * @return la vitesse initiale en X
	 */
	public double getVitesseInitX()
	{
		return vitesseInitX.get();
	}

	/**
	 * Modifie la vitesse initiale en X par celle entrée en paramètre
	 * 
	 * @param vitesseInitX La nouvelle vitesse initiale en X
	 */
	public void setVitesseInitX(double vitesseInitX)
	{
		this.vitesseInitX.set(vitesseInitX);
		;
	}

	/**
	 * Retourne la vitesse initiale en Y
	 * 
	 * @return la vitesse initiale en Y
	 */
	public double getVitesseInitY()
	{
		return vitesseInitY.get();
	}

	/**
	 * Modifie la vitesse initiale en Y par celle entrée en paramètre
	 * 
	 * @param vitesseInitY La nouvelle vitesse initiale en Y
	 */
	public void setVitesseInitY(double vitesseInitY)
	{
		this.vitesseInitY.set(vitesseInitY);
	}

	/**
	 * Retourne la vitesse finale en X
	 * 
	 * @return la vitesse finale en X
	 */
	public double getVitesseFinX()
	{
		return vitesseFinX.get();
	}

	/**
	 * Modifie la vitesse finale en X par celle entrée en paramètre
	 * 
	 * @param vitesseFinX La nouvelle vitesse initiale en X
	 */
	public void setVitesseFinX(double vitesseFinX)
	{
		this.vitesseFinX.set(vitesseFinX);
		;
	}

	/**
	 * Retourne la vitesse finale en Y
	 * 
	 * @return la vitesse finale en Y
	 */
	public double getVitesseFinY()
	{
		return vitesseFinY.get();
	}

	/**
	 * Modifie la vitesse finale en Y par celle entrée en paramètre
	 * 
	 * @param vitesseFinY La nouvelle vitesse initiale en Y
	 */
	public void setVitesseFinY(double vitesseFinY)
	{
		this.vitesseFinY.set(vitesseFinY);
		;
	}

	/**
	 * Retourne l'intensité du champ gravitionnel
	 * 
	 * @return l'intensité du le champ gravitationnel
	 */
	public double getChampGravitationnel()
	{
		return champGravitationnel.get();
	}

	/**
	 * Retourne le temps de départ
	 * 
	 * @return le temps de départ
	 */
	public double getTempsInit()
	{
		return tempsInit.get();
	}

	/**
	 * Modifie le temps initial par celui entré en paramètre
	 * 
	 * @param tempsInit La nouveau temps initial
	 */
	public void setTempsInit(double tempsInit)
	{
		this.tempsInit.set(tempsInit);
		;
	}

	/**
	 * Retourne le temps de fin
	 * 
	 * @return le temps de fin
	 */
	public double getTempsFin()
	{
		return tempsFin.get();
	}

	/**
	 * Modifie le temps final par celui entré en paramètre
	 * 
	 * @param tempsFin La nouveau temps final
	 */
	public void setTempsFin(double tempsFin)
	{
		this.tempsFin.set(tempsFin);
	}

	/**
	 * Retourne la variation de temps
	 * 
	 * @return la variation de temps
	 */
	public double getVariationTemps()
	{
		return variationTemps.get();
	}

	/**
	 * Modifie la variation du temps par celle entrée en paramètre
	 * 
	 * @param variationTemps La nouvelle variation du temps
	 */
	public void setVariationTemps(double variationTemps)
	{
		if (variationTemps >= 0)
		{
			this.variationTemps.set(variationTemps);
			this.tempsInit.set(0);
			this.tempsFin.set(variationTemps);
		}
	}

	/**
	 * Retourne l'angle initial
	 * 
	 * @return l'angle initial
	 */
	public double getAngleInit()
	{
		return angleInit.get();
	}

	/**
	 * Modifie l'angle initial par celui entré en paramètre
	 * 
	 * @param d Le nouvel angle initial
	 */
	public void setAngleInit(double angleInit)
	{
		this.angleInit.set(angleInit);
	}

	/**
	 * Retourne l'angle final
	 * 
	 * @return l'angle final
	 */
	public double getAngleFin()
	{
		return angleFin.get();
	}

	/**
	 * Modifie l'angle final par celui entré en paramètre
	 * 
	 * @param angleFin Le nouvel angle final
	 */
	public void setAngleFin(double angleFin)
	{
		this.angleFin.set(angleFin);
	}

	/**
	 * Retourne l'accélération combinée
	 * 
	 * @return l'accélération combinée
	 */
	public double getAcceleration()
	{
		return acceleration.get();
	}

	/**
	 * Modifie l'accélération combinée par celle entrée en paramètre
	 * 
	 * @param acceleration La nouvelle accélération combinée
	 */
	public void setAcceleration(double acceleration)
	{
		this.acceleration.set(acceleration);
		;
	}

	/**
	 * Retourne la vitesse initiale
	 * 
	 * @return la vitesse initiale
	 */
	public double getVitesseInit()
	{
		return vitesseInit.get();
	}

	/**
	 * Modifie la vitesse initiale combinée par celle entrée en paramètre
	 * 
	 * @param vitesseInit La nouvelle vitesse initiale combinée
	 */
	public void setVitesseInit(double vitesseInit)
	{
		this.vitesseInit.set(vitesseInit);
		;
	}

	/**
	 * Retourne la vitesse finale
	 * 
	 * @return la vitesse finale
	 */
	public double getVitesseFin()
	{
		return vitesseFin.get();
	}

	/**
	 * Modifie la vitesse finale combinée par celle entrée en paramètre
	 * 
	 * @param vitesseFin La nouvelle vitesse finale combinée
	 */
	public void setVitesseFin(double vitesseFin)
	{
		this.vitesseFin.set(vitesseFin);
		;
	}

	/**
	 * Retourne la variation de la position en X
	 * 
	 * @return la variation de la position en X
	 */
	public double getVariationPositionX()
	{
		return variationPositionX.get();
	}

	/**
	 * Modifie la variation de position en X par celle entrée en paramètre
	 * 
	 * @param variationPositionX La nouvelle variation de position en X
	 */
	public void setVariationPositionX(double variationPositionX)
	{
		this.variationPositionX.set(variationPositionX);
		;
	}

	/**
	 * Retourne la variation de la position en Y
	 * 
	 * @return la variation de la position en Y
	 */
	public double getVariationPositionY()
	{
		return variationPositionY.get();
	}

	/**
	 * Modifie la variation de position en Y par celle entrée en paramètre
	 * 
	 * @param variationPositionY La nouvelle variation de position en Y
	 */
	public void setVariationPositionY(double variationPositionY)
	{
		this.variationPositionY.set(variationPositionY);
		;
	}

	/**
	 * Retourne l'accélération en X
	 * 
	 * @return l'accélération en X
	 */
	public double getAccelerationX()
	{
		return accelerationX.get();
	}

	/**
	 * Modifie l'accélération en X par celle entrée en paramètre
	 * 
	 * @param accelerationX La nouvelle accélération en X
	 */
	public void setAccelerationX(double accelerationX)
	{
		this.accelerationX.set(accelerationX);
	}

	/**
	 * Retourne l'accélération en Y
	 * 
	 * @return l'accélération en Y
	 */
	public double getAccelerationY()
	{
		return accelerationY.get();
	}

	/**
	 * Modifie l'accélération en Y par celle entrée en paramètre
	 * 
	 * @param accelerationY La nouvelle accélération en Y
	 */
	public void setAccelerationY(double accelerationY)
	{
		this.accelerationY.set(accelerationY);
	}

	/**
	 * Retourne la vitesse moyenne en X
	 * 
	 * @return la vitesse moyenne en X
	 */
	public double getVitesseMoyenneX()
	{
		return vitesseMoyenneX.get();
	}

	/**
	 * Modifie la vitesse moyenne en X par celle entrée en paramètre
	 * 
	 * @param vitesseMoyenneX La nouvelle vitesse moyenne en X
	 */
	public void setVitesseMoyenneX(double vitesseMoyenneX)
	{
		this.vitesseMoyenneX.set(vitesseMoyenneX);
		;
	}

	/**
	 * Retourne la vitesse moyenne en Y
	 * 
	 * @return la vitesse moyenne en Y
	 */
	public double getVitesseMoyenneY()
	{
		return vitesseMoyenneY.get();
	}

	/**
	 * Modifie la vitesse moyenne en Y par celle entrée en paramètre
	 * 
	 * @param vitesseMoyenneY La nouvelle vitesse moyenne en Y
	 */
	public void setVitesseMoyenneY(double vitesseMoyenneY)
	{
		this.vitesseMoyenneY.set(vitesseMoyenneY);
		;
	}

	/**
	 * Modifie l'intensité du champ gravitationnel par celui entré en paramètre
	 * 
	 * @param champGravitationnel L'intensité du nouveau champ gravitationnel
	 */
	public void setChampGravitationnel(double champGravitationnelle)
	{
		this.champGravitationnel.set(champGravitationnelle);
		;
	}

	/**
	 * Retourne la variation de vitesse en X
	 * 
	 * @return la variation de vitesse en X
	 */
	public double getVariationVitesseX()
	{
		return variationVitesseX.get();
	}

	/**
	 * Modifie la variation de vitesse en X par celle entrée en paramètre
	 * 
	 * @param variationVitesseX La nouvelle variation de vitesse en X
	 */
	public void setVariationVitesseX(double variationVitesseX)
	{
		this.variationVitesseX.set(variationVitesseX);
		;
	}

	/**
	 * Retourne la variation de vitesse en Y
	 * 
	 * @return la variation de vitesse en Y
	 */
	public double getVariationVitesseY()
	{
		return variationVitesseY.get();
	}

	/**
	 * Modifie la variation de vitesse en Y par celle entrée en paramètre
	 * 
	 * @param variationVitesseY La nouvelle variation de vitesse en Y
	 */
	public void setVariationVitesseY(double variationVitesseY)
	{
		this.variationVitesseY.set(variationVitesseY);
		;
	}

	/**
	 * Retourne la variation de vitesse combinée
	 * 
	 * @return la variation de vitesse combinée
	 */
	public double getVariationVitesse()
	{
		return variationVitesse.get();
	}

	/**
	 * Modifie la variation de vitesse combinée par celle entrée en paramètre
	 * 
	 * @param variationVitesse La nouvelle variation de vitesse combinée
	 */
	public void setVariationVitesse(double variationVitesse)
	{
		this.variationVitesse.set(variationVitesse);
		;
	}

	/**
	 * Calcule la vitesse initiale en x avec la vitesse vectorielle et l'angle
	 * initial.
	 */
	public void calculerVitesseInitXAvecAngle()
	{
		this.vitesseInitX.set(vitesseInit.get() * Math.cos(angleInit.get()));
	}

	/**
	 * Calcule la vitesse initiale en y avec la vitesse vectorielle et l'angle
	 * initial.
	 */
	public void calculerVitesseInitYAvecAngle()
	{
		this.vitesseInitY.set(Math.sin(angleInit.get()) * vitesseInit.get());
	}

	/**
	 * Appelle les méthodes calculerVitesseInitXAvecAngle et
	 * calculerVitesseInitYAvecAngle
	 */
	public void calculerVitessesInitAvecAngle()
	{
		calculerVitesseInitXAvecAngle();
		calculerVitesseInitYAvecAngle();
	}

	/**
	 * Calcule le temps selon la vitesse initiale, la position initiale, la
	 * position finale et l'acceleration en y
	 */
	public void calculerTempsY()
	{

		double tempsMIN = (-vitesseInitY.get() - Math.sqrt(
				Math.pow(vitesseInitY.get(), 2) - 4 * 0.5 * accelerationY.get()
						* (positionInitY.get() - positionFinY.get())))
				/ (2 * 0.5 * accelerationY.get());
		double tempsMAX = (-vitesseInitY.get() + Math.sqrt(
				Math.pow(vitesseInitY.get(), 2) - 4 * 0.5 * accelerationY.get()
						* (positionInitY.get() - positionFinY.get())))
				/ (2 * 0.5 * accelerationY.get());

		variationTemps.set(Math.max(tempsMAX, tempsMIN));
	}

	/**
	 * Calcule et retourne le temps selon la position finale, la position
	 * initiale et la vitesse initiale en x.
	 * 
	 * @param positionFinX La position finale en x
	 * @param positionInitX La position initiale en x
	 * @param vitesseInitX La vitesse initiale en x
	 * @return Le temps calculé
	 */
	public double calculerTempsX(double positionFinX, double positionInitX,
			double vitesseInitX)
	{
		return ((positionFinX - positionInitX) / vitesseInitX);
	}

	public double calculerTempsVitesseY(double vitesseTemps, double vitesseInit)
	{
		return ((vitesseTemps - vitesseInit)
				/ ACCELERATION_GRAVITATIONNELLE_TERRE);
	}

	public void calculerAngleAvec2Points(Point2D pointDebut, Point2D pointFin)
	{
		/*
		 * En degré
		 */
		// angleInit.set(Math.atan(Math.abs((pointFin.getY() -
		// pointDebut.getY())/(pointFin.getX() - pointDebut.getX()))) *
		// (180/Math.PI));

		/*
		 * En radian
		 */

		angleInit.set(Math.abs(Math.atan((pointFin.getY() - pointDebut.getY())
				/ (pointFin.getX() - pointDebut.getX()))));
		if (angleInit.get() < 0)
		{
			System.out.println("Il y a une vitesse négative en y");
		}
	}
}
