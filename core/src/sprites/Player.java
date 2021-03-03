package sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import screens.GameMapScreen;
import utils.Data;

public class Player extends Sprite {

    private World world;
    private Body box2Body;
    private TextureRegion playerIdleTexture;
    public enum State {STANDING, RUNNING}
    public State currentState;
    private boolean runningToRight;

    public Player(World world, GameMapScreen gameMapScreen) {
        super(gameMapScreen.getAtlas().findRegion(Data.PLAYER_SPRITE));
        this.world = world;
        definePlayerBox2d();
        currentState = State.STANDING;
        runningToRight = true;
        playerIdleTexture = new TextureRegion(gameMapScreen.getAtlas().findRegion(Data.PLAYER_SPRITE), 2, 2, 50, 37);
        setBounds(0, 0, 16, 16);
        setRegion(playerIdleTexture);
    }
    private TextureRegion getFrame(float deltaTime)
    {
        currentState = getState();
        TextureRegion region;
        switch(currentState)
        {
            case STANDING:
            default:
                region = playerIdleTexture;
                break;
        }

        if((box2Body.getLinearVelocity().x < 0 || !runningToRight) && !region.isFlipX())
        {
            region.flip(true, false);
            runningToRight = false;
        }
        else if((box2Body.getLinearVelocity().x > 0 || runningToRight) && region.isFlipX())
        {
            region.flip(true, false);
            runningToRight = true;
        }
        return region;
    }

    public void update(float deltaTime)
    {
        setPosition(box2Body.getPosition().x - getWidth() / 2, box2Body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(deltaTime));
    }

    public void definePlayerBox2d() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(Data.Map1_WIDTH / 2, 10);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        box2Body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(16, 16);

        fixtureDef.shape = polygonShape;
        box2Body.createFixture(fixtureDef).setUserData(this);
    }

    public Body getBox2Body() {
        return box2Body;
    }

    public State getState() {
        return State.STANDING;
    }
}
