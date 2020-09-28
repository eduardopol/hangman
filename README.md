

# Requiriments:

* NPM and NodeJS (<a href="https://nodejs.org/en/download/">Click Here to Install</a>)
* JAVA (<a href="https://www.java.com/en/download/help/download_options.html">Click Here to Install</a>)
* Maven (mvn command) (<a href="https://maven.apache.org/install.html">Click Here to Install</a>)

# How to Run
To Run, you on windows, simply execute the PowerShell scripts (run-back-end.ps1 and run-front-end.ps1) on separate PowerShell windows and everything will be up and running.

For Linux, access the folder `hangman-java`, execute `mvn-install`, after it's done, execute `java -jar target/hangman-1.0.0.jar`. In a new terminal window, access the folder `hangman-angular`, execute `npm install`, `ng build --prod` and finally `ng serve --prod`.

The Java back-end will be running on http://localhost:8080 and the front-end will be running on http://localhost:4200.


# How to Play

1 - You'll be asked to inform your name (required to play)</br>
2 - The game page will apear</br>
3 - A word will be randomly selected for the game, you should use your keyboard or the buttons on the screen to discover the hidden word</br>
4 - If you guess 6 letters that are no part of the word, the man will be hanged, and you will lose the game.</br>
5 - To win, you should find the whole word.</br>
