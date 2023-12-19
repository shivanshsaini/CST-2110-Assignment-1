
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ApplicationRunner {

    public static void main(String[] args) {

        String dataFile = System.getProperty("user.dir") + File.separator + "wordlist.txt";
        
        List<String> wordList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                wordList.add(line.toLowerCase());
            }   

	} catch (IOException e) {
            e.printStackTrace();
	}// The title of the game is Word guessing game
        System.out.println("Word Guessing Game");
        // There are two possible inputs 1 and 0 
        while(true){
            System.out.print("Play (1) or Exit (0) > ");
            Scanner sc = new Scanner(System.in);
            String response = sc.nextLine();
            if(response.equals("1")){
               startGame(getRandomElement(wordList));
            }
            else if(response.equals("0")){
                break;
            }
        }
       

    }
    
    public static String getRandomElement(List<String> list) 
    { 
        Random rand = new Random(); 
        return list.get(rand.nextInt(list.size())); 
    }
    // The player loses the game after 10 attempts.
    public static void startGame(String word){
        String result = "lose :-( ";  
        List<Character> wrongGuesses = new ArrayList<>();
        List<Character> correctGuesses = new ArrayList<>();
        while(wrongGuesses.size()<10)
        {
            printCurrentRevealedWord(word,correctGuesses);
            System.out.print('\n');
            System.out.println(wrongGuesses.size() + " wrong guesses so far " + toString(wrongGuesses));
            System.out.println("Have a guess (lower case letter or * to give up)");
            System.out.print("> ");
            Scanner sc = new Scanner(System.in);
            char guess = 0;
            try {
                guess = sc.next(".").charAt(0);
            } catch (Exception e) {
                System.out.println("need to add a character");
                continue;
            }
            if(word.contains(""+guess)){
                correctGuesses.add(guess);
            }
// '*' is the input for quiting the game
            else if(guess == '*'){
                System.out.println("You gave up!");
                printCurrentRevealedWord(word, correctGuesses);
                System.out.print('\n');
                System.out.println(wrongGuesses.size() + " wrong guesses so far " + toString(wrongGuesses));
                break;
            }else if(wrongGuesses.contains(guess)){
                System.out.println("Already guessed this character. Retry again");
            }else{
                wrongGuesses.add(guess);
            }
            if(checkCompletion(word,correctGuesses)){ // If won!
                result = "Victory! :-)";
                printCurrentRevealedWord(word, correctGuesses);
                System.out.print('\n');
                System.out.println(wrongGuesses.size() + " wrong guesses so far " + toString(wrongGuesses));
                System.out.println('\n');
                break;
            }
            
            System.out.print('\n');
            
        }
        if(!checkCompletion(word,correctGuesses)){
            printCurrentRevealedWord(word, correctGuesses);
            System.out.print('\n');
            System.out.println(wrongGuesses.size() + " wrong guesses so far " + toString(wrongGuesses));
            System.out.println('\n');
        }
        // The result of the game
        System.out.println("The hidden word was "+word.toUpperCase());
        System.out.println("You "+result);
        System.out.print('\n');
    }
    // For revealing the correct word
    private static void printCurrentRevealedWord(String word, List <Character>correctGuesses ){
        for (int j=0;j<word.length();j++){
            String charToShow = "_ ";
                for(char ch : correctGuesses){
                    if(word.charAt(j)==ch)
                        charToShow = (char)ch + " ";
                    }
            System.out.print(charToShow);
        }
    }
    
    
    
    
    private static String toString(List<Character> guesses){
        String string = "[ ";
        for(char c: guesses){
            string = string + c + " ";
        }
        return string + "]";
    }
    // Correct guesses
    private static boolean checkCompletion(String word, List <Character>correctGuesses ){
        String currentWord = "";
        for (int j=0;j<word.length();j++){
            String charToShow = "#";
                for(char ch : correctGuesses){
                    if(word.charAt(j)==ch)
                        charToShow = (char)ch + "";
                    }
            currentWord = currentWord + charToShow;
        }
        return currentWord.equals(word);
    }

}


