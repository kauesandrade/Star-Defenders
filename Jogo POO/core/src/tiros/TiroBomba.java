/**
 * Esta classe representa um tipo de tiro de bomba no jogo. Ela herda da classe abstrata Tiro e define as
 * características específicas de um tiro de bomba.
 */
package tiros;

import com.badlogic.gdx.graphics.Texture;

public class TiroBomba extends Tiro {

	/**
	 * Construtor da classe TiroBomba que configura as características do tiro de
	 * bomba, como a textura e a velocidade.
	 *
	 * @param x A coordenada X inicial do tiro.
	 * @param y A coordenada Y inicial do tiro.
	 */
	public TiroBomba(float x, float y) {
		super(x, y);
		imgTiro = new Texture("tiroBomba.png");
		velocidade = 5;
	}

}
