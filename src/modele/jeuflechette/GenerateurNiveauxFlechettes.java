package modele.jeuflechette;

import java.util.ArrayList;
import java.util.List;

import modele.jeuflechette.obstacles.Caisse;
import modele.jeuflechette.obstacles.Cible;
import modele.jeuflechette.obstacles.Lampe;
import modele.jeuflechette.obstacles.Obstacle;
import modele.moteurdephysique.EquationsProjectile;
import modele.moteurdephysique.Projectile;

/**
 * Classe permettant de générer les niveaux du jeu de fléchettes
 * 
 * @author Thierry Cantin-Demers, Alexandre Khuong, Tommy Audet et Nicolas
 *         St-Laurent
 *
 */
public class GenerateurNiveauxFlechettes
{
	/**
	 * Le nombre de niveaux du jeu de fléchettes
	 */
	private static final int NBR_NIVEAUX = 10;

	/**
	 * La liste des niveaux du jeu de fléchettes
	 */
	private List<NiveauFlechettes> niveaux;

	/**
	 * La hauteur dans la scène où on peut afficher des objets
	 */
	private static final double HAUTEUR_SCENE = 1017;

	/**
	 * La largeur dans la scène où on peut afficher des objets
	 */
	private static final double LARGEUR_SCENE = 1920;

	/**
	 * Méthode qui génère tous les niveaux du jeu de fléchettes en créant chaque
	 * niveau avec sa méthode correspondante (ex. genererNiveau1() pour le
	 * premier niveau)
	 */
	public void genererNiveaux()
	{
		niveaux = new ArrayList<NiveauFlechettes>();
		niveaux.add(genererNiveau1());
		niveaux.add(genererNiveau2());
		niveaux.add(genererNiveau3());
		niveaux.add(genererNiveau4());
		niveaux.add(genererNiveau5());
		niveaux.add(genererNiveau6());
		niveaux.add(genererNiveau7());
		niveaux.add(genererNiveau8());
		niveaux.add(genererNiveau9());
		niveaux.add(genererNiveau10());
	}

	/**
	 * Méthode qui génère le premier niveau
	 * 
	 * @return le premier niveau
	 */
	private static NiveauFlechettes genererNiveau1()
	{
		/*
		 * Le chiffre en paramètre est la vitesse initiale du niveau actuel
		 */
		EquationsProjectile donneesNiveau = new EquationsProjectile(75);

		Projectile projectile = new Projectile();

		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

		// Création de la cible
		Cible cible = new Cible();

		cible.setX(1824);
		cible.setY(497);

		// Ajoute tous les obstacles à la liste d'obstacle
		obstacles.add(cible);

		NiveauFlechettes nv = new NiveauFlechettes(donneesNiveau, projectile,
				obstacles);

		nv.getPersonnage().setPosition(80, 550);

		donneesNiveau.setPositionInit(
				nv.getPersonnage().getPointDeLancement().getX(), HAUTEUR_SCENE
						- nv.getPersonnage().getPointDeLancement().getY());

		nv.setNumero(1);
		return nv;
	}

	/**
	 * Méthode qui génère le deuxième niveau
	 * 
	 * @return le deuxième niveau
	 */
	private static NiveauFlechettes genererNiveau2()
	{
		EquationsProjectile donneesNiveau = new EquationsProjectile(70);

		Projectile projectile = new Projectile();

		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

		// Création de la cible
		Cible cible = new Cible();

		cible.setScale(1);

		cible.setX(1700);
		cible.setY(650);

		// Création de la caisse
		Caisse caisse1 = new Caisse();

		caisse1.setScale(8.5);

		caisse1.setX(720);
		caisse1.setY(HAUTEUR_SCENE - caisse1.getFitHeight());

		// Création de la lampe
		Lampe lampe = new Lampe();

		lampe.setScale(3.25);

		lampe.setX(850);
		lampe.setY(0);

		// Ajoute tous les obstacles à la liste d'obstacle
		obstacles.add(cible);
		obstacles.add(caisse1);
		obstacles.add(lampe);

		NiveauFlechettes nv = new NiveauFlechettes(donneesNiveau, projectile,
				obstacles);

		nv.getPersonnage().setPosition(80, 550);

		donneesNiveau.setPositionInit(
				nv.getPersonnage().getPointDeLancement().getX(), HAUTEUR_SCENE
						- nv.getPersonnage().getPointDeLancement().getY());

		nv.setNumero(2);
		return nv;
	}

