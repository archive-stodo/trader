package com.example.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.domain.Trade;
import com.example.domain.Trader;
import com.example.repository.TradeDAO;
import com.example.repository.TraderDAO;


//hql
@NamedQueries({
	@NamedQuery(
	name = "fromTraderQuery",
	query = "from Trader"
	)
})
@SpringBootApplication
@Transactional
@Service
public class TTService {
	
   @Autowired  
   SessionFactory sessionFactory; 
   
   @Autowired
   TraderDAO traderDAO;
   
   @Autowired
   TradeDAO tradeDAO;
  
	@PersistenceContext
    private EntityManager em;
	
	public List<Object> getAllTraders(){
		Session session = sessionFactory.openSession();
		
		Query theQuery =
				session.createQuery(
					"from Trader ORDER BY lastName");
		
		List<Object> traders = theQuery.list();
		session.close();
		return traders;
	}
	
	public void traderAddTrade(int traderId, int tradeId){
		Trader trader = em.find(Trader.class, traderId);
		Trade trade = em.find(Trade.class, tradeId);
		
		trade.setTrader(trader);		
	}
	
	//send static image from /images
	public ResponseEntity<InputStreamResource> getImage() throws IOException{
		ClassPathResource image = new ClassPathResource("/images/edu.jpg");
		
		return ResponseEntity.ok()
				.contentLength(image.contentLength())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(image.getInputStream()));
	}
	
}
