/*
U-2

Rocket cost = $120 Million
Rocket weight = 18 Tonnes
Max weight (with cargo) = 29 Tonnes
Chance of launch explosion = 4% * (cargo carried / cargo limit)
Chance of landing crash = 8% * (cargo carried / cargo limit)

 */

import java.util.Random;

public class uTwo extends Rocket {

    private Random random = new Random();
    private double randomChance = random.nextDouble();
    private int rocketWeightEmty = 18000;
    private int allWeightLunch = rocketWeight + rocketWeightEmty;


    public uTwo() {
        maxWeight = 8000;
        rocketCost = 1000000;
    }

    public boolean lauch(){

        //Chance of launch explosion = 4% * (cargo carried / cargo limit)
        double crashPercent = 0.04;

        double chanceExplosion = crashPercent * ((double) allWeightLunch + (double) maxWeight);

        if(chanceExplosion >= randomChance){
            System.out.println("Rocket two explodes!!!");
            System.out.println("You need to lauch a new rocket.");
            return false;
        }
        else {
            System.out.println("Rocket one luched successfully!");
            return true;
        }
    }
    public boolean land(){

        //Chance of landing crash = 8% * (cargo carried / cargo limit)
        double crashPercent = 0.08;

        double chanceLanding = crashPercent * ((double) allWeightLunch + (double) maxWeight );

        if (chanceLanding >= randomChance){
            System.out.println("Rocket two failed to land save!!!");
            System.out.println("You need to lauch a new rocket.");
            return false;
        }
        else {
            System.out.println("Rocket one landed successfully!!!");
            return true;
        }
    }
}
