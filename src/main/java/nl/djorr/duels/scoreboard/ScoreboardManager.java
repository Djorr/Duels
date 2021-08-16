package nl.djorr.duels.scoreboard;

import nl.djorr.duels.Duels;

public class ScoreboardManager {

    private Duels plugin;

    public ScoreboardManager(Duels Duels) {
        plugin = Duels;
    }

    public void LobbyScoreboard() {
        plugin.ScoreBoard.setupScoreboard();
    }

}