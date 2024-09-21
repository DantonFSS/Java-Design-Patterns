package com.spring_design_patterns_gof_based.service;

import com.spring_design_patterns_gof_based.model.AddressModel;
import com.spring_design_patterns_gof_based.model.AddressRepo;
import com.spring_design_patterns_gof_based.model.ClientModel;
import com.spring_design_patterns_gof_based.model.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private ViaCepService cepService;

    public Iterable<ClientModel> searchAll() {
        return clientRepo.findAll();
    }

    public ClientModel searchForId(Long id) {
        Optional<ClientModel> cli = clientRepo.findById(id);
        return cli.get();
    }

    public void insert(ClientModel clientModel) {
        fillAndSaveClient(clientModel);
    }

    public void update(Long id, ClientModel clientModel) {
        Optional<ClientModel> cliDb = clientRepo.findById(id);
        if (cliDb.isPresent()) {
            fillAndSaveClient(clientModel);
        }
    }


    public void delete(Long id) {
        clientRepo.deleteById(id);
    }

    private void fillAndSaveClient(ClientModel clientModel) {
        if (clientModel.getAddress() == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
        String cep = clientModel.getAddress().getCep();
        AddressModel addressModel = addressRepo.findById(cep)
                .orElseGet(() -> {
                    AddressModel newAddressModel = cepService.searchCep(cep);
                    addressRepo.save(newAddressModel);
                    return newAddressModel;
                });
        clientModel.setAddress(addressModel);
        clientRepo.save(clientModel);
    }

}
