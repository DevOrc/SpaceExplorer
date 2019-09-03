package com.noahcharlton.spaceexplorer.graphics;

import com.noahcharlton.spaceexplorer.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameRenderer {

    private final Game game;
    private final OrthographicCamera camera;
    private final ScreenViewport viewport;
    private final SpriteBatch batch;

    public GameRenderer(Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        batch = new SpriteBatch();
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateCamera();

        batch.begin();
        game.getShip().render(batch);
        batch.end();
    }

    private void updateCamera() {
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void dispose() {
        batch.dispose();
    }
}
