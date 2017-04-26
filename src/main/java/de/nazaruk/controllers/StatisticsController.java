package de.nazaruk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StatisticsController {

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String statistics() {
        return "";
    }
}
