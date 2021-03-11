package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import utils.BattleHud;
import utils.GameData;

public class BattleScreen implements Screen {

    private Main mainGame;
    private TmxMapLoader mapLoader;
    private TiledMap map;

    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;
    private FillViewport playerViewport;

    private World world;
    private Player player;
    private Enemy enemy;
    private TextureAtlas atlas;
    private TextureAtlas enemyAtlas;
    private BattleHud hud;
    private Box2DDebugRenderer box2DDebugRenderer;

    public BattleScreen(Main mainGame) {
        this.mainGame = mainGame;
        world = new World(new Vector2(0,0), true);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/map2/map2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        playerViewport = new FillViewport(200, 200, camera);
        hud = new BattleHud(mainGame);

        atlas = new TextureAtlas("atlas/TextureAtlas.atlas");
        player = new Player(world, this, hud, (GameData.Map2_WIDTH / 2) - 50, 250);
        enemy = new Enemy(world,this,(GameData.Map2_WIDTH / 2) + 50, 250);
        box2DDebugRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void show() {

    }

    private void update(float dt)
    {
        world.step(1/60f, 6, 2);
        player.update(dt);
        enemy.update(dt);
        hud.update(dt);
        renderer.setView(camera);
        camera.position.x = GameData.Map2_WIDTH / 2;
        camera.position.y = 250;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        update(delta);
        renderer.render();
        mainGame.getBatch().setProjectionMatrix(camera.combined);
        mainGame.getBatch().begin();
        player.draw(mainGame.getBatch());
        enemy.draw(mainGame.getBatch());
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

    public TextureAtlas getEnemyAtlas() {
        return enemyAtlas;
    }
}
