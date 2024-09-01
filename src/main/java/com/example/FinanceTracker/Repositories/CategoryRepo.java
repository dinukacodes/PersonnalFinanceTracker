package com.example.FinanceTracker.Repositories;

import com.example.FinanceTracker.Models.Category;
import com.example.FinanceTracker.Models.Transaction;
import com.example.FinanceTracker.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    List<Category> findByUser(User user);
}
