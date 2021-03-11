package screens.mapScreens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.zeroz.games.Main;
import sprites.Enemy;
import sprites.Player;
import utils.*;

public class Map2Screen implements Screen {

    private Main mainGame;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private MapLayers mapLayers;

    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;
    private FillViewport playerViewport;

    private World world;
    private Player player;
    private PlayerMovement playerMovement;
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    private Enemy enemy4;
    private Enemy enemy5;
    private Enemy enemy6;
    private TextureAtlas atlas;
    private Hud hud;
    private Box2DDebugRenderer box2DDebugRenderer;

    public Map2Screen(Main mainGame) {
        this.mainGame = mainGame;
        world = new World(new Vector2(0,0), true);

        world.setContactListener(new WorldContactListener(mainGame));
        atlas = new TextureAtlas("atlas/TextureAtlas.atlas");
        player = new Player(world, this, GameData.Map2_WIDTH / 2, 50);
        enemy1 = new Enemy(world,this,(float) (GameData.Map2_WIDTH*0.2),(float)(GameData.Map2_HEIGHT*0.8));
        enemy2 = new Enemy(world,this,(float) (GameData.Map2_WIDTH*0.2 - 50),(float)(GameData.Map2_HEIGHT*0.8));
        enemy3 = new Enemy(world,this,(float) (GameData.Map2_WIDTH*0.2 - 100),(float)(GameData.Map2_HEIGHT*0.8));
        enemy4 = new Enemy(world,this,(float) (GameData.Map2_WIDTH*0.8 - 100),(float)(GameData.Map2_HEIGHT*0.8));
        enemy5 = new Enemy(world,this,(float) (GameData.Map2_WIDTH*0.8 - 50),(float)(GameData.Map2_HEIGHT*0.8));
        enemy6 = new Enemy(world,this,(float) (GameData.Map2_WIDTH*0.8),(float)(GameData.Map2_HEIGHT*0.8));
        box2DDebugRenderer = new Box2DDebugRenderer();

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/map2/map2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        mapLayers = new MapLayers(map, world);

        camera = new OrthographicCamera();
        playerViewport = new FillViewport(200, 200, camera);
        hud = new Hud(mainGame);
    }

    @Override
    public void show() {

    }

    private void update(float dt)
    {
        world.step(1/60f, 6, 2);
        player.update(dt);
        enemy1.update(dt);
        enemy2.update(dt);
        enemy3.update(dt);
        enemy4.update(dt);
        enemy5.update(dt);
        enemy6.update(dt);
        renderer.setView(camera);
        camera.position.x = player.getBox2Body().getPosition().x;
        camera.position.y = player.getBox2Body().getPosition().y;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        update(delta);
        playerMovement = new PlayerMovement(player);
        renderer.render();
        mainGame.getBatch().setProjectionMatrix(camera.combined);
        mainGame.getBatch().begin();
        player.draw(mainGame.getBatch());
        enemy1.draw(mainGame.getBatch());
        enemy2.draw(mainGame.getBatch());
        enemy3.draw(mainGame.getBatch());
        enemy4.draw(mainGame.getBatch());
        enemy5.draw(mainGame.getBatch());
        enemy6.draw(mainGame.getBatch());
        mainGame.getBatch().end();

        mainGame.getBatch().setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.getStage().act(delta);
        hud.getStage().draw();

        //box2DDebugRenderer.render(world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        playerViewport.update(width, height);
        hud.getStage().getViewport().update(width, height);
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
        hud.dispose();
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }
}
