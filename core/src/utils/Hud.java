package utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zeroz.games.Main;

public class Hud {

    private Main mainGame;
    private Viewport stageViewport;
    private Stage stage;
    private Label levelLabel;
    private Label levelNumberLabel;
    private Label healthLabel;
    private Label healthNumberLabel;
    private Label manaLabel;
    private Label manaNumberLabel;

    public Hud()
    {
        stageViewport = new FillViewport(Data.SCREEN_WIDTH, Data.SCREEN_HEIGHT);
        stage = new Stage(stageViewport);

        Table levelTable = new Table();
        levelTable.setFillParent(true);
        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        levelLabel = new Label("LEVEL", labelStyle);
        levelNumberLabel = new Label("1", labelStyle);
        levelTable.left().top().pad(10);
        levelTable.add(levelLabel);
        levelTable.add(levelNumberLabel).padLeft(10);

        Table HpAndMpTable = new Table();
        HpAndMpTable.setFillParent(true);
        healthLabel = new Label("HP", labelStyle);
        healthNumberLabel = new Label("100", labelStyle);
        manaLabel = new Label("Mana", labelStyle);
        manaNumberLabel = new Label("10", labelStyle);
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
