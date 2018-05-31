import java.util.Scanner;

public abstract class Player {
    private static final int RELOAD_AMOUNT = 50;
    private static int bet, money, betType, number, winLoseAmount, betTimes, totalBet;
    private static String name;

    //=====================================================================
    //  The Player constructor sets up  name and initial available money.
    //=====================================================================
    public Player (int initialMoney)
    {
        money = initialMoney;
        winLoseAmount = 0;
        totalBet = 0;
    } // constructor Player


    //=====================================================================
    //  Returns this player's name.
    //=====================================================================
    public static String getName()
    {
        return name;
    }  // method getName


    //=====================================================================
    //  Returns this player's current available money.
    //=====================================================================
    public static int getMoney()
    {
        return money;
    }  // method getMoney

    public int getBetTimes() { return betTimes; }
    public int getTotalBet() { return totalBet; }

    //=====================================================================
    //  Prompts the user and reads betting information.
    //=====================================================================
    public static void makeBet(Scanner scan)
    {
        
        Wheel.betOptions();
        boolean validBetType = false;
        while (!validBetType) {
            System.out.print("Choose your bet type: ");
            betType = scan.nextInt();
            if (betType < 1 || betType > 3) {
                System.out.println("Invalid betting type. Please re-bet again.\n");
                validBetType = false;
            }
            else
                validBetType = true;
        }
        
        if (betType == Wheel.NUMBER)
        {
            boolean validBetNumber = false;
            while (!validBetNumber) {
                System.out.print("Which number to bet on: ");
                number = scan.nextInt();
                if (number < Wheel.MIN_NUM || number > Wheel.MAX_NUM) {
                    System.out.println("Invalid bet number. Please try again.");
                    validBetNumber = false;
                }
                else
                    validBetNumber = true;
            }
        }
        if (money == 0) {
            System.out.println("Out of money, reloading...");
            money += RELOAD_AMOUNT; // reload
        }
        boolean validBetAmount = false;
        while (!validBetAmount) {
            System.out.print(name + " How much to bet: ");
            bet = scan.nextInt();
            if (bet >= Wheel.MIN_BET && bet <= money) {
                money = money - bet;
                validBetAmount = true;
                totalBet += bet;
            }
            else {
                System.out.println("Invalid betting amount. Please re-bet again.\n");
                validBetAmount = false;
            }
        }
    } // method makeBet

    public static void payment(Wheel wheel)
    {
        int payoffAmount = 0;
        if (betType == 1 || betType == 2)
            payoffAmount = Wheel.payoff(bet, betType);
        else if (betType == 3)
            payoffAmount = Wheel.payoff(bet, betType, number);
        System.out.println("Payoff amount for " + name + ": " + payoffAmount + "\n");
        money += payoffAmount;
        winLoseAmount = winLoseAmount + payoffAmount - bet;
    }

    //=====================================================================
    //  Determines if the player wants to play again.
    //=====================================================================
    public static boolean playAgain(Scanner scan)
    {
        String answer;
        System.out.print (name + " Play again [y/n]? ");
        answer = scan.next();
        return (answer.equals("y") || answer.equals("Y"));
    }  // method playAgain


    //=====================================================================
    //  Return relevant information about the player.
    //=====================================================================
    public String toString()
    {
        String result = "Player: " + name + "\nmoney: " + money;
        return result;
    }  // method toString
}