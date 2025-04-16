package edu.postech.csed409h;

public class Transaction {
    public static void transferFunds(Account from, Account to, double amount) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Both source and destination accounts must be valid");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        if (from.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance for the transfer");
        }
        from.withdraw(amount);
        to.deposit(amount);
    }
}