	/**
	 * Méthode qui génère le troisième niveau
	 * 
	 * @return le troisième niveau
	 */
	private static NiveauFlechettes genererNiveau3()
	{
		EquationsProjectile donneesNiveau = new EquationsProjectile(65);

		Projectile projectile = new Projectile();

		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

		// Création de la cible
		Cible cible = new Cible();

		cible.setScale(1.3945);

		cible.setX(1400);
		cible.setY(600);

		cible.setRotate(45);

		// Création des caisses
		Caisse caisse1 = new Caisse();

		caisse1.setScale(9);

		caisse1.setX(325);
		caisse1.setY(HAUTEUR_SCENE - caisse1.getFitHeight());

		// 2
		Caisse caisse2 = new Caisse();

		caisse2.setScale(9);

		caisse2.setX(caisse1.getX() + caisse1.getFitWidth());
		caisse2.setY(HAUTEUR_SCENE - caisse2.getFitHeight());

		// Création des lampes
		Lampe lampe = new Lampe();

		lampe.setScale(4);

		lampe.setX(600);
		lampe.setY(-150);

		// 2
		Lampe lampe2 = new Lampe();

		lampe2.setScale(4);

		lampe2.setX(1200);
		lampe2.setY(-100);

		// Ajoute tous les obstacles à la liste d'obstacle
		obstacles.add(cible);
		obstacles.add(caisse1);
		obstacles.add(caisse2);
		obstacles.add(lampe);
		obstacles.add(lampe2);

		NiveauFlechettes nv = new NiveauFlechettes(donneesNiveau, projectile,
				obstacles);

		nv.getPersonnage().setPosition(80, 550);

		donneesNiveau.setPositionInit(
				nv.getPersonnage().getPointDeLancement().getX(), HAUTEUR_SCENE
						- nv.getPersonnage().getPointDeLancement().getY());

		nv.setNumero(3);
		return nv;
	}

	/**
	 * Méthode qui génère le quatrième niveau
	 * 
	 * @return le quatrième niveau
	 */
	private static NiveauFlechettes genererNiveau4()
	{
		EquationsProjectile donneesNiveau = new EquationsProjectile(90);
		Projectile projectile = new Projectile();

		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

		// Création de la cible
		Cible cible = new Cible();

		cible.setScale(1);

		cible.setX(1100);
		cible.setY(730);

		cible.setRotate(90);

		// Création des caisses
		Caisse caisse1 = new Caisse();

		caisse1.setScale(9);

		caisse1.setX(400);
		caisse1.setY(HAUTEUR_SCENE - caisse1.getFitHeight());

		// 2
		Caisse caisse2 = new Caisse();

		caisse2.setScale(9);

		caisse2.setX(caisse1.getX());
		caisse2.setY(HAUTEUR_SCENE - caisse1.getFitHeight()
				- caisse1.getFitHeight());

		// Ajoute tous les obstacles à la liste d'obstacle
		obstacles.add(cible);
		obstacles.add(caisse1);
		obstacles.add(caisse2);

		NiveauFlechettes nv = new NiveauFlechettes(donneesNiveau, projectile,
				obstacles);

		nv.getPersonnage().setPosition(80, 550);

		donneesNiveau.setPositionInit(
				nv.getPersonnage().getPointDeLancement().getX(), HAUTEUR_SCENE
						- nv.getPersonnage().getPointDeLancement().getY());

		nv.setNumero(4);
		return nv;

	}

