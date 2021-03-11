package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import utils.GameData;
import com.zeroz.games.Main;

public class SettingsScreen implements Screen {

    private static final int BUTTON_WIDTH = 246;
    private static final int BUTTON_HEIGHT = 50;
    private static final int SETTINGS_BUTTON_Y = 600;
    private static final int BACK_BUTTON_Y = 50;
    private static final int FULLSCREEN_Y = 400;

    private Main mainGame;

    private Texture settingsButtonActive;
    private Texture backButtonActive;
    private Texture backButtonInactive;
    private Texture fullScreenActive;
    private Texture fullScreenInactive;

    public SettingsScreen(Main mainGame) {
        this.mainGame = mainGame;
        settingsButtonActive = new Texture("buttons/settingsbutton1.png");
        backButtonActive = new Texture("buttons/backbutton1.png");
        backButtonInactive = new Texture("buttons/backbutton2.png");
        fullScreenActive = new Texture("buttons/fullscreenbutton1.png");
        fullScreenInactive = new Texture("buttons/fullscreenbutton2.png");

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 128/255f, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainGame.getBatch().begin();
        int x = GameData.SCREEN_WIDTH / 2 - BUTTON_WIDTH / 2;
        mainGame.getBatch().draw(settingsButtonActive, x, SETTINGS_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        if(Gdx.input.getX() < x + BUTTON_WIDTH && Gdx.input.getX() > x && GameData.SCREEN_HEIGHT - Gdx.input.getY() < BACK_BUTTON_Y + BUTTON_HEIGHT && GameData.SCREEN_HEIGHT - Gdx.input.getY() > BACK_BUTTON_Y) {
            mainGame.getBatch().draw(backButtonActive, x, BACK_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(mainGame));
            }
        } else {
            mainGame.getBatch().draw(backButtonInactive, x, BACK_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
        /*if(Gdx.input.getX() < x + BUTTON_WIDTH && Gdx.input.getX() > x && GameData.SCREEN_HEIGHT - Gdx.input.getY() < FULLSCREEN_Y + BUTTON_HEIGHT && GameData.SCREEN_HEIGHT - Gdx.input.getY() > FULLSCREEN_Y) {
            mainGame.getBatch().draw(fullScreenActive, x, FULLSCREEN_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                if(Gdx.graphics.isFullscreen() == false) {
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                }
                else if (Gdx.graphics.isFullscreen() == true) {
                    Gdx.graphics.setWindowedMode(GameData.SCREEN_WIDTH, GameData.SCREEN_HEIGHT);
                }
            }
        } else {
            mainGame.getBatch().draw(fullScreenInactive, x, FULLSCREEN_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        }*/
        mainGame.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
