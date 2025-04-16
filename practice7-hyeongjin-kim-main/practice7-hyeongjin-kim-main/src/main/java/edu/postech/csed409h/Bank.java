package edu.postech.csed409h;


import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Account> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAllAccounts() {
        return accounts;
    }
}

