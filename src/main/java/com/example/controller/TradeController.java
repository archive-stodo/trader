package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Trade;
import com.example.repository.TradeDAO;
import com.example.repository.TraderDAO;
import com.example.services.serviceImpl.TradeServiceImpl;

@RestController
@RequestMapping("/trade")
@MultipartConfig(fileSizeThreshold = 20971520)
public class TradeController {

	@Autowired
	TradeDAO tradeDAO;

	@Autowired
	TraderDAO traderDAO;

	@Autowired
    TradeServiceImpl tradeService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(String instrumentName, long numberOfInstruments, float entryPrice, float closePrice, String notes, MultipartFile file, String side){
		return tradeService.create(instrumentName, numberOfInstruments, entryPrice, closePrice, notes, file, side);
	}

	@RequestMapping(value = "/addImage", method = RequestMethod.POST)
	public @ResponseBody String saveImage(
			@RequestParam("uploadedFile") MultipartFile file, int tradeId) throws IOException{
		return tradeService.saveImage(file, tradeId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ArrayList<Trade> getAllTrades(){
		return tradeDAO.findAll();
	}

	@RequestMapping(value = "/paginated/{traderId}", method = RequestMethod.GET)
	public Page<Trade> getPaginated(
			@PathVariable("traderId") int traderId,
			@RequestParam("page") int page,
			@RequestParam("size") int size
			){

		PageRequest request = new PageRequest(page,size);

//		or like this :-)
//		PageRequest request2 =
//				new PageRequest(page, size,
//		            new Sort(Sort.Direction.ASC, "instrumentName")
//                    .and(new Sort(Sort.Direction.DESC, "entryPrice"))
//				);

		return  tradeDAO.findAllByPage(traderId, request);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Trade getTradeById(@RequestParam int id){
		return tradeDAO.findById(id);
	}
}
