package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import sprites.Player;

public class PlayerMovement {

    public PlayerMovement(Player player) {
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.getBox2Body().applyLinearImpulse(new Vector2(5f, 0), player.getBox2Body().getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.getBox2Body().applyLinearImpulse(new Vector2(-5f, 0), player.getBox2Body().getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.getBox2Body().applyLinearImpulse(new Vector2(0, 5f), player.getBox2Body().getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.getBox2Body().applyLinearImpulse(new Vector2(0, -5f), player.getBox2Body().getWorldCenter(), true);
        }
        if(!Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.getBox2Body().setLinearVelocity(0,0);
        }
    }
}
