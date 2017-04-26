package de.nazaruk.controllers;

import de.nazaruk.model.Transaction;
import de.nazaruk.services.TransactionService;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Controller
public class TransactionsController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/transactions", method = RequestMethod.POST,
        produces = "application/json",
        consumes = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Transaction was added to the memory"),
            @ApiResponse(code = 204, message = "Transaction was not added to the memory")
    })
    public ResponseEntity<Void> transaction(
            @ApiParam(required = true)
            @RequestBody Transaction transactionRequest) {

        boolean wasAdded = transactionService.add(transactionRequest);

        return wasAdded ? new ResponseEntity<>(CREATED) : new ResponseEntity<>(NO_CONTENT);
    }

}