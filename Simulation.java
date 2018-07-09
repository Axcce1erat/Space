/*
Create a Simulation class that is responsible for reading item data and filling up the rockets.
The Simulation class should include these methods
 */

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class Simulation {

    private String messagebudget = "Total $ needed = %d\n";

    private int numberRocketsNeeded = 0;

    private List<Item> arrayPhase1 = new ArrayList<>();
    private List<Item> arrayPhase2 = new ArrayList<>();

    ArrayList <Rocket> arrayuOne = new ArrayList<>();
    ArrayList <Rocket> arrayuTwo = new ArrayList<>();
    private Item items;
    private int countRockets = 0;
    private Rocket rocketOne, rocketTwo;

    private BufferedReader bufferPhases;
    private Charset charset = Charset.forName("UTF-8");

    int budgetNeeded = 0;

    /*
    loadItems: this method loads all items from a text file and returns an ArrayList of Items:

    Each line in the text file consists of the item name followed by = then its weigh in kg.

    loadItems should read the text file line by line and create an Item object for each and then add it to an ArrayList of Items.
    The method should then return that ArrayList.
     */
    public void loadItems() throws FileNotFoundException {

        try  {
            FileInputStream phase1 = new FileInputStream("Phase-1.txt");
            FileInputStream phase2 = new FileInputStream("Phase-2.txt");

            SequenceInputStream sequence = new SequenceInputStream(phase1, phase2);
            bufferPhases = new BufferedReader(new InputStreamReader(sequence, charset));


            String line;
            String[]parts;
            String name;
            int weight;

            while ((line = bufferPhases.readLine())!=null && !line.isEmpty()){
                parts = line.split("(?<=\\D)(?=\\d)"); // die Informationen aus dem Array
                name = parts[0].trim();
                weight = Integer.parseInt(parts[1].trim());
                items = new Item(name, weight);
                if (phase1.getChannel().isOpen()){
                    arrayPhase1.add(items);
                }
                else if (phase2.getChannel().isOpen()){
                    arrayPhase2.add(items);
                }
            }
        }
        catch (NumberFormatException e){
            System.out.println("Number format exception!");
        }

        catch (IOException e) { // von bufferPhases.readLine()
            e.printStackTrace();
        }
        finally {
            if (bufferPhases != null){
                try {
                    bufferPhases.close();
                } catch (IOException e) { // Aus .clsoe()
                    e.printStackTrace();
                }

            }
        }
        System.out.println(arrayPhase1);
    }


    /*
    loadU1: this method takes the ArrayList of Items returned from loadItems and starts creating U1 rockets.
    It first tries to fill up 1 rocket with as many items as possible before creating a new rocket object and filling that one until all items are loaded.
    The method then returns the ArrayList of those U1 rockets that are fully loaded.
     */

    private void loadU1(List<Item> array, int countRockets){

        rocketOne = new uOne();
        for (Item mItem : array){
            while (!rocketOne.canCarry(mItem)){// aus der Rocket Class, solange es true ist (weightItems <= maxWeight)
                arrayuOne.add(rocketOne);// +1 zu dem ArrayList für uOne
                rocketOne = new uOne();
                countRockets++; // addieren eins zu dem Raketen Zähler
                System.out.println("A new U1 Rocket is created: " + countRockets);
            }
            rocketOne.carry(mItem);
        }
        System.out.println();
    }

    /*
    loadU2: this method also takes the ArrayList of Items and starts creating U2 rockets and filling them with those items the same way as with U1 until all items are loaded.
    The method then returns the ArrayList of those U2 rockets that are fully loaded.
     */

    private void loadU2(List<Item> array, int countRockets){

        rocketTwo = new uTwo();
        for (Item mItem : array){
            while (!rocketTwo.canCarry(mItem)){
                arrayuTwo.add(rocketTwo);
                rocketTwo = new uTwo();
                countRockets++;
                System.out.println("A new U2 Rocket is created: " + countRockets);
            }
            rocketTwo.carry(mItem);
        }
        System.out.println();

    }

    /*
    runSimulation: this method takes an ArrayList of Rockets and calls launch and land methods for each of the rockets in the ArrayList.
    Every time a rocket explodes or crashes (i.e if launch or land return false) it will have to send that rocket again.
    All while keeping track of the total budget required to send each rocket safely to Mars.
    runSimulation then returns the total budget required to send all rockets (including the crashed ones).
     */
    public void runSimulation(ArrayList<Rocket> array, int budgetNeeded){
        for (Rocket rocket : array){
            if(!rocket.lauch()){
                rocket.lauch();
                numberRocketsNeeded++;
                System.out.println("Rocket is sent again, beacuse of an explosion a the lauch.");
            }
            if (!rocket.land()){
                rocket.land();
                numberRocketsNeeded++;
                System.out.println("Another Rocket is sent again, because of a crash landing.");
            }
            budgetNeeded += rocket.rocketCost;
        }
        System.out.println("We need " + numberRocketsNeeded + " rocket");
        System.out.println(String.format(messagebudget, budgetNeeded));
    }

    public void UOneSimulation(){
        runSimulation(arrayuOne, budgetNeeded);
    }

    public void UTwoSimulation(){
        runSimulation(arrayuTwo, budgetNeeded);
    }

    public void loadPhase1ForU1(){
        loadU1(arrayPhase1, countRockets);
    }

    public void loadPhase1ForU2(){
        loadU2(arrayPhase1, countRockets);
    }

    public void loadPhase2ForU1(){
        loadU1(arrayPhase2, countRockets);
    }

    public void loadPhase2ForU2(){
        loadU2(arrayPhase2, countRockets);
    }
}

