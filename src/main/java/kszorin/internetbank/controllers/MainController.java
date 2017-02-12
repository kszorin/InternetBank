package kszorin.internetbank.controllers;

import kszorin.internetbank.implementations.BillDaoImpl;
import kszorin.internetbank.implementations.ClientDaoImpl;
import kszorin.internetbank.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by kszorin on 11.02.2017.
 */
@Controller
public class MainController {
    @Autowired
    ClientDaoImpl clientDaoImpl;
    @Autowired
    BillDaoImpl billDaoImpl;

    /*First method on start application*/
    /*Попадаем сюда на старте приложения (см. параметры аннотации и настройки пути после деплоя) */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allClients() {
        ModelAndView modelAndView = new ModelAndView();
        //получаем и передаём список клиентов
        List clients = clientDaoImpl.getAll();
        modelAndView.addObject("clientList", clients);
        //для формы добавления нового клиента
        modelAndView.addObject("clientModel", new kszorin.internetbank.models.Client());

        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/add-client", method = RequestMethod.GET)
    public ModelAndView addClient(@ModelAttribute("clientModel") kszorin.internetbank.models.Client client) {
        ModelAndView modelAndView = new ModelAndView();
        //вставляем в БД нового клиента
        clientDaoImpl.insert(client);
        //получаем и передаём список клиентов
        List clients = clientDaoImpl.getAll();
        modelAndView.addObject("clientList", clients);
        //для формы добавления нового клиента
        modelAndView.addObject("clientModel", new kszorin.internetbank.models.Client());

        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/client-bills/{idClient}", method = RequestMethod.GET)
    public ModelAndView showClientBills(@PathVariable(value="idClient") Integer idClient) {
        ModelAndView modelAndView = new ModelAndView();
        //получаем и передаём инфу о клиенте
        Client client = clientDaoImpl.getById(idClient);
        modelAndView.addObject("client", client);
        //получаем и передаём список счетов
        List bills = billDaoImpl.getAllByIdClient(idClient);
        modelAndView.addObject("billList", bills);

        modelAndView.setViewName("clientBills");
        return modelAndView;
    }
}
