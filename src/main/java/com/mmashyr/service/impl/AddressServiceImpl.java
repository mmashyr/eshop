package com.mmashyr.service.impl;

import com.mmashyr.entity.Address;
import com.mmashyr.repository.AddressRepository;
import com.mmashyr.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Anton on 13.01.2017.
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    @Qualifier("addressRepository")
    AddressRepository addressRepository;

    @Override
    @Transactional
    public void save(Address address) {
        addressRepository.save(address);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        addressRepository.delete(id);
    }

    @Override
    public Address findOne(Long id) {
       return addressRepository.findOne(id);
    }

    @Override
    public List<Address> getAll() {
        return (List<Address>) addressRepository.findAll();
    }

}
