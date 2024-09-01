package com.example.FinanceTracker.Repositories;


import com.example.FinanceTracker.Models.Transaction;
import com.example.FinanceTracker.Models.TransactionType;
import com.example.FinanceTracker.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Long> {
    List<Transaction> findByUser(User user);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user = :user AND t.transactionType = :type")
    BigDecimal getTotalAmountByUserAndType(@Param("user")User user, @Param("type") TransactionType type);


}
