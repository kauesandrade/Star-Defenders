/**
 * Esta classe é responsável por gerenciar o spawn de objetos (como meteoros e power-ups) no jogo.
 * Ela controla a criação, movimento e renderização desses objetos, bem como a detecção de colisões.
 */
package utilitarios;

import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import powerUps.BombaTiro;
import powerUps.Escudo;
import powerUps.PowerUp;

public class Spawn {

	private Array<Rectangle> rectangles = new Array<Rectangle>();
	private Array<PowerUp> arrPowerUps = new Array<>();

	private Texture img;
	private Colisao colisao = new Colisao();
	private float ultimoNanoTime = 0;

	/**
	 * Construtor da classe Spawn que recebe a textura do objeto a ser spawnado e
	 * uma instância de Colisao.
	 *
	 * @param img     A textura do objeto a ser spawnado.
	 * @param colisao A instância de Colisao para detecção de colisões.
	 */
	public Spawn(Texture img, Colisao colisao) {
		this.img = img;
		this.colisao = colisao;
	}

	/**
	 * Construtor da classe Spawn que recebe uma instância de Colisao.
	 *
	 * @param colisao A instância de Colisao para detecção de colisões.
	 */
	public Spawn(Colisao colisao) {
		this.colisao = colisao;
	}

	/**
	 * Obtém a lista de power-ups presentes no jogo.
	 *
	 * @return A lista de power-ups.
	 */
	public Array<PowerUp> getArrPowerUps() {
		return arrPowerUps;
	}

	public void setArrPowerUps(Array<PowerUp> arrPowerUps) {
		this.arrPowerUps = arrPowerUps;
	}

	/**
	 * Desenha os objetos spawnados na tela usando o lote de sprites fornecido.
	 *
	 * @param batch O lote de sprites no qual renderizar os objetos spawnados.
	 */
	public void desenhar(SpriteBatch batch) {
		for (Rectangle r : rectangles) {
			batch.draw(img, r.x, r.y);
		}
	}

	/**
	 * Realiza o spawn de um objeto na tela.
	 */
	public void spawnObjetos() {
		Rectangle r = new Rectangle(1536 - img.getWidth(), MathUtils.random(0, 864 - img.getHeight()), img.getWidth(),
				img.getHeight());
		rectangles.add(r);
		ultimoNanoTime = TimeUtils.nanoTime();
	}

	/**
	 * Move os objetos na direção horizontal com base em um tempo e velocidade.
	 *
	 * @param time       O intervalo de tempo para spawn de objetos.
	 * @param velocidade A velocidade de movimento dos objetos.
	 */
	public void moverX(int time, int velocidade) {

		if (TimeUtils.nanoTime() - ultimoNanoTime > time) {
			this.spawnObjetos();
		}

		for (Iterator<Rectangle> iter = rectangles.iterator(); iter.hasNext();) {
			Rectangle r = iter.next();
			r.x -= velocidade;
			if (r.x + r.getWidth() < 0 || colisao.colisaoRectangleNave(r) || colisao.colisaoTiro(r)) {
				iter.remove();
			}

		}
	}

	/**
	 * Move os objetos na direção vertical com base em um tempo e velocidade.
	 *
	 * @param time       O intervalo de tempo para spawn de objetos.
	 * @param velocidade A velocidade de movimento dos objetos.
	 */
	public void moverY(long time, int velocidade) {
		if (TimeUtils.nanoTime() - ultimoNanoTime > time * 9) {
			this.spawnObjetos();
		}
		for (Iterator<Rectangle> iter = rectangles.iterator(); iter.hasNext();) {
			Rectangle r = iter.next();

			if (colisao.colisaoRectangleNave(r) || colisao.colisaoTiro(r)) {
				iter.remove();
			}
		}
	}

	/**
	 * Realiza o spawn de power-ups com base no tipo especificado.
	 *
	 * @param tipo O tipo de power-up a ser spawnado.
	 */
	public void spawnPowerUps(String tipo) {
		switch (tipo) {
		case "escudo":
			arrPowerUps.add(new Escudo(1536, MathUtils.random(0 + 100, 864 - 100), colisao));
			break;
		case "tiroBomba":
			arrPowerUps.add(new BombaTiro(1536, MathUtils.random(0 + 100, 864 - 100), colisao));
			break;
		}

	}

	/**
	 * Renderiza os power-ups na tela usando o lote de sprites fornecido.
	 *
	 * @param batch O lote de sprites no qual renderizar os power-ups.
	 */
	public void renderPowerUps(SpriteBatch batch) {
		for (PowerUp p : arrPowerUps) {
			p.render(batch);
		}
	}

	/**
	 * Remove os power-ups que devem ser removidos do jogo.
	 */
	public void removerPowerUps() {
		Array<PowerUp> removerPowerUp = new Array<>();
		for (PowerUp p : arrPowerUps) {
			p.update();
			if (p.isRemover()) {
				if (p.isPegou()) {
					p.ativarPowerUp();
				}
				removerPowerUp.add(p);
			}

		}
		arrPowerUps.removeAll(removerPowerUp, false);

	}

}
