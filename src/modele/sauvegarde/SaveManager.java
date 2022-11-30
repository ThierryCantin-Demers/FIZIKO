package modele.sauvegarde;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe permettant de sauvegarder la partie de l'utilisateur dans le fichier
 * dernierePartie.sauvegarde
 * 
 * @author Thierry Cantin-Demers, Alexandre Khuong, Nicolas St-Laurent, Tommy
 *         Audet
 *
 */
public class SaveManager
{
	/**
	 * Méthode qui enregistre l'état de la partie dans un fichier
	 * (dernierePartie.sauvegarde)
	 * 
	 * @param etatDuJeu : état du jeu que nous voulons enregistrer dans un
	 *            fichier
	 * @return vrai si aucun problème n'a été renctonré lors de l'enregistrement
	 *         de la partie
	 * @throws IOException si un problème est survenu lors de la fermeture du
	 *             fichier
	 */
	public static boolean saveGame(GameState etatDuJeu) throws IOException
	{
		boolean succes = true;

		ObjectOutputStream out = null;

		try
		{
			out = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(
							"ressources/sauvegardes/dernierePartie.sauvegarde")));
			out.writeObject(etatDuJeu);
		}
		catch (IOException i)
		{
			succes = false;
		}
		finally
		{
			if (out != null)
				out.close();
		}

		return succes;

	}

	/**
	 * Charge la dernière partie qui a été jouée (dernierePartie.sauvegarde)
	 * 
	 * @return l'état
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static GameState loadLastGame() throws IOException
	{
		ObjectInputStream fichierObjet = null;

		GameState gameState = null;

		try
		{
			fichierObjet = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(
							"ressources/sauvegardes/dernierePartie.sauvegarde")));

			gameState = (GameState) fichierObjet.readObject();
		}
		catch (FileNotFoundException e)
		{
			gameState = null;
		}
		catch (IOException e)
		{
			System.out.println(
					"Un problème est survenu lors du chargement de la dernière partie.");

			gameState = null;
		}
		catch (Exception e)
		{
			System.out.println("Un problème inconnu est survenu.");

			gameState = null;
		}
		finally
		{
			if (fichierObjet != null)
			{
				fichierObjet.close();
			}
		}

		return gameState;
	}
}
