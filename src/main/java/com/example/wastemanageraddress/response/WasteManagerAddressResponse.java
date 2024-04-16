package com.example.wastemanageraddress.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class WasteManagerAddressResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String direccion;

    @Getter
    @Setter
    private Boolean isEnabled;

    public WasteManagerAddressResponse() {
    }

    public WasteManagerAddressResponse(String direccion, Boolean isEnabled) {
        this.direccion = direccion;
        this.isEnabled = isEnabled;
    }
}
