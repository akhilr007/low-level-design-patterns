package problems.SwiggyOrZomato.entity;


import java.util.ArrayList;
import java.util.List;

// Singleton
public class RestaurantManager {

    private List<Restaurant> restaurants = new ArrayList<>();
    private RestaurantManager() {};

    private static class RestaurantManagerInstance {
        private static final RestaurantManager instance = new RestaurantManager();
    }

    public static RestaurantManager getInstance() {
        return RestaurantManagerInstance.instance;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public void removeRestaurant(Restaurant restaurant) {
        restaurants.remove(restaurant);
    }

    public List<Restaurant> searchByLocation(String location) {
        return restaurants.stream()
                .filter(restaurant -> restaurant.getAddress().equals(location))
                .toList();
    }

    public Restaurant searchByName(String name) {
        return (Restaurant) restaurants.stream()
                .filter(restaurant -> restaurant.getName().equals(name));
    }
}