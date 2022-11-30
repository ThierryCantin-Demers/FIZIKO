package controller;

import java.util.ArrayList;
import java.util.List;

import application.SceneManager;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.util.Duration;
import modele.controller.Controller;
import modele.jeuflechette.GenerateurNiveauxFlechettes;
import modele.jeuflechette.NiveauFlechettes;
import modele.jeuflechette.Personnage;
import modele.jeuflechette.obstacles.Obstacle;
import modele.sauvegarde.SaveManager;

/**
 * Contrôleur de la scène du jeu de fléchettes
 * 
 * @author Thierry Cantin-Demers, Alexandre Khuong, Tommy Audet et Nicolas
 *         St-Laurent
 */
public class JeuFlechettesController implements Controller
{

	/**
	 * Label qui affiche le temps de l'animation à l'écran
	 */
	@FXML
	private Label labelTemps;

	/**
	 * La root de la scène
	 */
	@FXML
	private Pane root;

	/**
	 * Le gestionnaire de scène de la fenêtre principale
	 */
	private SceneManager sceneManager;

	/**
	 * La transition de fade du bouton retour
	 */
	private FadeTransition retourBoutonFadeTransition;

	/**
	 * Hauteur de la scène à utiliser pôur placer des objets dans la scène
	 */
	private static final int HAUTEUR_SCENE = 1017;

	/**
	 * Largeur de la scène à utiliser pôur placer des objets dans la scène
	 */
	private static final int LARGEUR_SCENE = 1920;

	/**
	 * Le rayon maximal de la ligne de mire
	 */
	private static final double RAYON_MIRE = 480;
	/**
	 * Image foncé du bouton retour qui apparait en animation lorsque le bouton
	 * retour est cliqué
	 */
	@FXML
	private ImageView boutonRetourDark;

	/**
	 * EventHandler qui écoute les changements de la position de la souris pour
	 * afficher, entre autres, la ligne de mire
	 */
	private EventHandler<MouseEvent> ligneDeMireEventHandler;

	/**
	 * EventHandler qui écoute les les appuis sur les touches de clavier pour
	 * calculer la trajectoire du dard et qui le lance
	 */
	private EventHandler<KeyEvent> lancementEspaceEventHandler;

	/**
	 * Liste contenant tous les objets qui ont étés ajoutés sur la scène pour le
	 * niveau et qui devront être enlevés lorqu'on le quittera
	 */
	private List<Node> elementsAjoutes;

	/**
	 * Point (x,y) qui représente la poisition actuelle de la souris
	 */
	private Point2D positionSouris;

	/**
	 * ListView contenant les informations du niveau actuel
	 */
	@FXML
	private ListView<String> listView;

	/**
	 * Listener qui écoute les changements dans de la propriété de temps de
	 * l'animation pour recalculer la vitesse automatiquement et l'afficher dans
	 * la ListView
	 */
	private ChangeListener<Duration> tempsAnimationListener;

	/**
	 * Le niveau auquel nous jouons actuellement
	 */
	private NiveauFlechettes niveauActuel;

	/**
	 * La vitesse initiale actuelle qui dépend de la distance entre le point de
	 * lancement et la souris
	 */
	private double vitesseInitialeActuelle;

	/**
	 * Image foncée du bouton retour de l'écran de victoire
	 */
	@FXML
	private ImageView boutonRetourDark2;

	/**
	 * Image foncée du bouton pour accéder au prochain niveau de l'écran de
	 * victoire
	 */
	@FXML
	private ImageView boutonProchainNiveauDark;

	/**
	 * Image du bouton pour accéder au prochain niveau de l'écran de victoire
	 */
	@FXML
	private ImageView boutonProchainNiveau;

	/**
	 * Image foncée du bouton retour de l'écran de quand le joueur a perdu
	 */
	@FXML
	private ImageView boutonRetourDark3;

	/**
	 * Image foncée du bouton retour pour le jeu de fléchettes
	 */
	@FXML
	private ImageView boutonRejouerDark;

	/**
	 * Pane qui contient les éléments de l'écran de victoire
	 */
	@FXML
	private Pane paneVictoire;

