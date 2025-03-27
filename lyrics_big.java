import java.util.Scanner;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    
    // Preserve first and last character, replace middle with asterisks
    return word.charAt(0) + 
           "*".repeat(Math.max(0, word.length() - 2)) + 
           word.charAt(word.length() - 1);
  }

  // Method to censor text
  private static String censorText(String text) {
    // Get obfuscated swear words
    List<String> SWEAR_WORDS = getSwearWords();
    
    // Create a pattern that matches whole words or partial words
    StringBuilder patternBuilder = new StringBuilder("(");
    for (int i = 0; i < SWEAR_WORDS.size(); i++) {
      patternBuilder.append(SWEAR_WORDS.get(i));
      if (i < SWEAR_WORDS.size() - 1) {
        patternBuilder.append("|");
      }
    }
    patternBuilder.append(")");
    
    Pattern swearPattern = Pattern.compile(patternBuilder.toString(), 
                                            Pattern.CASE_INSENSITIVE);
    
    // Use a matcher to find and replace swear words
    Matcher matcher = swearPattern.matcher(text);
    StringBuffer result = new StringBuffer();
    
    while (matcher.find()) {
      String match = matcher.group();
      String censored = censorWord(match);
      matcher.appendReplacement(result, censored);
    }
    matcher.appendTail(result);
    
    return result.toString();
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
      
      // Removes quotation marks and censors text
     artistfix = artist;
      String artistfix2 = censorText(artistfix);
	  String artistfix3 = artistfix2.replace("\"", "");
      String artistfix4 = artistfix3.replace("\\n", "\n");

      // Copies censored text to clipboard
      StringSelection stringSelection = new StringSelection(artistfix4);
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      clipboard.setContents(stringSelection, null);
      
      System.out.println("\n" + artistfix4);
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
