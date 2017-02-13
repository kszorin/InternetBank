package kszorin.internetbank.dao;

import kszorin.internetbank.models.Bill;

import java.util.List;

/**
 * Created by kszorin on 12.02.2017.
 */
public interface BillDao {
    void insert(Bill bill);

    Bill getById(Integer id);

    List<Bill> getAll();

    List<Bill> getAllByIdClient(Integer idClient);

    List<Integer> getBillIdsByIdClient(Integer idClient);
}
