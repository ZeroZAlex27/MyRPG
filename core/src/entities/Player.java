package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import world.GameMap;

public class Player extends Entity {

    private static final int SPEED = 60;

    Texture image;

    public Player(float x, float y, GameMap map) {
        super(x, y, EntityType.PLAYER, map);
        image = new Texture("player/player.png");
    }

    public void update(float deltaTime) {
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            moveX(-SPEED * deltaTime);
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            moveX(SPEED * deltaTime);
        if(Gdx.input.isKeyPressed(Input.Keys.W))
            moveY(SPEED * deltaTime);
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            moveY(-SPEED * deltaTime);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
    }
}
