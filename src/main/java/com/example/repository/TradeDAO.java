package com.example.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.domain.Trade;
import com.example.domain.Trader;

@Transactional
public interface TradeDAO extends CrudRepository<Trade, Long>{

	public Trade findById(int id);
	public ArrayList<Trade> findAll();
	public Set<Trade> findAllByInstrumentName(String instrumentName);
	
	@Query("FROM Trade as trade" +
			" WHERE trade.trader.id = :traderId")
	public Page<Trade> findAllByPage(@Param("traderId") int traderId, Pageable pageable);
	
}
