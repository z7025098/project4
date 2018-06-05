import java.io.File;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws Exception {
        Roulette rouletteSet[] = initialization();
        Scanner scan = new Scanner(System.in);
        mainMenu();
        int mainMenuOption = 0;
        boolean validMainMenuOption = false;
        while (!validMainMenuOption) {
            mainMenuOption = mainMenuOptions(scan);
            if (mainMenuOption >= 1 && mainMenuOption <= 3)
                validMainMenuOption = true;
            else
                System.out.println("Invalid menu option!");
        }
        scan.nextLine();
        processMainMenuOptions(mainMenuOption, scan, rouletteSet);
    }

    public static Roulette[] initialization() throws Exception {
        System.out.println("Initialize games. Please wait ...");
        System.out.println("All games are ready.");
        System.out.print("Available games: ");
        // output available games
        File file = new File("E://cs//src//games.txt");
        Scanner textScan = new Scanner(file);
        String model = textScan.next();
        Integer numberOfModels = textScan.nextInt();
        Roulette rouletteSet[] = new Roulette[numberOfModels];
        for (int i = 0; i < numberOfModels; i++) {
            rouletteSet[i] = new Roulette(model + Integer.toString(i + 1));
            System.out.print(rouletteSet[i].getName());
            if (i != numberOfModels - 1)
                System.out.print(", ");
        }
        System.out.println("\n");
        return rouletteSet;
    }

    public static void mainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Select a game");
        System.out.println("2. Add a new player to the list");
        System.out.println("3. Quit");
        System.out.print("\n");
    }

    public static int mainMenuOptions(Scanner scan) {
        System.out.print("Option --> ");
        int menu_option = scan.nextInt();
        return menu_option;
    }

    public static void processMainMenuOptions (int option, Scanner scan, Roulette[] set) throws Exception
    {
        switch(option) {
            case 1:
                Roulette currentGame = Roulette.gameSelection(scan, set);
                currentGame.gameMenu();
                int gameMenuOption = currentGame.gameMenuOption(scan);
                currentGame.processGameMenuOptions(gameMenuOption, scan, set);
                break;
            case 2:
				if(Roulette.playerQueue.size() >= 5)
				{
					System.out.println("Sorry, the table is full.");
			
				}
				else {
					Roulette.addPlayer();
				}
                returnMain(scan, set);
                break;
            case 3:
                Roulette.generateReport();
                break;
        }
    }

    public static void returnMain(Scanner scan, Roulette[] set) throws Exception
    {
        mainMenu();
        processMainMenuOptions(mainMenuOptions(scan), scan, set);
    }
}
