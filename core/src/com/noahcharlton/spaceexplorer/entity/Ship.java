package com.noahcharlton.spaceexplorer.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ship extends Entity{

    private final Texture texture = new Texture(Gdx.files.internal("ship.png"));

    @Override
    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            y += 1;
        }else if(Gdx.input.isKeyPressed(Input.Keys.S)){
            y -= 1;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            x -= 1;
        }else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            x += 1;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }
}
