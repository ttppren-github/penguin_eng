package com.fy.penguineng.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
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
	private Label outGas;
	private Image gasImg;

	public WorldRender(GameStage world) {
		bobView = new WordCloudView();
		icebergView = new IcebergView(world.iceberg.bounds);
		micPowerView = new MicPowerView();
		micPowerView.setPosition(world.micPower.position);

		gasImg = new Image(Assets.getInstance().getTexture(Assets.CLOUD));
		gasImg.setBounds(20, 740, 36, 36);
		world.addActor(gasImg);
		
		LabelStyle tfStyle = new LabelStyle();
		tfStyle.font = Assets.getInstance().getFont();
		tfStyle.fontColor = Color.RED;
		outGas = new Label("0", tfStyle);
		outGas.setBounds(60, 730, 100, 60);
		world.addActor(outGas);

		frustum_width = Assets.VIRTUAL_WIDTH;
		frustum_height = Assets.VIRTUAL_HEIGHT;
		this.world = world;
	}

	public void render(Batch batch) {
		renderBackground(batch);
		renderObjects(batch);
	}

	public void renderBackground(Batch batch) {
		float w = world.assests.getTexture(Assets.FACTORY).getWidth() * 3 / 4;
		float x = (frustum_width - w) / 2;
		batch.draw(world.assests.getTexture(Assets.BACKGROUND), 0, 0);
		batch.draw(world.assests.getTexture(Assets.FACTORY), x, 0, w,
				(int) (frustum_height * 0.2));
	}

	public void renderObjects(Batch batch) {
		micPowerView.setVolume(world.micPower.volume);
		micPowerView.render(batch);

		icebergView.setPosition(world.iceberg.position);
		icebergView.setHeight(String.valueOf(world.iceberg.mHeight));
		icebergView.render(batch);

		bobView.setBound(world.bob.bounds);
		bobView.setPostion(world.bob.position);
		bobView.setText(world.bob.getWord());
		bobView.render(batch);

		outGas.setText(String.valueOf(world.outValue));
	}
}
