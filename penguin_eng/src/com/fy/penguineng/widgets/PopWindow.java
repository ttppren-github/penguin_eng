package com.fy.penguineng.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.fy.penguineng.Assets;
import com.fy.penguineng.FreetypeFontWrap;

public class PopWindow extends Window {
	public static final int F_OK = 1;
	public static final int F_CANCEL = 2;

	private final String OK = "确定";
	private final String CANCEL = "取消";
	private Button btnOk, btnCancel;
	private int flag;
	private String message;

	public static PopWindow create(Stage parent, int flag) {
		PopWindow instance;
		FreetypeFontWrap font = new FreetypeFontWrap();
		WindowStyle style = new WindowStyle(font.getFont(""), Color.BLACK, null);
		instance = new PopWindow("", style);
		instance.flag = flag;
		instance.setParent(parent);

		return instance;
	}

	public Button getBtnOk() {
		return btnOk;
	}

	public Button getBtnCancel() {
		return btnCancel;
	}

	private PopWindow(String title, WindowStyle style) {
		super(title, style);

		Assets assets = Assets.getInstance();
		setWidth(400);
		setHeight(240);
		setPosition(40, 200);
		setModal(true);
		TextureRegion bg = new TextureRegion(assets.getTexture(Assets.PopUp));
		setBackground(new TextureRegionDrawable(bg));

		btnOk = new Button(assets.skin, Assets.BtnSmall);
		btnCancel = new Button(assets.skin, Assets.BtnSmall);
	}

	public void setOnOKListener(ClickListener listener) {
		btnOk.addListener(listener);
	}

	public void setOnCancelListener(ClickListener listener) {
		btnCancel.addListener(listener);
	}

	public void setMessage(String msg) {
		this.message = msg;

		if (flag < F_OK) {
			return;
		}

		FreetypeFontWrap font = new FreetypeFontWrap();
		LabelStyle labelStyle = new LabelStyle(font.getFont(OK + CANCEL
				+ this.message), Color.BLACK);

		Label msgLab = new Label(this.message, labelStyle);
		this.add(msgLab).padTop(70).align(Align.center);
		this.row();

		Button tmp = getBtnOk();
		if (F_OK == (flag & F_OK)) {
			Label lab = new Label(OK, labelStyle);
			btnOk.add(lab);
			tmp = getBtnOk();
		}
		if (F_CANCEL == (flag & F_CANCEL)) {
			Label lab2 = new Label(CANCEL, labelStyle);
			btnCancel.add(lab2);
			tmp = btnCancel;
		}

		if ((F_CANCEL | F_OK) == flag) {
			showTwo(getBtnOk(), btnCancel);
		} else {
			showOne(tmp);
		}
	}

	private void setParent(Stage baseStage) {
		baseStage.addActor(this);
		setVisible(false);
	}

	public void showPopup() {
		if (!isVisible()) {
			this.setVisible(true);
		}
	}

	public void closePopup() {
		if (isVisible()) {
			this.setVisible(false);
		}
	}

	private void showOne(Button btn) {
		this.add(btn).align(Align.center).spaceTop(20);
	}

	private void showTwo(Button btnL, Button btnR) {
		btnL.setPosition(50, 20);
		this.addActor(btnL);
		btnR.setPosition(230, 20);
		this.addActor(btnR);
	}

}
