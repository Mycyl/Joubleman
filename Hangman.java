import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
* This class represents the object holding the methods, of which codify the logic behind the hangman game
*
* @author Michael Madrid
*/

public class Hangman {

   /** The word that the user has to guess */
   private String secretWord;

   /** The ArrayList that contains all the secret words read from the text file*/
   private ArrayList<String> allSecretWords;

   /** The ArrayList that holds each character of the secret word */
   private ArrayList<String> characterList;

   /** The ArrayList that holds each character that the user has guessed and the blanks*/
   private ArrayList<String> guessedCorrectLetters;


   /** Every incorrect letters */
   private ArrayList<String> incorrectGuessedLetters;

   private ArrayList<String> unkownCharIndexList;

   /**
    * Instantiates a Hangman object.
    * */
   public Hangman () {
       allSecretWords = new ArrayList<String>();
       characterList = new ArrayList<String>();
       guessedCorrectLetters = new ArrayList<String>();
       incorrectGuessedLetters = new ArrayList<String>();
       unkownCharIndexList = new ArrayList<>();
       createAllSecretWordList();
       secretWord = parseSecretWord();
       instantiateGuessedLetterList();
       instantiateSecretWordList();
   }

   /**
    * Populates the allSecretWords list with every secret word read from the text file
    * */
   private void createAllSecretWordList () {
       try {
           BufferedReader reader = new BufferedReader(new FileReader("word.txt")); // Do src when using a different IDE
           String line;
           while ((line = reader.readLine()) != null) {
               allSecretWords.add(line);
           }
           reader.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   public ArrayList<String> getIncorrect () {
        return incorrectGuessedLetters;
   }

   public ArrayList<String> getCorrect () {
        return guessedCorrectLetters;
   }

   public ArrayList<String> getAllSecretWords() {
       return allSecretWords;
   }

   public ArrayList<String> getCharacterList () {
    return characterList;
   }

   /**
    * Gets a random word from the allSecretWord ArrayList
    * @return the randomly selected secret word
    * */
   private String parseSecretWord () { // Obtains word from text file
       return allSecretWords.get((int) (Math.random() * (allSecretWords.size()))).toUpperCase();
   }

   public String getSecretWord () {
    return secretWord;
   }

   /**
    * Populates the ArrayList characterList with the characters of the secret word.
    * */
   private void instantiateSecretWordList () { // Creates
       for (int i = 0; i < secretWord.length(); i++) {
           characterList.add(secretWord.substring(i, i + 1));
       }
   }

   public void setGuessedLetters(ArrayList<String> newGuessedLetterList) {
       guessedCorrectLetters = newGuessedLetterList;
   }

   /**
    * Populates the ArrayList guessedLetters with the characters that the user has guessed and the blanks
    * */
   private void instantiateGuessedLetterList () {
       for (int i = 0; i < secretWord.length(); i++) {
           guessedCorrectLetters.add("__");
       }
   }

   /**
    * Checks the letter that the user has inputted
    * @param guessLetter The letter that the user has inputted
    * @return whether the letter is in the word or not
    * */
   public boolean checkLetter (String guessLetter) {
       if (characterList.indexOf(guessLetter) != -1) {
           return true;
       } else {
           return false;
       }

   }

   /**
    * Returns the guessed letter list with the user inputted letter if it is in the secret word
    * @param letter the guessed letter
    * @return the updated guessed letter list (the guessed letter list must be reassigned to the updated guessed letter list in the logic code)
    * */
   public ArrayList<String> updateGuessedLetterList (String letter) {
       ArrayList<String> updatedLetterList = new ArrayList<String>();
       updatedLetterList = guessedCorrectLetters;
       for (int i = 0; i < characterList.size(); i++) {
           if (characterList.get(i).equals(letter)) {
               updatedLetterList.set(i, letter);
           }
       }
       return updatedLetterList;
   }

   public void updateUnknownCharIndex () {
        for (int i = 0; i < guessedCorrectLetters.size(); i++) {
            if (guessedCorrectLetters.get(i).equals("__")) {
                unkownCharIndexList.add(i + "");
            }
        }
   }

   public ArrayList<String> getUnknownCharIndexList () {
    return unkownCharIndexList;
   }

   /**
    * Returns a string that encapsulates the blanks and the correctly guessed letters in a string so that it is printable
    * @return the string that encapsulates the blanks and correctly guessed letters
    * */
   public String formatGuessedCorrectLetters () {
       String returnStr = "";
       for (int i = 0; i < guessedCorrectLetters.size(); i++) {
           returnStr += guessedCorrectLetters.get(i) + " ";
       }
       return returnStr;
   }

    public String formatIncorrectLetters () {
       String returnStr = "";
       String lineStr = "----";
       for (int i = 0; i < incorrectGuessedLetters.size(); i++) {
           lineStr += "--";
       }
       returnStr += lineStr + "\n";
       returnStr += "| ";
       for (int i = 0; i < incorrectGuessedLetters.size(); i++) {
           returnStr += incorrectGuessedLetters.get(i) + " ";
       }
       returnStr += " |";
       returnStr += "\n" + lineStr;


       return returnStr;
   }

   public void updateIncorrectLetters (String incorrectLetter) {
       incorrectGuessedLetters.add(incorrectLetter);
   }


   /**
    * Checks whether the word has been successfully guessed
    * @return boolean representing whether the word has been successfully guessed
    * */
   public boolean isWordGuessed () {
       if (guessedCorrectLetters.contains("__")) {
           return false;
       }
       return true;
   }

   
}
