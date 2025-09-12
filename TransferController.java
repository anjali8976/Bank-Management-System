package net.javaguides.banking_app.controller;
import net.javaguides.banking_app.service.TransferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;  // covers RestController, RequestMapping, RequestBody, PostMapping

import net.javaguides.banking_app.dto.TransferDto;
import net.javaguides.banking_app.service.TransferService;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {
    @Autowired
    private TransferService transferService;

    @PostMapping
    public String transferMoney(@RequestBody TransferDto transferDto) {
        return transferService.transferMoney(transferDto);
    }
}
