package testJade;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.HashMap;
import java.util.Map;

public class AgentDeDonnees extends Agent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Integer> evaluationsUtilisateurs = new HashMap<>();

    protected void setup() {
        System.out.println("Agent de Données " + getAID().getName() + " prêt.");

        // Ajouter un comportement pour traiter les requêtes et les évaluations
        addBehaviour(new TraitementRequetesEtEvaluationsBehaviour());
    }

    private class TraitementRequetesEtEvaluationsBehaviour extends CyclicBehaviour {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void action() {
            // Logique pour traiter les requêtes et les évaluations
            ACLMessage message = receive(); // Attendre un message

            if (message != null) {
                String contenuMessage = message.getContent();

                if (message.getPerformative() == ACLMessage.REQUEST) {
                    // Traitement d'une requête
                    if (contenuMessage.equals("REQUETE_EVALUATIONS")) {
                        envoyerEvaluations(message.getSender());
                    }
                } else if (message.getPerformative() == ACLMessage.INFORM) {
                    // Traitement d'une évaluation
                    if (contenuMessage.startsWith("EVALUATION")) {
                        traiterEvaluation(contenuMessage);
                    }
                }
            }
        }

        private void envoyerEvaluations(AID destinataire) {
            // Créer un message ACL pour envoyer les évaluations
            ACLMessage message = new ACLMessage(ACLMessage.INFORM);
            message.addReceiver(destinataire);
            message.setContent("EVALUATIONS|" + evaluationsUtilisateurs);

            // Envoyer le message
            send(message);
        }

        private void traiterEvaluation(String contenuMessage) {
            // Logique pour traiter une évaluation reçue
            String[] parties = contenuMessage.split("\\|");
            if (parties.length == 3 && parties[0].equals("EVALUATION")) {
                String element = parties[1];
                int evaluation = Integer.parseInt(parties[2]);

                // Stocker l'évaluation
                evaluationsUtilisateurs.put(element, evaluation);
            }
        }
    }
}
