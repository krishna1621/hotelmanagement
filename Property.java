package com.clarit.hs.service.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clarit.hs.service.items.repo.ItemRepository;

import java.util.List;

/**
 * Created by mnachiappan on 1/4/23.
 */
@Service
public class Property implements IProperty {
    @Autowired
    ItemRepository itemRepository;
    @Override
    public List<Room> getAll(boolean isOccupied) {
        return getAllRooms();
    }

    @Override
    public Room book(int roomNumber) {
        return null;
    }


    @Override
    public Room book(Room room) {
        return bookRoom(room);
    }

    @Override
    public List<Room> get(int roomNumber) {
        return itemRepository.findByNumber(roomNumber);
    }

    @Override
    public Room cancelBooking(int number) {
        return itemRepository.deleteAll(number);
    }

    private List<Room> getAllRooms(){
        return itemRepository.findAll();
    }

    private Room bookRoom(Room room) {

        return itemRepository.save(room);
    }
}
