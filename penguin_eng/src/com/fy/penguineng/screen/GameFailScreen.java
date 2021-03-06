/**
 * 
 */
package com.fy.penguineng.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fy.penguineng.Assets;
import com.fy.penguineng.FreetypeFontWrap;
import com.fy.penguineng.PenguinEng;
import com.fy.penguineng.TtsCtrl;
import com.fy.penguineng.icontrol.ITtsCtrl;
import com.fy.penguineng.world.WordPool;

/**
 * @author liufy
 * 
 */
public class GameFailScreen extends BaseScreen {
	// private final String TAG = GameFailScreen.class.getSimpleName();
	private final String RETURN = "选择关卡";
	private final String RESTART = "重玩";
	private PenguinEng gameMain;
	private Button btnBack, btnRestart;
	private List<String> list;
	private TextArea tx;
	private Image blackboardImg;
	private String selectedWord;
	private ITtsCtrl speaker;

	/**
	 * 
	 */
	public GameFailScreen(PenguinEng game) {
		this.gameMain = game;
		selectedWord = "";

		FreetypeFontWrap font = new FreetypeFontWrap();
		LabelStyle labelStyle = new LabelStyle(font.getFont(RETURN + RESTART),
				Color.BLACK);

		btnBack = new Button(Assets.getInstance().skin, Assets.Btn);
		btnBack.add(new Label(RETURN, labelStyle));
		btnBack.addListener(clickListener);

		btnRestart = new Button(Assets.getInstance().skin, Assets.Btn);
		btnRestart.add(new Label(RESTART, labelStyle));
		btnRestart.addListener(clickListener);

		list = new List<String>(Assets.getInstance().skin, Assets.ListView);
		final ScrollPane scroller = new ScrollPane(list);
		list.addListener(clickListener);

		TextFieldStyle tfStyle = new TextFieldStyle();
		tfStyle.font = Assets.getInstance().getFont();
		tfStyle.fontColor = Color.RED;
		tx = new TextArea("", tfStyle);
		tx.setBounds(40, 620, 400, 120);
		blackboardImg = new Image(assets.getTexture(Assets.Blackboard));
		blackboardImg.setBounds(10, 640, 460, 140);
		baseStage.addActor(blackboardImg);
		baseStage.addActor(tx);

		final Table tableRoot = new Table();
		tableRoot.setFillParent(true);
		tableRoot.row().spaceTop(20);
		tableRoot.add(scroller);
		tableRoot.row().spaceTop(20);
		tableRoot.add(btnBack);
		tableRoot.row();
		tableRoot.add(btnRestart);
		tableRoot.pad(160, 20, 40, 20);
		baseStage.addActor(tableRoot);

		speaker = new TtsCtrl();
	}

	@Override
	public void render(float delta) {
		String str = (String) list.getSelected();
		if (!str.matches(selectedWord)) {
			TextFieldStyle style = new TextFieldStyle();
			FreetypeFontWrap font = new FreetypeFontWrap();

			selectedWord = str;
			String text = WordPool.getInstance().getText(selectedWord);
			tx.clear();

			style.font = font.getFont(text, 24);
			style.fontColor = Color.WHITE;
			tx.setStyle(style);
			tx.setText(text);
		}

		super.render(delta);
	}

	@Override
	public void show() {
//		WordPool.getInstance().loadJson("dic/1stage_dic.json");
		list.setItems(WordPool.getInstance().getFailWords());
		this.setBackground(assets.getTexture(Assets.BgFail));

		super.show();
	}

	private ClickListener clickListener = new ClickListener() {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (event.getListenerActor() == btnBack) {
				gameMain.setScreen(gameMain.swichScreen);
			} else if (event.getListenerActor() == btnRestart) {
				WordPool.getInstance().reload();
				gameMain.setScreen(gameMain.gameScreen);
			} else if (event.getListenerActor() == list) {
				if (speaker != null) {
					String path = "sounds/" + WordPool.getInstance().getStage()
							+ "/" + selectedWord.replace(" ", "") + ".ogg";
					speaker.unload();
					speaker.load(path);
					speaker.speakOut();
				}
			}
		}
	};
}
