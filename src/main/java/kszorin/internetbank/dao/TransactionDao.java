package kszorin.internetbank.dao;

import kszorin.internetbank.models.Transaction;

import java.util.List;

/**
 * Created by kszorin on 13.02.2017.
 */
public interface TransactionDao {
    void insert(Transaction transaction);

    Transaction getById(Integer id);

    List<Transaction> getAll();

    List<Transaction> getAllByIdBill(Integer idBill);

    List<Transaction> getAllByIdClient(Integer idClient);
}
