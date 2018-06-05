import java.util.Random;

public class Wheel {
    // public name constants -- accessible to others
    public final static int BLACK     =  0;			// even numbers
    public final static int RED       =  1;			// odd numbers
    public final static int GREEN     =  2;			// 00 OR 0
    public final static int NUMBER    =  3;			// number bet
    public final static int MIN_NUM   =  1;			// smallest number to bet
    public final static int MAX_NUM   = 36;			// largest number to bet
    public final static int MAX_BET   = 10;			// largest amount to bet
    public final static int MIN_BET   = 1;			// smallest amount to bet

    public static int houseWinLoseAmount = 0;
    // private name constants -- internal use only
    private final static int MAX_POSITIONS = 38;	// number of positions on wheel
    private final static int NUMBER_PAYOFF = 35;	// payoff for number bet
    private final static int COLOR_PAYOFF  = 2;		// payoff for color bet

    // private variables -- internal use only
    private static int ballPosition;				// 00, 0, 1 .. 36
    private static int color;						// GREEN, RED, OR BLACK

    //=====================================================================
    //  Presents welcome message
    //=====================================================================
    public static void welcomeMessage()
    {
        System.out.println("Welcome to a simple version of roulette game.");
        System.out.println("You can place a bet on black, red, or a number.");
        System.out.println("A color bet is paid " + COLOR_PAYOFF + " the bet amount.");
        System.out.println("A number bet is paid " + NUMBER_PAYOFF + " the bet amount.");
        System.out.println("You can bet on a number from " + MIN_NUM + " to " + MAX_NUM + ".");
        System.out.println("You can bet an amount between $" + MIN_BET + " and $" + MAX_BET + ".");
        System.out.println("Gamble responsibly.  Have fun and good luck!\n");
    }


    //=====================================================================
    //  Presents betting options
    //=====================================================================
    public static void betOptions()
    {
        System.out.println("Betting Options:");
        System.out.println("    1. Bet on black (even numbers)");
        System.out.println("    2. Bet on red (odd numbers)");
        System.out.println("    3. Bet on a number between " + MIN_NUM +
                " and " + MAX_NUM);
        System.out.println();
    }

    public static void spin()
    {
        Random rng = new Random();
        ballPosition = rng.nextInt(MAX_POSITIONS);
        if (ballPosition == 37 || ballPosition == 0) {
            color = GREEN;
            System.out.println("Color: GREEN");
        }
        else if (ballPosition % 2 == 1)
        {
            color = RED;
            System.out.println("Color: RED");
        }
        else
        {
            color = BLACK;
            System.out.println("Color: BLACK");
        }
        System.out.println("Position: " + ballPosition + "\n");
    }

    public static int payoff(int betAmount, int betType, Integer... betNumber)
    {
        int payoff = 0;
        if (ballPosition == 0 || ballPosition == 37)
            payoff = 0;
        else if (betType == 3)
        {
            Integer betNumberExtracted = betNumber.length > 0 ? betNumber[0] : 0;
            if (betNumberExtracted == ballPosition)
                payoff = NUMBER_PAYOFF * betAmount;
            else
                payoff = 0;
        }
        else if (betType == 1 || betType == 2)
        {
            if (color + 1 == betType)
                payoff = COLOR_PAYOFF * betAmount;
            else
                payoff = 0;
        }
        houseWinLoseAmount = houseWinLoseAmount + betAmount - payoff;
        return payoff;
    }
}
}
