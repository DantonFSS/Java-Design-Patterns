package com.spring_design_patterns_gof_based.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends CrudRepository<AddressModel, String> {

}
