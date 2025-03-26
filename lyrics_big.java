import java.util.Scanner;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;

//open source, public domain, yadayadayada. made by Jordon O. (Humanish)

class lyrics {
  // Obfuscated swear words method
  private static List<String> getSwearWords() {
    String obfuscated = "ZnVjayxzaGl0LGRhbW4sY3JhcCxiaXRjaCxhc3Nob2xlLGRpY2ssY29jaxxwdXNzeSx3YW5rLGJhc3RhcmQsYm9sbG9ja3MsY3VudA==";
    byte[] decoded = java.util.Base64.getDecoder().decode(obfuscated);
    String[] words = new String(decoded).split(",");
    return new ArrayList<>(java.util.Arrays.asList(words));
  }

  // Method to censor a word
  private static String censorWord(String word) {
    if (word.length() <= 1) return word;
    
    // Keeps first and last letter, replaces middle with asterisks
    return word.charAt(0) + 
           "*".repeat(Math.max(0, word.length() - 2)) + 
           word.charAt(word.length() - 1);
  }

  // Method to censor text
  private static String censorText(String text) {
    // Get obfuscated swear words
    List<String> SWEAR_WORDS = getSwearWords();
    
    // Split text into words
    String[] words = text.split("\\s+");
    
    // Process each word
    for (int i = 0; i < words.length; i++) {
      // Convert to lowercase for case-insensitive matching
      String lowerWord = words[i].toLowerCase()
        .replaceAll("[^a-zA-Z]", "");
      
      // Check if word is a swear word
      for (String swear : SWEAR_WORDS) {
        if (lowerWord.equals(swear)) {
          words[i] = censorWord(words[i]);
          break;
        }
      }
    }
    
    // Reconstruct the text
    return String.join(" ", words);
  }

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String track = "";
    String artist = "";
    String trackfix = "";
    String artistfix = "";
    boolean lyrics = false;
    System.out.println("TRACK_NAME / x\nARTIST_NAME / LYRICS");
    track = scan.nextLine();
    if (track.equals("x")) {
      lyrics = true;
    } else {
      for (int i = 0; i < track.length(); i++) {
        String ltr = track.substring(i, i + 1);
        trackfix += (ltr.equals(" ")) ? "+" : ltr;
      }
    }
    artist = scan.nextLine();
    if (lyrics) {
      artistfix = artist.replace("\\n", "\n");
      
      // Removes quotation marks and censors text
      artistfix = artistfix.replace("\"", "");
      artistfix = censorText(artistfix);
      
      // Copies formatted lyrics to clipboard
      StringSelection stringSelection = new StringSelection(artistfix);
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      clipboard.setContents(stringSelection, null);
      
      System.out.println("\n" + artistfix);
    } else {
      for (int i = 0; i < artist.length(); i++) {
        String ltr = artist.substring(i, i + 1);
        artistfix += (ltr.equals(" ")) ? "+" : ltr;
      }
      String urlString = "https://lrclib.net/api/get?artist_name=" + artistfix + "&track_name=" + trackfix;
      System.out.println(urlString);
    }
    scan.close();
  }
}