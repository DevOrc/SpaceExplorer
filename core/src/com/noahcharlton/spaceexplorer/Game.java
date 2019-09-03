package com.noahcharlton.spaceexplorer;

import com.badlogic.gdx.ApplicationAdapter;
import com.noahcharlton.spaceexplorer.entity.Ship;
import com.noahcharlton.spaceexplorer.graphics.GameRenderer;

public class Game extends ApplicationAdapter {

	private GameRenderer gameRenderer;
	private Ship ship;
	
	@Override
	public void create () {
		gameRenderer = new GameRenderer(this);
		ship = new Ship();
	}

	@Override
	public void render () {
		gameRenderer.render();

		ship.update();
	}
	
	@Override
	public void dispose () {
		gameRenderer.dispose();
	}

	public Ship getShip() {
		return ship;
	}
}
