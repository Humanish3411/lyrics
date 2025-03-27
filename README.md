lyrics.java: main application

lyrics_big.java: original application before I obfuscated it

If you're using PowerShell, download lyrics.java, cd to the folder you put it in, run `javac lyrics.java`, then `java lyrics`. Needs a JDK to run, pretty sure. Replace lyrics with lyrics_big if you prefer the prettier code, or whatever you rename it to if you feel like doing so (weirdo). 

The app itself takes two String inputs: the first is either the name of the track you want to generate the link for or an "x" if you're inputting lyrics, and the second is the artist name or lyrics depending on the first input. 

If your input was a track and an artist, the application will return a link to an API call. When you're there, copy the plainLyrics JSON and return to the application. Input x for the first blank and what you just copied as the second. 
The app will remove quotation marks and censor an admittedly small list of swear words, as well as format the text output correctly. It'll also copy the formatted output to your clipboard. It might as well make you coffee and treat you to a manicure or something with how "feature-rich" it already is. 

I wanted it to automatically make the API call, but I couldn't be bothered to tinker with a bajillion apps to get it to work. I do it manually, so can you.
(though, if you want to fork it to add such functionality, I'll be incredibly grateful). 

Enjoy?
