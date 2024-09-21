package com.spring_design_patterns_gof_based.model;

import jakarta.persistence.*;

@Entity
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    private AddressModel addressModel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressModel getAddress() {
        return addressModel;
    }

    public void setAddress(AddressModel addressModel) {
        this.addressModel = addressModel;
    }

}
