package net.javaguides.banking_app.service;

import net.javaguides.banking_app.dto.TransferDto;

public interface TransferService {
    String transferMoney(TransferDto transferDto);
}
