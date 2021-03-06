import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;


public class Boggle implements WordSearchGame {
   private TreeSet<String> lexicon; 
   private List<Integer> path;
   private List<Integer> actualPath;
   private int length;
   private String[][] board;
   private Boolean[][]visited;
   private SortedSet<String> vaildWords;
   private int minLength;
   private boolean lexiconLoaded;


   public Boggle() {
      lexicon = new TreeSet<String>();
      path = new ArrayList<Integer>();
      vaildWords = new TreeSet<String>();
      actualPath = new ArrayList<Integer>();
   }


   public void loadLexicon(String fileName) {
      if (fileName == null) {
         throw new IllegalArgumentException("Incorrect entry");
      }
      Scanner fileScan;
      Scanner lineScanner;
      String line;
      try {
         fileScan = new Scanner(new FileReader(fileName));
         while (fileScan.hasNext()) {
            line = fileScan.nextLine();
            lineScanner = new Scanner(line);
            lineScanner.useDelimiter(" ");
            while (lineScanner.hasNext()) {
               lexicon.add(lineScanner.next().toLowerCase());
            }
         
         }
      } 
      catch (Exception e) {
         throw new IllegalArgumentException("Incorrect entry");
      }
   
      lexiconLoaded = true;
   }


   public void setBoard(String[] letterArray) {
   
      if (letterArray == null) {
         throw new IllegalArgumentException("Incorrect Entry");
      }
      
      double dimension = Math.sqrt(letterArray.length);
   
      if (dimension % 1 > 0) {
         throw new IllegalArgumentException("Incorrect Entry");
      }
      
      else {
         length = (int) dimension;
         board = new String[length][length];
         visited = new Boolean[length][length];
         int count = 0;
         for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
               visited[i][j] = false;
               board[i][j] = letterArray[count].toLowerCase();
               count++;
            }
         }
      }
   }


   public SortedSet getAllValidWords(int minimumWordLength) {
      minLength = minimumWordLength;
      vaildWords.clear();
      
      if (!lexiconLoaded) {
         throw new IllegalStateException("Load Lexicon");
      }
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException("Invalid Number");
      }
      
      for (int i = 0; i < length; i++) {
         for (int j = 0; j < length; j++) {
            locateWord(board[i][j], i, j);
         }
      }
      return vaildWords;
   }


   public boolean isValidWord(String wordToCheck) {
   
      if (!lexiconLoaded) {
         throw new IllegalStateException("Load lexicon");
      }
      
      if (wordToCheck == null) {
         throw new IllegalArgumentException("Invalid word");
      }
   
      return lexicon.contains(wordToCheck.toLowerCase());
   }


   public boolean isValidPrefix(String prefixToCheck) {
   
      if (!lexiconLoaded) {
         throw new IllegalStateException("Load lexicon");
      }
      
      if (prefixToCheck == null) {
         throw new IllegalArgumentException("Invalid word");
      }
      
      return lexicon.ceiling(prefixToCheck).startsWith(prefixToCheck);
   }


   public List<Integer> isOnBoard(String wordToCheck) {
   
      if (!lexiconLoaded) {
         throw new IllegalStateException("Load lexicon");
      }
   
      if (wordToCheck == null) {
         throw new IllegalArgumentException("Invalid word");
      }
      
      path.clear();
      actualPath.clear();
   
      for (int i = 0; i < (int) length; i++) {
         for (int j = 0; j < length; j++) {
            if (Character.toUpperCase(board[i][j].charAt(0))
               == Character.toUpperCase(wordToCheck.charAt(0))) {
               int value = j + (i * length);
               path.add(value);
               recursion(wordToCheck, board[i][j], i, j);
               if (!actualPath.isEmpty()) {
                  return actualPath;
               }
               path.clear();
               actualPath.clear();
            }
         }
      }
      return path;
   }


   public void locateWord(String word, int x, int y) {
   
      if (!isValidPrefix(word)) {
         return;
      }
   
      visited[x][y] = true;
   
      if (isValidWord(word) && word.length() >= minLength) {
         vaildWords.add(word.toUpperCase());
      }
   
      for (int i = -1; i <= 1; i++) {
         for (int j = -1; j <= 1; j++) {
            if ((x + i) <= ((int) length - 1)
               && (y + j) <= ((int) length - 1)
               && (x + i) >= 0 && (y + j) >= 0 && !visited[x + i][y + j]) {
               visited[x + i][y + j] = true;
               locateWord(word + board[x + i][y + j], x + i, y + j);
               visited[x + i][y + j] = false;
            }
         }
      }
      visited[x][y] = false;
   }


   public void recursion(String wordToCheck, String current, int x, int y) {
   
      visited[x][y] = true;
      if (!(isValidPrefix(current))) {
         return;
      }
      if (current.toUpperCase().equals(wordToCheck.toUpperCase())) {
         actualPath = new ArrayList(path);
         return;
      }
      for (int i = -1; i <= 1; i++) {
         for (int j = -1; j <= 1; j++) {
            if (current.equals(wordToCheck)) {
               return;
            }
            if ((x + i) <= (length - 1)
               && (y + j) <= (length - 1)
               && (x + i) >= 0 && (y + j) >= 0 && !visited[x + i][y + j]) {
               visited[x + i][y + j] = true;
               int value = (x + i) * length + y + j;
               path.add(value);
               recursion(wordToCheck, current
                  + board[x + i][y + j], x + i, y + j);
               visited[x + i][y + j] = false;
               path.remove(path.size() - 1);
            }
         }
      }
      visited[x][y] = false;
      return;
   }


   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
   
      if (!lexiconLoaded) {
         throw new IllegalStateException("Load lexicon");
      }
   
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException("length must be > 0");
      }
   
      int score = 0;
   
      for (String word: words) {
         int size = word.length();
         score += 1 + (size - minimumWordLength);
      }
   
      return score;
   }


   public String getBoard() {
   
      String result = "";
      for (String[] s: board) {
         for (String string: s) {
         
            result = result + string;
         }
      }
   
      return result;
   }
}