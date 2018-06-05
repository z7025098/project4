import java.util.Queue;
import java.util.Scanner;

class Player {
    private static final int RELOAD_AMOUNT = 50;
    private int bet, money, betType, number, winLoseAmount, betTimes, totalBet;
    private String firstName, lastName, ID;

    //=====================================================================
    //  The Player constructor sets up  name and initial available money.
    //=====================================================================
    public Player () {}
    public Player (int initialmoney)
    {
    	firstName = "";
    	lastName = Roulette.playerQueue.size()+1+" ";
        money = initialmoney;
        winLoseAmount = 0;
        totalBet = 0;
        ID = " ";
    } // constructor Player 
    
    public Player (int initialMoney, String id, String first, String last) {
    	money = initialMoney;
    	ID = id;
    	firstName = first;
    	lastName = last;
    } // constructor Player

    //=====================================================================
    //  Returns this player's name.
    //=====================================================================
    public String getFirstName()
    {
      	return firstName;
    }  // method getFirstName 
    
    public String getLastName()
    {
      	return lastName;
    }   // method getLastName 

    //=====================================================================
    //  Returns this player's current available money.
    //=====================================================================
    public int getMoney()
    {
        return money;
    }  // method getMoney

    public int getBetTimes() { return betTimes; }
    public int getTotalBet() { return totalBet; }

    public void getReady(Scanner scan,Roulette[] set) throws Exception {
        System.out.print("Option --> ");
        int option = scan.nextInt();
    	switch(option) {
    		case 1:
    			Wheel.betOptions();
    			makeBet(scan);
    			Wheel.roundOptions();
    			break;
    		case 2:
    			System.out.println("How much money do you want to add:");
    			int addMoney = scan.nextInt();
    			money += addMoney;
    			Wheel.roundOptions();
    			break;
    		case 3:
    			
    		case 4:
    		case 5:
    			Roulette.returnGameMenu(scan, set);
    			break;
    	}
    		
    	
    }
    //=====================================================================
    //  Prompts the user and reads betting information.
    //=====================================================================
    public void makeBet(Scanner scan)
    {
    	if (money == 0) {
            System.out.println("Out of money, reloading...");
            money += RELOAD_AMOUNT; // reload
            System.out.println (toString());
        }
    	 boolean validBetAmount = false;
         while (!validBetAmount) {
             System.out.print(firstName + lastName + "\tHow much to bet: ");
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
        Wheel.betOptions();
        boolean validBetType = false;
        while (!validBetType) {
            System.out.print(firstName + lastName + "\tChoose your bet type: ");
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
                System.out.print(firstName + lastName + "\tEnter the number you wish to bet on:");
                number = scan.nextInt();
                if (number < Wheel.MIN_NUM || number > Wheel.MAX_NUM) {
                    System.out.println("Invalid bet number. Please try again.");
                    validBetNumber = false;
                }
                else
                    validBetNumber = true;
            }
        }
       
    } // method makeBet

    public void payment(Wheel wheel)
    {
    	int wins = Wheel.payoff(bet, betType, number);
    	money = money + wins;
    	if (wins > 0)
    		System.out.println(firstName + lastName + " won " + wins + " dollars!");
    	else
    		System.out.println(firstName + lastName + " lost " + bet + " dollars!");
    }

    //=====================================================================
    //  Determines if the player wants to play again.
    //=====================================================================
    public boolean playAgain(Scanner scan)
    {
        String answer;
        System.out.print (firstName + lastName + " Play again [y/n]? ");
        answer = scan.next();
        return (answer.equals("y") || answer.equals("Y"));
    }  // method playAgain


    //=====================================================================
    //  Return relevant information about the player.
    //=====================================================================
    public String toString()
    {
    	String	result = "\nPlayer: " + firstName + " " + lastName + " " + ID + "\tmoney: " + money;
    	return result;
    }  // method toString
}