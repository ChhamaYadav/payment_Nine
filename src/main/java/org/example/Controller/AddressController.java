package org.example.Controller;

import org.apache.coyote.Response;
import org.example.Service.AddressService;
import org.example.dto.AddressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/save")
    public ResponseEntity<String> saveAddress(@RequestBody
    AddressRequest addressRequest ){
        addressService.saveAddress(addressRequest);
        return  ResponseEntity.ok("Address saved");
    }
}
