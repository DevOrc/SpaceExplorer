package com.noahcharlton.spaceexplorer.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.noahcharlton.spaceexplorer.Game;
import com.noahcharlton.spaceexplorer.graphics.GameRenderer;

public class Torpedo extends Entity {

    private static final float SIZE = 25 / GameRenderer.PIXELS_PER_METER;
    private static final float FORCE = 25;
    private static final Texture texture = new Texture(Gdx.files.internal("torpedo.png"));

    public Torpedo(Game game) {
        super(game);

        initPosition(game.getShip());
        initForces();
    }

    private void initPosition(Ship ship) {
        float angle = ship.getBody().getAngle();
        Vector2 pos = ship.getBody().getPosition();

        float newX = pos.x - (float) (Math.sin(angle) * SIZE * 2);
        float newY = pos.y + (float) (Math.cos(angle) * SIZE * 2);

        System.out.printf("(%f, %f)\n", newX, newY);

        body.setTransform(newX, newY, ship.getBody().getAngle());
    }

    @Override
    protected void initPhysicsBody(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0, 0);
        bodyDef.fixedRotation = false;

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(SIZE / 2f, SIZE / 2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 125f;
        fixtureDef.friction = 0.5f;
        body.createFixture(fixtureDef);
    }

    private void initForces() {
        float x = (float)Math.sin(body.getAngle() - Math.PI);
        float y = (float)Math.cos(body.getAngle());

        body.setLinearVelocity(x * FORCE, y * FORCE);
    }

    @Override
    public void render(SpriteBatch batch) {
        renderTexture(batch, texture, SIZE, SIZE);
    }
}
