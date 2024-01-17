package fiziko.modele.sauvegarde;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe permet de contenir l'état de progression du jeu, donc l'état de
 * complétion des niveaux des trois jeux.
 *
 * @author Thierry Cantin-Demers, Alexandre Khuong, Nicolas St-Laurent, Tommy
 * Audet
 */
public class GameState implements Serializable {
    /**
     * UID de sérialisation
     */
    private static final long serialVersionUID = 956598872679862167L;

    /**
     * Détermine si l'état de jeu provient d'un fichier ou non
     */
    private boolean fromFile;

    /**
     * Liste contenant la valeur de complétion des niveaux du jeu de fléchettes
     */
    private List<Boolean> niveauxFlechettesCompletion;

    /**
     * Le nombre de niveaux
     */
    private int nbrNiveaux;

    /**
     * Crée un nouvel état du jeu en initialisant la complétion de chaque niveau
     * à false
     *
     * @throws IOException
     */
    public GameState() {
        nbrNiveaux = 10;

        niveauxFlechettesCompletion = new ArrayList<Boolean>();

        for (int i = 0; i < nbrNiveaux; i++) {
            niveauxFlechettesCompletion.add(false);
        }

    }

    /**
     * Change si l'état de jeu provient d'un fichier ou non
     *
     * @param fromFile : la provenance de l'état de jeu
     */
    public void setFromFile(boolean fromFile) {
        this.fromFile = fromFile;
    }

    /**
     * Retourne vrai si le l'état de jeu provient d'un fichier
     *
     * @return vrai si le l'état de jeu provient d'un fichier
     */
    public boolean getFromFile() {
        return fromFile;
    }

    /**
     * Retourne la liste de complétion des niveaux du jeu de fléchettes
     *
     * @return la liste de complétion des niveaux du jeu de fléchettes
     */
    public List<Boolean> getNiveauxFlechettesCompletion() {
        return this.niveauxFlechettesCompletion;
    }

    /**
     * Change la liste de complétion des niveaux du jeu fléchettes
     *
     * @param niveauxFlechettesCompletion : la nouvelle liste de complétion des
     *                                    niveaux du jeu fléchettes
     */
    public void setNiveauxFlechettesCompletion(
            List<Boolean> niveauxFlechettesCompletion) {
        this.niveauxFlechettesCompletion = niveauxFlechettesCompletion;
    }

}
