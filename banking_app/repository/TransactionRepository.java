package net.javaguides.banking_app.repository;

import net.javaguides.banking_app.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    // All transactions (already added before)
    List<TransactionEntity> findByAccountIdOrderByTimestampDesc(Long accountId);

    // Mini statement → only last 5 transactions
    List<TransactionEntity> findTop5ByAccountIdOrderByTimestampDesc(Long accountId);
}
