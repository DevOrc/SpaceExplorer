package com.noahcharlton.spaceexplorer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.noahcharlton.spaceexplorer.graphics.GameRenderer;

public class SpaceExplorer extends ApplicationAdapter {

	private Game game;
	private GameRenderer gameRenderer;

	@Override
	public void create () {
		Box2D.init();

		game = new Game();
		gameRenderer = new GameRenderer(game);
	}

	@Override
	public void render () {
		gameRenderer.render();

		game.update();
	}
	
	@Override
	public void dispose () {
		gameRenderer.dispose();
	}
}
