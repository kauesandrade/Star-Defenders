/**
 * Esta classe representa um tipo de tiro normal no jogo. Ela herda da classe abstrata Tiro e define as
 * características específicas de um tiro normal.
 */
package tiros;

import com.badlogic.gdx.graphics.Texture;

public class TiroNormal extends Tiro {

	/**
	 * Construtor da classe TiroNormal que configura as características do tiro
	 * normal, como a textura e a velocidade.
	 *
	 * @param x A coordenada X inicial do tiro.
	 * @param y A coordenada Y inicial do tiro.
	 */
	public TiroNormal(float x, float y) {
		super(x, y);
		imgTiro = new Texture("lazerVermelho.png");
		velocidade = 20;
	}

}
