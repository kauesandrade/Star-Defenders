package powerUps;

import com.badlogic.gdx.graphics.Texture;

import utilitarios.Colisao;

public class Escudo extends PowerUp {

	/**
	 * Construtor da classe Escudo que herda de PowerUp e define a posição inicial
	 * do power-up e a colisão associada a ele.
	 *
	 * @param x       A coordenada X inicial do power-up.
	 * @param y       A coordenada Y inicial do power-up.
	 * @param colisao O objeto de colisão utilizado para verificar interações com a
	 *                nave.
	 */
	public Escudo(float x, float y, Colisao colisao) {
		super(x, y, colisao);
		this.imgPowerUp = new Texture("escudo.png");
	}

	/**
	 * Ativa o efeito do power-up na nave, permitindo que a nave ganhe um escudo
	 * extra.
	 */
	@Override
	public void ativarPowerUp() {
		colisao.getNave().ganharEscudo();
	}

}