	/**
	 * Pane qui contient les éléments de l'écran de quand le joueur a perdu
	 */
	@FXML
	private Pane panePerdu;

	/**
	 * Détermine si le dard a été lancé
	 */
	private boolean lance;

	/**
	 * Représente le numéro du niveau qui est à l'écran
	 */
	private int numero;

	/**
	 * La liste des niveaux du jeu de fléchettes
	 */
	private List<NiveauFlechettes> niveaux;

	/**
	 * Le temps actuel de l'animation du dard
	 */
	private double tempsActuel;

	/**
	 * Méthode qui permet de retourner au menu de choix de niveaux
	 * 
	 * @param event : lorsque le bouton retour est cliqué
	 */
	@FXML
	void retourAuMenuChoixDeNiveaux(MouseEvent event)
	{
		retourBoutonFadeTransition.setNode(boutonRetourDark);
		retourBoutonFadeTransition.setOnFinished((e) -> {
			sceneManager.changeCurrentScene("Flechettes Niveaux Menu");
		});

		if (niveauActuel.getDonneesAnimation() != null
				&& niveauActuel.getDonneesAnimation().getAnimation() != null)
			niveauActuel.getDonneesAnimation().getAnimation().stop();

		retourBoutonFadeTransition.play();

		tempsActuel = 0;
	}

	/**
	 * Méthode qui permet de retourner au menu de choix de niveaux de l'écran de
	 * victoire
	 * 
	 * @param event : lorsque le bouton retour est cliqué
	 */
	@FXML
	void retourAuMenuChoixDeNiveaux2(MouseEvent event)
	{
		retourBoutonFadeTransition.setNode(boutonRetourDark2);
		retourBoutonFadeTransition.setOnFinished((e) -> {
			sceneManager.changeCurrentScene("Flechettes Niveaux Menu");
		});
		retourBoutonFadeTransition.play();
	}

	/**
	 * Méthode qui permet de retourner au menu de choix de niveaux de l'écran de
	 * quand le joueur a perdu
	 * 
	 * @param event : lorsque le bouton retour est cliqué
	 */
	@FXML
	void retourAuMenuChoixDeNiveaux3(MouseEvent event)
	{
		retourBoutonFadeTransition.setNode(boutonRetourDark3);
		retourBoutonFadeTransition.setOnFinished((e) -> {
			sceneManager.changeCurrentScene("Flechettes Niveaux Menu");
		});
		retourBoutonFadeTransition.play();
	}

	/**
	 * Méthode qui apporte le joueur au prochain niveau, s'il y en a un prochain
	 * 
	 * @param event
	 */
	@FXML
	void prochainNiveau(MouseEvent event)
	{
		retourBoutonFadeTransition.setNode(boutonProchainNiveauDark);
		retourBoutonFadeTransition.setOnFinished((e) -> {
			resetMenu();
			System.out.println();

			if (numero < GenerateurNiveauxFlechettes.getNbrNiveaux())
			{
				this.numero += 1;
				initMenu();
			}

		});

		retourBoutonFadeTransition.play();
	}

	/**
	 * Méthode qui réinitialise la scène pour rejouer au niveau
	 * 
	 * @param event
	 */
	@FXML
	void rejouerNiveau(MouseEvent event)
	{
		retourBoutonFadeTransition.setNode(boutonRejouerDark);
		retourBoutonFadeTransition.setOnFinished((e) -> {
			resetMenu();
			initMenu();
		});

		retourBoutonFadeTransition.play();
	}

	/**
	 * Attribue à numero le numéro du niveau passé en paramètre
	 * 
	 * @param numero : le numéro que nous voulons garder dans le contrôleur
	 */
	public void setNumero(int numero)
	{
		this.numero = numero;
	}

	public void setNiveaux(List<NiveauFlechettes> niveaux)
	{
		this.niveaux = niveaux;
	}

