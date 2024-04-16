package com.example.wastemanageraddress.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wastemanageraddress.core.service.WasteManagerAddressService;
import com.example.wastemanageraddress.entity.WasteManagerAddressEntity;
import com.example.wastemanageraddress.repository.WasteManagerAddressRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class WasteManagerAddressServiceImpl implements WasteManagerAddressService {

	@Autowired
	private WasteManagerAddressRepository wasteManagerAddressRepository;

	@Override
	public void create(WasteManagerAddressEntity wasteManagerAddress) {
		wasteManagerAddressRepository.save(wasteManagerAddress);
	}

	@Override
	public void update(WasteManagerAddressEntity wasteManagerAddress) {
		WasteManagerAddressEntity temp = wasteManagerAddressRepository.findById(wasteManagerAddress.getId()).get();
		temp.setDireccion(wasteManagerAddress.getDireccion());
		temp.setIsEnabled(wasteManagerAddress.getIsEnabled());
		temp.setVersion(wasteManagerAddress.getVersion());
		wasteManagerAddressRepository.save(temp);
	}

	@Override
	public WasteManagerAddressEntity findById(Long id) {

		if (wasteManagerAddressRepository.existsById(id)) {
			return wasteManagerAddressRepository.findById(id).get();
		} else {
			return new WasteManagerAddressEntity();
		}
	}

	@Override
	public void delete(Long id) {
		if (wasteManagerAddressRepository.existsById(id)) {
			wasteManagerAddressRepository.deleteById(id);
		} else {
			throw new EntityNotFoundException("Waste Manager Address with id " + id + " not found");
		}
	}

	@Override
	public List<WasteManagerAddressEntity> listAll() {
		return wasteManagerAddressRepository.findAll();
	}

}
