package com.clarit.hs.service.items.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.clarit.hs.service.items.Room;

public interface ItemRepository extends MongoRepository<Room, String> {

    @Query(value = "{number:?0}")
    List<Room> findByNumber(int number);

    @Query(value = "{isOccupied:'?0'}")
    List<Room> findAll(boolean isOccupied);

    public long count();

    @Query(value = "{number:?0}", delete = true)
    Room deleteAll(int number);
}