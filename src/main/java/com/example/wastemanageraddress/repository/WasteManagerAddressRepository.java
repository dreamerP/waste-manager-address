package com.example.wastemanageraddress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.wastemanageraddress.entity.WasteManagerAddressEntity;




@Repository
public interface WasteManagerAddressRepository extends JpaRepository<WasteManagerAddressEntity, Long> {

}
