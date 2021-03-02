package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.zeroz.games.Main;

/*public class SettingsScreen implements Screen {

    private static final int BUTTON_WIDTH = 246;
    private static final int BUTTON_HEIGHT = 50;
    private static final int SETTINGS_BUTTON_Y = 600;
    private static final int BACK_BUTTON_Y = 50;

    Main game;

    Texture settingsButtonActive;
    Texture backButtonActive;
    Texture backButtonInactive;

    public SettingsScreen (Main game) {
        this.game = game;
        settingsButtonActive = new Texture("buttons/settingsbutton1.png");
        backButtonActive = new Texture("buttons/backbutton1.png");
        backButtonInactive = new Texture("buttons/backbutton2.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 128/255f, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        int x = Main.SCREEN_WIDTH / 2 - BUTTON_WIDTH / 2;
        game.batch.draw(settingsButtonActive, x, SETTINGS_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        if(Gdx.input.getX() < x + BUTTON_WIDTH && Gdx.input.getX() > x && Main.SCREEN_HEIGHT - Gdx.input.getY() < BACK_BUTTON_Y + BUTTON_HEIGHT && Main.SCREEN_HEIGHT - Gdx.input.getY() > BACK_BUTTON_Y) {
            game.batch.draw(backButtonActive, x, BACK_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        } else {
            game.batch.draw(backButtonInactive, x, BACK_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
        game.batch.end();
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
}*/
