package edu.postech.csed409h;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Banking System Tests")
class BankingSystemTest {
    private Bank bank;
    private Account account1;
    private Account account2;

    // This method runs before each test method in this class.
    @BeforeEach
    void setUp() {
        System.out.println("Before Each - This runs before each test");
        bank = new Bank();
        account1 = new Account("John", 1000);
        account2 = new Account("Alice", 500);
        bank.addAccount(account1);
        bank.addAccount(account2);
    }

    @AfterEach
    void tearDown() {
        bank = null;
    }

    @Test
    @DisplayName("Test deposit and withdraw in an account")
    void testAccountDepositWithdraw() {
        // Hint: Deposit an amount and then assert that the balance has increased correctly.
        // Next, withdraw an amount and assert that the balance has decreased correctly.
        account1.deposit(1000);
        assertEquals(2000, account1.getBalance());
        account1.withdraw(500);
        assertEquals(1500,account1.getBalance());
    }

    @Test
    @DisplayName("Test transferring funds between accounts")
    void testTransferFunds() {
        // Hint: Implement a transfer of funds from one account to another and assert that both balances have updated correctly.
        Transaction.transferFunds(account1, account2, 400);
        assertEquals(600, account1.getBalance());
        assertEquals(900, account2.getBalance());
    }

    @Test
    @DisplayName("Test adding and retrieving accounts from the bank")
    void testAddRetrieveAccount() {
        // Hint: Add accounts to the bank and then retrieve them. Check if the retrieved accounts match the added accounts.
        Account account3 = new Account("Kate", 1200);
        bank.addAccount(account3);
        assertTrue(bank.getAllAccounts().contains(account3));
    }

    @Test
    @DisplayName("Test insufficient balance during transfer")
    void testInsufficientBalanceDuringTransfer() {
        // Hint: Attempt a transfer that should fail due to insufficient balance. Check that an exception is thrown.
        assertThrows(IllegalArgumentException.class, () ->{
            Transaction.transferFunds(account1, account2, 2000);
        });


    }

    @Test
    @DisplayName("Test negative deposit amount")
    void testNegativeDepositAmount() {
        // Hint: Attempt to deposit a negative amount. Check that an exception is thrown.
        assertThrows(IllegalArgumentException.class, () -> {
           account1.deposit(-500);
        });
    }

    @Test
    @DisplayName("Test negative withdraw amount")
    void testNegativeWithdrawAmount() {
      // Hint: Attempt to withdraw a negative amount. Check that an exception is thrown.
        assertThrows(IllegalArgumentException.class, () -> {
            account1.withdraw(-500);
        });
    }

    @Test
    @DisplayName("Test insufficient withdraw amount")
    void testInsufficientWithdrawAmount() {
        // Hint: Attempt to withdraw a negative amount. Check that an exception is thrown.
        assertThrows(IllegalArgumentException.class, () -> {
            account1.withdraw(2000);
        });
    }

    @Test
    @DisplayName("Test negative transfer amount")
    void testNegativeTransferAmount() {
        // Hint: Attempt to withdraw a negative amount. Check that an exception is thrown.
        assertThrows(IllegalArgumentException.class, () -> {
            Transaction.transferFunds(account1,account2,-500);
        });
    }
}