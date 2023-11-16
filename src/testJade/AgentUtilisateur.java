package testJade;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

public class AgentUtilisateur extends Agent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void setup() {
        System.out.println("Agent Utilisateur " + getAID().getName() + " prêt.");

        // Ajouter un comportement pour émettre des évaluations et demander des recommandations
        addBehaviour(new EmissionEvaluationBehaviour());
        addBehaviour(new DemandeRecommandationBehaviour());
    }

    private class EmissionEvaluationBehaviour extends SimpleBehaviour {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void action() {
            // Logique pour émettre une évaluation
            String element = "Film123";
            int evaluation = 4;

            // Créer un message ACL pour l'évaluation
            ACLMessage message = new ACLMessage(ACLMessage.INFORM);
            message.addReceiver(getAgentDeDonneesAID());
            message.setContent("EVALUATION|" + element + "|" + evaluation);

            // Envoyer le message
            send(message);
        }

        public boolean done() {
            return true; // Ce comportement est exécuté une seule fois
        }
    }

    private class DemandeRecommandationBehaviour extends SimpleBehaviour {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void action() {
            // Logique pour demander des recommandations

            // Créer un message ACL pour demander des recommandations
            ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
            message.addReceiver(getAgentDeRecommandationAID());
            message.setContent("DEMANDE_RECOMMANDATION");

            // Envoyer le message
            send(message);
        }

        public boolean done() {
            return true; // Ce comportement est exécuté une seule fois
        }
    }

    private AID getAgentDeDonneesAID() {
        // Retourne l'AID de l'AgentDeDonnees (à implémenter en fonction de votre configuration)
        return new AID("AgentDeDonnees", AID.ISLOCALNAME);
    }

    private AID getAgentDeRecommandationAID() {
        // Retourne l'AID de l'AgentDeRecommandation (à implémenter en fonction de votre configuration)
        return new AID("AgentDeRecommandation", AID.ISLOCALNAME);
    }
}
