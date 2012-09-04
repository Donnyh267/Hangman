package edu.drexel.cs451.hangman;

import edu.drexel.cs451.hangman.accessor.GameStatus;
import edu.drexel.cs451.hangman.accessor.PubnubService;
import edu.drexel.cs451.hangman.view.MultiplayerScreenView;

public class MultiPlayerGame extends SinglePlayerGame {
    
    private String playerName;
    private MultiplayerScreenView view;
    private final PubnubService pubnub;
    
    public MultiPlayerGame(HangmanGame game, String playerName) {
        super(game);
        setPlayerName(playerName);
        pubnub = PubnubService.getInstance();
        pubnub.setGame(this);
        pubnub.subscribe();
        pubnub.hi();
    }
    
    @Override
    public MultiplayerScreenView getView() {
        return view;
    }
    
    public void start() {
        this.setPickedWord("PLEASE WAIT");
        view = new MultiplayerScreenView(this);
        view.makeWait();
        game.changePanel(view);
        view.requestFocus();
        view.requestFocusInWindow();
    }
    
    public void start(String w) {
        this.setPickedWord(w);
        status = GameStatus.CONTINUE;
        view = new MultiplayerScreenView(this);
        game.changePanel(view);
        view.requestFocus();
        view.requestFocusInWindow();
    }
    
    public void setPlayerName(String name) {
        playerName = name;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    // called when player guessed a word
    @Override
    public void guess(String guessedWord) {
        super.guess(guessedWord);
        pubnub.sendMsg(playerName + " guessed letter " + guessedWord);
        if (status == GameStatus.WIN) {
            pubnub.win();
        }
    }
    
    public void someoneWon(String winner) {
        view.addMsg(winner + " won the game.. Try again next time.");
        view.drawLoseScreen();
    }
    
    public void quit() {
        game.changePanel(game.menuScreenView);
    }

    public void addMsg(String string) {
        view.addMsg(string);        
    }
}
