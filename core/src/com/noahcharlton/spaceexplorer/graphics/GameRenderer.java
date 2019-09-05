package com.noahcharlton.spaceexplorer.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.noahcharlton.spaceexplorer.Game;

public class GameRenderer {

    public static final float PIXELS_PER_METER = 64f;

    private final Game game;
    private final OrthographicCamera camera;
    private final ScreenViewport viewport;
    private final SpriteBatch batch;
    private final Box2DDebugRenderer physicsRenderer = new Box2DDebugRenderer();

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
        game.getEntities().forEach(entity -> entity.render(batch));
        batch.end();
    }

    private void updateCamera() {
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        batch.setProjectionMatrix(camera.combined);
    }

    public void dispose() {
        batch.dispose();
    }
}
