package testJade;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

public class AgentDeRecommandation extends Agent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void setup() {
        System.out.println("Agent de Recommandation " + getAID().getName() + " prêt.");

        // Ajouter un comportement pour traiter les demandes de recommandation
        addBehaviour(new TraitementDemandeRecommandationBehaviour());
    }

    private class TraitementDemandeRecommandationBehaviour extends SimpleBehaviour {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void action() {
            // Logique pour traiter les demandes de recommandation
            ACLMessage demandeMessage = receive(); // Attendre un message de demande de recommandation

            if (demandeMessage != null) {
                String contenuMessage = demandeMessage.getContent();

                if (contenuMessage.equals("DEMANDE_RECOMMANDATION")) {
                    // Traiter la demande et envoyer les recommandations
                    String recommandations = genererRecommandations();
                    envoyerRecommandations(demandeMessage.getSender(), recommandations);
                }
            }
        }

        public boolean done() {
            return false; // Ce comportement est exécuté en continu
        }

        private String genererRecommandations() {
            // Logique pour générer des recommandations (simulée ici)
            return "Film456, Livre789, ProduitXYZ";
        }

        private void envoyerRecommandations(AID destinataire, String recommandations) {
            // Créer un message ACL pour envoyer les recommandations
            ACLMessage message = new ACLMessage(ACLMessage.INFORM);
            message.addReceiver(destinataire);
            message.setContent("RECOMMANDATIONS|" + recommandations);

            // Envoyer le message
            send(message);
        }
    }
}
