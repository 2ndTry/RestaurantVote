package com.alexeymiroshnikov.graduation.service;

import com.alexeymiroshnikov.graduation.model.Dish;
import com.alexeymiroshnikov.graduation.model.Restaurant;
import com.alexeymiroshnikov.graduation.repository.DishRepository;
import com.alexeymiroshnikov.graduation.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

import static com.alexeymiroshnikov.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service("dishService")
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @CacheEvict(value = "restaurants", allEntries = true)
    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new IllegalArgumentException("Restaurant not found by id " + restaurantId));
        dish.setRestaurant(restaurant);
        if (dish.getDate() == null) {
            dish.setDate(LocalDateTime.now());
        }
        return dishRepository.save(dish);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int id, int restId) {
        dishRepository.delete(id, restId );
    }

    public Dish get(int id, int restId) {
        return dishRepository.get(id, restId).orElseThrow(() -> new IllegalArgumentException("Dish not found by id " + id));
    }

    @Transactional
    @CacheEvict(value = "restaurants", allEntries = true)
    public Dish update(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        dish.setRestaurant(restaurantRepository.getOne(restaurantId));
        checkNotFoundWithId(dishRepository.save(dish), dish.getId());
        return dish;

    }

    public List<Dish> getAll(int restId) {
        return dishRepository.getAll(restId).orElseThrow(() -> new IllegalArgumentException("not found dishes with restaurant id " + restId));
    }

}
