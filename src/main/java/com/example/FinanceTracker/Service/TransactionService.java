package com.example.FinanceTracker.Service;

import com.example.FinanceTracker.Models.Transaction;
import com.example.FinanceTracker.Models.TransactionType;
import com.example.FinanceTracker.Models.User;
import com.example.FinanceTracker.Repositories.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    TransactionRepo transactionRepo;

    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }
    public Transaction getTransactionById(long id) {
        return transactionRepo.findById(id).orElse(null);
    }
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }
    public void deleteTransaction(long id) {
         transactionRepo.deleteById(id);
    }
    public long updateTransaction(Transaction transaction) {
        return transactionRepo.save(transaction).getId();

    }
    public List<Transaction> getTransactionsByUser(User user) {
        return transactionRepo.findByUser(user);
    }

    public BigDecimal getTotalIncomeByUser(User user) {
        return transactionRepo.getTotalAmountByUserAndType(user, TransactionType.INCOME);
    }

    public BigDecimal getTotalExpensesByUser(User user) {
        return transactionRepo.getTotalAmountByUserAndType(user, TransactionType.EXPENSE);
    }
    public BigDecimal getNetBalanceByUser(User user) {
        BigDecimal totalIncome = getTotalIncomeByUser(user);
        BigDecimal totalExpenses = getTotalExpensesByUser(user);

        return totalIncome.subtract(totalExpenses);
    }


}
