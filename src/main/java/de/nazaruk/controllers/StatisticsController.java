package de.nazaruk.controllers;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import de.nazaruk.model.Statistics;
import de.nazaruk.model.Transaction;
import de.nazaruk.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "/transactions", method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<Void> transaction(@RequestBody Transaction transactionRequest) {
        boolean wasAdded = statisticsService.add(transactionRequest);

        return wasAdded ? new ResponseEntity<>(CREATED) : new ResponseEntity<>(NO_CONTENT);
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Statistics> statistics() {
        Statistics statistics = statisticsService.getStatisticsForTheLast60Sec();
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }
}
