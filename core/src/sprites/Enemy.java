package sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import screens.GameMapScreen;

public class Enemy extends Sprite {

    private World world;
    private Body box2Body;
    private TextureRegion standing;

    public Enemy(World world, GameMapScreen screen, float x, float y) {
        this.world = world;
        setPosition(x, y);
        standing = new TextureRegion(screen.getAtlas().findRegion("skeleton"), 0, 0, 16, 16);
        setBounds(getX(), getY(), 16, 16);
        setRegion(standing);
        defineBody();
    }

    public void defineBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        box2Body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(16, 16);

        fixtureDef.shape = polygonShape;
        box2Body.createFixture(fixtureDef).setUserData(this);
    }

    public void update(float deltaTime)
    {
        setPosition(box2Body.getPosition().x - getWidth() / 2, box2Body.getPosition().y - getHeight() / 2);
    }
}
