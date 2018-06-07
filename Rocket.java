/*
Create a class Rocket that implements the SpaceShip Interface and hence implements all the methods above.
        launch and land methods in the Rocket class should always return true.
        When U1 and U2 classes extend the Rocket class they will override these methods to return true or false based on the actual probability of each type.
        carry and canCarry should be implemented here and will not need to be overridden in the U1 and U2 classes
*/
public class Rocket implements SpaceShip {

    public int rocketCost;
    public int rocketWeight;
    public static int maxWeight; // static da Konstante
    public static double crashPercent; // static da Konstante
    public boolean lauch(){
        return true;
    }

    public boolean land(){
        return true;
    }

    public boolean canCarry(Item item){
        int weightItems = rocketWeight + item.weight;
        System.out.println(weightItems);
        if (weightItems <= maxWeight){
            return true;
        }
        else{
            System.out.println("Load another rocket.");
            return false;
        }
    }

    public int carry(Item item){
        rocketWeight += item.weight;
        System.out.println(rocketWeight);
        return rocketWeight;
    }

}
