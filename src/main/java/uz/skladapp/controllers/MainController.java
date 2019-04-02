package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.skladapp.controllers.excel.ExcelReader;
import uz.skladapp.controllers.excel.ExcelView;
import uz.skladapp.dao.ClientDAO;
import uz.skladapp.model.Client;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController

public class MainController {
    @Autowired
    ClientDAO dao;


    @GetMapping("/user-excel")
    public ModelAndView download(HttpServletRequest request, HttpServletResponse response) {

        return new ModelAndView(new ExcelView());

    }

    @GetMapping("/excel")
    public void check() {
        ExcelReader excelReader = new ExcelReader();
        List<Client> excelClients = excelReader.listofClients();
        for (Client client : excelClients) {
            dao.clientCreate(client);
        }
    }


}
