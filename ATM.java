import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner sc = new Scanner(System.in);

        System.out.println("----- Welcome to the ATM -----");
        System.out.print("Enter User ID: ");
        String userId = sc.nextLine();

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        User currentUser = bank.authenticateUser(userId, pin);

        if (currentUser != null) {
            System.out.println("\nLogin Successful!\n");
            ATMOperations operations = new ATMOperations(currentUser, sc);
            operations.start();
        } else {
            System.out.println("Invalid credentials. Exiting...");
        }

        sc.close();
    }
}
