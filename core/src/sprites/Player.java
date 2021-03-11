package sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import screens.BattleScreen;
import screens.mapScreens.Map1Screen;
import screens.mapScreens.Map2Screen;
import utils.BattleHud;
import utils.GameData;

public class Player extends Sprite {

    private BattleHud battleHud;
    private World world;
    private Body box2Body;
    private TextureRegion playerIdleTexture;
    public enum State {STANDING, RUNNING, ATTACKING}
    public State previousState;
    public State currentState;
    private float stateTimer;
    private boolean runningToRight;
    private boolean isBattle = true;
    private Animation<TextureRegion> runAnimation;
    private Animation<TextureRegion> standAnimation;
    private Animation<TextureRegion> attackAnimation;

    public Player(World world, Map1Screen map1Screen, int x, int y) {
        this.world = world;
        definePlayerBox2d(x, y);
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        playerIdleTexture = new TextureRegion(map1Screen.getAtlas().findRegion(GameData.PLAYER_SPRITE), 1, 2, 50, 37);


        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i = 0; i <= 150; i+= 50) {
            frames.add(new TextureRegion(map1Screen.getAtlas().findRegion(GameData.PLAYER_SPRITE), i * 1, 2, 50, 37));
        }
        standAnimation = new Animation<>(0.3f, frames);
        frames.clear();
        for(int i = 50; i <= 300; i+= 50) {
            frames.add(new TextureRegion(map1Screen.getAtlas().findRegion(GameData.PLAYER_SPRITE), i * 1, 37, 50, 37));
        }
        runAnimation = new Animation<>(0.1f, frames);
        frames.clear();

        setBounds(0, 0, 50, 37);
        setRegion(playerIdleTexture);
    }

    public Player(World world, Map2Screen map2Screen, int x, int y) {
        this.world = world;
        definePlayerBox2d(x, y);
        currentState = State.STANDING;
        previousState = State.STANDING;
        runningToRight = true;
        stateTimer = 0;
        playerIdleTexture = new TextureRegion(map2Screen.getAtlas().findRegion(GameData.PLAYER_SPRITE), 1, 2, 50, 37);

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i = 0; i <= 150; i+= 50) {
            frames.add(new TextureRegion(map2Screen.getAtlas().findRegion(GameData.PLAYER_SPRITE), i * 1, 2, 50, 37));
        }
        standAnimation = new Animation<>(0.3f, frames);
        frames.clear();
        for(int i = 50; i <= 300; i+= 50) {
            frames.add(new TextureRegion(map2Screen.getAtlas().findRegion(GameData.PLAYER_SPRITE), i * 1, 37, 50, 37));
        }
        runAnimation = new Animation<>(0.1f, frames);
        frames.clear();

        setBounds(0, 0, 50, 37);
        setRegion(playerIdleTexture);
    }

    public Player(World world, BattleScreen battleScreen, BattleHud battleHud, int x, int y) {
        this.battleHud = battleHud;
        this.world = world;
        definePlayerBox2d(x, y);
        currentState = State.STANDING;
        previousState = State.STANDING;
        runningToRight = true;
        stateTimer = 0;
        playerIdleTexture = new TextureRegion(battleScreen.getAtlas().findRegion(GameData.PLAYER_SPRITE), 1, 2, 50, 37);

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i = 150; i <= 300; i+= 50)
        {
            frames.add(new TextureRegion(battleScreen.getAtlas().findRegion(GameData.PLAYER_SPRITE), i * 1, 185, 50, 37));
        }
        standAnimation = new Animation<>(0.3f, frames);
        frames.clear();
        for(int i = 0; i <= 300; i+= 50) {
            frames.add(new TextureRegion(battleScreen.getAtlas().findRegion(GameData.PLAYER_SPRITE), i * 1, 222, 50, 37));
        }
        for(int i = 0; i <= 300; i+= 50) {
            frames.add(new TextureRegion(battleScreen.getAtlas().findRegion(GameData.PLAYER_SPRITE), i * 1, 259, 50, 37));
        }
        for(int i = 0; i <= 150; i+= 50) {
            frames.add(new TextureRegion(battleScreen.getAtlas().findRegion(GameData.PLAYER_SPRITE), i * 1, 296, 50, 37));
        }
        attackAnimation = new Animation<>(0.1f, frames);
        frames.clear();

        setBounds(0, 0, 50, 37);
        setRegion(playerIdleTexture);
    }

    private TextureRegion getFrame(float deltaTime)
    {
        currentState = getState();
        TextureRegion region;
        switch(currentState)
        {
            case ATTACKING:
                region = attackAnimation.getKeyFrame(stateTimer, true);
                break;
            case RUNNING:
                region = runAnimation.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
            default:
                region = standAnimation.getKeyFrame(stateTimer, true);
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

    public void definePlayerBox2d(int x, int y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        box2Body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(8, 16);

        fixtureDef.shape = polygonShape;
        box2Body.createFixture(fixtureDef).setUserData(this);
    }

    public Body getBox2Body() {
        return box2Body;
    }

    public State getState() {
        if (box2Body.getLinearVelocity().x != 0 || box2Body.getLinearVelocity().y != 0) {
            return State.RUNNING;
        }
        if(this.battleHud != null) {
            if(battleHud.cd == true) {
                return State.ATTACKING;
            }
        }
        return State.STANDING;
    }
}
