package com.fy.penguineng;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Assets {
	private final static String TAG = Assets.class.getSimpleName();

	public final static String PATH = "data/";
	public final static String FONT = PATH + "font.fnt";
	public final static String BACKGROUND = PATH + "background.png";
	public final static String CLOUD = PATH + "cloud.png";
	public final static String FACTORY = PATH + "factory.png";
	public final static String ICEBERG = PATH + "iceberg.png";
	public final static String MIC = PATH + "mic.png";
	public final static String PAUSE = PATH + "pause.png";
	public final static String SUN = PATH + "sun.9.png";
	public final static String MainMenu_Bg = PATH + "mainmenu.png";
	public final static String SwitchScreen_Bg = PATH + "switch_bg.png";
	public final static String Ruler = PATH + "ruler.png";
	public final static String Btn = "btn";
	public final static String BtnSmall = "btnSmall";
	public final static String BtnReturn = "ImageButton";
	public final static String BtnStar = PATH + "normal_star.png";
	public final static String BtnStar1 = PATH + "star1.png";
	public final static String BtnStar2 = PATH + "star2.png";
	public final static String BtnStar3 = PATH + "star3.png";
	public final static String LockStar = PATH + "lock_star.png";
	public final static String BgSucc = PATH + "success.png";
	public final static String BgFail = PATH + "fail.png";
	public final static String Blackboard = PATH + "blackboard.png";
	public final static String Letter = PATH + "certificate.png";
	public final static String Medal = PATH + "medal.png";
	public final static String PopUp = PATH + "pop.png";
	public final static String ListView = "List";

	public static final int CLOUD_WIDTH = 100;
	public static final int CLOUD_HEIGHT = 50;
	public static final int LETTER_HEIGHT = 18;
	public static final int LETTER_WIDTH = 20;
	public static final int TEMP_WIDTH = 20;
	public static final int TEMP_HEIGHT = 224;
	public static float VIRTUAL_WIDTH = 480;
	public static float VIRTUAL_HEIGHT = 800;

	public Texture name, logo;
	public Skin skin;

	private AssetManager manager;

	private static Assets instance;
	private final String btnNormal = PATH + "btn_normal.png";
	private final String btnPressed = PATH + "btn_pressed.png";
	private final String btnNormalSmall = PATH + "btn_normal_small.png";
	private final String btnPressedSmall = PATH + "btn_pressed_small.png";

	public Assets() {
		manager = new AssetManager();
	}

	/**
	 * preLoading
	 * <p>
	 * It should be called first for loading resources<br>
	 * for loading UI.
	 * 
	 * @param null
	 * @return void
	 */
	public void preLoading() {
		logo = new Texture(Gdx.files.internal("data/logo.png"));
		name = new Texture(Gdx.files.internal("data/name.png"));
	}

	public void unLoadPreLoading() {
		logo.dispose();
		name.dispose();
	}

	/**
	 * Loading other all resource. Add them to loading queue, and should be
	 * really load by calling getProgress().
	 */
	public void loadResources() {
		manager.load(FONT, BitmapFont.class);
		manager.load(BACKGROUND, Texture.class);
		manager.load(CLOUD, Texture.class);
		manager.load(FACTORY, Texture.class);
		manager.load(ICEBERG, Texture.class);
		manager.load(MIC, Texture.class);
		manager.load(PAUSE, Texture.class);
		manager.load(SUN, Texture.class);
		manager.load(MainMenu_Bg, Texture.class);
		manager.load(SwitchScreen_Bg, Texture.class);
		manager.load(btnNormal, Texture.class);
		manager.load(btnPressed, Texture.class);
		manager.load(btnNormalSmall, Texture.class);
		manager.load(btnPressedSmall, Texture.class);
		manager.load(BtnStar, Texture.class);
		manager.load(BtnStar1, Texture.class);
		manager.load(BtnStar2, Texture.class);
		manager.load(BtnStar3, Texture.class);
		manager.load(LockStar, Texture.class);
		manager.load(Ruler, Texture.class);
		manager.load(BgSucc, Texture.class);
		manager.load(BgFail, Texture.class);
		manager.load(Blackboard, Texture.class);
		manager.load(Letter, Texture.class);
		manager.load(Medal, Texture.class);
		manager.load(PopUp, Texture.class);

		// manager.finishLoading();
		Gdx.app.log(TAG, "loadResources()");
	}

	/**
	 * Unload all resources.
	 */
	public void unLoad() {
		manager.dispose();
		Gdx.app.log(TAG, "unLoad()");
	}

	/**
	 * Get Texture resource from AssetsManager
	 * 
	 * @param name
	 *            Resource file name;
	 * @return Resource
	 */
	public Texture getTexture(String name) {
		if (manager.isLoaded(name)) {
			return manager.get(name, Texture.class);
		} else {
			throw new NullPointerException();
		}
	}

	/**
	 * Get Font resource from AssetsManager
	 * 
	 * @param name
	 *            Resource file name;
	 * @return Resource
	 */
	public BitmapFont getFont() {
		if (manager != null) {
			return manager.get(FONT);
		} else {
			throw new NullPointerException();
		}
	}

	/**
	 * Get instance of Assets
	 * 
	 * @return instance
	 */
	public static Assets getInstance() {
		if (null == instance) {
			instance = new Assets();
		}

		return instance;
	}

	/**
	 * update AssetsManager resource
	 * 
	 * @param
	 * @return boolean if true resource load complete
	 */
	public boolean update() {
		if (manager != null) {
			return manager.update();
		}

		return false;
	}

	/**
	 * get AssetsManager load progress
	 * 
	 * @see AssetsManager getProgress
	 */
	public float getProgress() {
		if (manager != null) {
			return manager.getProgress();
		}

		return 0;
	}

	public void loadStyle() {
		ButtonStyle btnStyle = new ButtonStyle();
		btnStyle.over = new TextureRegionDrawable(new TextureRegion(
				manager.get(btnNormal, Texture.class)));
		btnStyle.up = new TextureRegionDrawable(new TextureRegion(manager.get(
				btnPressed, Texture.class)));

		ButtonStyle btnStyleSmall = new ButtonStyle();
		btnStyleSmall.over = new TextureRegionDrawable(new TextureRegion(
				manager.get(btnNormalSmall, Texture.class)));
		btnStyleSmall.up = new TextureRegionDrawable(new TextureRegion(manager.get(
				btnPressedSmall, Texture.class)));

		LabelStyle labelStyle = new LabelStyle((BitmapFont) manager.get(FONT),
				Color.BLACK);

		ImageButtonStyle imgBtnStyle = new ImageButtonStyle();
		imgBtnStyle.up = new TextureRegionDrawable(new TextureRegion(
				manager.get(PAUSE, Texture.class)));

		ImageButtonStyle star = new ImageButtonStyle();
		star.up = new TextureRegionDrawable(new TextureRegion(manager.get(
				BtnStar, Texture.class)));
		ImageButtonStyle star1 = new ImageButtonStyle();
		star1.up = new TextureRegionDrawable(new TextureRegion(manager.get(
				BtnStar1, Texture.class)));
		ImageButtonStyle star2 = new ImageButtonStyle();
		star2.up = new TextureRegionDrawable(new TextureRegion(manager.get(
				BtnStar2, Texture.class)));
		ImageButtonStyle star3 = new ImageButtonStyle();
		star3.up = new TextureRegionDrawable(new TextureRegion(manager.get(
				BtnStar3, Texture.class)));

		ImageButtonStyle lockStar = new ImageButtonStyle();
		lockStar.up = new TextureRegionDrawable(new TextureRegion(manager.get(
				LockStar, Texture.class)));

		ListStyle listStyle = new ListStyle();
		listStyle.fontColorSelected = Color.RED;
		listStyle.font = manager.get(FONT);
		listStyle.fontColorSelected = Color.WHITE;
		listStyle.fontColorUnselected = Color.BLACK;
		// listStyle.selection = new TextureRegionDrawable(new TextureRegion(
		// manager.get(btnNormal, Texture.class)));
		listStyle.selection = new SpriteDrawable(new Sprite(new Texture(
				new Pixmap(20, 20, Format.RGBA8888))));

		skin = new Skin();
		skin.add(FONT, labelStyle);
		skin.add(Btn, btnStyle);
		skin.add(BtnSmall, btnStyleSmall);
		skin.add(BtnReturn, imgBtnStyle);
		skin.add(BtnStar, star);
		skin.add(BtnStar1, star1);
		skin.add(BtnStar2, star2);
		skin.add(BtnStar3, star3);
		skin.add(LockStar, lockStar);
		skin.add(ListView, listStyle);
	}
}
