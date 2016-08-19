# Three-Syllable-Lingo
A variant of the popular Lingo game concept

## Game Description
Here is how the Three-Syllable-Singo game works:
* The game starts and a random three-syllable word is chosen by the game engine
* The word is show to the player:
    * The first character of the word will be visible
    * The total length of the word will be visible, where the characters other than the first one are shown as wildcards
    * The syllables will be distinguishable
* The player can now guess the word by putting in characters on the word's available character locations
* The player confirms his input
* The game will verify the input:
    * Locations that contain a valid character are marked as "Valid"
    * Locations that do not contain a valid character:
        * If the character does not exist in the word, the location is marked as "Invalid"
        * If the character does exist in the word but on another location, the location is marked as "Misplaced"
    * The result of the verified input will define the following actions:
        * If the verified input only contains "Valid" locations, the player has guessed the word. The game restarts with a new random word.
        * If the verified input contains one or more "Invalid" or "Misplaced" locations, the player can guess again by providing new input.
* The game ends when the player quits guessing and closes the game

To summarize, the differences with the default Lingo concept are:
* Variable word length
* Unlimited tries
* Syllable distinguishing

## Game Specifications
These are the requirements that are defined for the game. They are open for change as the game development progresses.
### Must-have
1. Language Support: Dutch (NL)
2. Java Swing GUI

### Should-have
1. Language Support for other languages
2. Score system
3. Multiplayer
4. Web-based GUI


### Could-have
1. Option for sentence that rhymes on the guessed word (as a joke)
2. Multiplayer over network
3. Sound/voice input

### Won't-have