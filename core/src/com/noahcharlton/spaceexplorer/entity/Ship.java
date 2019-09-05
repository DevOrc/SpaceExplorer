package com.noahcharlton.spaceexplorer.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.noahcharlton.spaceexplorer.Game;
import com.noahcharlton.spaceexplorer.graphics.GameRenderer;

public class Ship extends Entity {

    private static final float IMPULSE_FORCE = 4000f;
    private final Texture texture = new Texture(Gdx.files.internal("ship.png"));

    public Ship(Game game) {
        super(game);
    }

    @Override
    public Body initPhysicsBody(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 0);
        bodyDef.fixedRotation = false;

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(64, 64);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = .1f;
        fixtureDef.friction = 0.5f;
        body.createFixture(fixtureDef);

        shape.dispose();

        return body;
    }

    @Override
    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            body.applyForceToCenter(0f, IMPULSE_FORCE, true);
        } else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            body.applyForceToCenter(0f, -IMPULSE_FORCE, true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            body.applyForceToCenter(-IMPULSE_FORCE, 0f, true);
        } else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            body.applyForceToCenter(IMPULSE_FORCE, 0f, true);
        }

        body.setAngularVelocity(.5f);
    }

    @Override
    public void render(SpriteBatch batch) {
        int x = (int) (body.getPosition().x * GameRenderer.PIXELS_PER_METER);
        int y = (int) (body.getPosition().y * GameRenderer.PIXELS_PER_METER);
        int rotation = (int) (body.getAngle() * 180 / Math.PI);

        batch.draw(texture, x, y, 64, 64, 128, 128, 1, 1,
                rotation, 0, 0, 128, 128, false, false);
    }
}
