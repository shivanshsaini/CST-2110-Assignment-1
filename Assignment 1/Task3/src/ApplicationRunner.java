
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ApplicationRunner {

    public static void main(String[] args) {

        String dataFile = System.getProperty("user.dir") + File.separator + "lexicon.txt";

        List<String> wordList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                wordList.add(line.toLowerCase());
            }   

	} catch (IOException e) {
            e.printStackTrace();
	}
        // Title for the anagram solver
        while(true){
            System.out.println("Please enter a string (single word or phrase)");
            System.out.print("> ");
            Scanner sc = new Scanner(System.in);
            printAnagrams(wordList,sc.nextLine());
            // Inputs for trying again and quiting
            System.out.print("Try again (1) or Exit (0) > ");
            String response = sc.nextLine();
            if(response.equals("0")){
               break;
            }else if(response.equals("1")){
               continue;
            }else{
                System.out.println("wrong input");
            }
        }
      
        
        String stringWithoutSpaces = dataFile.replaceAll("\\s+", "");
    }
    
    // Enter only lower case letters and avoid higher case letters
    static void printAnagrams(List<String> list, String word){
        List<String> anagramList = new ArrayList<>();
        for(String str: list){
            char[] charArray1 = str.toLowerCase().toCharArray();
            char[] charArray2 = word.toLowerCase().replaceAll("\\s+", "").toCharArray();
            if(areAnagram(charArray1, charArray2)){
                anagramList.add(str.toLowerCase());
            }
        }
        
        String response = "Possible anagrams for \"" + word + "\": " + anagramList.toString();
        System.out.println(response);
    }
    
    static boolean areAnagram(char[] str1, char[] str2) 
    { 
        // Get lengths of both strings 
        int n1 = str1.length; 
        int n2 = str2.length; 
  
        // If length of both strings is not same, 
        // then they cannot be anagram 
        if (n1 != n2) 
            return false; 
  
        // Sort both strings 
        Arrays.sort(str1); 
        Arrays.sort(str2); 
  
        // Compare sorted strings 
        for (int i = 0; i < n1; i++) 
            if (str1[i] != str2[i]) 
                return false; 
  
        return true; 
    } 
    
}