	/**
	 * Initialise certaines propriétés lors du chargement du fichier fxml
	 */
	@FXML
	void initialize()
	{
		// Initialisation de la transisiton de fade pour le bouton retour
		retourBoutonFadeTransition = new FadeTransition(new Duration(400));

		retourBoutonFadeTransition.setFromValue(0);
		retourBoutonFadeTransition.setToValue(1);

		elementsAjoutes = new ArrayList<Node>();

		positionSouris = new Point2D(0, 0);

		root.getStylesheets().add("/css/tableview.css");

		listView.setMouseTransparent(true);

		labelTemps.setTextFill(Color.BLACK);
		labelTemps.setText("0 ms");

	}

	/**
	 * Méthode qui charge les attributs du niveau donné en paramètre pour jouer
	 * à ce niveau
	 * 
	 * @param niveau : le niveau auquel l'utilisateur jouera
	 */
	public void jouerNiveau(NiveauFlechettes niveau)
	{
		if (niveau != null)
		{
			niveauActuel = niveau;
			vitesseInitialeActuelle = niveau.getDonneesNiveau()
					.getVitesseInit();

			niveau.etatDuNiveauProperty().addListener((a, o, n) -> {

				niveau.getDonneesAnimation().getAnimation()
						.currentTimeProperty()
						.removeListener(tempsAnimationListener);

				listView.getItems().set(3,
						"Vitesse actuelle : "
								+ Math.round(niveau.getDonneesNiveau()
										.calculerVitesseVectorielleTempsDonne(
												tempsActuel))
								+ " px/s");

				if (n.equals("gagné"))
				{

					// Sauve la progression de la partie
					try
					{
						sceneManager.getCurrentGameState()
								.getNiveauxFlechettesCompletion()
								.set(niveau.getNumero() - 1, true);
						SaveManager
								.saveGame(sceneManager.getCurrentGameState());
					}
					catch (Exception e)
					{
						System.out.println(e.getMessage());
					}

					paneVictoire.toFront();
					listView.toFront();
					labelTemps.toFront();
					paneVictoire.setVisible(true);

					// Cache le bouton pour aller au prochain niveau si on est
					// au dernier
					if (numero >= GenerateurNiveauxFlechettes.getNbrNiveaux())
					{
						boutonProchainNiveauDark.setVisible(false);
						boutonProchainNiveau.setVisible(false);
					}
				}
				else
					if (n.equals("perdu"))
					{
						panePerdu.toFront();
						listView.toFront();
						labelTemps.toFront();
						panePerdu.setVisible(true);

					}

				niveau.etatDuNiveauProperty().set("");
			});

			// Ajoute tous les obstacles à l'écran
			for (Obstacle obstacle : niveau.getObstacles())
			{
				Node obs = (Node) obstacle;
				root.getChildren().add(obs);
				elementsAjoutes.add(obs);
			}

			// Ajoute le personnage à l'écran
			Personnage personnage = niveau.getPersonnage();

			root.getChildren().add(personnage);
			elementsAjoutes.add(personnage);

			// root.addEventFilter(MouseEvent.MOUSE_PRESSED,
			// new EventHandler<MouseEvent>()
			// {
			// @Override
			// public void handle(MouseEvent mouseEvent)
			// {
			// System.out.println("x = " + mouseEvent.getSceneX()
			// + "\ny = " + mouseEvent.getSceneY());
			// }
			// });

			// Ajoute une ligne de mire pour le dard
			Point2D pointDeLancement = niveau.getPersonnage()
					.getPointDeLancement();

			Line ligneDeMire = new Line();
			ligneDeMire.setFill(Color.RED);
			ligneDeMire.setStrokeWidth(5.0);
			ligneDeMire.setStroke(Color.RED);
			ligneDeMire.getStrokeDashArray().addAll(25d);

			ligneDeMire.setMouseTransparent(true);

			root.getChildren().add(ligneDeMire);
			elementsAjoutes.add(ligneDeMire);

			Arc arc = new Arc();
			arc.setCenterX(pointDeLancement.getX());
			arc.setCenterY(pointDeLancement.getY());
			arc.setRadiusX(70);
			arc.setRadiusY(70);
			arc.setStartAngle(0);
			arc.setLength(0);
			arc.setType(ArcType.OPEN);
			arc.setStroke(Color.RED);
			arc.setStrokeWidth(4);
			arc.setFill(null);

			root.getChildren().add(arc);
			elementsAjoutes.add(arc);

			// Ajoute les informations à la ListView
			listView.getItems().add("Niveau " + niveau.getNumero());
			listView.getItems().add("Accélération gravitationelle : "
					+ niveau.getDonneesNiveau().getAccelerationY() + " px/s²");
			listView.getItems().add("Vitesse initiale : "
					+ niveau.getDonneesNiveau().getVitesseInit() + " px/s");
			listView.getItems().add("Vitesse actuelle : " + 0 + " px/s");
			listView.getItems().add("Angle de lancement : "
					+ niveau.getDonneesNiveau().getAngleInit() + " degrés");

			listView.setStyle("-fx-background-color : black;");

			ligneDeMireEventHandler = new EventHandler<MouseEvent>()
			{
				@Override
				public void handle(MouseEvent event)
				{

					Point2D pointLancementVers = new Point2D(
							Math.max(event.getX(), pointDeLancement.getX()),
							Math.min(event.getY(), pointDeLancement.getY()));

					/*
					 * On calcule l'angle entre le point de départ et le point
					 * du clic
					 */
					niveau.getDonneesNiveau().calculerAngleAvec2Points(
							pointDeLancement, pointLancementVers);

					double x = event.getX();
					double y = event.getY();

					if (niveau.getDonneesNiveau().getAngleInit() == Math.PI / 2
							&& (pointDeLancement.getY()
									- event.getY()) > RAYON_MIRE)
					{
						x = pointDeLancement.getX();
						y = pointDeLancement.getY() - RAYON_MIRE;
					}

					if ((event.getX() - pointDeLancement.getX())
							/ Math.cos(niveau.getDonneesNiveau()
									.getAngleInit()) > RAYON_MIRE)
					{
						x = Math.cos(niveau.getDonneesNiveau().getAngleInit())
								* RAYON_MIRE + pointDeLancement.getX();
						y = pointDeLancement.getY() - Math
								.sin(niveau.getDonneesNiveau().getAngleInit())
								* RAYON_MIRE;
					}

					positionSouris = new Point2D(
							Math.max(x, pointDeLancement.getX()),
							Math.min(y, pointDeLancement.getY()));

					ligneDeMire.setStartX(pointDeLancement.getX());
					ligneDeMire.setStartY(pointDeLancement.getY());

					ligneDeMire.setEndX(positionSouris.getX());
					ligneDeMire.setEndY(positionSouris.getY());

					arc.setLength(niveau.getDonneesNiveau().getAngleInit() * 180
							/ Math.PI);

					listView.getItems().set(4,
							"Angle de lancement : "
									+ Math.round(niveau.getDonneesNiveau()
											.getAngleInit() * 180 / Math.PI)
									+ " degrés");

					listView.getItems().set(2, "Vitesse initiale : " + Math
							.round(niveau.getDonneesNiveau().getVitesseInit() *

									(1 + Math.sqrt(Math.pow(
											ligneDeMire.getEndX()
													- ligneDeMire.getStartX(),
											2)
											+ Math.pow(ligneDeMire.getEndY()
													- ligneDeMire.getStartY(),
													2))
											/ 480))
							+ " px/s");

				}
			};

			root.addEventFilter(MouseEvent.MOUSE_MOVED,
					ligneDeMireEventHandler);

			root.getChildren()
					.add(niveau.getProjectile().getImageViewProjectile());
			// root.getChildren().add(niveau.getProjectile().getHitbox());

			niveau.getProjectile().getImageViewProjectile().setVisible(false);
			elementsAjoutes
					.add(niveau.getProjectile().getImageViewProjectile());
			// elementsAjoutes.add(niveau.getProjectile().getHitbox());

			lancementEspaceEventHandler = new EventHandler<KeyEvent>()
			{

				@Override
				public void handle(KeyEvent event)
				{
					/*
					 * On crée 3 points localement qui serviront seulement à
					 * stocker temporairement leur valeur.
					 */

					if (event.getCode() == KeyCode.SPACE && !lance
							&& !(positionSouris.getX() == pointDeLancement
									.getX()
									&& positionSouris.getY() == pointDeLancement
											.getY()))
					{
						lance = true;
						personnage.jouerAnimationLancement();

						root.removeEventFilter(MouseEvent.MOUSE_MOVED,
								ligneDeMireEventHandler);

						root.getChildren().remove(ligneDeMire);
						elementsAjoutes.remove(ligneDeMire);

						root.getChildren().remove(arc);
						elementsAjoutes.remove(arc);

						/*
						 * Ajustement de l'angle dans le cas où l'angle est de 0
						 * ou 90 degrés
						 */
						if (niveau.getDonneesNiveau().getAngleInit() == Math.PI
								/ 2)
						{
							niveau.getDonneesNiveau().setAngleInit(
									niveau.getDonneesNiveau().getAngleInit()
											- 0.00001);
						}
						else
							if (niveau.getDonneesNiveau().getAngleInit() == 0)
							{
								niveau.getDonneesNiveau().setAngleInit(
										niveau.getDonneesNiveau().getAngleInit()
												+ 0.00001);
							}

						/*
						 * On crée une QuadCurve qui sera la trajectoire du
						 * projectile
						 */
						QuadCurve trajectoire = new QuadCurve();
						Point2D p0 = new Point2D(pointDeLancement.getX(),
								pointDeLancement.getY());
						trajectoire.setStartX(pointDeLancement.getX());
						trajectoire.setStartY(pointDeLancement.getY());

						/**
						 * On set la position de départ au point de départ
						 */
						niveau.getDonneesNiveau().setPositionInit(
								pointDeLancement.getX(),
								(HAUTEUR_SCENE - pointDeLancement.getY()));

						/*
						 * On "scale" la vitesse initiale selon la longueur de
						 * la ligne de mire
						 */

						niveau.getDonneesNiveau().setVitesseInit(
								niveau.getDonneesNiveau().getVitesseInit() *

										(1 + Math.sqrt(Math
												.pow(ligneDeMire.getEndX()
														- ligneDeMire
																.getStartX(),
														2)
												+ Math.pow(ligneDeMire.getEndY()
														- ligneDeMire
																.getStartY(),
														2))
												/ 480));

						/*
						 * On calcule la vitesse initiale en x et en y
						 */
						niveau.getDonneesNiveau()
								.calculerVitessesInitAvecAngle();

						/*
						 * On calcule un nouveau temps à la hauteur max
						 * 
						 * (moitié)
						 */
						niveau.getDonneesNiveau().setVariationTemps(
								niveau.getDonneesNiveau().calculerTempsVitesseY(
										0, niveau.getDonneesNiveau()
												.getVitesseInitY()));
						double tempsMilieu = niveau.getDonneesNiveau()
								.getVariationTemps();

						/*
						 * On calcule le couple x,y au nouveau temps calculé
						 */

						niveau.getDonneesNiveau().setVariationTemps(
								niveau.getDonneesNiveau().calculerTempsVitesseY(
										0, niveau.getDonneesNiveau()
												.getVitesseInitY()));

						niveau.getDonneesNiveau()
								.calculerPositionFinVitesseInitiale("X");
						niveau.getDonneesNiveau()
								.calculerPositionFinVitesseInitiale("Y");

						/**
						 * On calcule le point maximal (en hauteur)
						 */
						Point2D pMax = new Point2D(
								niveau.getDonneesNiveau().getPositionFinX(),
								HAUTEUR_SCENE - niveau.getDonneesNiveau()
										.getPositionFinY());

						// trajectoire.setControlX(
						// niveau.getDonneesNiveau().getPositionFinX());
						// trajectoire.setControlY(HAUTEUR_SCENE
						// - niveau.getDonneesNiveau().getPositionFinY());

						/*
						 * On calcule le temps total de l'animation
						 */
						niveau.getDonneesNiveau().setPositionFinY(0);

						niveau.getDonneesNiveau().calculerTempsY();
						double tempsTotal = niveau.getDonneesNiveau()
								.getVariationTemps();

						/*
						 * On calcule la position finale de l'animation en Y et
						 * X
						 */
						niveau.getDonneesNiveau()
								.calculerPositionFinVitesseInitiale("Y");
						niveau.getDonneesNiveau()
								.calculerPositionFinVitesseInitiale("X");

						Point2D p2 = new Point2D(
								niveau.getDonneesNiveau().getPositionFinX(),
								HAUTEUR_SCENE);

						trajectoire.setEndX(
								niveau.getDonneesNiveau().getPositionFinX());
						trajectoire.setEndY(HAUTEUR_SCENE);

						/*
						 * On calcule le point de contrôle
						 */

						double p1x;
						double p1y;
						double t = tempsMilieu / tempsTotal;

						p1x = (pMax.getX() - (1 - t) * (1 - t) * p0.getX()
								- t * t * p2.getX()) / (2 * (1 - t) * t);
						p1y = (pMax.getY() - (1 - t) * (1 - t) * p0.getY()
								- t * t * p2.getY()) / (2 * (1 - t) * t);

						trajectoire.setControlX(p1x);
						trajectoire.setControlY(p1y);

						niveau.changerAnimation(trajectoire,
								niveau.getDonneesNiveau().getVariationTemps()
										* 0.1);
						niveau.getDonneesAnimation().deplacerProjectile();

						niveau.getDonneesNiveau()
								.setVitesseInit(vitesseInitialeActuelle);

						tempsAnimationListener = new ChangeListener<Duration>()
						{

							@Override
							public void changed(
									ObservableValue<? extends Duration> observable,
									Duration oldValue, Duration newValue)
							{
								listView.getItems().set(3, "Vitesse actuelle : "
										+ Math.round(niveau.getDonneesNiveau()
												.calculerVitesseVectorielleTempsDonne(
														newValue.toSeconds()))
										+ " px/s");

								if (newValue.toSeconds() != 0)
								{
									tempsActuel = newValue.toSeconds();
									labelTemps.setText(
											Math.round(newValue.toMillis())
													+ " ms");
								}
							}
						};

						niveau.getDonneesAnimation().getAnimation()
								.currentTimeProperty()
								.addListener(tempsAnimationListener);
					}
				}

			};

			root.addEventFilter(KeyEvent.KEY_PRESSED,
					lancementEspaceEventHandler);
			listView.toFront();
			labelTemps.toFront();
		}
	}

