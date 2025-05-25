package org.example.Controller;

import org.example.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment/address")
public class AddressController {

    @Autowired
    private AddressService addressService;


}
