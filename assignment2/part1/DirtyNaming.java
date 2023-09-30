/*
 * This class simulates a bank transaction.
 * It has dirty naming practices.
 * You will correct these while ensuring that the code still runs as expected.
 */
public class DirtyNaming {
    private static double wAmt = 0;
    private static double dAmount = 0;
    private static double a = 1000;

    public static void main(String[] args) {
        // Assume args: "-d 100.50 -w 50" means deposit $100.50 and withdraw $50

        if (args.length > 0) {
            pArgs(args); 
        }

        cB();
        makeDeposit(dAmount);
        cB();
        withdraw(wAmt);
        cB();
    }

    // checks the balance
    public static void cB() {
        System.out.println("Account balance is: $" + a);
    }

    // Makes a deposit
    public static void makeDeposit(double amt) {
        a += amt;
    }

    // Withdraw with overage charge
    public static void withdraw(double w) {
        a -= w;
        if (a < 0) {
            a += w;
            a -= 2;
            System.out.println("Insufficient balance! Withdrawal not processed.");
        }
    }

    // Parse the arguments
    public static void pArgs(String[] str) {
        for (int i = 0; i < str.length; i++) {
            switch (str[i]) {
                case "-d":
                    if (i + 1 < str.length) {
                        try {
                            dAmount = Double.parseDouble(str[i + 1]);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid deposit amount.");
                        }
                    }
                    break;
                case "-w":
                    if (i + 1 < str.length) {
                        try {
                            wAmt = Double.parseDouble(str[i + 1]);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid withdrawal amount.");
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }
}