	/**
	 * Méthode qui génère le cinquième niveau
	 * 
	 * @return le cinquième niveau
	 */
	private static NiveauFlechettes genererNiveau5()
	{
		EquationsProjectile donneesNiveau = new EquationsProjectile(70);

		Projectile projectile = new Projectile();

		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

		// Création de la cible
		Cible cible = new Cible();

		cible.setScale(1.5);

		cible.setX(1500);
		cible.setY(730);

		cible.setRotate(90);

		// Création des caisses
		Caisse caisse1 = new Caisse();

		caisse1.setScale(6);

		caisse1.setX(600);
		caisse1.setY(HAUTEUR_SCENE - caisse1.getFitHeight());

		// 2
		Caisse caisse2 = new Caisse();

		caisse2.setScale(6);

		caisse2.setX(caisse1.getX() + caisse1.getFitWidth());
		caisse2.setY(HAUTEUR_SCENE - caisse2.getFitHeight());

		// 3
		Caisse caisse3 = new Caisse();

		caisse3.setScale(6);

		caisse3.setX(caisse2.getX() - caisse2.getFitWidth() / 2);
		caisse3.setY(HAUTEUR_SCENE - caisse1.getFitHeight()
				- caisse3.getFitHeight());

		// Création des lampes
		Lampe lampe = new Lampe();

		lampe.setScale(3);

		lampe.setX(500);
		lampe.setY(0);

		// 2
		Lampe lampe2 = new Lampe();

		lampe2.setScale(3);

		lampe2.setX(1100);
		lampe2.setY(0);

		// Ajoute tous les obstacles à la liste d'obstacle
		obstacles.add(cible);
		obstacles.add(caisse1);
		obstacles.add(caisse2);
		obstacles.add(caisse3);
		obstacles.add(lampe);
		obstacles.add(lampe2);

		NiveauFlechettes nv = new NiveauFlechettes(donneesNiveau, projectile,
				obstacles);

		nv.getPersonnage().setPosition(80, 550);

		donneesNiveau.setPositionInit(
				nv.getPersonnage().getPointDeLancement().getX(), HAUTEUR_SCENE
						- nv.getPersonnage().getPointDeLancement().getY());

		nv.setNumero(5);
		return nv;
	}

	/**
	 * Méthode qui génère le sixième niveau
	 * 
	 * @return le sixième niveau
	 */
	private static NiveauFlechettes genererNiveau6()
	{
		EquationsProjectile donneesNiveau = new EquationsProjectile(70);

		Projectile projectile = new Projectile();

		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

		// Création de la cible
		Cible cible = new Cible();

		cible.setScale(1);

		cible.setX(1500);
		cible.setY(730);

		cible.setRotate(45);

		// Création des caisses
		Caisse caisse1 = new Caisse();

		caisse1.setScale(5.5);

		caisse1.setX(600);
		caisse1.setY(HAUTEUR_SCENE - caisse1.getFitHeight());

		// 2
		Caisse caisse2 = new Caisse();

		caisse2.setScale(5.5);

		caisse2.setX(caisse1.getX());
		caisse2.setY(HAUTEUR_SCENE - caisse2.getFitHeight()
				- caisse1.getFitHeight());

		// 3
		Caisse caisse3 = new Caisse();

		caisse3.setScale(5.75);

		caisse3.setX(caisse2.getX() + caisse1.getFitWidth() + 50);
		caisse3.setY(HAUTEUR_SCENE - caisse3.getFitHeight());

		// Création de la lampe
		Lampe lampe = new Lampe();

		lampe.setScale(3.25);

		lampe.setX(700);
		lampe.setY(0);

		// Ajoute tous les obstacles à la liste d'obstacle
		obstacles.add(cible);
		obstacles.add(caisse1);
		obstacles.add(caisse2);
		obstacles.add(caisse3);
		obstacles.add(lampe);

		NiveauFlechettes nv = new NiveauFlechettes(donneesNiveau, projectile,
				obstacles);

		nv.getPersonnage().setPosition(80, 550);

		donneesNiveau.setPositionInit(
				nv.getPersonnage().getPointDeLancement().getX(), HAUTEUR_SCENE
						- nv.getPersonnage().getPointDeLancement().getY());

		nv.setNumero(6);
		return nv;
	}

