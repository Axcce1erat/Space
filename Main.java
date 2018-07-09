import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args)throws FileNotFoundException {
        Simulation simulation = new Simulation (); // Erzeugt das Objekt Simulation
        simulation.loadItems(); // Ruft loadItems Methode auf in simulation

        System.out.println("\nLoading uOne for Phase one:\n"); // \n zur übersicht der Darstellung
        simulation.loadPhase1ForU1();

        System.out.println("\nLoading uTwo for Phase one:\n"); // \n zur übersicht der Darstellung
        simulation.loadPhase1ForU2();

        System.out.println("\nLoading uOne for Phase two:\n"); // \n zur übersicht der Darstellung
        simulation.loadPhase2ForU2();

        System.out.println("\nLoading uTwo for Phase two:\n"); // \n zur übersicht der Darstellung
        simulation.loadPhase2ForU2();

        simulation.UOneSimulation();
        simulation.UTwoSimulation();

    }
}
