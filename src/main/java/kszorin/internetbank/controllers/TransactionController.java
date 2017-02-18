package kszorin.internetbank.controllers;

import kszorin.internetbank.implementations.TransactionDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zorin on 17.02.2017.
 */
public class TransactionController {

    @Autowired
    TransactionDaoImpl transactionDaoImpl;

    @RequestMapping(value = "/client-bills/{idClient}/add-transaction", method = RequestMethod.POST)
    public ModelAndView addTransaction(@PathVariable(value="idClient") Integer idClient, @ModelAttribute("transactionlModel") kszorin.internetbank.models.Transaction transaction) {
        ModelAndView modelAndView = new ModelAndView();
        //получаем и передаём инфу о клиенте
//        Client client = clientDao.getById(idClient);
//        modelAndView.addObject("client", client);

        //получаем и передаём список счетов
//        List bills = billDao.getAllByIdClient(idClient);
//        modelAndView.addObject("billList", bills);
        //для формы добавления нового счёта
        modelAndView.addObject("billModel", new kszorin.internetbank.models.Bill());

        //для списка id счетов
//        modelAndView.addObject("listBillIds", billDao.getBillIdsByIdClient(idClient));
        //для формы добавления новой транзакции
        modelAndView.addObject("transactionModel", new kszorin.internetbank.models.Transaction());

        modelAndView.setViewName("billList");
        return modelAndView;
    }
}
