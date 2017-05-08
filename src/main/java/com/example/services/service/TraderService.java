package com.example.services.service;

import com.example.domain.Trader;

import java.util.List;

/**
 * Created by Admin on 07/05/2017.
 */
public interface TraderService {
    public Trader findById(int id);
    public Trader findByUSername(String username);
    public List<Trader> findAll();
}
