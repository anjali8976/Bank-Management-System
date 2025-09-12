package net.javaguides.banking_app.service.impl;

import net.javaguides.banking_app.entity.TransactionEntity;
import net.javaguides.banking_app.repository.TransactionRepository;
import net.javaguides.banking_app.dto.TransferDto;
import net.javaguides.banking_app.entity.Account;
import net.javaguides.banking_app.entity.TransferEntity;
import net.javaguides.banking_app.repository.AccountRepository;
import net.javaguides.banking_app.repository.TransferRepository;
import net.javaguides.banking_app.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
@Service
public class TransferServiceimpl implements TransferService{
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    public String transferMoney(TransferDto transferDto) {
        Account fromAccount = accountRepository.findById(transferDto.getFromAccountId())
                .orElseThrow(() -> new RuntimeException("Sender account not found"));
        Account toAccount = accountRepository.findById(transferDto.getToAccountId())
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        if (fromAccount.getBalance() < transferDto.getAmount()) {
            return "Insufficient balance!";
        }

        // Deduct & Add money
        fromAccount.setBalance(fromAccount.getBalance() - transferDto.getAmount());
        toAccount.setBalance(toAccount.getBalance() + transferDto.getAmount());

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Save transfer record
        TransferEntity transfer = new TransferEntity();
        transfer.setFromAccountId(fromAccount.getId());
        transfer.setToAccountId(toAccount.getId());
        transfer.setAmount(transferDto.getAmount());
        transfer.setTimestamp(LocalDateTime.now());
        transfer.setStatus("SUCCESS");

        transferRepository.save(transfer);



// Save Transaction
        TransactionEntity tx1 = new TransactionEntity();
        tx1.setAccountId(fromAccount.getId());
        tx1.setType("TRANSFER-DEBIT");
        tx1.setAmount(transferDto.getAmount());
        tx1.setTimestamp(LocalDateTime.now());
        tx1.setStatus("SUCCESS");
        transactionRepository.save(tx1);

        TransactionEntity tx2 = new TransactionEntity();
        tx2.setAccountId(toAccount.getId());
        tx2.setType("TRANSFER-CREDIT");
        tx2.setAmount(transferDto.getAmount());
        tx2.setTimestamp(LocalDateTime.now());
        tx2.setStatus("SUCCESS");
        transactionRepository.save(tx2);
        return "Transfer successful!";
    }
}
