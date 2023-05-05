package rob.queue;

import java.util.LinkedList;

import rob.data.Animal;
import rob.data.AnimalType;

public class AnimalQueue {

    LinkedList<Animal> cats, dogs;
    int numAnimals;

    public AnimalQueue() {
        this.cats = new LinkedList<Animal>();
        this.dogs = new LinkedList<Animal>();
        this.numAnimals= 0;
    }

    void enqueue(Animal a) throws Exception {
        a.rank = ++numAnimals;
        if( a.type == AnimalType.CAT ) {
            cats.add(a);  // append to end of list
        }
        else if ( a.type == AnimalType.DOG ) {
            dogs.add(a);
        }
        else {
            throw new Exception("Animal " + a + " has unknown type!");
        }
    }

    Animal dequeueAnyBad() throws Exception {
        if( numAnimals == 0 ) {
            throw new Exception("no animals!");
        }
        int catRank = -1;
        int dogRank = -1;
        if( !cats.isEmpty() ) {
            catRank = cats.get(0).rank;
        }
        if( !dogs.isEmpty() ) {
            dogRank = dogs.get(0).rank;
        }

        numAnimals--;
        if( (catRank != -1) && (catRank < dogRank) ) {
            return cats.remove(0);
        }
        else {
            return dogs.remove(0);
        }
    }

    Animal dequeueAny() throws Exception {
        if( cats.isEmpty() ) {
            return dequeueDog();
        }
        else if( dogs.isEmpty() ) {
            return dequeueCat();
        }

        Animal cat = cats.peek();
        Animal dog = dogs.peek();
        if( cat.isOlderThan(dog) ) {
            return dequeueCat();
        } else {
            return dequeueDog();
        }
    }

    Animal dequeueDog() throws Exception {
        if( dogs.isEmpty() ) {
            throw new Exception("no dogs!");
        }
        numAnimals--;
        return dogs.remove(0);
    }

    Animal dequeueCat() throws Exception {
        if( cats.isEmpty() ) {
            throw new Exception("no cats!");
        }
        numAnimals--;
        return cats.remove(0);
    }

    public static void main(String[] args) throws Exception {
        AnimalQueue queue = new AnimalQueue();
        Animal rebel = new Animal(AnimalType.DOG, "rebel");
        Animal maxi = new Animal(AnimalType.DOG, "maxi");
        Animal kikiba = new Animal(AnimalType.CAT, "kikiba");

        queue.enqueue(rebel);
        queue.enqueue(maxi);
        queue.enqueue(kikiba);

        System.out.println(queue.dequeueAny().name.toString()); // rebel
        System.out.println(queue.dequeueCat().name.toString()); // kikiba
//        System.out.println(queue.dequeueCat().type.toString()); // no cats!
        System.out.println(queue.dequeueAny().name.toString()); // maxi
//        System.out.println(queue.dequeueAny().name.toString()); // no animals!
    }
}


/*
 * A few improvements to this approach:
 *
 * 1. Use private for Animal fields to prevent confusion. that way access to this variable is controlled
 * via an explicit get method.
 *
 * 2. You enqueue a LIST, not an element. So function names should have been dequeueDogs, not dequeueDog
 *
 * 3. Should have made Animal an abstact class, then made minimal classes for Dog and Cat, e.g.
 *
 * public class Dog extends animal {
 *     public Dog(String name) { super(name) }
 * }
 *
 * That would have made dequeueAny a little better.
 *
 * 4. In LinkedLists, poll() does same thing as remove(0).
 *
 */
