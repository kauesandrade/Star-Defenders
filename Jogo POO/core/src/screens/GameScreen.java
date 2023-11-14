/**
 * Esta classe representa a tela principal do jogo, onde a jogabilidade ocorre. Ela lida com a renderização
 * dos objetos, colisões, pontuação e controle das ações do jogador.
 */
package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.MyGdxGame;

import objetos.Alien;
import objetos.Meteoro;
import objetos.Nave;
import powerUps.PowerUp;
import utilitarios.Colisao;
import utilitarios.Spawn;

public class GameScreen extends ScreenAdapter {
	SpriteBatch batch;
	Texture img;
	Nave nave;
	Meteoro meteoro;
	Alien alien;
	Colisao colisao;
	PowerUp powerUp;
	Spawn spawnPowerUp;

	float ultimoNanoTime;
	int pontos;
	int time;

	private FreeTypeFontGenerator gerador;
	private FreeTypeFontGenerator.FreeTypeFontParameter parametro;
	private BitmapFont bitMap;

	/**
	 * Inicializa a tela do jogo, criando objetos e configurando elementos visuais,
	 * como a fonte de pontuação.
	 */
	@Override
	public void show() {
		gerador = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
		parametro = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parametro.size = 30;
		parametro.borderWidth = 1;
		parametro.borderColor = Color.BLACK;
		parametro.color = Color.WHITE;
		bitMap = gerador.generateFont(parametro);

		batch = new SpriteBatch();
		img = new Texture("space.png");
		nave = new Nave();
		colisao = new Colisao(nave);
		meteoro = new Meteoro(colisao);
		alien = new Alien(colisao);
		spawnPowerUp = new Spawn(colisao);

		time = 999999999;
	}

	/**
	 * Método de renderização onde o jogo é atualizado e desenhado na tela.
	 *
	 * @param delta O tempo passado desde o último quadro.
	 */
	@Override
	public void render(float delta) {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();

		batch.draw(img, 0, 0);
		batch.draw(nave.getNave(), nave.getPostX(), nave.getPostY());
		batch.draw(nave.getImgEscudos(), 350, Gdx.graphics.getHeight() - 75);
		bitMap.draw(batch, "Pontos: " + pontos, 40, Gdx.graphics.getHeight() - 40);

		// Lógica para atualização de pontos e geração de power-ups
		if (TimeUtils.nanoTime() - ultimoNanoTime > time) {
			pontos += 10;
			ultimoNanoTime = TimeUtils.nanoTime();
			if (pontos % 100 == 0) {
				if (nave.getEscudos() == 0 || nave.getEscudos() == 1) {
					spawnPowerUp.spawnPowerUps("escudo");
				} else {
					int numeroSorteado = MathUtils.random(1, 2);
					switch (numeroSorteado) {
					case 1:
						spawnPowerUp.spawnPowerUps("tiroBomba");
						break;
					case 2:
						spawnPowerUp.spawnPowerUps("escudo");
						break;
					}
				}

				time -= 10000;
			}
		}

		// Renderização de objetos e atualizações
		spawnPowerUp.renderPowerUps(batch);
		spawnPowerUp.removerPowerUps();
		meteoro.moverMeteoros();
		meteoro.desenharMeteoro(batch);
		alien.moverAlien();
		alien.desenharAlien(batch);
		nave.moverNave();
		nave.atirar();
		nave.renderBalas(batch);
		nave.removerBalas();

		// Verifica se o jogador perdeu o jogo e redireciona para a tela de Game Over
		if (nave.getEscudos() == -1) {
			MyGdxGame game = (MyGdxGame) Gdx.app.getApplicationListener();
			game.setScreen(new GameOverScreen("" + pontos));
		}

		batch.end();

	}

	/**
	 * Método chamado quando a tela do jogo é ocultada. Realiza a limpeza de
	 * recursos.
	 */
	@Override
	public void hide() {
		batch.dispose();
		img.dispose();
	}

}