	/**
	 * Méthode qui génère le septième niveau
	 * 
	 * @return le septième niveau
	 */
	private static NiveauFlechettes genererNiveau7()
	{
		EquationsProjectile donneesNiveau = new EquationsProjectile(60);

		Projectile projectile = new Projectile();

		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

		// Création de la cible
		Cible cible = new Cible();

		cible.setScale(1.2);

		cible.setX(1150);
		cible.setY(800);

		cible.setRotate(90);

		// Création des caisses
		Caisse caisse1 = new Caisse();

		caisse1.setScale(5);

		caisse1.setX(600);
		caisse1.setY(HAUTEUR_SCENE - caisse1.getFitHeight());

		// 2
		Caisse caisse2 = new Caisse();

		caisse2.setScale(5);

		caisse2.setX(caisse1.getX());
		caisse2.setY(HAUTEUR_SCENE - caisse2.getFitHeight()
				- caisse1.getFitHeight());

		// 3
		Caisse caisse3 = new Caisse();

		caisse3.setScale(5);

		caisse3.setX(caisse2.getX() + caisse1.getFitWidth());
		caisse3.setY(HAUTEUR_SCENE - caisse3.getFitHeight());

		// 4
		Caisse caisse4 = new Caisse();

		caisse4.setScale(5);

		caisse4.setX(caisse3.getX() + caisse3.getFitWidth() + 250);
		caisse4.setY(HAUTEUR_SCENE - caisse1.getFitHeight());

		// 5
		Caisse caisse5 = new Caisse();

		caisse5.setScale(5);

		caisse5.setX(caisse4.getX() + caisse4.getFitWidth());
		caisse5.setY(HAUTEUR_SCENE - caisse5.getFitHeight());

		// 6
		Caisse caisse6 = new Caisse();

		caisse6.setScale(5);

		caisse6.setX(caisse5.getX());
		caisse6.setY(HAUTEUR_SCENE - caisse6.getFitHeight()
				- caisse5.getFitHeight());

		// Création de la lampe
		Lampe lampe = new Lampe();

		lampe.setScale(3.25);

		lampe.setX(400);
		lampe.setY(0);

		// Ajoute tous les obstacles à la liste d'obstacle
		obstacles.add(cible);
		obstacles.add(caisse1);
		obstacles.add(caisse2);
		obstacles.add(caisse3);
		obstacles.add(caisse4);
		obstacles.add(caisse5);
		obstacles.add(caisse6);
		obstacles.add(lampe);

		NiveauFlechettes nv = new NiveauFlechettes(donneesNiveau, projectile,
				obstacles);

		nv.getPersonnage().setPosition(80, 550);

		donneesNiveau.setPositionInit(
				nv.getPersonnage().getPointDeLancement().getX(), HAUTEUR_SCENE
						- nv.getPersonnage().getPointDeLancement().getY());

		nv.setNumero(7);
		return nv;
	}

