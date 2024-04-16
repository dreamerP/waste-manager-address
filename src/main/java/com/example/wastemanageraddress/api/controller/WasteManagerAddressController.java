package com.example.wastemanageraddress.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.wastemanageraddress.core.service.WasteManagerAddressService;
import com.example.wastemanageraddress.entity.WasteManagerAddressEntity;
import com.example.wastemanageraddress.response.WasteManagerAddressResponse;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/wasteManagerAddresses")
public class WasteManagerAddressController {

    @Autowired
    private WasteManagerAddressService wasteManagerAddressService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody WasteManagerAddressResponse wasteManagerAddressDto, BindingResult bindingResult)
            throws Exception {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        try {
            WasteManagerAddressEntity wasteManagerAddressEntity = modelMapper.map(wasteManagerAddressDto, WasteManagerAddressEntity.class);
            wasteManagerAddressService.create(wasteManagerAddressEntity);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody WasteManagerAddressResponse wasteManagerAddressDto, BindingResult bindingResult)
            throws Exception {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        try {
            WasteManagerAddressEntity wasteManagerAddressEntity = modelMapper.map(wasteManagerAddressDto, WasteManagerAddressEntity.class);
            wasteManagerAddressService.update(wasteManagerAddressEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<?> findById(@PathVariable Long addressId) throws Exception {
        try {
            WasteManagerAddressEntity wasteManagerAddressEntity = wasteManagerAddressService.findById(addressId);
            if (wasteManagerAddressEntity != null) {
                WasteManagerAddressResponse wasteManagerAddressDto = modelMapper.map(wasteManagerAddressEntity, WasteManagerAddressResponse.class);
                return new ResponseEntity<>(wasteManagerAddressDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Waste manager address not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> delete(@PathVariable Long addressId) throws Exception {
        try {
            wasteManagerAddressService.delete(addressId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> listAll() throws Exception {
        try {
            List<WasteManagerAddressEntity> addresses = wasteManagerAddressService.listAll();
            List<WasteManagerAddressResponse> addressDtos = addresses.stream()
                    .map(address -> modelMapper.map(address, WasteManagerAddressResponse.class))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(addressDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

