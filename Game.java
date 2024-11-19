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
 * __Made it have a loop for the play()
 * __Added a shitty player picker that does nothing yet.
 * __Added the help command option
 * 
 * @author Gage R
 * @version 11.14.2024
 */

import java.util.Scanner;

public class Game 
{
    private java.util.Scanner reader;
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private boolean finished;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        reader = new java.util.Scanner(System.in);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside;
        Room inside;

        // create the rooms
        outside = new Room("outside the room");
        inside = new Room("inside the room");

        // initialise room exits
        outside.setExit("east", inside);
        inside.setExit("west", outside);

        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
        characterPicker();
        
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        finished = false;//notifies program the user wishes to end the game
        do{
            Command command = parser.getCommand();
            finished = processCommand(command);
        }while(finished == false);
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println("this is a game.");
        System.out.println(currentRoom.getShortDescription());
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
            System.out.println("There is no door!");
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

    private void characterPicker()
    {
        System.out.println("Pick a character ->");
        System.out.println("Jack    Abby    Devin   Steven");
        String choice = reader.nextLine();
        switch(choice.toLowerCase())
        {
            case "jack":
                System.out.println("you chose Jack");
                break;
            case "abby":
                System.out.println("you chose Abby");
                break;
            case "devin":
                System.out.println("you chose Devin");
                break;
            case "steven":
                System.out.println("you chose Steven");
                break;
        }
    }
}
