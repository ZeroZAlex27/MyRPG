package screens.mapScreens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.zeroz.games.Main;
import sprites.Player;
import utils.Data;
import utils.Hud;
import utils.WorldContactListener;

public class Map2Screen implements Screen {

    Main mainGame;
    TmxMapLoader mapLoader;
    TiledMap map;

    OrthographicCamera camera;
    OrthogonalTiledMapRenderer renderer;

    private World world;
    private Player player;
    private TextureAtlas atlas;
    private Hud hud;
    private Box2DDebugRenderer box2DDebugRenderer;

    public Map2Screen(Main mainGame) {
        this.mainGame = mainGame;
        world = new World(new Vector2(0,0), true);

        world.setContactListener(new WorldContactListener());
        atlas = new TextureAtlas("player/Player_sprites.atlas");
        player = new Player(world, this, (Data.Map2_WIDTH / 2), 50);
        box2DDebugRenderer = new Box2DDebugRenderer();

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/map2/map2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        hud = new Hud(camera);
        //camera.position.set(hud.getViewport().getWorldWidth() / 2, hud.getViewport().getWorldHeight() / 2, 0);

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;
        for(MapObject mapObject : map.getLayers().get(Data.COLLISION_LAYER).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(rectangle.getX() + rectangle.getWidth() / 2,
                    rectangle.getY() + rectangle.getHeight() / 2);
            body = world.createBody(bodyDef);
            shape.setAsBox(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }
    }

    private void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.getBox2Body().applyLinearImpulse(new Vector2(5f, 0), player.getBox2Body().getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            player.getBox2Body().applyLinearImpulse(new Vector2(-5f, 0), player.getBox2Body().getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            player.getBox2Body().applyLinearImpulse(new Vector2(0, 5f), player.getBox2Body().getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            player.getBox2Body().applyLinearImpulse(new Vector2(0, -5f), player.getBox2Body().getWorldCenter(), true);
        }
        if(!Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)){
            player.getBox2Body().setLinearVelocity(0,0);
        }
    }

    @Override
    public void show() {

    }

    private void update(float dt)
    {
        world.step(1/60f, 6, 2);
        player.update(dt);
        camera.update();
        renderer.setView(camera);
        camera.position.x = player.getBox2Body().getPosition().x;
        camera.position.y = player.getBox2Body().getPosition().y;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        handleInput();
        renderer.render();
        mainGame.getBatch().begin();
        player.draw(mainGame.getBatch());
        mainGame.getBatch().end();
        mainGame.getBatch().setProjectionMatrix(camera.combined);
        //hud.getStage().draw();
        box2DDebugRenderer.render(world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        hud.getViewport().update(width, height);
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
        map.dispose();
        renderer.dispose();
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }
}
