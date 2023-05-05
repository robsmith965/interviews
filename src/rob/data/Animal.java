package rob.data;

public class Animal {
    public String name;
    public int rank;
    public AnimalType type;

    public Animal(AnimalType type) {
        this.type = type;
    }

    public Animal(AnimalType type, String name) {
        this.type = type;
        this.name = name;
    }

    public boolean isOlderThan(Animal other) {
        return rank < other.rank;   // lower number means earlier timestamp
    }
}
