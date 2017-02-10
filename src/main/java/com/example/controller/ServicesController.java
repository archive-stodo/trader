package com.example.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Trade;
import com.example.domain.Trader;
import com.example.domain.View;
import com.example.repository.TradeDAO;
import com.example.repository.TraderDAO;
import com.example.service.TTService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/service")
public class ServicesController {

	@Autowired
	TTService services;
	
	@PersistenceContext
    private EntityManager em;
	
	@RequestMapping(value = "/traders", method = RequestMethod.GET)
	public List<Object> getAllTraders(){
		return services.getAllTraders();
	}
	
	@Transactional
	@RequestMapping(value = "/traderAddTrade", method = RequestMethod.POST)
	public void traderAddTrade(
			@RequestParam int traderId,
			@RequestParam int tradeId){
		
	//	services.traderAddTrade(traderId, tradeId);
		
		Trader trader;
		Trade trade;
		
		trade = em.find(Trade.class, tradeId);
		trader = em.find(Trader.class, traderId);
		
		
		trade.setTrader(trader);		
	}
	
	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> getImage() throws IOException{
		return services.getImage();
	}
}
