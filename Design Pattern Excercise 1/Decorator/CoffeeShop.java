// Component interface
interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete component
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public double getCost() {
        return 1.0;
    }
}

// Base decorator
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

// Concrete decorators
class Milk extends CoffeeDecorator {
    public Milk(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }
}

class Sugar extends CoffeeDecorator {
    public Sugar(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.2;
    }
}

class Whip extends CoffeeDecorator {
    public Whip(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Whip";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.7;
    }
}

// Client code
public class CoffeeShop {
    public static void main(String[] args) {
        // Order a simple coffee
        Coffee coffee = new SimpleCoffee();
        System.out.println("Order: " + coffee.getDescription());
        System.out.println("Cost: $" + coffee.getCost());

        // Order a coffee with milk
        Coffee milkCoffee = new Milk(new SimpleCoffee());
        System.out.println("\nOrder: " + milkCoffee.getDescription());
        System.out.println("Cost: $" + milkCoffee.getCost());

        // Order a coffee with milk and sugar
        Coffee sweetMilkCoffee = new Sugar(new Milk(new SimpleCoffee()));
        System.out.println("\nOrder: " + sweetMilkCoffee.getDescription());
        System.out.println("Cost: $" + sweetMilkCoffee.getCost());

        // Order a complex coffee
        Coffee complexCoffee = new Whip(new Sugar(new Milk(new SimpleCoffee())));
        System.out.println("\nOrder: " + complexCoffee.getDescription());
        System.out.println("Cost: $" + complexCoffee.getCost());
    }
}