import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Roulette {
    private int money;
    private boolean current;
    private String name;
    static Queue<Object> playerQueue = new LinkedList<>();
    private static Scanner scan = new Scanner(System.in);
    static Wheel wheel = new Wheel(); 
    static boolean done = false;
    static Player player = new Player(50);
    
    public Roulette(String nm) { name = nm; }
    public static void gameMenu()
    {
        System.out.println("Game Menu");
        System.out.println("1. Add a player to the game");
        System.out.println("2. Play one round");
        System.out.println("3. Game status");
        System.out.println("4. Return to main menu");
        System.out.print("\n");
    }

    public void setCurrent() { current = true; }
    public boolean getCurrent() { return current; }

    public String getName() { return name; }

    public static int gameMenuOption(Scanner scan){
        boolean validInput = false;
        int game_option = 0;
        while (!validInput) {
            System.out.print("Option --> ");
            game_option = scan.nextInt();
            if (game_option >= 1 && game_option <= 4)
                validInput = true;
            else
                System.out.println("Please enter a valid option.");
        }
        return game_option;
    }

    public static void addPlayer() throws Exception{
        System.out.println("Adding player " + (playerQueue.size() + 1) + " to the game.");
        // output number of current players
        File file = new File("E://cs//src//players.txt");
        Scanner scan = new Scanner(file);
        for (int i = 0; i < playerQueue.size(); i++)
            scan.nextLine();
        switch(scan.nextInt()) {
            case 0:
                regularPlayer player = new regularPlayer(scan.nextInt());
                scan.nextLine();
                playerQueue.add(player);
                break;
            case 1:
                VIP VIPPlayer = new VIP(scan.nextInt(), scan.next(), scan.next(),scan.next());
                scan.nextLine();
                playerQueue.add(VIPPlayer);
                break;
            case 2:
                SuperVIP SuperVIPPlayer = new SuperVIP(scan.nextInt(), scan.next(), scan.next(),scan.next());
                scan.nextLine();
                playerQueue.add(SuperVIPPlayer);
                break;
        }
        System.out.println(playerQueue);
    }

    public static Roulette gameSelection(Scanner scan, Roulette[] set)
    {
        boolean gameFound = false;
        while (!gameFound) {
            System.out.print("Select a game --> ");
            String game_selection = scan.nextLine();
            System.out.print("\n");
            if (searchGames(game_selection, set)) {
                setCurrentGame(set);
                gameFound = true;
            }
        }
        return getCurrentGame(set);
    }


    public static void generateReport()
    {
        System.out.println("Generating report ...");
        System.out.println("Shutting down all games.");
    }

    public static void play()
    {
    	Wheel.welcomeMessage();
    	do {
    	System.out.println (player.toString());
    	player.makeBet(scan);
    	
    	wheel.spin();
    	
    	player.payment(wheel);
    	
    	done = !player.playAgain(scan);
    	} while (!done);
    	
    }

    public static void reportStatus()
    {

    }

    public static void processGameMenuOptions(int option, Scanner scan, Roulette[] set) throws Exception
    {
        switch(option) {
            case 1:
				if(playerQueue.size() >= 5)
				{
					System.out.println("Sorry, the set are full.");
			
				}
				else {
					addPlayer();
				}
                returnGameMenu(scan, set);
                break;
            case 2:
                play();
                returnGameMenu(scan, set);
                break;
            case 3:
                reportStatus();
                returnGameMenu(scan, set);
                break;
            case 4:
                Driver.returnMain(scan, set);
                break;
        }
    }

    public static boolean searchGames(String game, Roulette[] set)
    {
        boolean hasCurrent = false;
        for (int i = 0; i < set.length; i++) {
            if (game.equals(set[i].getName())) {
                set[i].setCurrent();
                hasCurrent = true;
            }
        }
        if (!hasCurrent)
            System.out.println("Game not found!");
        return hasCurrent;
    }

    public static void setCurrentGame(Roulette[] set)
    {
        Roulette currentGame = set[0]; // just for initialization
        for (int i = 0; i < set.length; i++)
        {
            if (set[i].getCurrent()) {
                currentGame = set[i];
                System.out.println("Now " + currentGame.getName() + " is selected as current.");
            }
        }
    }

    public static Roulette getCurrentGame (Roulette[] set)
    {
        Roulette currentGame = set[0];
        for (int i = 0; i < set.length; i++)
        {
            if (set[i].current)
                currentGame = set[i];
        }
        return currentGame;
    }
    public static void returnGameMenu(Scanner scan, Roulette[] set) throws Exception
    {
    	gameMenu();
    	processGameMenuOptions(gameMenuOption(scan), scan, set);
    }
    
}
