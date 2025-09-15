import java.util.Scanner;

public class ATMOperations {
    private User user;
    private Scanner sc;

    public ATMOperations(User user, Scanner sc) {
        this.user = user;
        this.sc = sc;
    }

    public void start() {
        int choice;
        do {
            System.out.println("\n----- ATM Menu -----");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    private void showTransactionHistory() {
        System.out.println("\n--- Transaction History ---");
        for (Transaction t : user.getTransactionHistory()) {
            System.out.println(t);
        }
        if (user.getTransactionHistory().isEmpty()) {
            System.out.println("No transactions yet.");
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: $");
        double amount = sc.nextDouble();
        if (user.withdraw(amount)) {
            System.out.println("Withdrawal successful. Current balance: $" + user.getBalance());
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: $");
        double amount = sc.nextDouble();
        user.deposit(amount);
        System.out.println("Deposit successful. Current balance: $" + user.getBalance());
    }

    private void transfer() {
        System.out.print("Enter recipient User ID: ");
        sc.nextLine(); // consume newline
        String recipientId = sc.nextLine();
        Bank bank = new Bank(); // create a temporary bank instance to get user (should be shared in real case)
        User recipient = bank.getUser(recipientId);

        if (recipient == null) {
            System.out.println("Recipient not found.");
            return;
        }

        System.out.print("Enter amount to transfer: $");
        double amount = sc.nextDouble();

        if (user.transfer(recipient, amount)) {
            System.out.println("Transfer successful. Current balance: $" + user.getBalance());
        } else {
            System.out.println("Insufficient balance. Transfer failed.");
        }
    }
}
