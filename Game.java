/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * 11.14.2024
 * __Made it have a loop for the play()
 * __Added a shitty player picker that does nothing yet.
 * __Added the help command option
 * 
 * 11.19.2024
 * __Added the weapon and item variable
 * __Also added a checker for characterPicker()
 * 
 * 11.20.2024
 * __A bunch of shit really
 * __Added my first few rooms
 * __Added players
 * __Started adding items
 * __Started work on inventory system
 * 
 * @author Gage R
 * @version 11.14.2024
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Game 
{
    private java.util.Scanner reader;
    private Parser parser;
    private Room currentRoom;
    private Item currentWeapon;
    private Player player;
    private Item item;

    private int killCounter; //kill counter for the player
    private boolean isDead; //if the player is dead or not
    private Item[] inventory; //inventory

    //items
    Item fists;
    Item drugs;
    
    
    private boolean finished; //checks if the game is finished

    /**
     * Create the game and items
     */
    public Game() 
    {
        createObjects();
        inventory = new Item[10];
        parser = new Parser();
        reader = new java.util.Scanner(System.in);
        currentWeapon = fists;
        
        
        inventory[0] = drugs;
    }

    /**
     * Create all the rooms and link their exits together.
     * 
     * Sets players current room based on current character
     */
    public void createRooms()
    {
        Room finalHallway;
        Room azzysRoom;
        Room exit;

        // create the rooms
        azzysRoom = new Room ("Azzy's room", 0, false);
        finalHallway = new Room("outside the room", 1, false);
        exit = new Room(". . .", 0, true);

        // initialise room exits
        azzysRoom.setExit("west", finalHallway);
        finalHallway.setExit("north", exit);
        finalHallway.setExit("east", azzysRoom);

        //sets players current room based on what character they are
        switch(player.gName().toLowerCase())
        {
            case "jack":
                break;
            case "abby":
                break;
            case "devin":
                break;
            case "steven":
                break;
            case "azzy":
                currentRoom = finalHallway;
                break;
        }
    }

    /**
     * Creates all the items in the game
     */
    private void createObjects()
    {   
        //item items
        drugs = new Item(25, 25, 0, 0, false, false, true, "drugs", "some drugs");
        
        //weapon items
        fists = new Item(0, 0, 0, 0, true, false, false, "fists", "its all you've got");
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
        characterPicker();
        createRooms();
        System.out.println(currentRoom.getShortDescription());
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        finished = false;//notifies program the user wishes to end the game
        do{
            Command command = parser.getCommand();
            finished = processCommand(command);
            hasHealth();
            if(currentRoom.gIsExit() == true)
            {
                finished = true;
            }
            if(isDead == true)
            {
                finished = true;
            }
        }while(finished == false);
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println("this is a game.");
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("Try again please.");
                break;
            case WALK:
                goRoom(command);
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
            case HELP:
                printHelp();
                break;
            case INVENTORY:
                inventoryAndStats();
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no room!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getShortDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Chooses a character and then sets the current player character to the inputted one
     */
    private void characterPicker()
    {
        System.out.println("Pick a character ->");
        System.out.println("Jack    Abby    Devin   Steven");
        boolean correct = false;
        do{
            String choice = reader.nextLine();
            switch(choice.toLowerCase())
            {
                case "jack":
                    System.out.println("you chose Jack");
                    correct = true;
                    break;
                case "abby":
                    System.out.println("you chose Abby");
                    correct = true;
                    break;
                case "devin":
                    System.out.println("you chose Devin");
                    correct = true;
                    break;
                case "steven":
                    System.out.println("you chose Steven");
                    correct = true;
                    break;
                case "az":
                    player = new Player(10, 10, 10, 100, 100, 100, 100, "Azzy", "Ass real");
                    player.printPlayerStats();
                    correct = true;
                    break;
                default:
                    System.out.println("Think YOU have free will here? Try again.");
                    correct = false;
                    break;
            }
        }while(correct == false);
    }

    private void combat()
    {

    }

    private boolean hasHealth()
    {
        if(player.gHealth() <= 0)
        {
            return isDead = true;
        }
        else
        {
            return isDead = false;
        }
    }

    /**
     * Displays both the players inventory and stats and then allows the player to use an item from the inventory
     */
    public void inventoryAndStats()
    {
        System.out.println("=== STATS ===");
        player.printPlayerStats();
        System.out.println();

        System.out.println("=== INVENTORY ===");
        System.out.println();
        
        System.out.println("Current Weapon = " + currentWeapon.gName() + " - " + currentWeapon.gDescription());
        System.out.println();
        
        printInventory();
        usingInventory();
    }
    
    /**
     * Prints the players inventory, if a space is blank it will display "- - -"
     */
    private void printInventory()
    {   
        int num = 1;
        for(int i = 0; i < inventory.length; i++)
        {
            System.out.print(num + ") ");
            if(inventory[i] == null)
            {
                System.out.println(" - - - ");
            }
            else
            {
                System.out.println(inventory[i].gName() + " - " + inventory[i].gDescription());
            }
            num++;
        }
    }
    
    /**
     * The inventory UI and where you pick what item to use
     */
    private void usingInventory()
    {
        System.out.println();
        System.out.println("Input the number of the object you want to use or type 'back'");
        
        boolean done = false;
        do{
            String option = reader.nextLine();
            switch(option)
            {
                case "1":
                    useItem(inventory[0]);
                    inventory[0] = null;
                    break;
                case "2":
                    useItem(inventory[1]);
                    inventory[1] = null;
                    break;
                case "3":
                    useItem(inventory[2]);
                    inventory[2] = null;
                    break;
                case "4":
                    useItem(inventory[3]);
                    inventory[3] = null;
                    break;
                case "5":
                    useItem(inventory[4]);
                    inventory[4] = null;
                    break;
                case "6":
                    useItem(inventory[5]);
                    inventory[5] = null;
                    break;
                case "7":
                    useItem(inventory[6]);
                    inventory[6] = null;
                    break;
                case "8":
                    useItem(inventory[7]);
                    inventory[7] = null;
                    break;
                case "9":
                    useItem(inventory[8]);
                    inventory[8] = null;
                    break;
                case "10":
                    useItem(inventory[9]);
                    inventory[9] = null;
                    break;
                case "back":
                    done = true;
                    break;
            }
        }while(done == false);
    }
    
    /**
     * Uses the item selected 
     *  - if weapon it switches the chosen item to your selected inventory weapon
     *  - if consumable it uses the item
     * 
     * @param selectedItem the item you are using selected from usingInventory()
     */
    private void useItem(Item selectedItem)
    {
        if(selectedItem != null)
        {
            player.sHealth(player.gHealth() + selectedItem.gHealthMod());
            
            if(selectedItem.gIsWeapon() == true)
            {
                currentWeapon = selectedItem;
                System.out.println("Weapon equiped!");
            }
            else
            {
                System.out.println("Item used!");
            }
        }
        else
        {
            System.out.println("There is nothing here!");
        }
    }
}
