package com.ksundaysky.service;

import com.ksundaysky.model.Client;


import java.util.List;

public interface ClientService {

    public void saveClient(Client client);
    public List<Client> findAll();
    public Client findById(int id);
    public void updateClient(Client user);
    public void deleteById(int id);
}
