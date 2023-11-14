/**
 * Esta classe representa a tela exibida quando o jogo termina. Ela exibe a pontuação do jogador, uma mensagem
 * de "Game Over" e instruções para reiniciar o jogo. O jogador pode reiniciar o jogo pressionando a tecla "F".
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

public class GameOverScreen extends ScreenAdapter {
	private SpriteBatch batch;
	private FreeTypeFontGenerator generator;
	private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
	private BitmapFont bitmap;
	private Texture img;
	private String pontos;

	/**
	 * Construtor da classe GameOverScreen, que recebe a pontuação do jogador.
	 *
	 * @param pontos A pontuação alcançada pelo jogador.
	 */
	public GameOverScreen(String pontos) {
		this.pontos = pontos;
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		img = new Texture("space.png");
		generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 30;
		parameter.borderWidth = 1;
		parameter.borderColor = Color.BLACK;
		parameter.color = Color.WHITE;
		bitmap = generator.generateFont(parameter);
	}

	/**
	 * Método de renderização da tela de Game Over. Exibe a pontuação, a mensagem
	 * "Game Over" e as instruções para reiniciar o jogo pressionando a tecla "F".
	 *
	 * @param delta O tempo passado desde o último quadro.
	 */
	@Override
	public void render(float delta) {
		changeScreen();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		bitmap.draw(batch, "Game Over", Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2 + 100);
		bitmap.draw(batch, "Voce fez: " + pontos + " pontos", Gdx.graphics.getWidth() / 2 - 250,
				Gdx.graphics.getHeight() / 2);
		bitmap.draw(batch, "Aperte F para jogar novamente", Gdx.graphics.getWidth() / 2 - 350,
				Gdx.graphics.getHeight() / 2 - 100);
		batch.end();
	}

	@Override
	public void hide() {
		img.dispose();
	}

	/**
	 * Método para verificar se o jogador pressionou a tecla "F" para reiniciar o
	 * jogo. Se a tecla for pressionada, a tela do jogo principal é exibida.
	 */
	public void changeScreen() {
		if (Gdx.input.isKeyPressed(Input.Keys.F)) {
			MyGdxGame game = (MyGdxGame) Gdx.app.getApplicationListener();
			game.setScreen(new GameScreen());
		}
	}

}
