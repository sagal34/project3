import java.util.Scanner;
import java.util.ArrayList;
public class PROJECT {
    // Constants
    private static final String EVC_CODE = "*770#";
    private static final int CORRECT_PIN = 5050;

    // User data
    private static double balance = 1000.0;
    private static ArrayList<String> transactionHistory = new ArrayList<>();

    // Scanner for input
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter EVC code: ");
        String code = input.nextLine();

        if (code.equals(EVC_CODE)) {
            authenticateUser();
        } else {
            System.out.println("Invalid EVC code. Please try again.");
        }
    }

    private static void authenticateUser() {
        System.out.print("\n-EVCPLUS-\nEnter your PIN: ");
        int enteredPin = input.nextInt();
        input.nextLine();

        if (enteredPin == CORRECT_PIN) {
            showMainMenu();
        } else {
            System.out.println("Incorrect PIN. Please try again.");
        }
    }

    private static void showMainMenu() {
        boolean continueMenu = true;

        while (continueMenu) {
            System.out.println("\n------ EVCPlus ------");
            System.out.println("1. i tus haraagaaga");
            System.out.println("2. kaarka ku hadalka");
            System.out.println("3. bixi Biil");
            System.out.println("4. u wareeji EVCPlus");
            System.out.println("5. warbixin kooban");
            System.out.println("6. Salaam Bank");
            System.out.println("7. Maaraynta");
            System.out.println("8. Bill Payment");
            System.out.println("0. kabax");
            System.out.print("Dooro mid kamida: ");

            int option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    mobileServicesMenu();
                    break;
                case 3:
                    payBillMenu();
                    break;
                case 4:
                    transferToEVCPlus();
                    break;
                case 5:
                    miniStatementMenu();
                    break;
                case 6:
                    salaamBankMenu();
                    break;
                case 7:
                    managementMenu();
                    break;
                case 8:
                    billPaymentMenu();
                    break;
                case 0:
                    continueMenu = false;
                    System.out.println("macsalama. waad ku mahadan tahy isticmaalidaa adeega EVCPlus.");
                    break;
                default:
                    System.out.println("option qaldan. fadlan ku celi markle.");
            }
        }
    }

    private static void checkBalance() {
        System.out.println("Your balance is: $" + balance);
        addTransaction("Balance checked");
    }

    private static void mobileServicesMenu() {
        System.out.println("\nMobile Services");
        System.out.println("1. Top Up Airtime");
        System.out.println("2. Send Airtime");
        System.out.println("3. MIFI Packages");
        System.out.println("4. Internet Bundle");
        System.out.println("5. Send Money (MMT)");
        System.out.print("Select option (1-5): ");

        int subOption = input.nextInt();
        input.nextLine();

        switch (subOption) {
            case 1:
                topUpAirtime();
                break;
            case 2:
                sendAirtime();
                break;
            case 3:
                mifiPackagesMenu();
                break;
            case 4:
                internetBundle();
                break;
            case 5:
                sendMoneyMMT();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void topUpAirtime() {
        System.out.print("Enter mobile number: ");
        String mobile = input.nextLine();
        System.out.print("Enter amount: $");
        double amount = input.nextDouble();
        input.nextLine();

        if (confirmTransaction("top up $" + amount + " to " + mobile)) {
            if (deductAmount(amount)) {
                System.out.println("Airtime topped up successfully. New balance: $" + balance);
                addTransaction("Topped up $" + amount + " to " + mobile);
            }
        } else {
            System.out.println("Top up cancelled.");
        }
    }

    private static void sendAirtime() {
        System.out.print("Enter recipient mobile number: ");
        String mobile = input.nextLine();
        System.out.print("Enter amount: $");
        double amount = input.nextDouble();
        input.nextLine();

        if (confirmTransaction("send $" + amount + " to " + mobile)) {
            if (deductAmount(amount)) {
                System.out.println("Airtime sent successfully. New balance: $" + balance);
                addTransaction("Sent $" + amount + " to " + mobile);
            }
        } else {
            System.out.println("Transfer cancelled.");
        }
    }

    private static void mifiPackagesMenu() {
        System.out.println("\n-- Internet Bundle Recharge --");
        System.out.println("1. Weekly");
        System.out.println("2. Daily");
        System.out.println("3. Monthly");
        System.out.print("Select option (1-3): ");

        int bundleType = input.nextInt();
        input.nextLine();

        switch (bundleType) {
            case 1:
                System.out.println("Select weekly bundle:");
                System.out.println("1. $5 = 10 GB");
                System.out.println("2. $10 = 25 GB");
                System.out.print("Select option (1-2): ");
                int weeklyOption = input.nextInt();
                input.nextLine();

                if (weeklyOption == 1) {
                    purchaseBundle(5, "10GB Weekly");
                } else if (weeklyOption == 2) {
                    purchaseBundle(10, "25GB Weekly");
                } else {
                    System.out.println("Invalid option.");
                }
                break;

            case 2:
                System.out.println("Select daily bundle:");
                System.out.println("1. $1 = 2 GB");
                System.out.println("2. $2 = 5 GB");
                System.out.print("Select option (1-2): ");
                int dailyOption = input.nextInt();
                input.nextLine();

                if (dailyOption == 1) {
                    purchaseBundle(1, "2GB Daily");
                } else if (dailyOption == 2) {
                    purchaseBundle(2, "5GB Daily");
                } else {
                    System.out.println("Invalid option.");
                }
                break;

            case 3:
                System.out.println("Select monthly bundle:");
                System.out.println("1. $20 = 40 GB");
                System.out.println("2. $40 = 85 GB");
                System.out.println("3. $60 = 150 GB");
                System.out.println("4. $25 = Monthly Unlimited");
                System.out.print("Select option (1-4): ");
                int monthlyOption = input.nextInt();
                input.nextLine();

                if (monthlyOption == 1) {
                    purchaseBundle(20, "40GB Monthly");
                } else if (monthlyOption == 2) {
                    purchaseBundle(40, "85GB Monthly");
                } else if (monthlyOption == 3) {
                    purchaseBundle(60, "150GB Monthly");
                } else if (monthlyOption == 4) {
                    purchaseBundle(25, "Monthly Unlimited");
                } else {
                    System.out.println("Invalid option.");
                }
                break;

            default:
                System.out.println("Invalid option.");
        }
    }

    private static void purchaseBundle(double cost, String description) {
        if (confirmTransaction("purchase " + description + " for $" + cost)) {
            if (deductAmount(cost)) {
                System.out.println("You have successfully purchased " + description + ". New balance: $" + balance);
                addTransaction("Purchased " + description + " for $" + cost);
            }
        } else {
            System.out.println("Purchase cancelled.");
        }
    }

    private static void internetBundle() {
        System.out.print("Enter mobile number: ");
        String mobile = input.nextLine();
        System.out.print("Enter internet amount: $");
        double amount = input.nextDouble();
        input.nextLine();

        if (confirmTransaction("purchase internet bundle of $" + amount + " for " + mobile)) {
            if (deductAmount(amount)) {
                System.out.println("Internet bundle purchased successfully. New balance: $" + balance);
                addTransaction("Internet bundle $" + amount + " for " + mobile);
            }
        } else {
            System.out.println("Purchase cancelled.");
        }
    }

    private static void sendMoneyMMT() {
        System.out.print("Enter recipient mobile number (MMT): ");
        String mobile = input.nextLine();
        System.out.print("Enter amount: $");
        double amount = input.nextDouble();
        input.nextLine();

        if (confirmTransaction("send $" + amount + " to " + mobile + " via MMT")) {
            if (deductAmount(amount)) {
                System.out.println("Money sent successfully via MMT. New balance: $" + balance);
                addTransaction("Sent $" + amount + " to " + mobile + " via MMT");
            }
        } else {
            System.out.println("Transfer cancelled.");
        }
    }

    private static void payBillMenu() {
        System.out.println("\n--- Pay Bill ---");
        System.out.println("1. Post Paid");
        System.out.println("2. Merchant Payment");
        System.out.print("Select option (1-2): ");

        int billOption = input.nextInt();
        input.nextLine();

        switch (billOption) {
            case 1:
                postPaidMenu();
                break;
            case 2:
                merchantPayment();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void postPaidMenu() {
        System.out.println("\n-- Post Paid --");
        System.out.println("1. View Bill");
        System.out.println("2. Pay Full Bill");
        System.out.println("3. Pay Partial Bill");
        System.out.print("Select option (1-3): ");

        int postPaidOption = input.nextInt();
        input.nextLine();

        switch (postPaidOption) {
            case 1:
                double postPaidBill = 25.0;
                System.out.println("Your post paid bill is: $" + postPaidBill);
                addTransaction("Viewed post paid bill");
                break;
            case 2:
                payFullPostPaidBill();
                break;
            case 3:
                payPartialPostPaidBill();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void payFullPostPaidBill() {
        double fullBill = 25.0;
        if (confirmTransaction("pay full post paid bill of $" + fullBill)) {
            if (deductAmount(fullBill)) {
                System.out.println("Post paid bill paid successfully. New balance: $" + balance);
                addTransaction("Paid full post paid bill $" + fullBill);
            }
        } else {
            System.out.println("Payment cancelled.");
        }
    }

    private static void payPartialPostPaidBill() {
        System.out.print("Enter amount to pay: $");
        double partialPay = input.nextDouble();
        input.nextLine();

        if (partialPay > 0) {
            if (confirmTransaction("pay $" + partialPay + " towards post paid bill")) {
                if (deductAmount(partialPay)) {
                    System.out.println("Partial payment of $" + partialPay + " successful. New balance: $" + balance);
                    addTransaction("Paid $" + partialPay + " towards post paid bill");
                }
            } else {
                System.out.println("Payment cancelled.");
            }
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private static void merchantPayment() {
        System.out.println("\n--- Merchant Payment ---");
        System.out.print("Enter business ID: ");
        String businessId = input.nextLine();
        System.out.print("Enter business name: ");
        String businessName = input.nextLine();
        System.out.print("Enter payment amount: $");
        double payAmount = input.nextDouble();
        input.nextLine();

        if (confirmTransaction("pay $" + payAmount + " to " + businessName)) {
            if (deductAmount(payAmount)) {
                System.out.println("Payment to " + businessName + " successful. New balance: $" + balance);
                addTransaction("Paid $" + payAmount + " to " + businessName + " (ID: " + businessId + ")");
            }
        } else {
            System.out.println("Payment cancelled.");
        }
    }

    private static void transferToEVCPlus() {
        System.out.print("Enter mobile number: ");
        String sendTo = input.nextLine();
        System.out.print("Enter amount: $");
        double amount = input.nextDouble();
        input.nextLine();

        if (confirmTransaction("transfer $" + amount + " to " + sendTo)) {
            if (deductAmount(amount)) {
                System.out.println("Transfer of $" + amount + " to " + sendTo + " successful.");
                System.out.println("[-EVCPLUS-] New balance: $" + balance);
                addTransaction("Transferred $" + amount + " to " + sendTo);
            }
        } else {
            System.out.println("Transfer cancelled.");
        }
    }

    private static void miniStatementMenu() {
        System.out.println("\nMini Statement");
        System.out.println("1. Last Action");
        System.out.println("2. Last Transfer");
        System.out.println("3. Last Purchase");
        System.out.println("4. Last 3 Actions");
        System.out.println("5. Email Statement");
        System.out.print("Select option (1-5): ");

        int subOption = input.nextInt();
        input.nextLine();

        switch (subOption) {
            case 1:
                showLastTransaction();
                break;
            case 2:
                showLastTransfer();
                break;
            case 3:
                showLastPurchase();
                break;
            case 4:
                showLastThreeTransactions();
                break;
            case 5:
                emailStatement();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void showLastTransaction() {
        if (!transactionHistory.isEmpty()) {
            System.out.println("[-EVCPLUS-] Last transaction:");
            System.out.println(transactionHistory.get(transactionHistory.size() - 1));
        } else {
            System.out.println("No transactions found.");
        }
    }

    private static void showLastTransfer() {
        // Simplified version - in a real app, you would filter transfer transactions
        if (!transactionHistory.isEmpty()) {
            System.out.println("Last transfer:");
            System.out.println(transactionHistory.get(transactionHistory.size() - 1));
        } else {
            System.out.println("No transfers found.");
        }
    }

    private static void showLastPurchase() {
        // Simplified version - in a real app, you would filter purchase transactions
        if (!transactionHistory.isEmpty()) {
            System.out.println("Last purchase:");
            System.out.println(transactionHistory.get(transactionHistory.size() - 1));
        } else {
            System.out.println("No purchases found.");
        }
    }

    private static void showLastThreeTransactions() {
        if (transactionHistory.size() >= 3) {
            System.out.println("Last 3 transactions:");
            for (int i = transactionHistory.size() - 1; i >= transactionHistory.size() - 3; i--) {
                System.out.println(transactionHistory.get(i));
            }
        } else if (!transactionHistory.isEmpty()) {
            System.out.println("All transactions:");
            for (int i = transactionHistory.size() - 1; i >= 0; i--) {
                System.out.println(transactionHistory.get(i));
            }
        } else {
            System.out.println("No transactions found.");
        }
    }

    private static void emailStatement() {
        System.out.print("Enter your email: ");
        String email = input.nextLine();
        System.out.print("Enter start date (e.g. 01/04/2025): ");
        String startDate = input.nextLine();
        System.out.print("Enter end date (e.g. 30/04/2025): ");
        String endDate = input.nextLine();

        System.out.println("Your statement will be sent to " + email +
                " for the period from " + startDate + " to " + endDate);
        addTransaction("Requested statement to be emailed to " + email);
    }

    private static void salaamBankMenu() {
        System.out.println("\n--- Salaam Bank ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer to EVCPlus");
        System.out.print("Select option (1-4): ");

        int bankOption = input.nextInt();
        input.nextLine();

        switch (bankOption) {
            case 1:
                double bankBalance = 300.0;
                System.out.println("Your Salaam Bank balance is: $" + bankBalance);
                addTransaction("Checked Salaam Bank balance");
                break;
            case 2:
                depositToBank();
                break;
            case 3:
                withdrawFromBank();
                break;
            case 4:
                transferFromBank();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void depositToBank() {
        System.out.print("Enter amount to deposit: $");
        double deposit = input.nextDouble();
        input.nextLine();

        if (confirmTransaction("deposit $" + deposit + " to Salaam Bank")) {
            if (deductAmount(deposit)) {
                System.out.println("Deposit of $" + deposit + " to Salaam Bank successful.");
                System.out.println("EVCPlus balance: $" + balance);
                addTransaction("Deposited $" + deposit + " to Salaam Bank");
            }
        } else {
            System.out.println("Deposit cancelled.");
        }
    }

    private static void withdrawFromBank() {
        System.out.print("Enter amount to withdraw: $");
        double withdraw = input.nextDouble();
        input.nextLine();

        if (withdraw > 0) {
            balance += withdraw;
            System.out.println("Withdrawal of $" + withdraw + " from Salaam Bank successful.");
            System.out.println("EVCPlus balance: $" + balance);
            addTransaction("Withdrew $" + withdraw + " from Salaam Bank");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private static void transferFromBank() {
        System.out.print("Enter amount to transfer from Salaam Bank: $");
        double transferToBank = input.nextDouble();
        input.nextLine();

        if (confirmTransaction("transfer $" + transferToBank + " from Salaam Bank")) {
            if (deductAmount(transferToBank)) {
                System.out.println("Transfer of $" + transferToBank + " from Salaam Bank successful.");
                System.out.println("EVCPlus balance: $" + balance);
                addTransaction("Transferred $" + transferToBank + " from Salaam Bank");
            }
        } else {
            System.out.println("Transfer cancelled.");
        }
    }

    private static void managementMenu() {
        System.out.println("\nManagement");
        System.out.println("1. Change PIN");
        System.out.println("2. Change Language");
        System.out.println("3. Report Lost Mobile");
        System.out.println("4. Block Funds");
        System.out.println("5. Request Refund");
        System.out.print("Select option (1-5): ");

        int option = input.nextInt();
        input.nextLine();

        switch (option) {
            case 1:
                changePin();
                break;
            case 2:
                changeLanguage();
                break;
            case 3:
                reportLostMobile();
                break;
            case 4:
                blockFunds();
                break;
            case 5:
                requestRefund();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void changePin() {
        System.out.print("Enter new PIN: ");
        String newPin = input.nextLine();
        System.out.print("Confirm new PIN: ");
        String confirmPin = input.nextLine();

        if (newPin.equals(confirmPin)) {
            System.out.println("[-EVCPlus-] PIN changed successfully");
            addTransaction("Changed PIN");
        } else {
            System.out.println("PINs don't match. Please try again.");
        }
    }

    private static void changeLanguage() {
        System.out.println("Select language:");
        System.out.println("1. English");
        System.out.println("2. Somali");
        System.out.print("Select (1-2): ");

        int languageOption = input.nextInt();
        input.nextLine();

        if (languageOption == 1) {
            System.out.println("Language changed to English.");
            addTransaction("Changed language to English");
        } else if (languageOption == 2) {
            System.out.println("Language changed to Somali.");
            addTransaction("Changed language to Somali");
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void reportLostMobile() {
        System.out.print("Enter lost mobile number: ");
        String lostMobile = input.nextLine();
        System.out.print("Enter secret PIN: ");
        String pin = input.nextLine();

        System.out.println("Confirm reporting lost number " + lostMobile + "?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Select: ");

        int confirm = input.nextInt();
        input.nextLine();

        if (confirm == 1) {
            System.out.println("Mobile " + lostMobile + " reported as lost successfully.");
            addTransaction("Reported lost mobile " + lostMobile);
        } else if (confirm == 2) {
            System.out.println("Report cancelled.");
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void blockFunds() {
        System.out.print("Enter incorrect recipient number: ");
        String wrongNumber = input.nextLine();
        System.out.print("Enter correct recipient number: ");
        String correctNumber = input.nextLine();
        System.out.print("Enter details: ");
        String info = input.nextLine();

        System.out.println("Confirm blocking funds?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Select: ");

        int approve = input.nextInt();
        input.nextLine();

        if (approve == 1) {
            System.out.println("Funds blocked for verification between " + wrongNumber + " and " + correctNumber);
            addTransaction("Blocked funds between " + wrongNumber + " and " + correctNumber);
        } else if (approve == 2) {
            System.out.println("Blocking cancelled.");
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void requestRefund() {
        System.out.print("Enter incorrect recipient number: ");
        String wrongRecipient = input.nextLine();
        System.out.print("Enter incorrect amount sent: $");
        double wrongAmount = input.nextDouble();
        input.nextLine();

        System.out.print("Confirm requesting refund of $" + wrongAmount + " sent to " + wrongRecipient + "? (Yes/No): ");
        String confirmReturn = input.nextLine();

        if (confirmReturn.equalsIgnoreCase("Yes")) {
            System.out.println("Refund request for $" + wrongAmount + " to " + wrongRecipient + " submitted.");
            addTransaction("Requested refund of $" + wrongAmount + " to " + wrongRecipient);
        } else if (confirmReturn.equalsIgnoreCase("No")) {
            System.out.println("Refund request cancelled.");
        } else {
            System.out.println("Invalid response.");
        }
    }

    private static void billPaymentMenu() {
        System.out.println("\nBill Payment");
        System.out.println("1. View Bill Balance");
        System.out.println("2. Pay Full Bill");
        System.out.println("3. Pay Partial Bill");
        System.out.print("Select (1-3): ");

        int option = input.nextInt();
        input.nextLine();

        switch (option) {
            case 1:
                showBillBalance();
                break;
            case 2:
                payFullBill();
                break;
            case 3:
                payPartialBill();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void showBillBalance() {
        double billAmount = 50.0;
        System.out.println("Your bill balance is: $" + billAmount);
        addTransaction("Viewed bill balance");
    }

    private static void payFullBill() {
        double totalBill = 50.0;
        if (confirmTransaction("pay full bill of $" + totalBill)) {
            if (deductAmount(totalBill)) {
                System.out.println("Full bill payment of $" + totalBill + " successful.");
                System.out.println("New balance: $" + balance);
                addTransaction("Paid full bill of $" + totalBill);
            }
        } else {
            System.out.println("Payment cancelled.");
        }
    }

    private static void payPartialBill() {
        System.out.print("Enter amount to pay: $");
        double partbill = input.nextDouble();
        input.nextLine();

        if (confirmTransaction("pay partial bill of $" + partbill)) {
            if (deductAmount(partbill)) {
                System.out.println("Partial bill payment of $" + partbill + " successful.");
                System.out.println("New balance: $" + balance);
                addTransaction("Paid partial bill of $" + partbill);
            }
        } else {
            System.out.println("Payment cancelled.");
        }
    }

    // Helper methods
    private static boolean confirmTransaction(String action) {
        System.out.print("Confirm " + action + "? (Yes/No): ");
        String confirmation = input.nextLine();
        return confirmation.equalsIgnoreCase("Yes");
    }

    private static boolean deductAmount(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return false;
        }

        if (amount > balance) {
            System.out.println("Insufficient balance.");
            return false;
        }

        balance -= amount;
        return true;
    }

    private static void addTransaction(String description) {
        transactionHistory.add(description);
        // Keep only the last 100 transactions to prevent memory issues
        if (transactionHistory.size() > 100) {
            transactionHistory.remove(0);
        }
    }
}