package Taask2;

public class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String withdraw(double amount) {
        if (amount > bankAccount.getBalance()) {
            return "Insufficient funds. Transaction failed.";
        } else if (amount <= 0) {
            return "Invalid amount. Please enter a positive value.";
        } else {
            bankAccount.setBalance(bankAccount.getBalance() - amount);
            return String.format("Successfully withdrew $%.2f. Current balance: $%.2f", amount, bankAccount.getBalance());
        }
    }

    public String deposit(double amount) {
        if (amount <= 0) {
            return "Invalid amount. Please enter a positive value.";
        } else {
            bankAccount.setBalance(bankAccount.getBalance() + amount);
            return String.format("Successfully deposited $%.2f. Current balance: $%.2f", amount, bankAccount.getBalance());
        }
    }

    public String checkBalance() {
        return String.format("Your current balance is: $%.2f", bankAccount.getBalance());
    }
}
