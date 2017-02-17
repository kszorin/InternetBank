package kszorin.internetbank.controllers;

import kszorin.internetbank.implementations.BillDaoImpl;
import kszorin.internetbank.implementations.ClientDaoImpl;
import kszorin.internetbank.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by zorin on 17.02.2017.
 */
@Controller
@RequestMapping(value = "/clients")
public class BillController {
    @Autowired
    BillDaoImpl billDaoImpl;
    @Autowired
    ClientDaoImpl clientDaoImpl;

    @RequestMapping(value = "/{idClient}/bill-list", method = RequestMethod.POST)
    public ModelAndView showClientBills(@PathVariable(value="idClient") Integer idClient, @ModelAttribute("newBill") kszorin.internetbank.models.Bill newBill) {
        ModelAndView modelAndView = new ModelAndView();

        //вставляем в БД новый счёт
        billDaoImpl.insert(newBill);

//        Получаем и передаём инфу о клиенте.
        Client client = clientDaoImpl.getById(idClient);
        modelAndView.addObject("client", client);
//        Получаем и передаём список счетов.
        List billList = billDaoImpl.getAllByIdClient(idClient);
        modelAndView.addObject("billList", billList);
//        Для формы добавления нового счёта.
        modelAndView.addObject("newBill", new kszorin.internetbank.models.Bill());
//        Для списка id счетов.
        modelAndView.addObject("listBillIds", billDaoImpl.getBillIdsByIdClient(idClient));
//        Для формы добавления новой транзакции.
        modelAndView.addObject("newTransaction", new kszorin.internetbank.models.Transaction());

        modelAndView.setViewName("billList");
        return modelAndView;
    }


}
