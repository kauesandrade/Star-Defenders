/**
 * Esta classe abstrata representa um power-up no jogo. Ela lida com a criação, movimento e renderização
 * de power-ups, bem como a detecção de colisões com a nave do jogador.
 */
package powerUps;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import utilitarios.Colisao;

public abstract class PowerUp {

	protected Texture imgPowerUp;
	protected float PowerUpY;
	protected float PowerUpX;
	protected float velocidade = 5;
	protected boolean remover = false;
	protected boolean pegou = false;
	protected Colisao colisao;

	/**
	 * Construtor da classe PowerUp que define a posição inicial do power-up e a
	 * colisão associada a ele.
	 *
	 * @param x       A coordenada X inicial do power-up.
	 * @param y       A coordenada Y inicial do power-up.
	 * @param colisao O objeto de colisão utilizado para verificar interações com a
	 *                nave.
	 */
	public PowerUp(float x, float y, Colisao colisao) {
		this.PowerUpX = x;
		this.PowerUpY = y;
		this.colisao = colisao;
	}

	public Texture getImgPowerUp() {
		return imgPowerUp;
	}

	public void setImgPowerUp(Texture imgTiro) {
		this.imgPowerUp = imgTiro;
	}

	public float getPowerUpY() {
		return PowerUpY;
	}

	public void setPowerUpY(float tiroY) {
		this.PowerUpY = tiroY;
	}

	public float getPowerUpX() {
		return PowerUpX;
	}

	public void setPowerUpX(float tiroX) {
		this.PowerUpX = tiroX;
	}

	public void setRemover(boolean remover) {
		this.remover = remover;
	}

	public boolean isRemover() {
		return remover;
	}

	public boolean isPegou() {
		return pegou;
	}

	public void setPegou(boolean pegou) {
		this.pegou = pegou;
	}

	/**
	 * Atualiza a posição do power-up durante o jogo e verifica se ele foi coletado
	 * pela nave.
	 */
	public void update() {
		PowerUpX -= velocidade;
		if (PowerUpX < 0 || colisao.colisaNavePowerUp(PowerUpX, PowerUpY, imgPowerUp)) {
			if (colisao.colisaNavePowerUp(PowerUpX, PowerUpY, imgPowerUp)) {
				pegou = true;
			}
			remover = true;
		}
	}

	/**
	 * Renderiza o power-up na tela usando o lote de sprites fornecido.
	 *
	 * @param batch O lote de sprites no qual renderizar o power-up.
	 */
	public void render(SpriteBatch batch) {
		batch.draw(imgPowerUp, PowerUpX, PowerUpY);
	}

	/**
	 * Método que ativa o efeito do power-up na nave ou em outros elementos do jogo.
	 */
	public void ativarPowerUp() {
		// Este método pode ser implementado nas subclasses para ativar o efeito do
		// power-up.
	}

}
