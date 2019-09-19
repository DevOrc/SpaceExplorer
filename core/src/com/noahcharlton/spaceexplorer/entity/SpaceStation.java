package com.noahcharlton.spaceexplorer.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.noahcharlton.spaceexplorer.Game;
import com.noahcharlton.spaceexplorer.graphics.GameRenderer;

public class SpaceStation extends Entity{

    private static final float SIZE = 512 / GameRenderer.PIXELS_PER_METER;

    private final Texture texture = new Texture(Gdx.files.internal("space_station.png"));

    public SpaceStation(Game game) {
        super(game);
    }

    @Override
    public Body initPhysicsBody(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 10);
        bodyDef.fixedRotation = false;

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(SIZE / 2f, SIZE / 2f);

        body.createFixture(shape, 8f);

        shape.dispose();

        return body;
    }

    @Override
    public void render(SpriteBatch batch) {
        renderTexture(batch, texture, SIZE, SIZE);
    }
}
