package kszorin.internetbank.dao;

import kszorin.internetbank.models.Client;

import java.util.List;

/**
 * Created by kszorin on 12.02.2017.
 */
public interface ClientDao {
    void insert(Client client);

    Client getById(Integer id);

    List<Client> getAll();
}
