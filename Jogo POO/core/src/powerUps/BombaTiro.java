package powerUps;

import com.badlogic.gdx.graphics.Texture;

import utilitarios.Colisao;

public class BombaTiro extends PowerUp {

	/**
	 * Construtor da classe BombaTiro que herda de PowerUp e define a posição
	 * inicial do power-up e a colisão associada a ele.
	 *
	 * @param x       A coordenada X inicial do power-up.
	 * @param y       A coordenada Y inicial do power-up.
	 * @param colisao O objeto de colisão utilizado para verificar interações com a
	 *                nave.
	 */
	public BombaTiro(float x, float y, Colisao colisao) {
		super(x, y, colisao);
		this.imgPowerUp = new Texture("tiroBombaP.png");
	}

	/**
	 * Ativa o efeito do power-up na nave, permitindo que a nave mude o tipo de tiro
	 * para "Bomba".
	 */
	@Override
	public void ativarPowerUp() {
		colisao.getNave().setTipoTiro("Bomba");
	}

}
