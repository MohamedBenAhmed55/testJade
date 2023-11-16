package testJade;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class MessageACL {

    private int performatif;
    private AID expediteur;
    private AID destinataire;
    private String contenu;

    public MessageACL(int performatif, AID expediteur, AID destinataire, String contenu) {
        this.performatif = performatif;
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        this.contenu = contenu;
    }

    public ACLMessage creerACLMessage() {
        ACLMessage aclMessage = new ACLMessage(performatif);
        aclMessage.setSender(expediteur);
        aclMessage.addReceiver(destinataire);
        aclMessage.setContent(contenu);
        return aclMessage;
    }

    public int getPerformatif() {
        return performatif;
    }

    public AID getExpediteur() {
        return expediteur;
    }

    public AID getDestinataire() {
        return destinataire;
    }

    public String getContenu() {
        return contenu;
    }
}
