package com.noahcharlton.spaceexplorer.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.noahcharlton.spaceexplorer.Game;

public abstract class Entity {

    protected Body body;

    public Entity(Game game) {
        game.registerEntity(this);

        this.body = initPhysicsBody(game.getWorld());
    }

    public void render(SpriteBatch batch){}

    public void update(){}

    protected abstract Body initPhysicsBody(World world);
}
