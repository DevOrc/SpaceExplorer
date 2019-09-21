package com.noahcharlton.spaceexplorer.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.noahcharlton.spaceexplorer.Game;
import com.noahcharlton.spaceexplorer.graphics.GameRenderer;

public class Ship extends Entity {

    private static final float SIZE = 128 / GameRenderer.PIXELS_PER_METER;
    private static final float IMPULSE_FORCE = 25f;

    private final Texture texture = new Texture(Gdx.files.internal("ship.png"));

    public Ship(Game game) {
        super(game);
    }

    @Override
    public void initPhysicsBody(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 0);
        bodyDef.fixedRotation = false;

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(SIZE / 2f, SIZE / 2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.5f;
        fixtureDef.friction = 0.5f;
        body.createFixture(fixtureDef);

        shape.dispose();
    }

    @Override
    public void update() {
        applyEngineForces();
        updateRotation();
    }

    private void applyEngineForces() {
        float x = (float)Math.sin(body.getAngle() - Math.PI);
        float y = (float)Math.cos(body.getAngle());

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            body.applyForceToCenter(x * IMPULSE_FORCE, y * IMPULSE_FORCE, true);
        } else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            body.applyForceToCenter(x * -IMPULSE_FORCE, y * -IMPULSE_FORCE, true);
        }
    }

    private void updateRotation() {
        float rotRad = body.getAngle();

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            rotRad += .1f;
        } else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            rotRad -= .1f;
        }

        body.setTransform(body.getPosition(), rotRad);
        body.setAngularVelocity(0);
    }

    @Override
    public void render(SpriteBatch batch) {
        renderTexture(batch, texture, SIZE, SIZE);
    }
}