	/**
	 * Méthode qui génère le huitième niveau
	 * 
	 * @return le huitième niveau
	 */
	private static NiveauFlechettes genererNiveau8()
	{
		EquationsProjectile donneesNiveau = new EquationsProjectile(70);

		Projectile projectile = new Projectile();

		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

		// Création de la cible
		Cible cible = new Cible();

		cible.setScale(1);

		cible.setX(1800);
		cible.setY(500);

		// Création des caisses
		Caisse caisse1 = new Caisse();

		caisse1.setScale(3.5);

		caisse1.setX(500);
		caisse1.setY(HAUTEUR_SCENE - caisse1.getFitHeight());

		// 2
		Caisse caisse2 = new Caisse();

		caisse2.setScale(3.5);

		caisse2.setX(caisse1.getX() + caisse1.getFitHeight());
		caisse2.setY(HAUTEUR_SCENE - caisse2.getFitHeight());

		// 3
		Caisse caisse3 = new Caisse();

		caisse3.setScale(3.5);

		caisse3.setX(caisse2.getX() + caisse2.getFitHeight());
		caisse3.setY(HAUTEUR_SCENE - caisse3.getFitHeight());

		// 4
		Caisse caisse4 = new Caisse();

		caisse4.setScale(3.5);

		caisse4.setX(caisse3.getX() + caisse3.getFitHeight());
		caisse4.setY(HAUTEUR_SCENE - caisse4.getFitHeight());

		// 5
		Caisse caisse5 = new Caisse();

		caisse5.setScale(3.5);

		caisse5.setX(caisse4.getX() + caisse4.getFitHeight());
		caisse5.setY(HAUTEUR_SCENE - caisse5.getFitHeight());

		// 6
		Caisse caisse6 = new Caisse();

		caisse6.setScale(3.5);

		caisse6.setX(caisse5.getX() + caisse5.getFitHeight());
		caisse6.setY(HAUTEUR_SCENE - caisse6.getFitHeight());

		// 7
		Caisse caisse21 = new Caisse();

		caisse21.setScale(3.5);

		caisse21.setX(caisse2.getX());
		caisse21.setY(HAUTEUR_SCENE - caisse2.getFitHeight()
				- caisse21.getFitHeight());

		// 8
		Caisse caisse31 = new Caisse();

		caisse31.setScale(3.5);

		caisse31.setX(caisse3.getX());
		caisse31.setY(HAUTEUR_SCENE - caisse3.getFitHeight()
				- caisse31.getFitHeight());

		// 9
		Caisse caisse41 = new Caisse();

		caisse41.setScale(3.5);

		caisse41.setX(caisse4.getX());
		caisse41.setY(HAUTEUR_SCENE - caisse4.getFitHeight()
				- caisse41.getFitHeight());

		// 10
		Caisse caisse51 = new Caisse();

		caisse51.setScale(3.5);

		caisse51.setX(caisse5.getX());
		caisse51.setY(HAUTEUR_SCENE - caisse5.getFitHeight()
				- caisse51.getFitHeight());

		// 11
		Caisse caisse32 = new Caisse();

		caisse32.setScale(3.5);

		caisse32.setX(caisse3.getX());
		caisse32.setY(HAUTEUR_SCENE - caisse3.getFitHeight()
				- caisse31.getFitHeight() - caisse32.getFitHeight());

		// 12
		Caisse caisse42 = new Caisse();

		caisse42.setScale(3.5);

		caisse42.setX(caisse4.getX());
		caisse42.setY(HAUTEUR_SCENE - caisse4.getFitHeight()
				- caisse41.getFitHeight() - caisse42.getFitHeight());

		// 13
		Caisse caisse52 = new Caisse();

		caisse52.setScale(3.5);

		caisse52.setX(caisse5.getX());
		caisse52.setY(HAUTEUR_SCENE - caisse5.getFitHeight()
				- caisse51.getFitHeight() - caisse52.getFitHeight());

		// Création des lampes
		Lampe lampe = new Lampe();

		lampe.setScale(3.25);

		lampe.setX(400);
		lampe.setY(150);

		// 2
		Lampe lampe2 = new Lampe();

		lampe2.setScale(3.25);

		lampe2.setX(lampe.getX() + 150);
		lampe2.setY(100);
		// 3
		Lampe lampe3 = new Lampe();

		lampe3.setScale(3.25);

		lampe3.setX(lampe2.getX() + 150);
		lampe3.setY(50);

		// 4
		Lampe lampe4 = new Lampe();

		lampe4.setScale(3.25);

		lampe4.setX(lampe3.getX() + 150);
		lampe4.setY(0);

		// Ajoute tous les obstacles à la liste d'obstacle
		obstacles.add(cible);

		obstacles.add(caisse1);

		obstacles.add(caisse2);
		obstacles.add(caisse21);

		obstacles.add(caisse3);
		obstacles.add(caisse31);
		obstacles.add(caisse32);

		obstacles.add(caisse4);
		obstacles.add(caisse41);
		obstacles.add(caisse42);

		obstacles.add(caisse5);
		obstacles.add(caisse51);
		obstacles.add(caisse52);

		obstacles.add(caisse6);

		obstacles.add(lampe);
		obstacles.add(lampe2);
		obstacles.add(lampe3);
		obstacles.add(lampe4);

		NiveauFlechettes nv = new NiveauFlechettes(donneesNiveau, projectile,
				obstacles);

		nv.getPersonnage().setPosition(80, 650);

		donneesNiveau.setPositionInit(
				nv.getPersonnage().getPointDeLancement().getX(), HAUTEUR_SCENE
						- nv.getPersonnage().getPointDeLancement().getY());

		nv.setNumero(8);
		return nv;
	}

