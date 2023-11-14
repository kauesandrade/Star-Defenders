/**
 * Esta classe representa a tela de menu do jogo. Ela lida com a renderização do menu principal e a
 * detecção de entrada do jogador para iniciar o jogo.
 */
package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.MyGdxGame;

public class MenuScreen extends ScreenAdapter {
	private SpriteBatch batch;
	private FreeTypeFontGenerator generator;
	private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
	private BitmapFont bitmap;
	private Texture img;
	private Texture nave;

	@Override
	public void show() {
		batch = new SpriteBatch();
		img = new Texture("space.png");
		nave = new Texture("nave.png");
		generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

		parameter.size = 30;
		parameter.borderWidth = 1;
		parameter.borderColor = Color.BLACK;
		parameter.color = Color.WHITE;

		bitmap = generator.generateFont(parameter);
	}

	@Override
	public void render(float delta) {
		changeScreen();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		bitmap.draw(batch, "STAR DEFENDERS", Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2 + 100);
		batch.draw(nave, Gdx.graphics.getWidth() / 2 + 190, Gdx.graphics.getHeight() / 2 + 13);
		bitmap.draw(batch, "Aperte F para jogar", Gdx.graphics.getWidth() / 2 - 190,
				Gdx.graphics.getHeight() / 2 - 100);
		batch.end();
	}

	@Override
	public void hide() {
		img.dispose();
	}

	/**
	 * Verifica se a tecla 'F' foi pressionada e, se sim, transita para a tela de
	 * jogo.
	 */
	public void changeScreen() {
		if (Gdx.input.isKeyPressed(Input.Keys.F)) {
			MyGdxGame game = (MyGdxGame) Gdx.app.getApplicationListener();
			game.setScreen(new GameScreen());
		}
	}

}
