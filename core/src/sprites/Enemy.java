package sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import screens.BattleScreen;
import screens.mapScreens.Map2Screen;
import utils.BattleHud;
import utils.GameData;

public class Enemy extends Sprite {

    private World world;
    private Body box2Body;
    private TextureRegion standing;
    public enum State {STANDING}
    public Enemy.State previousState;
    public Enemy.State currentState;
    private float stateTimer;
    private Animation<TextureRegion> standAnimation;
    private boolean flipBody = true;

    public Enemy(World world, Map2Screen map2Screen, float x, float y) {
        this.world = world;
        defineBody(x, y);
        standing = new TextureRegion(map2Screen.getAtlas().findRegion(GameData.ENEMIES_SPRITE), 150, 200, 30, 30);

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i = 366; i <= 478; i+= 16)
        {
            frames.add(new TextureRegion(map2Screen.getAtlas().findRegion(GameData.ENEMIES_SPRITE), i * 1, 70, 20, 30));
        }
        standAnimation = new Animation<>(0.1f, frames);
        frames.clear();

        setBounds(getX(), getY(), 30, 30);
        setRegion(standing);
    }

    public Enemy(World world, BattleScreen battleScreen, float x, float y) {
        this.world = world;
        defineBody(x, y);
        standing = new TextureRegion(battleScreen.getAtlas().findRegion(GameData.ENEMIES_SPRITE), 150, 200, 20, 30);

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i = 366; i <= 478; i+= 16)
        {
            frames.add(new TextureRegion(battleScreen.getAtlas().findRegion(GameData.ENEMIES_SPRITE), i * 1, 70, 20, 30));
        }
        standAnimation = new Animation<>(0.1f, frames);
        frames.clear();

        setBounds(getX(), getY(), 30, 30);
        setRegion(standing);
    }

    private TextureRegion getFrame(float deltaTime)
    {
        currentState = getState();
        TextureRegion region;
        switch(currentState)
        {
            case STANDING:
            default:
                region = standAnimation.getKeyFrame(stateTimer, true);
                break;
        }

        if(!region.isFlipX() && flipBody == true)
        {
            region.flip(true, false);
        }

        if(currentState == previousState) {
            stateTimer = stateTimer + deltaTime;
        } else {
            stateTimer = 0;
        }
        previousState = currentState;

        return region;
    }

    public void update(float deltaTime)
    {
        setPosition(box2Body.getPosition().x - getWidth() / 2, box2Body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(deltaTime));
    }

    public void defineBody(float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        box2Body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(8, 16);

        fixtureDef.shape = polygonShape;
        box2Body.createFixture(fixtureDef).setUserData(this);
    }

    public Enemy.State getState() {
        return Enemy.State.STANDING;
    }
}
