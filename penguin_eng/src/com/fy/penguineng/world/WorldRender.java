package com.fy.penguineng.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.fy.penguineng.Assets;
import com.fy.penguineng.world.views.IcebergView;
import com.fy.penguineng.world.views.MicPowerView;
import com.fy.penguineng.world.views.WordCloudView;

public class WorldRender {
	float frustum_width;
	float frustum_height;

	private WordCloudView bobView;
	private IcebergView icebergView;
	private MicPowerView micPowerView;
	private GameStage world;

	public WorldRender(GameStage world) {
		bobView = new WordCloudView();
		icebergView = new IcebergView(world.iceberg.bounds);
		micPowerView = new MicPowerView();

		frustum_width = Assets.VIRTUAL_WIDTH;
		frustum_height = Assets.VIRTUAL_HEIGHT;
		this.world = world;
	}

	public void render(Batch batch) {
		renderBackground(batch);
		renderObjects(batch);
	}

	public void renderBackground(Batch batch) {
		batch.draw(world.assests.getTexture(Assets.BACKGROUND), 0, 0);
		batch.draw(world.assests.getTexture(Assets.FACTORY),
				(frustum_width - world.assests.getTexture(Assets.FACTORY)
						.getWidth()) / 2, 0,
				world.assests.getTexture(Assets.FACTORY).getWidth(),
				(int) (frustum_height * 0.2));
	}

	public void renderObjects(Batch batch) {
		micPowerView.setPosition(world.micPower.position);
		micPowerView.setVolume(world.micPower.volume);
		micPowerView.render(batch);

		icebergView.setPosition(world.iceberg.position);
		icebergView.render(batch);

		bobView.setBound(world.bob.bounds);
		bobView.setPostion(world.bob.position);
		bobView.setText(world.bob.getWord());
		bobView.render(batch);
	}
}
