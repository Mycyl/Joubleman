import java.util.ArrayList;
public class Hint {

    private Hangman hangman;

 
    public Hint (Hangman hangman) {
        this.hangman = hangman;
    }
 
 
    public String hintLetter() {
        hangman.updateUnknownCharIndex();
        ArrayList<String> unknownCharIndexes = hangman.getUnknownCharIndexList();
        return (hangman.getCharacterList().get(Integer.parseInt(unknownCharIndexes.get((int) (Math.random() * unknownCharIndexes.size())))));
    }
 
 }
 