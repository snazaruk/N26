package de.nazaruk.statistics.controller;

import de.nazaruk.statistics.model.Statistics;
import de.nazaruk.statistics.model.Transaction;
import de.nazaruk.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Controller
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "/transactions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
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
