package stats;

import com.zeroz.games.Main;

public class PlayerStats {

    private Main mainGame;
    private int playerLevel = 1;
    private int playerHP = 100;
    private int playerMP = 10;
    private int playerAP = 10;

    public PlayerStats(Main mainGame) {
        this.mainGame = mainGame;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }

    public int getPlayerHP() {
        return playerHP;
    }

    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }

    public int getPlayerMP() {
        return playerMP;
    }

    public void setPlayerMP(int playerMP) {
        this.playerMP = playerMP;
    }

    public int getPlayerAP() {
        return playerAP;
    }

    public void setPlayerAP(int playerAP) {
        this.playerAP = playerAP;
    }
}
