package kszorin.internetbank.controllers;

import kszorin.internetbank.dao.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by kszorin on 11.02.2017.
 */
//@Validated
@Controller
public class ClientController {
    @Autowired
    ClientDao clientDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showClientList() {
        ModelAndView modelAndView = new ModelAndView();
//        Получаем из БД список клиентов и передаём в View.
        List clientList = clientDao.getAll();
        modelAndView.addObject("clientList", clientList);
//        Создаём нового клиента для формы добавления нового клиента.
        modelAndView.addObject("newClient", new kszorin.internetbank.models.Client());
        modelAndView.setViewName("clientList");
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView addNewAndShowClientList(@ModelAttribute("newClient") kszorin.internetbank.models.Client newClient) {
        ModelAndView modelAndView = new ModelAndView();
//        Проверка на добавление нового пользователя.
        if (newClient.getSurname()!= null) {
            String result="";
//        Проверка на пустые поля.
            if ((newClient.getSurname().trim().isEmpty() || newClient.getName().trim().isEmpty() ||
                    newClient.getPatronymic().trim().isEmpty() || newClient.getAddress().trim().isEmpty())) {
                result = "error";
            } else {
//        Вставляем в БД нового клиента.
                clientDao.insert(newClient);
                result = "success";
            }
            modelAndView.addObject("resultString", result);
        }

//        Получаем из БД список клиентов и передаём в View.
        List clientList = clientDao.getAll();
        modelAndView.addObject("clientList", clientList);
//        Создаём нового клиента для формы добавления нового клиента.
        modelAndView.addObject("newClient", new kszorin.internetbank.models.Client());
        modelAndView.setViewName("clientList");
        return modelAndView;
    }
}
