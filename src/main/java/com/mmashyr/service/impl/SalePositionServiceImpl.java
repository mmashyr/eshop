package com.mmashyr.service.impl;

import com.mmashyr.entity.SalePosition;
import com.mmashyr.repository.SalePositionRepository;
import com.mmashyr.service.SalePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Anton on 13.01.2017.
 */
@Service
public class SalePositionServiceImpl implements SalePositionService {
    @Autowired
    SalePositionRepository salePositionRepository;

    @Override
    @Transactional
    public void save(SalePosition salePosition) {
        salePositionRepository.save(salePosition);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        salePositionRepository.delete(id);
    }

    @Override
    public SalePosition findOne(Long id) {
        return salePositionRepository.findOne(id);
    }

    @Override
    public List<SalePosition> getAll() {
        return (List<SalePosition>) salePositionRepository.findAll();
    }
}
