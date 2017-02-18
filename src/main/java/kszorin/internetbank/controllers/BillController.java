package kszorin.internetbank.controllers;

import kszorin.internetbank.dao.BillDao;
import kszorin.internetbank.dao.ClientDao;
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
    BillDao billDao;
    @Autowired
    ClientDao clientDao;

    @RequestMapping(value = "/{idClient}/bill-list", method = RequestMethod.GET)
    public ModelAndView showClientBills(@PathVariable(value="idClient") Integer idClient) {
        ModelAndView modelAndView = new ModelAndView();

//        Получаем и передаём инфу о клиенте.
        Client client = clientDao.getById(idClient);
        modelAndView.addObject("client", client);
//        Получаем и передаём список счетов.
        List billList = billDao.getAllByIdClient(idClient);
        modelAndView.addObject("billList", billList);
//        Для формы добавления нового счёта.
        modelAndView.addObject("newBill", new kszorin.internetbank.models.Bill());
//        Для списка id счетов.
        modelAndView.addObject("listBillIds", billDao.getBillIdsByIdClient(idClient));
//        Для формы добавления новой транзакции.
        modelAndView.addObject("newTransaction", new kszorin.internetbank.models.Transaction());

        modelAndView.setViewName("billList");
        return modelAndView;
    }

    @RequestMapping(value = "/{idClient}/bill-list", method = RequestMethod.POST)
    public ModelAndView addNewAndShowClientBills(@PathVariable(value="idClient") Integer idClient, @ModelAttribute("newBill") kszorin.internetbank.models.Bill newBill) {
        ModelAndView modelAndView = new ModelAndView();

        //вставляем в БД новый счёт
        billDao.insert(newBill);
//        Получаем и передаём инфу о клиенте.
        Client client = clientDao.getById(idClient);
        modelAndView.addObject("client", client);
//        Получаем и передаём список счетов.
        List billList = billDao.getAllByIdClient(idClient);
        modelAndView.addObject("billList", billList);
//        Для формы добавления нового счёта.
        modelAndView.addObject("newBill", new kszorin.internetbank.models.Bill());
//        Для списка id счетов.
        modelAndView.addObject("listBillIds", billDao.getBillIdsByIdClient(idClient));
//        Для формы добавления новой транзакции.
        modelAndView.addObject("newTransaction", new kszorin.internetbank.models.Transaction());

        modelAndView.setViewName("billList");
        return modelAndView;
    }


}
