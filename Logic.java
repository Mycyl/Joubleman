import java.util.Scanner;

public class Logic {

   private int guesses;
   private int incorrectStreak;
   private boolean hintUsed;

   public Logic(){
    guesses = 0;
    incorrectStreak = 0;
    hintUsed = false;
   }

   public void Start () {

       Scanner scan = new Scanner(System.in);
       System.out.println("Hello, welcome to Joubleman where you'll have the chance to play our fantastic version of hangman. ");
       Stats stats = new Stats();
       String yOrNresponse = "y";
       while (!yOrNresponse.equals("n")) {
           // code
           Hangman hangman = new Hangman();
           Hint hint = new Hint(hangman);
           guesses = hangman.getSecretWord().length() + 6;
           incorrectStreak = 0;
           hintUsed = false;

           while(!hangman.isWordGuessed() && guesses != 0) {
               System.out.println("___________________________________");
               System.out.println("Amount of guesses left: " + guesses);
               System.out.println();
               System.out.println(hangman.formatGuessedCorrectLetters());
               System.out.println();
               System.out.println("INCORRECT LETTERS:");
               System.out.println(hangman.formatIncorrectLetters());
               System.out.println();
               if (!hintUsed && incorrectStreak == 3) {
                    System.out.println("You got 3 incorrect in a row heres a hint!: " + hint.hintLetter());
                    hintUsed = true;
               }
               System.out.print("Guess a letter: ");
               String guessedLetter = scan.nextLine().toUpperCase();
               while (hangman.getIncorrect().indexOf(guessedLetter) != -1 || hangman.getCorrect().indexOf(guessedLetter) != -1) {
                    System.out.print("Already guessed that letter!: (Input another letter) ");
                    guessedLetter = scan.nextLine().toUpperCase();
               }
               if (hangman.checkLetter(guessedLetter)) {
                   System.out.println(guessedLetter + " is correct!");
                   hangman.setGuessedLetters(hangman.updateGuessedLetterList(guessedLetter));
                   incorrectStreak = 0;
               } else if (!hangman.checkLetter(guessedLetter)) {
                   System.out.println(guessedLetter + " is not correct!");
                   hangman.updateIncorrectLetters(guessedLetter);
                   incorrectStreak++;
               }
               System.out.println("___________________________________");
               System.out.println();
               guesses--;
           }

           if (hangman.isWordGuessed()) {
                System.out.println("You won!");
                stats.win();
           }

           if (guesses == 0 && !hangman.isWordGuessed()) {
                System.out.println("You lost!");
                stats.loss();
           }

           System.out.println();
           System.out.println("------- GAME STATISTICS -------");
           System.out.println("Amount of losses: " + stats.getLoss());
           System.out.println("Amount of wins: " + stats.getWins());
           System.out.println("Total amount of games: " + stats.getTotalGames());
           System.out.println("Average win rate: " + stats.averageWin() + "%");
           System.out.println("-------------------------------");

           System.out.print("Do you want to play again?: ");
           yOrNresponse = scan.nextLine();

       }
   }
}

