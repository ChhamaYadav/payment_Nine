package org.example.Service;


import org.example.Entity.Address;
import org.example.Entity.AddressRepository;

import org.example.dto.AddressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public void saveAddress(AddressRequest addressRequest){
        Address address=new Address();
        address.setUserId(addressRequest.getUserId());
        address.setStreet(addressRequest.getStreet());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setPincode(addressRequest.getPincode());
        address.setCountry(addressRequest.getCountry());

        addressRepository.save(address);

    }
}
