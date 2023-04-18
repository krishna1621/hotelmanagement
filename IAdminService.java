package com.clarit.hs.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clarit.hs.service.items.Room;

/**
 * Created by mnachiappan on 1/4/23.
 */
@RestController
@RequestMapping(value = "/rooms")
public interface IAdminService {

	@GetMapping(produces = { "application/hal+json" })
	public CollectionModel<Room> getAll(boolean occupied);
	
	@PostMapping(value = "/{number}")
	public Room book(@PathVariable Integer number);
	
	@GetMapping(value = "/{number}")
	public CollectionModel<Room>  get(@PathVariable int number);
	
	@DeleteMapping(value = "{number}")
	public Room cancelBooking(int number);

}
