package modele.controller;

import application.SceneManager;

/**
 * Interface permettant de faire reconnaître une classe comme contrôleur
 * 
 * @author Nicolas St-Laurent, Alexandre Khuong, Tommy Audet et Thierry
 *         Cantin-Demers
 *
 */
public interface Controller
{
	public void setSceneManager(SceneManager manager);

	public void resetMenu();

	public void initMenu();
}
