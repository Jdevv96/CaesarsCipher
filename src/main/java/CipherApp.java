import java.util.Scanner;

public class CipherApp {
    private final char FIRST_CHAR_OF_UPPER_ALPHA_A = 'A';
    private final char LAST_CHAR_OF_UPPER_ALPHA_Z = 'Z';
    private final char FIRST_CHAR_OF_LOWER_ALPHA_A = 'a';
    private final char LAST_CHAR_OF_LOWER_ALPHA_Z = 'z';
    private final int ALPHABET_SIZE = 26;
    private static final Scanner KEYBOARD = new Scanner(System.in);
    private String message;

    // Main; creates app and runs, taking in user input
    public static void main(String[] args) {

        CipherApp app = new CipherApp();
        app.runApp();
    }

    public void runApp() {
        System.out.println();
        System.out.println("Welcome to Caesars Cipher!");
        System.out.println("A handy application to help encrypt a secret message, by offsetting each letter a certain number. ");
        System.out.println("Or decipher a hidden message using the number of letters its offset by. ");
        System.out.println("To start, select from one of the menu options below.");
        System.out.println();

        while (true) {
            printMenuOptions();
            int mainMenuChoice = promptForMenuSelection();
            // Encode message
            if (mainMenuChoice == 1) {
                while (true) {
                    getUserMessage();
                    System.out.println("Enter a number positive or negative to shift by (Negative will result in a left rotation): ");
                    int amountToOffset = Integer.parseInt(KEYBOARD.nextLine());
                    String encodedMessage = cipherMessage(message, amountToOffset);
                    System.out.println("Your original message is: " + message);
                    System.out.println("Your new ciphered message is: " + encodedMessage);
                    System.out.println();
                    break;
                }
                // Decipher message
            } else if (mainMenuChoice == 2) {
                while (true) {
                    getUserMessage();
                    System.out.println("Enter the amount the message is offset by: ");
                    int amountIsOffsetBy = Integer.parseInt(KEYBOARD.nextLine());
                    String decipheredMessage = decipherMessage(message, amountIsOffsetBy);
                    System.out.println("Your original encrypted message is: " + message);
                    System.out.println("Your deciphered message is: " + decipheredMessage);
                    System.out.println();
                    break;
                }
            } else if (mainMenuChoice == 0) {
                break;
            }
        }
    }

    /*
       Using given String rotate right (positive rotation) given int
       to offset by. Calls shiftRight method in order to offset message.
       Returns new string of offset chars
    */
    public void getUserMessage(){
        System.out.println("To being, enter your message: ");
        message = KEYBOARD.nextLine();
    }

    public String cipherMessage(String message, int amountToOffsetBy) {
        amountToOffsetBy %= ALPHABET_SIZE;
        char[] messageToChars = message.toCharArray();
        shiftBy(messageToChars, amountToOffsetBy);
        return new String(messageToChars);
    }

    private String decipherMessage(String messageToDecode, int amountIsOffsetBy) {
        amountIsOffsetBy %= ALPHABET_SIZE;
        char[] messageToChars = messageToDecode.toCharArray();
        correctOffset(messageToChars, amountIsOffsetBy);
        return new String(messageToChars);
    }

    private void correctOffset(char[] messageToChars, int amountIsOffsetBy) {
        for (int i = 0; i < messageToChars.length; i++) {
            if (messageToChars[i] >= FIRST_CHAR_OF_UPPER_ALPHA_A && messageToChars[i] <= LAST_CHAR_OF_UPPER_ALPHA_Z) {
                messageToChars[i] -= amountIsOffsetBy;
                if (messageToChars[i] < FIRST_CHAR_OF_UPPER_ALPHA_A) {
                    messageToChars[i] += ALPHABET_SIZE;
                }
            } else if (messageToChars[i] >= FIRST_CHAR_OF_LOWER_ALPHA_A && messageToChars[i] <= LAST_CHAR_OF_LOWER_ALPHA_Z) {
                messageToChars[i] -= amountIsOffsetBy;
                if (messageToChars[i] < FIRST_CHAR_OF_LOWER_ALPHA_A) {
                    messageToChars[i] += ALPHABET_SIZE;
                }
            }
        }
    }

    // Uses ASCII values of alpha chars to offset given message.
    private void shiftBy(char[] messageToChars, int amountToOffsetBy) {
        for (int i = 0; i < messageToChars.length; i++) {
            // if char is uppercase or greater in ASCII value (skips punctuation, symbols, etc)
            if (messageToChars[i] >= FIRST_CHAR_OF_UPPER_ALPHA_A && messageToChars[i] <= LAST_CHAR_OF_UPPER_ALPHA_Z) {
                messageToChars[i] += amountToOffsetBy;
                if (messageToChars[i] > LAST_CHAR_OF_UPPER_ALPHA_Z) {
                    messageToChars[i] -= ALPHABET_SIZE;
                }
            } else if (messageToChars[i] >= FIRST_CHAR_OF_LOWER_ALPHA_A && messageToChars[i] <= LAST_CHAR_OF_LOWER_ALPHA_Z) {
                messageToChars[i] += amountToOffsetBy;
                if (messageToChars[i] > LAST_CHAR_OF_LOWER_ALPHA_Z) {
                    messageToChars[i] -= ALPHABET_SIZE;
                }
            }
        }
    }

    // Prompts user for menu options they can select
    private int promptForMenuSelection() {
        System.out.println("Please select an option from above: ");
        int menuOption;
        try {
            menuOption = Integer.parseInt(KEYBOARD.nextLine());
        } catch (NumberFormatException e) {
            menuOption = -1;
        }
        return menuOption;
    }

    // Prints diff options to menu screen for user
    private void printMenuOptions() {
        System.out.println("1: Encode a message");
        System.out.println("2: Decipher a message");
        System.out.println("0: Exit");
        System.out.println();
    }

}
