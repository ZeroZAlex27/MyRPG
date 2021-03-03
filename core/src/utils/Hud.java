package utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud {

    private Data data;
    private Viewport viewport;
    private Stage stage;

    public Hud()
    {
        viewport = new FitViewport(data.Map1_WIDTH, data.Map1_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport);
        Table table = new Table();
        table.setFillParent(true);
        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
    }
}
