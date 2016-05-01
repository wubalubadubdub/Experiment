/**
 * Created by bearg on 4/29/2016.
 */
public class Vehicle {
    public void drive() {
        System.out.println("Driving vehicle");
    }
}

class Car extends Vehicle {
    /*@Override
    public void drive() {
        System.out.println("Driving car");
    }*/
}

class Truck extends Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving truck");
    }

    public void load(){
        System.out.println("Loading truck");
    }
}