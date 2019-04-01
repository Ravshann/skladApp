package uz.skladapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import uz.skladapp.controllers.excel.ExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController

public class MainController {


    @GetMapping("/user-excel")
    public ModelAndView download(HttpServletRequest request, HttpServletResponse response) {

        return new ModelAndView(new ExcelView());

    }

}
