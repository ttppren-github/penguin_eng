package com.fy.penguineng;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.fy.penguineng.views.BaseView;
import com.fy.penguineng.views.BobView;
import com.fy.penguineng.views.IcebergView;
import com.fy.penguineng.views.MicPowerView;
import com.fy.penguineng.views.PlayButtonView;
import com.fy.penguineng.views.TemperaterView;

public class WorldRender extends BaseView {
	float frustum_width;
	float frustum_height;

	BobView bobView;
	private IcebergView icebergView;
	private TemperaterView temperaterView;
	private MicPowerView micPowerView;
	private PlayButtonView playButtonView;

	public WorldRender(SpriteBatch batch, WorldControl world) {
		super(batch, world);

		bobView = new BobView(batch, world);
		icebergView = new IcebergView(batch, world);
		temperaterView = new TemperaterView(batch, world);
		micPowerView = new MicPowerView(batch, world);
		playButtonView = new PlayButtonView(batch, world);

		frustum_width = Gdx.graphics.getWidth();
		frustum_height = Gdx.graphics.getHeight();
	}

	public void render() {
		renderBackground();
		renderObjects();
	}

	public void renderBackground() {
		batch.enableBlending();
		batch.begin();
		batch.draw(Assets.backgroundRegion, 0, 0, frustum_width, frustum_height);

		batch.draw(Assets.factory, 0, 0);
		batch.end();
	}

	public void renderObjects() {
		batch.enableBlending();
		batch.begin();

		icebergView.render();
		temperaterView.render();
		bobView.render();
		micPowerView.render();
		playButtonView.render();
		/*
		 * renderPlatforms(); renderItems(); renderSquirrels(); renderCastle();
		 */
		batch.end();
	}

	/*
	 * private void renderPlatforms () { int len = world.platforms.size(); for
	 * (int i = 0; i < len; i++) { Platform platform = world.platforms.get(i);
	 * TextureRegion keyFrame = Assets.platform; if (platform.state ==
	 * Platform.PLATFORM_STATE_PULVERIZING) { keyFrame =
	 * Assets.brakingPlatform.getKeyFrame(platform.stateTime,
	 * Animation.ANIMATION_NONLOOPING); }
	 * 
	 * batch.draw(keyFrame, platform.position.x - 1, platform.position.y -
	 * 0.25f, 2, 0.5f); } }
	 * 
	 * private void renderItems () { int len = world.springs.size(); for (int i
	 * = 0; i < len; i++) { Spring spring = world.springs.get(i);
	 * batch.draw(Assets.spring, spring.position.x - 0.5f, spring.position.y -
	 * 0.5f, 1, 1); }
	 * 
	 * len = world.coins.size(); for (int i = 0; i < len; i++) { Coin coin =
	 * world.coins.get(i); TextureRegion keyFrame =
	 * Assets.coinAnim.getKeyFrame(coin.stateTime, Animation.ANIMATION_LOOPING);
	 * batch.draw(keyFrame, coin.position.x - 0.5f, coin.position.y - 0.5f, 1,
	 * 1); } }
	 * 
	 * private void renderSquirrels () { int len = world.squirrels.size(); for
	 * (int i = 0; i < len; i++) { Squirrel squirrel = world.squirrels.get(i);
	 * TextureRegion keyFrame =
	 * Assets.squirrelFly.getKeyFrame(squirrel.stateTime,
	 * Animation.ANIMATION_LOOPING); float side = squirrel.velocity.x < 0 ? -1 :
	 * 1; if (side < 0) batch.draw(keyFrame, squirrel.position.x + 0.5f,
	 * squirrel.position.y - 0.5f, side * 1, 1); else batch.draw(keyFrame,
	 * squirrel.position.x - 0.5f, squirrel.position.y - 0.5f, side * 1, 1); } }
	 * 
	 * private void renderCastle () { Castle castle = world.castle;
	 * batch.draw(Assets.castle, castle.position.x - 1, castle.position.y - 1,
	 * 2, 2); }
	 */
}
