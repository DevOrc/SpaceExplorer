package com.noahcharlton.spaceexplorer;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.noahcharlton.spaceexplorer.entity.Asteroid;
import com.noahcharlton.spaceexplorer.entity.Entity;
import com.noahcharlton.spaceexplorer.entity.Ship;
import com.noahcharlton.spaceexplorer.entity.SpaceStation;

public class Game {

    private final World world;
    private final Array<Entity> entities = new Array<>();

    private final Ship ship;
    private final SpaceStation spaceStation;

    public Game() {
        world =  new World(new Vector2(0f, 0f), true);
        world.setContactListener(new WorldCollider());

        for(int i = 0; i < 15; i++){
            new Asteroid(this);
        }

        ship = new Ship(this);
        spaceStation = new SpaceStation(this);
    }

    public void registerEntity(Entity entity){
        entities.add(entity);
    }

    public void update(){
        world.step(1/60f, 6, 2);
        entities.forEach(Entity::update);
    }

    public Array<Entity> getEntities() {
        return entities;
    }

    public Ship getShip() {
        return ship;
    }

    public World getWorld() {
        return world;
    }
}
