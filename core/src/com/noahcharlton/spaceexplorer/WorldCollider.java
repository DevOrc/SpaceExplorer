package com.noahcharlton.spaceexplorer;

import com.badlogic.gdx.physics.box2d.*;
import com.noahcharlton.spaceexplorer.entity.Asteroid;
import com.noahcharlton.spaceexplorer.entity.Ship;

import java.util.Objects;
import java.util.Optional;

public class WorldCollider implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        boolean shipContacted = doesContactHaveEntity(Ship.class, contact).isPresent();
        boolean asteroidContacted = doesContactHaveEntity(Asteroid.class, contact).isPresent();

        if(shipContacted && asteroidContacted){
            System.exit(0);
        }
    }

    private <T> Optional<T> doesContactHaveEntity(Class<T> type, Contact contact){
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        Objects.requireNonNull(bodyA.getUserData(), "Found Body without user data: " + bodyA);
        Objects.requireNonNull(bodyB.getUserData(), "Found Body without user data: " + bodyB);

        if(type.isAssignableFrom(bodyA.getUserData().getClass())){
            return Optional.of((T) bodyA);
        }else if(type.isAssignableFrom(bodyB.getUserData().getClass())){
            return Optional.of((T) bodyB);
        }

        return Optional.empty();
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
