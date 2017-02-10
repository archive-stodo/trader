package com.example.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Trader;
import com.example.domain.View;
import com.example.repository.TraderDAO;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/trader")
public class TraderController {

	@Autowired
	private TraderDAO traderDAO;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createTrader(
			@RequestParam String firstName,
			@RequestParam String lastName,
			@RequestParam String email){
		int traderId = -1;
		try{
			Trader trader = new Trader(firstName, lastName, email);		
			traderDAO.save(trader);
			traderId = trader.getId();
		}catch(Exception e){
			return "Error creating the user: " + e.toString();
		}
		return "TRADER succesfully created with id = " + traderId;
	}
	
	@JsonView(View.Trader.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Trader getById(@RequestParam(value = "id") int id){
		return traderDAO.findById(id);
	}
	
	@JsonView(View.Trader.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Trader getByIdPathVariable(@PathVariable int id){
		return traderDAO.findById(id);
	}
	
	@JsonView(View.Trader.class)
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ArrayList<Trader> getAllTraders(){
		return traderDAO.findAll();
	}
	
	@RequestMapping(value = "/firstName", method = RequestMethod.GET)
	public ArrayList<Trader> getTraderByfirstName(@RequestParam String firstName){
		return traderDAO.findAllByFirstName(firstName);
	}
	
	@RequestMapping(value = "/lastName", method = RequestMethod.GET)
	public ArrayList<Trader> getTraderBylastName(@RequestParam String firstName){
		return traderDAO.findAllByLastName(firstName);
	}
	
	@RequestMapping(value = "/name", method = RequestMethod.GET)
	public ArrayList<Trader> getTraderByfirstNameAndlastName
		(@RequestParam String firstName, @RequestParam String lastName){
		return traderDAO.findAllByFirstNameAndLastName(firstName, lastName);
	}
}
