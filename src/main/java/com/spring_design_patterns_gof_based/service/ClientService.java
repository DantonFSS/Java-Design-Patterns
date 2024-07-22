package com.spring_design_patterns_gof_based.service;

import com.spring_design_patterns_gof_based.model.Client;

public interface ClientService {

    Iterable<Client> searchAll();
    Client searchForId(Long id);

    void insert(Client client);
    void update(Long id, Client client);
    void delete(Long id);
}
