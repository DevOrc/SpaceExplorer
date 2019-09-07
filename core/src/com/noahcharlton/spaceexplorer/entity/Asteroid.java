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

import java.util.Random;

public class Asteroid extends Entity {

    private static final Random random = new Random();
    private final Texture texture = new Texture(Gdx.files.internal("asteroid.png"));
    private static final float SIZE = 80 / GameRenderer.PIXELS_PER_METER;

    public Asteroid(Game game) {
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
        shape.setAsBox(SIZE / 2f, SIZE / 2f);

        body.createFixture(shape, 8f);

        shape.dispose();

        randomizePosition();
        randomVelocities();

        return body;
    }

    private void randomVelocities() {
        float angularVelocity = random.nextFloat() / random.nextInt(5);
        float xVelocity = random.nextFloat() - .5f;
        float yVelocity = random.nextFloat() - .5f;

        body.setAngularVelocity(angularVelocity);
        body.setLinearVelocity(xVelocity, yVelocity);
    }

    private void randomizePosition() {
        int xPos = random.nextInt(50) - 25;
        int yPos = random.nextInt(50) - 25;
        float angle = (float) (random.nextInt(180) * Math.PI / 180);

        body.setTransform(xPos, yPos, angle);
    }

    @Override
    public void render(SpriteBatch batch) {
        if(Float.isNaN(body.getPosition().x) || Float.isNaN(body.getPosition().y) )
            return;

        renderTexture(batch, texture, SIZE, SIZE);
    }
}
