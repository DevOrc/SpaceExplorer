package com.noahcharlton.spaceexplorer.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.noahcharlton.spaceexplorer.Game;
import com.noahcharlton.spaceexplorer.graphics.GameRenderer;

public abstract class Entity {

    protected Body body;

    public Entity(Game game) {
        game.registerEntity(this);

        initPhysicsBody(game.getWorld());
        this.body.setUserData(this);
    }

    public void render(SpriteBatch batch){}

    public void update(){}

    protected abstract void initPhysicsBody(World world);

    public void renderTexture(SpriteBatch batch, Texture texture, float w, float h) {
        int x = (int) (body.getPosition().x * GameRenderer.PIXELS_PER_METER);
        int y = (int) (body.getPosition().y * GameRenderer.PIXELS_PER_METER);
        int rotation = (int) (body.getAngle() * 180 / Math.PI);

        int width = (int) (w * GameRenderer.PIXELS_PER_METER);
        int height = (int) (h * GameRenderer.PIXELS_PER_METER);

        x -= width / 2;
        y -= height / 2;

        batch.draw(texture, x, y, width / 2f, height / 2f, width, height, 1, 1,
                rotation, 0, 0, width, height, false, false);
    }

    public Body getBody() {
        return body;
    }
}
