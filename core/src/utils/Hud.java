package utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zeroz.games.Main;
import stats.PlayerStats;

public class Hud {

    private Main mainGame;
    private PlayerStats playerStats;
    private Viewport stageViewport;
    private Stage stage;
    private Label levelLabel;
    private Label levelNumberLabel;
    private Label healthLabel;
    private Label healthNumberLabel;
    private Label manaLabel;
    private Label manaNumberLabel;

    public Hud(Main mainGame)
    {
        this.mainGame = mainGame;
        playerStats = new PlayerStats(mainGame);
        stageViewport = new FillViewport(GameData.SCREEN_WIDTH, GameData.SCREEN_HEIGHT);
        stage = new Stage(stageViewport);
        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table levelTable = new Table();
        levelTable.setFillParent(true);
        levelLabel = new Label("LEVEL", labelStyle);
        levelNumberLabel = new Label("" + playerStats.getPlayerLevel(), labelStyle);
        levelTable.left().top().pad(10);
        levelTable.add(levelLabel);
        levelTable.add(levelNumberLabel).padLeft(10);

        Table HpAndMpTable = new Table();
        HpAndMpTable.setFillParent(true);
        healthLabel = new Label("HP", labelStyle);
        healthNumberLabel = new Label("" + playerStats.getPlayerHP(), labelStyle);
        manaLabel = new Label("Mana", labelStyle);
        manaNumberLabel = new Label("" + playerStats.getPlayerMP(), labelStyle);
        HpAndMpTable.center().padBottom(150);
        HpAndMpTable.add(healthLabel);
        HpAndMpTable.add(healthNumberLabel).padLeft(10);
        HpAndMpTable.row();
        HpAndMpTable.add(manaLabel);
        HpAndMpTable.add(manaNumberLabel).padLeft(10);

        stage.addActor(levelTable);
        stage.addActor(HpAndMpTable);
    }

    public Stage getStage() {
        return stage;
    }

    public void dispose() {
        stage.dispose();
    }
}
