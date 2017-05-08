package com.example.services.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 07/05/2017.
 */
public interface TraderTradeService {
    public List<Object> getAllTraders();
    public void traderAddTrade(int traderId, int tradeId);

    //static image - not used
    public ResponseEntity<InputStreamResource> getImage();
}