	/**
	 * Donne accès au gestionnaire de scènes
	 * 
	 * @param manager : le gestionnaire de scènes
	 */
	@Override
	public void setSceneManager(SceneManager manager)
	{
		this.sceneManager = manager;
	}

	/**
	 * Enlève tous les éléments qui ont été ajoutés à la scène pour
	 * réinitialiser la scène
	 */
	@Override
	public void resetMenu()
	{
		niveauActuel.removeCollisionListener();

		boutonRetourDark.setOpacity(0);
		boutonRetourDark2.setOpacity(0);
		boutonRetourDark3.setOpacity(0);
		boutonProchainNiveauDark.setOpacity(0);
		boutonRejouerDark.setOpacity(0);

		root.removeEventFilter(MouseEvent.MOUSE_MOVED, ligneDeMireEventHandler);
		root.removeEventFilter(KeyEvent.KEY_PRESSED,
				lancementEspaceEventHandler);

		root.getChildren().removeAll(elementsAjoutes);

		elementsAjoutes.clear();

		listView.getItems().clear();

		if (niveauActuel != null)
		{
			
			if (niveauActuel.getDonneesAnimation() != null && niveauActuel.getDonneesAnimation().getAnimation() != null)
			{
				niveauActuel.getDonneesAnimation().getAnimation()
						.currentTimeProperty()
						.removeListener(tempsAnimationListener);
			}

			niveauActuel = null;
		}

		tempsActuel = 0;

		labelTemps.setText("0 ms");

		paneVictoire.setVisible(false);

		boutonProchainNiveau.setVisible(true);

		boutonProchainNiveauDark.setVisible(true);

		panePerdu.setVisible(false);

		lance = false;
	}

	/**
	 * Lance un certain niveau lorsque nous arrivons sur cette scène
	 */
	@Override
	public void initMenu()
	{
		jouerNiveau(GenerateurNiveauxFlechettes.genererNiveauX(numero));

	}

}
