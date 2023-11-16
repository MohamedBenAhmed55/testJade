package testJade;
import java.util.HashMap;
import java.util.Map;

public class BaseDeDonnees {

    private static Map<String, Integer> evaluationsUtilisateurs = new HashMap<>();

    public static void stockerEvaluation(String element, int evaluation) {
        // Stocker l'évaluation dans la base de données
        evaluationsUtilisateurs.put(element, evaluation);
    }

    public static Map<String, Integer> getEvaluationsUtilisateurs() {
        // Récupérer toutes les évaluations stockées dans la base de données
        return new HashMap<>(evaluationsUtilisateurs);
    }
}
