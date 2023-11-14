/**
 * Esta classe é responsável pela detecção de colisões no jogo, incluindo colisões entre a nave do jogador,
 * os tiros disparados e outros objetos, bem como a detecção de colisões entre a nave e os power-ups.
 */
package utilitarios;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import objetos.Nave;
import tiros.Tiro;

public class Colisao {

	private Nave nave;

	private Array<Tiro> arrTiros = new Array<>();

	/**
	 * Construtor da classe Colisao que recebe uma instância de Nave para detecção
	 * de colisões.
	 *
	 * @param nave A instância de Nave para detecção de colisões.
	 */
	public Colisao(Nave nave) {
		super();
		this.nave = nave;
		this.arrTiros = nave.getArrTiros();
	}

	public Colisao() {
		super();
	}

	/**
	 * Obtém a instância de Nave associada à detecção de colisões.
	 *
	 * @return A instância de Nave associada à detecção de colisões.
	 */
	public Nave getNave() {
		return nave;
	}

	public void setNave(Nave nave) {
		this.nave = nave;
	}

	/**
	 * Verifica se ocorreu uma colisão entre um objeto retangular (representado por
	 * um retângulo delimitado pelas coordenadas x, y, altura e largura) e a nave do
	 * jogador.
	 *
	 * @param r O retângulo que representa o objeto com o qual a colisão é
	 *          verificada.
	 * @return true se houve uma colisão, caso contrário, false.
	 */
	public Boolean colisaoRectangleNave(Rectangle r) {
		if (colicao(r.x, r.y, r.height, r.width, nave.getPostX(), nave.getPostY(), nave.getImgNave().getHeight(),
				nave.getImgNave().getWidth())) {
			nave.perderEscudo();
			return true;
		}
		return false;
	}

	/**
	 * Verifica se ocorreu uma colisão entre um objeto retangular (representado por
	 * um retângulo delimitado pelas coordenadas x, y, altura e largura) e um dos
	 * tiros disparados pela nave.
	 *
	 * @param r O retângulo que representa o objeto com o qual a colisão é
	 *          verificada.
	 * @return true se houve uma colisão, caso contrário, false.
	 */
	public Boolean colisaoTiro(Rectangle r) {
		for (Tiro t : arrTiros) {
			if (colicao(r.x, r.y, r.height, r.width, t.getTiroX(), t.getTiroY(), t.getImgTiro().getHeight(),
					t.getImgTiro().getWidth())) {
				if (t.getImgTiro().toString() != "tiroBomba.png") {
					t.setRemover(true);
				}
				nave.setArrTiros(arrTiros);
				return true;
			}
		}
		return false;

	}

	/**
	 * Verifica se ocorreu uma colisão entre um power-up e a nave do jogador.
	 *
	 * @param powerUpX A coordenada x do power-up.
	 * @param powerUpY A coordenada y do power-up.
	 * @param img      A textura do power-up.
	 * @return true se houve uma colisão, caso contrário, false.
	 */
	public Boolean colisaNavePowerUp(float powerUpX, float powerUpY, Texture img) {
		if (colicao(powerUpX, powerUpY, img.getHeight(), img.getWidth(), nave.getPostX(), nave.getPostY(),
				nave.getImgNave().getHeight(), nave.getImgNave().getWidth())) {
			return true;
		}
		return false;

	}

	/**
	 * Verifica se ocorreu uma colisão entre dois retângulos.
	 *
	 * @param x1 A coordenada x do primeiro retângulo.
	 * @param y1 A coordenada y do primeiro retângulo.
	 * @param h1 A altura do primeiro retângulo.
	 * @param w1 A largura do primeiro retângulo.
	 * @param x2 A coordenada x do segundo retângulo.
	 * @param y2 A coordenada y do segundo retângulo.
	 * @param h2 A altura do segundo retângulo.
	 * @param w2 A largura do segundo retângulo.
	 * @return true se houve uma colisão entre os retângulos, caso contrário, false.
	 */
	public boolean colicao(float x1, float y1, float h1, float w1, float x2, float y2, float h2, float w2) {
		if (x1 + w1 > x2 && x1 < x2 + w2 && y1 + h1 > y2 && y1 < y2 + h2) {
			return true;
		}
		return false;
	}

}
