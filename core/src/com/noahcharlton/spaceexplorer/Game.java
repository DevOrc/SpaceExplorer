package com.noahcharlton.spaceexplorer;

import com.badlogic.gdx.ApplicationAdapter;
import com.noahcharlton.spaceexplorer.graphics.GameRenderer;

public class Game extends ApplicationAdapter {

	private GameRenderer gameRenderer;
	
	@Override
	public void create () {
		gameRenderer = new GameRenderer();
	}

	@Override
	public void render () {
		gameRenderer.render();
	}
	
	@Override
	public void dispose () {
		gameRenderer.dispose();
	}
}
