/**
 * Esta classe abstrata representa um tiro no jogo. Ela lida com a criação, movimento e renderização
 * de tiros, bem como a detecção de quando um tiro deve ser removido.
 */
package tiros;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Tiro {

	protected Texture imgTiro;
	protected float tiroY;
	protected float tiroX;
	protected float velocidade;
	protected boolean remover = false;

	/**
	 * Construtor da classe Tiro que recebe as coordenadas iniciais do tiro.
	 *
	 * @param x A coordenada X inicial do tiro.
	 * @param y A coordenada Y inicial do tiro.
	 */
	public Tiro(float x, float y) {
		this.tiroX = x;
		this.tiroY = y;
	}

	public Texture getImgTiro() {
		return imgTiro;
	}

	public void setImgTiro(Texture imgTiro) {
		this.imgTiro = imgTiro;
	}

	public float getTiroY() {
		return tiroY;
	}

	public void setTiroY(float tiroY) {
		this.tiroY = tiroY;
	}

	public float getTiroX() {
		return tiroX;
	}

	public void setTiroX(float tiroX) {
		this.tiroX = tiroX;
	}

	public void setRemover(boolean remover) {
		this.remover = remover;
	}

	public boolean isRemover() {
		return remover;
	}

	/**
	 * Atualiza a posição do tiro e verifica se ele deve ser removido com base na
	 * sua posição.
	 */
	public void update() {
		tiroX += velocidade;
		if (tiroX > 1536) {
			remover = true;
		}
	}

	/**
	 * Renderiza o tiro na tela usando o lote de sprites fornecido.
	 *
	 * @param batch O lote de sprites no qual renderizar o tiro.
	 */
	public void render(SpriteBatch batch) {
		batch.draw(imgTiro, tiroX, tiroY);
	}
}