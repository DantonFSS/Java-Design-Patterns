package com.spring_design_patterns_gof_based.service.impl;

import com.spring_design_patterns_gof_based.model.Address;
import com.spring_design_patterns_gof_based.model.AddressRepo;
import com.spring_design_patterns_gof_based.model.Client;
import com.spring_design_patterns_gof_based.model.ClientRepo;
import com.spring_design_patterns_gof_based.service.ClientService;
import com.spring_design_patterns_gof_based.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private ViaCepService cepService;

    @Override
    public Iterable<Client> searchAll() {
        return clientRepo.findAll();
    }
    @Override
    public Client searchForId(Long id) {
        Optional<Client> cli = clientRepo.findById(id);
        return cli.get();
    }
    @Override
    public void insert(Client client) {
        fillAndSaveClient(client);
    }
    @Override
    public void update(Long id, Client client) {
        Optional<Client> cliDb = clientRepo.findById(id);
        if (cliDb.isPresent()) {
            fillAndSaveClient(client);
        }
    }

    @Override
    public void delete(Long id) {
        clientRepo.deleteById(id);
    }

    private void fillAndSaveClient(Client client) {
        String cep = client.getAddress().getCep();
        Address address = addressRepo.findById(cep)
                .orElseGet(() -> {
                    Address newAddress = cepService.searchCep(cep);
                    addressRepo.save(newAddress);
                    return newAddress;
                });
        client.setAddress(address);
        clientRepo.save(client);
    }
}
