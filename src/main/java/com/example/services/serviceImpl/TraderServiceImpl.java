package com.example.services.serviceImpl;

import com.example.domain.Trader;
import com.example.repository.TraderDAO;
import com.example.services.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Created by Admin on 07/05/2017.
 */
public class TraderServiceImpl implements TraderService {

    @Autowired
    TraderDAO traderDAO;

    @Override
    @PreAuthorize("hasRole('USER')")
    public Trader findById(int id) {
        return traderDAO.findById(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Trader findByUSername(String username) {
        return traderDAO.findByUsername(username);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Trader> findAll() {
        return traderDAO.findAll();
    }
}
