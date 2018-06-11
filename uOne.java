/*
U-1

Rocket cost = $100 Million
Rocket weight = 10 Tonnes
Max weight (with cargo) = 18 Tonnes
Chance of launch explosion = 5% * (cargo carried / cargo limit)
Chance of landing crash = 1% * (cargo carried / cargo limit)

 */

import java.util.Random;

public class uOne extends Rocket {

    private int rocketWeightEmty = 10000;
    private int allWeightLunch =  rocketWeight + rocketWeightEmty;
    private Random random = new Random();
    private double randomChance = random.nextDouble();

    public uOne() {
        maxWeight = 8000;
        rocketCost = 1000000;
    }

    public boolean lauch(){
        // Chance of launch explosion = 5% * (cargo carried / cargo limit)

        double crashPercent = 0.05;

        double chanceExplosion = crashPercent * ((double) maxWeight / (double) allWeightLunch);
        // Calculation of explosion chance

        // Start condition Rocket one
        if (chanceExplosion >= randomChance){
            System.out.println("Rocket one explodes!!!");
            System.out.println("You need to lauch a new rocket.");
            return false;
        }
        else {
            System.out.println("Rocket one luched successfully!");
            return true;
        }
    }

    public boolean land() {
        // Chance of landing crash = 1% * (cargo carried / cargo limit)

        double crashPercent = 0.01;

        double chanceLanding = crashPercent * ((double) maxWeight / (double) allWeightLunch);
        // same as chanceExplosion with different crashPercent

        // Land condition Rocket one
        if(chanceLanding >= randomChance){
            System.out.println("Rocket one failed to land save!!!");
            System.out.println("You need to lauch a new rocket.");
            return false;
        }
        else{
            System.out.println("Rocket one landed successfully!!!");
            return true;
        }

    }

}
