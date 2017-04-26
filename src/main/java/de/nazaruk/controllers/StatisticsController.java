package de.nazaruk.controllers;

import de.nazaruk.model.Statistics;
import de.nazaruk.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "/statistics", method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<Statistics> statistics() {
        Statistics statistics = statisticsService.getStatisticsForTheLast60Sec();

        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }
}
