package com.fy.penguineng.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.fy.penguineng.Assets;
import com.fy.penguineng.FreetypeFontWrap;
import com.fy.penguineng.world.views.IcebergView;
import com.fy.penguineng.world.views.MicPowerView;
import com.fy.penguineng.world.views.WordCloudView;

public class WorldRender {
	private final String TT = "废气量";
	private final String HH = "雪山高度";
	private final String STAGE = "第关";
	private final int FontSize = 24;

	private float frustum_width;
	private float frustum_height;
	private WordCloudView bobView;
	private IcebergView icebergView;
	private MicPowerView micPowerView;
	private GameStage world;
	private Label outGas, icebergHeight, stageNo;
	private FreetypeFontWrap ft;

	public WorldRender(GameStage world) {
		frustum_width = Assets.VIRTUAL_WIDTH;
		frustum_height = Assets.VIRTUAL_HEIGHT;

		this.world = world;

		bobView = new WordCloudView();
		icebergView = new IcebergView(world.iceberg.bounds);
		micPowerView = new MicPowerView();
		micPowerView.setPosition(world.micPower.position);

		ft = new FreetypeFontWrap();
		LabelStyle tfStyle = new LabelStyle();
		tfStyle.font = ft.getFont(TT + HH + STAGE + "0123456789", FontSize);
		tfStyle.fontColor = Color.BLACK;
		outGas = new Label(TT + "0", tfStyle);
		outGas.setBounds(20, 700, 100, 60);
		world.addActor(outGas);

		icebergHeight = new Label("8848", tfStyle);
		icebergHeight.setBounds(20, 660, 100, 60);
		world.addActor(icebergHeight);

		stageNo = new Label("", tfStyle);
		world.addActor(stageNo);
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
		String s = String.format("第%s关", WordPool.getInstance().getStage());
		stageNo.setBounds((frustum_width - s.length() * FontSize) / 2, 730,
				100, 60);
		stageNo.setText(s);

		micPowerView.setVolume(world.micPower.volume);
		micPowerView.render(batch);

		icebergView.setPosition(world.iceberg.position);
		icebergView.render(batch);

		bobView.setBound(world.bob.bounds);
		bobView.setPostion(world.bob.position);
		bobView.setText(world.bob.getWord());
		bobView.render(batch);

		outGas.setText(TT + " " + String.valueOf(world.outValue));
		icebergHeight.setText(HH + " " + String.valueOf(world.iceberg.mHeight));
	}

	public void dispose() {
		ft.dispose();
	}
}