	/**
	 * Méthode qui génère le neuvième niveau
	 * 
	 * @return le neuvième niveau
	 */
	private static NiveauFlechettes genererNiveau9()
	{
		EquationsProjectile donneesNiveau = new EquationsProjectile(150, -50);

		Projectile projectile = new Projectile();

		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

		// Création de la cible
		Cible cible = new Cible();

		cible.setScale(1);

		cible.setX(1500);
		cible.setY(730);

		cible.setRotate(90);

		// Création des caisses
		Caisse caisse1 = new Caisse();

		caisse1.setScale(3.2);

		caisse1.setX(600);
		caisse1.setY(HAUTEUR_SCENE - caisse1.getFitHeight() - 30);

		// 2
		Caisse caisse2 = new Caisse();

		caisse2.setScale(3.2);

		caisse2.setX(caisse1.getX() + caisse2.getFitHeight());
		caisse2.setY(HAUTEUR_SCENE - caisse2.getFitHeight() - 30);

		// 3
		Caisse caisse21 = new Caisse();

		caisse21.setScale(3.2);

		caisse21.setX(caisse2.getX());
		caisse21.setY(caisse2.getY() - caisse21.getFitHeight());

		// 4
		Caisse caisse22 = new Caisse();

		caisse22.setScale(3.2);

		caisse22.setX(caisse2.getX());
		caisse22.setY(caisse21.getY() - caisse22.getFitHeight());

		// 5
		Caisse caisse23 = new Caisse();

		caisse23.setScale(3.2);

		caisse23.setX(caisse2.getX());
		caisse23.setY(caisse22.getY() - caisse23.getFitHeight());

		// 6
		Caisse caisse24 = new Caisse();

		caisse24.setScale(3.2);

		caisse24.setX(caisse2.getX());
		caisse24.setY(caisse23.getY() - caisse24.getFitHeight());

		// 7
		Caisse caisse3 = new Caisse();

		caisse3.setScale(3.2);

		caisse3.setX(caisse2.getX() + caisse3.getFitWidth());
		caisse3.setY(HAUTEUR_SCENE - caisse3.getFitHeight() - 30);

		// 8
		Caisse caisse31 = new Caisse();

		caisse31.setScale(3.2);

		caisse31.setX(caisse3.getX());
		caisse31.setY(caisse3.getY() - caisse31.getFitHeight());

		// 9
		Caisse caisse32 = new Caisse();

		caisse32.setScale(3.2);

		caisse32.setX(caisse3.getX());
		caisse32.setY(caisse31.getY() - caisse32.getFitHeight());

		// 10
		Caisse caisse33 = new Caisse();

		caisse33.setScale(3.2);

		caisse33.setX(caisse3.getX());
		caisse33.setY(caisse32.getY() - caisse33.getFitHeight());

		// Ajoute tous les obstacles à la liste d'obstacle
		obstacles.add(cible);

		obstacles.add(caisse1);

		obstacles.add(caisse2);
		obstacles.add(caisse21);
		obstacles.add(caisse22);
		obstacles.add(caisse23);
		obstacles.add(caisse24);

		obstacles.add(caisse3);
		obstacles.add(caisse31);
		obstacles.add(caisse32);
		obstacles.add(caisse33);

		NiveauFlechettes nv = new NiveauFlechettes(donneesNiveau, projectile,
				obstacles);

		nv.getPersonnage().setPosition(80, 550);

		donneesNiveau.setPositionInit(
				nv.getPersonnage().getPointDeLancement().getX(), HAUTEUR_SCENE
						- nv.getPersonnage().getPointDeLancement().getY());

		nv.setNumero(9);
		return nv;
	}

