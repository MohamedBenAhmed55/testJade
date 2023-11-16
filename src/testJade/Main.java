package testJade;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class Main extends Agent {

    public static void main(String[] args) {
        Main main = new Main();
        main.lancerSystemeMultiAgent();
    }

    private void lancerSystemeMultiAgent() {
        // Initialiser la plateforme JADE
        Runtime runtime = Runtime.instance();
        Profile profile = new ProfileImpl();
        AgentContainer mainContainer = runtime.createMainContainer(profile);

        // Créer et déployer les agents
        deployerAgent(mainContainer, "AgentDeDonnees", AgentDeDonnees.class.getName());
        deployerAgent(mainContainer, "AgentDeRecommandation", AgentDeRecommandation.class.getName());
        deployerAgent(mainContainer, "AgentUtilisateur1", AgentUtilisateur.class.getName());
        deployerAgent(mainContainer, "AgentUtilisateur2", AgentUtilisateur.class.getName());

        // Attendre que les agents terminent leur exécution (simulation)
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Terminer la plateforme JADE
        runtime.shutDown();
    }

    private void deployerAgent(AgentContainer container, String nomAgent, String classeAgent) {
        try {
            AgentController agentController = container.createNewAgent(nomAgent, classeAgent, null);
            agentController.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
