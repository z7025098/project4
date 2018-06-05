import java.util.Scanner;

class Player {
    private static final int RELOAD_AMOUNT = 50;
    private int bet, money, betType, number, winLoseAmount, betTimes, totalBet;
    private String name;

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
    public String getName()
    {
        return name;
    }  // method getName 


    //=====================================================================
    //  Returns this player's current available money.
    //=====================================================================
    public int getMoney()
    {
        return money;
    }  // method getMoney

    public int getBetTimes() { return betTimes; }
    public int getTotalBet() { return totalBet; }

    //=====================================================================
    //  Prompts the user and reads betting information.
    //=====================================================================
    public void makeBet(Scanner scan)
    {
        
        Wheel.betOptions();
        boolean validBetType = false;
        while (!validBetType) {
            System.out.print(name + "\tChoose your bet type: ");
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
                System.out.print(name + "\tEnter the number you wish to bet on:");
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

    public void payment(Wheel wheel)
    {
    	int wins = Wheel.payoff(bet, betType, number);
    	money = money + wins;
    	if (wins > 0)
    		System.out.println(name + " won " + wins + " dollars!");
    	else
    		System.out.println(name + " lost " + bet + " dollars!");
    }

    //=====================================================================
    //  Determines if the player wants to play again.
    //=====================================================================
    public boolean playAgain(Scanner scan)
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
        String result = "Player: " + name + "\tmoney: " + money + "\n";
        return result;
    }  // method toString
}
