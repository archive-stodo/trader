package com.example.repository;

import java.util.ArrayList;

import javax.persistence.Id;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Trader;

@Transactional
public interface TraderDAO extends CrudRepository<Trader, Long> {
	
	public Trader findById(int id);
	public ArrayList<Trader> findAll();
	
	public ArrayList<Trader> findAllByFirstName(String firstName);
	public ArrayList<Trader> findAllByEmail(String email);
	public ArrayList<Trader> findAllByLastName(String lastName);
	
	public ArrayList<Trader> findAllByFirstNameAndLastName(String firstName, String lastName);
	
	public Trader findByLastName(String lastName);
	
}
