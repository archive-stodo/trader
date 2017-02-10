package com.example.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Trade;
import com.example.repository.TradeDAO;
import com.example.repository.TraderDAO;

@Transactional
@Service
@EnableSpringDataWebSupport
public class TradeService {

   @Autowired  
   SessionFactory sessionFactory; 
   
   @Autowired
   TraderDAO traderDAO;
   
   @Autowired
   TradeDAO tradeDAO;
   
	public Page<Trade> getTraderTrades(
			@PathVariable("traderId") int traderId,
			@RequestParam("page") int page,
			@RequestParam("size") int size
			){
		PageRequest request = new PageRequest(page,size);
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createQuery(
				"FROM Trade as trade" +
				" WHERE trade.trader.id = :traderId"
				);
		query.setParameter("traderId" , traderId);
		
		Page<Trade> trades =  (Page<Trade>) query.list();
		
		sessionFactory.close();
	  		
		return trades;
	}
   
   public String saveImage(MultipartFile file, int tradeId) throws IOException{		
	    if(!file.isEmpty()){
			Trade trade = tradeDAO.findById(tradeId);
			
			byte[] bytes = file.getBytes();
			trade.setImage(bytes);
		
			return "Trade with id: " + tradeId + " had an image successfuly added.";
		}else{
			return "File is empty";
		}
	}
   
   public String create(String instrumentName, long numberOfInstruments, float entryPrice, float closePrice,
		   String notes, MultipartFile file, String side){
	   int tradeId = -1;
	   
	   if(file != null){
		   try{
				byte[] bytes = file.getBytes();
				Trade trade = new Trade(instrumentName, numberOfInstruments, entryPrice, closePrice, notes, bytes, new Date(), side);
				tradeDAO.save(trade);
				tradeId = trade.getId();
			}catch(Exception e){
				return "Error creating the trade: " + e.toString();
			}
			return "New trade (with image) successfully created with id = " + tradeId;
	   }else{		
			Trade trade = new Trade(instrumentName, numberOfInstruments, entryPrice, closePrice, notes, null, new Date(), side);
			tradeDAO.save(trade);
			tradeId = trade.getId();
			return "New trade successfully created with id = " + tradeId;
	   }
   }

}