	/**
	 * Méthode qui génère le dixième niveau
	 * 
	 * @return le dixième niveau
	 */
	private static NiveauFlechettes genererNiveau10()
	{
		EquationsProjectile donneesNiveau = new EquationsProjectile(150, -45);

		Projectile projectile = new Projectile();

		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

		// Création de la cible
		Cible cible = new Cible();

		cible.setScale(1);

		cible.setX(1500);
		cible.setY(800);
		cible.setRotate(90);

		// Création des caisses
		Caisse caisse1 = new Caisse();

		caisse1.setScale(3.5);

		caisse1.setX(400);
		caisse1.setY(HAUTEUR_SCENE - caisse1.getFitHeight());

		// 2
		Caisse caisse2 = new Caisse();

		caisse2.setScale(3.5);

		caisse2.setX(caisse1.getX() + caisse1.getFitHeight());
		caisse2.setY(HAUTEUR_SCENE - caisse2.getFitHeight());

		// 3
		Caisse caisse3 = new Caisse();

		caisse3.setScale(3.5);

		caisse3.setX(caisse2.getX() + caisse2.getFitHeight());
		caisse3.setY(HAUTEUR_SCENE - caisse3.getFitHeight());

		// 4
		Caisse caisse4 = new Caisse();

		caisse4.setScale(3.5);

		caisse4.setX(caisse3.getX() + caisse3.getFitHeight());
		// caisse1.setY(782 - caisse1.getFitHeight());
		caisse4.setY(HAUTEUR_SCENE - caisse4.getFitHeight());

		// 5
		Caisse caisse5 = new Caisse();

		caisse5.setScale(3.5);

		caisse5.setX(caisse4.getX() + caisse4.getFitHeight());
		caisse5.setY(HAUTEUR_SCENE - caisse5.getFitHeight());

		// 6
		Caisse caisse6 = new Caisse();

		caisse6.setScale(3.5);

		caisse6.setX(caisse5.getX() + caisse5.getFitHeight());
		caisse6.setY(HAUTEUR_SCENE - caisse6.getFitHeight());

		// 7
		Caisse caisse21 = new Caisse();

		caisse21.setScale(3.5);

		caisse21.setX(caisse2.getX());
		caisse21.setY(HAUTEUR_SCENE - caisse2.getFitHeight()
				- caisse21.getFitHeight());

		// 8
		Caisse caisse31 = new Caisse();

		caisse31.setScale(3.5);

		caisse31.setX(caisse3.getX());
		caisse31.setY(HAUTEUR_SCENE - caisse3.getFitHeight()
				- caisse31.getFitHeight());

		// 9
		Caisse caisse41 = new Caisse();

		caisse41.setScale(3.5);

		caisse41.setX(caisse4.getX());
		caisse41.setY(HAUTEUR_SCENE - caisse4.getFitHeight()
				- caisse41.getFitHeight());

		// 10
		Caisse caisse51 = new Caisse();

		caisse51.setScale(3.5);

		caisse51.setX(caisse5.getX());
		caisse51.setY(HAUTEUR_SCENE - caisse5.getFitHeight()
				- caisse51.getFitHeight());

		// 11
		Caisse caisse22 = new Caisse();

		caisse22.setScale(3.5);

		caisse22.setX(caisse2.getX());
		caisse22.setY(HAUTEUR_SCENE - caisse2.getFitHeight()
				- caisse21.getFitHeight() - caisse22.getFitHeight());

		// 12
		Caisse caisse32 = new Caisse();

		caisse32.setScale(3.5);

		caisse32.setX(caisse3.getX());
		caisse32.setY(HAUTEUR_SCENE - caisse3.getFitHeight()
				- caisse31.getFitHeight() - caisse32.getFitHeight());

		// 13
		Caisse caisse42 = new Caisse();

		caisse42.setScale(3.5);

		caisse42.setX(caisse4.getX());
		caisse42.setY(HAUTEUR_SCENE - caisse4.getFitHeight()
				- caisse41.getFitHeight() - caisse42.getFitHeight());

		// 14
		Caisse caisse33 = new Caisse();

		caisse33.setScale(3.5);

		caisse33.setX(caisse3.getX());
		caisse33.setY(
				HAUTEUR_SCENE - caisse3.getFitHeight() - caisse31.getFitHeight()
						- caisse32.getFitHeight() - caisse33.getFitHeight());

		// Création des lampes
		Lampe lampe = new Lampe();

		lampe.setScale(3.25);

		lampe.setX(200);
		lampe.setY(0);

		// 2
		Lampe lampe2 = new Lampe();

		lampe2.setScale(3.25);

		lampe2.setX(1200);
		lampe2.setY(0);

		// 3
		Lampe lampe3 = new Lampe();

		lampe3.setScale(3.25);

		lampe3.setX(1400);
		lampe3.setY(0);

		// Ajoute tous les obstacles à la liste d'obstacle
		obstacles.add(cible);

		obstacles.add(caisse1);

		obstacles.add(caisse2);
		obstacles.add(caisse21);
		obstacles.add(caisse22);

		obstacles.add(caisse3);
		obstacles.add(caisse31);
		obstacles.add(caisse32);
		obstacles.add(caisse33);

		obstacles.add(caisse4);
		obstacles.add(caisse41);
		obstacles.add(caisse42);

		obstacles.add(caisse5);
		obstacles.add(caisse51);

		obstacles.add(caisse6);

		obstacles.add(lampe);

		obstacles.add(lampe2);
		obstacles.add(lampe3);

		NiveauFlechettes nv = new NiveauFlechettes(donneesNiveau, projectile,
				obstacles);

		nv.getPersonnage().setPosition(80, 550);

		donneesNiveau.setPositionInit(
				nv.getPersonnage().getPointDeLancement().getX(), HAUTEUR_SCENE
						- nv.getPersonnage().getPointDeLancement().getY());

		nv.setNumero(10);
		return nv;
	}

