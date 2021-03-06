package utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud {

    Viewport viewport;
    private Stage stage;
    private Label levelLabel;
    private Label levelNumberLabel;

    public Hud(OrthographicCamera camera)
    {
        viewport = new FillViewport(300, 300, camera);
        stage = new Stage(viewport);
        Table table = new Table();
        table.setFillParent(true);
        table.debug();
        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        levelLabel = new Label("LEVEL", labelStyle);
        levelNumberLabel = new Label("1", labelStyle);
        table.top();
        table.add(levelLabel);
        table.add(levelNumberLabel);

        stage.addActor(table);
    }

    public Stage getStage() {
        return stage;
    }

    public Viewport getViewport() {
        return viewport;
    }
}
