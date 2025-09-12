package net.javaguides.banking_app.repository;

import net.javaguides.banking_app.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TransferRepository extends JpaRepository<TransferEntity, Long> {
}