	/**
	 * Génère le niveau x et le retourne, x ayant un valeur de 1 < x <
	 * NBR_NIVEAUX, et le retourne
	 * 
	 * @param x : le numéro du niveau que nous voulons générer
	 * @return le niveau associé au numéro donné en paramètre
	 */
	public static NiveauFlechettes genererNiveauX(int x)
	{
		NiveauFlechettes nv = null;

		switch (x)
		{
			case 1:
				nv = genererNiveau1();
				break;
			case 2:
				nv = genererNiveau2();
				break;
			case 3:
				nv = genererNiveau3();
				break;
			case 4:
				nv = genererNiveau4();
				break;
			case 5:
				nv = genererNiveau5();
				break;
			case 6:
				nv = genererNiveau6();
				break;
			case 7:
				nv = genererNiveau7();
				break;
			case 8:
				nv = genererNiveau8();
				break;
			case 9:
				nv = genererNiveau9();
				break;
			case 10:
				nv = genererNiveau10();
				break;
		}

		return nv;
	}

	/**
	 * Retourne la liste des niveaux du jeu de fléchettes
	 * 
	 * @return la liste des niveaux du jeu de fléchettes
	 */
	public List<NiveauFlechettes> getNiveaux()
	{
		return this.niveaux;
	}

	/**
	 * Retourne le nombre de niveaux du jeu de fléchettes
	 * 
	 * @return le nombre de niveaux du jeu de fléchettes
	 */
	public static int getNbrNiveaux()
	{
		return GenerateurNiveauxFlechettes.NBR_NIVEAUX;
	}
}
