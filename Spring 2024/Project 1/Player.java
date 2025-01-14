/***************************
Purpose: Player class accepts player's name (String), height (type Height), 
and age (int), returns string of a player with their fields (name, height, age)
labeled appropriately
***************************/

public class Player {
    private String name;
    private Height height;
    private int age;

    // constructor
    public Player(String name, Height height, int age) {
        this.name = name;
        this.height = height;
        this.age = age;
    }

    // getter
    public String getName() {
        return name;
    }

    // getter
    public Height getHeight() {
        return height;
    }

    // getter
    public int getAge() {
        return age;
    }

    @Override
    // displays player's name, height, and age
    public String toString() {
        return   "\nName: " + name + "  Height: " + height + "  Age: " + age;
    }
}