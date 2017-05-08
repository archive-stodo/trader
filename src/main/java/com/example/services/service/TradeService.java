package com.example.services.service;

import com.example.domain.Trade;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Admin on 07/05/2017.
 */
public interface TradeService {
    public Page<Trade> getTraderTrades(
        @PathVariable("traderId") int traderId,
        @RequestParam("page") int page,
        @RequestParam("size") int size
    );

    public String saveImage(MultipartFile file, int tradeId) throws IOException;

    public String create(String instrumentName, long numberOfInstruments, float entryPrice, float closePrice,
                         String notes, MultipartFile file, String side);

}
