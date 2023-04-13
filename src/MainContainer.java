import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class MainContainer {
    public static void main(String[] args) {
        // créer l'environnement JADE
        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_HOST, "localhost");
        p.setParameter(Profile.GUI, "true");
        AgentContainer container = rt.createMainContainer(p);

        try {
            // créer l'agent vendeur
            AgentController vendeurCtrl = container.createNewAgent("vendeur", VendeurAgent.class.getName(), null);
            vendeurCtrl.start();

            // créer deux agents acheteurs
            AgentController acheteur1Ctrl = container.createNewAgent("acheteur1", AcheteurAgent.class.getName(), null);
            acheteur1Ctrl.start();
            AgentController acheteur2Ctrl = container.createNewAgent("acheteur2", AcheteurAgent.class.getName(), null);
            acheteur2Ctrl.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }

}
