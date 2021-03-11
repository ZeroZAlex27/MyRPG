package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zeroz.games.Main;
import screens.mapScreens.Map2Screen;
import stats.EnemyStats;
import stats.PlayerStats;

public class BattleHud {

    private PlayerStats playerStats;
    private EnemyStats enemyStats;
    private Viewport stageViewport;
    private Stage stage;
    private Label myLevelLabel;
    private Label myLevelNumberLabel;
    private Label myHealthLabel;
    private Label myHealthNumberLabel;
    private Label myManaLabel;
    private Label myManaNumberLabel;
    private Label enemyLevelLabel;
    private Label enemyLevelNumberLabel;
    private Label enemyHealthLabel;
    private Label enemyHealthNumberLabel;
    private boolean playerTurn = true;
    public boolean cd = false;
    private float deltaTimer = 0;

    public BattleHud(final Main mainGame) {
        playerStats = new PlayerStats(mainGame);
        enemyStats = new EnemyStats(mainGame);
        stageViewport = new FillViewport(GameData.SCREEN_WIDTH, GameData.SCREEN_HEIGHT);
        stage = new Stage(stageViewport);
        Gdx.input.setInputProcessor(stage);
        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table myHpAndMpTable = new Table();
        myHpAndMpTable.setFillParent(true);
        myLevelLabel = new Label("Level", labelStyle);
        myLevelNumberLabel = new Label("" + playerStats.getPlayerLevel(), labelStyle);
        myHealthLabel = new Label("HP", labelStyle);
        myHealthNumberLabel = new Label("" + playerStats.getPlayerHP(), labelStyle);
        myManaLabel = new Label("Mana", labelStyle);
        myManaNumberLabel = new Label("" + playerStats.getPlayerMP(), labelStyle);
        myHpAndMpTable.center().padBottom(200).padRight(650);
        myHpAndMpTable.add(myLevelLabel);
        myHpAndMpTable.add(myLevelNumberLabel).padLeft(10);
        myHpAndMpTable.row();
        myHpAndMpTable.add(myHealthLabel);
        myHpAndMpTable.add(myHealthNumberLabel).padLeft(10);
        myHpAndMpTable.row();
        myHpAndMpTable.add(myManaLabel);
        myHpAndMpTable.add(myManaNumberLabel).padLeft(10);

        Table enemyHpAndMpTable = new Table();
        enemyHpAndMpTable.setFillParent(true);
        enemyLevelLabel = new Label("Level", labelStyle);
        enemyLevelNumberLabel = new Label("" + enemyStats.getEnemyLevel(), labelStyle);
        enemyHealthLabel = new Label("HP", labelStyle);
        enemyHealthNumberLabel = new Label("" + enemyStats.getEnemyHP(), labelStyle);
        enemyHpAndMpTable.center().padBottom(200).padLeft(650);
        enemyHpAndMpTable.add(enemyLevelLabel);
        enemyHpAndMpTable.add(enemyLevelNumberLabel).padLeft(10);
        enemyHpAndMpTable.row();
        enemyHpAndMpTable.add(enemyHealthLabel);
        enemyHpAndMpTable.add(enemyHealthNumberLabel).padLeft(10);

        Skin skin = new Skin(Gdx.files.internal("buttons/skin/lgdxs-ui.json"));
        final Button button1 = new TextButton("Attack", skin);
        button1.setSize(200, 70);
        button1.setPosition(150, 170);
        final Button button2 = new TextButton("Escape", skin);
        button2.setSize(200, 70);
        button2.setPosition(1000, 170);
        if(playerTurn == true) {
            button1.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    enemyStats.setEnemyHP(enemyStats.getEnemyHP() - playerStats.getPlayerAP());
                    enemyHealthNumberLabel.setText(enemyStats.getEnemyHP());
                    if(enemyStats.getEnemyHP() <= 0) {
                        mainGame.setScreen(new Map2Screen(mainGame));
                    }
                    playerTurn = false;
                    cd = true;
                    return true;
                }
            });
            button2.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    playerStats.setPlayerHP(playerStats.getPlayerHP() - (int) (playerStats.getPlayerHP() * 0.1));
                    mainGame.setScreen(new Map2Screen(mainGame));
                    return true;
                }
            });
        }
        stage.addActor(myHpAndMpTable);
        stage.addActor(enemyHpAndMpTable);
        stage.addActor(button1);
        stage.addActor(button2);
    }

    public void update(float deltaTime) {
        if(cd == true) {
            Gdx.input.setInputProcessor(null);

            deltaTimer += deltaTime;
            if(deltaTimer >= 2) {
                playerStats.setPlayerHP(playerStats.getPlayerHP() - enemyStats.getEnemyAP());
                myHealthNumberLabel.setText(playerStats.getPlayerHP());
                cd = false;
                deltaTimer = 0;
                playerTurn = true;
                Gdx.input.setInputProcessor(stage);
            }
        }
    }

    public Stage getStage() {
        return stage;
    }

    public void dispose() {
        stage.dispose();
    }
}