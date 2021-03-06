package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import screens.mapScreens.Map1Screen;
import screens.mapScreens.Map2Screen;
import utils.Data;
import com.zeroz.games.Main;

public class MainMenuScreen implements Screen {

    private static final int BUTTON_WIDTH = 246;
    private static final int BUTTON_HEIGHT = 50;
    private static final int PLAY_BUTTON_Y = 500;
    private static final int SETTINGS_BUTTON_Y = 300;
    private static final int EXIT_BUTTON_Y = 100;


    Main mainGame;

    Texture playButtonActive;
    Texture playButtonInactive;
    Texture settingsButtonActive;
    Texture settingsButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;

    public MainMenuScreen(Main mainGame) {
        this.mainGame = mainGame;
        playButtonActive = new Texture("buttons/playbutton1.png");
        playButtonInactive = new Texture("buttons/playbutton2.png");
        settingsButtonActive = new Texture("buttons/settingsbutton1.png");
        settingsButtonInactive = new Texture("buttons/settingsbutton2.png");
        exitButtonActive = new Texture("buttons/exitbutton1.png");
        exitButtonInactive = new Texture("buttons/exitbutton2.png");
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 128/255f, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainGame.getBatch().begin();
        int x = Data.SCREEN_WIDTH / 2 - BUTTON_WIDTH / 2;
        if(Gdx.input.getX() < x + BUTTON_WIDTH && Gdx.input.getX() > x && Data.SCREEN_HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + BUTTON_HEIGHT && Data.SCREEN_HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y) {
            mainGame.getBatch().draw(playButtonActive, x, PLAY_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Map2Screen(mainGame));
            }
        } else {
            mainGame.getBatch().draw(playButtonInactive, x, PLAY_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
        if(Gdx.input.getX() < x + BUTTON_WIDTH && Gdx.input.getX() > x && Data.SCREEN_HEIGHT - Gdx.input.getY() < SETTINGS_BUTTON_Y + BUTTON_HEIGHT && Data.SCREEN_HEIGHT - Gdx.input.getY() > SETTINGS_BUTTON_Y) {
            mainGame.getBatch().draw(settingsButtonActive, x, SETTINGS_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new SettingsScreen(mainGame));
            }
        } else {
            mainGame.getBatch().draw(settingsButtonInactive, x, SETTINGS_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
        if(Gdx.input.getX() < x + BUTTON_WIDTH && Gdx.input.getX() > x && Data.SCREEN_HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + BUTTON_HEIGHT && Data.SCREEN_HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y) {
            mainGame.getBatch().draw(exitButtonActive, x, EXIT_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            mainGame.getBatch().draw(exitButtonInactive, x, EXIT_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
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
