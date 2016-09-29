package com.theironyard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	float x, y, xv, yv;

	static final float MAX_VELOCITY = 100;
	static final float FRICTION = 0.9f;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		move();

		System.out.println(xv + " " + yv);

		Gdx.gl.glClearColor(255, 0, 128, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, x, y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	public void move() {
		if (Gdx.input.isKeyPressed(Input.Keys.W)){
			yv = MAX_VELOCITY;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)){
			yv = MAX_VELOCITY * -1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			xv = MAX_VELOCITY;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			xv = MAX_VELOCITY * -1;
		}

		x += xv * Gdx.graphics.getDeltaTime();
		y += yv * Gdx.graphics.getDeltaTime();

		xv = decelerate(xv);
		yv = decelerate(yv);
	}

	public float decelerate(float velocity) {
		velocity *= FRICTION;
		if (Math.abs(velocity) <1) {
			velocity = 0;
		}
		return velocity;
	}

}
