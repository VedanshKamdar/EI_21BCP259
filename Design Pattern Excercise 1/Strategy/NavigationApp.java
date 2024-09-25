import java.util.ArrayList;
import java.util.List;

// Strategy interface
interface RoutingStrategy {
    List<String> calculateRoute(String start, String end);
}

// Concrete strategies
class FastestRouteStrategy implements RoutingStrategy {
    @Override
    public List<String> calculateRoute(String start, String end) {
        List<String> route = new ArrayList<>();
        route.add("Start from: " + start);
        route.add("Take the highway");
        route.add("Use express lanes");
        route.add("Arrive at: " + end);
        return route;
    }
}

class ShortestRouteStrategy implements RoutingStrategy {
    @Override
    public List<String> calculateRoute(String start, String end) {
        List<String> route = new ArrayList<>();
        route.add("Start from: " + start);
        route.add("Take local roads");
        route.add("Use shortcuts through neighborhoods");
        route.add("Arrive at: " + end);
        return route;
    }
}

class ScenicRouteStrategy implements RoutingStrategy {
    @Override
    public List<String> calculateRoute(String start, String end) {
        List<String> route = new ArrayList<>();
        route.add("Start from: " + start);
        route.add("Take the coastal road");
        route.add("Drive through the national park");
        route.add("Stop at scenic viewpoints");
        route.add("Arrive at: " + end);
        return route;
    }
}

// Context
class NavigationSystem {
    private RoutingStrategy strategy;

    public void setStrategy(RoutingStrategy strategy) {
        this.strategy = strategy;
    }

    public List<String> navigate(String start, String end) {
        return strategy.calculateRoute(start, end);
    }
}

// Client code
public class NavigationApp {
    public static void main(String[] args) {
        NavigationSystem nav = new NavigationSystem();

        // Using fastest route strategy
        nav.setStrategy(new FastestRouteStrategy());
        List<String> fastestRoute = nav.navigate("New York", "Los Angeles");
        System.out.println("Fastest Route:");
        fastestRoute.forEach(System.out::println);

        System.out.println();

        // Using shortest route strategy
        nav.setStrategy(new ShortestRouteStrategy());
        List<String> shortestRoute = nav.navigate("New York", "Los Angeles");
        System.out.println("Shortest Route:");
        shortestRoute.forEach(System.out::println);

        System.out.println();

        // Using scenic route strategy
        nav.setStrategy(new ScenicRouteStrategy());
        List<String> scenicRoute = nav.navigate("New York", "Los Angeles");
        System.out.println("Scenic Route:");
        scenicRoute.forEach(System.out::println);
    }
}