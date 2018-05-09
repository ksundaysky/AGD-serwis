package com.ksundaysky.service;

import com.ksundaysky.model.Client;
import com.ksundaysky.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("clientService")
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(int id) { return clientRepository.findById(id);}

    @Override
    public void updateClient(Client client) {

        clientRepository.save(client);
    }

    @Override
    public void deleteById(int id) {

        clientRepository.deleteById(id);
        //userRepository.delete(Long.valueOf((long)id));
    }
}
