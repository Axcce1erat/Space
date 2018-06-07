/*
Create an Item class that includes a String name and an int weight that will represent an item to be carried by the rockets
 */
public class Item {
    String name;
    int weight;
    public String toString() {
        return name + weight;
    }

    public Item (String name, int weight){
        this.name = name;
        this.weight = weight;
    }
}
