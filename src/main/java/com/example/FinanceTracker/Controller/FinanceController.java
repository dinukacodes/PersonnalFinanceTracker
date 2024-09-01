package com.example.FinanceTracker.Controller;

import com.example.FinanceTracker.Models.Category;
import com.example.FinanceTracker.Models.Transaction;
import com.example.FinanceTracker.Models.User;

import com.example.FinanceTracker.SecurityConfigs;
import com.example.FinanceTracker.Service.CategoryService;
import com.example.FinanceTracker.Service.TransactionService;
import com.example.FinanceTracker.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/finance")
public class FinanceController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final TransactionService transactionService;
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    SecurityConfigs securityConfig;

    @Autowired
    public FinanceController(UserService userService, TransactionService transactionService, CategoryService categoryService) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.categoryService = categoryService;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @GetMapping(value="/index")
    @ResponseBody
    public String index() {
        return "WElcome to Student API V1.1.1";
    }

    // Login Page
    @GetMapping("/login")
    public String showLoginPage() {
        return "loginPage";  // Returns the loginPage.html Thymeleaf template
    }

    // Registration Page
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "registerPage";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "registerPage";
        }
        userService.addUser(user);
        redirectAttributes.addFlashAttribute("message", "Registration successful!");
        return "redirect:/finance/register";
    }

    // Dashboard Page
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        User currentUser = getCurrentUser();
        model.addAttribute("transactions", transactionService.getTransactionsByUser(currentUser));
        model.addAttribute("totalIncome", transactionService.getTotalIncomeByUser(currentUser));
        model.addAttribute("totalExpense", transactionService.getTotalExpensesByUser(currentUser));
        return "dashboardPage";
    }

    // Category Page
    @GetMapping("/category")
    public String showCategories(Model model) {
        User currentUser = getCurrentUser();
        List<Category> categories = categoryService.getCategoriesByUser(currentUser);
        model.addAttribute("categories", categories);
        model.addAttribute("category", new Category());
        return "categoryPage";
    }

    @PostMapping("/category")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "categoryPage";
        }
        category.setUser(getCurrentUser());
        categoryService.saveCategory(category);
        redirectAttributes.addFlashAttribute("message", "Category added successfully!");
        return "redirect:/finance/category";
    }

    // Add Transaction Page
    @GetMapping("/addtransaction")
    public String showAddTransactionPage(Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("categories", categoryService.getCategoriesByUser(getCurrentUser()));
        return "addTransactionPage";
    }

    @PostMapping("/addtransaction")
    public String addTransaction(@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "addTransactionPage";
        }
        transaction.setUser(getCurrentUser());
        transactionService.saveTransaction(transaction);
        redirectAttributes.addFlashAttribute("message", "Transaction added successfully!");
        return "redirect:/finance/transactionhistory";
    }

    // Transaction History Page
    @GetMapping("/transactionhistory")
    public String showTransactionHistory(Model model) {
        User currentUser = getCurrentUser();
        model.addAttribute("transactions", transactionService.getTransactionsByUser(currentUser));
        return "transactionHistoryPage";
    }
}
