/**
 * Esta classe representa os meteoros que aparecem no jogo. Ela lida com a criação, movimento e renderização
 * dos meteoros.
 */
package objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import utilitarios.Colisao;
import utilitarios.Spawn;

public class Meteoro {

	private Texture imgMeteoro = new Texture("meteoro.png");
	private Spawn spawn;

	/**
     * Construtor da classe Meteoro que recebe um objeto Colisao e configura o spawn dos meteoros.
     *
     * @param colisao Objeto utilizado para detectar colisões.
     */
	public Meteoro(Colisao colisao) {
		spawn = new Spawn(imgMeteoro, colisao);
	}

	 /**
     * Renderiza os meteoros na tela usando o lote de sprites fornecido.
     *
     * @param batch O lote de sprites no qual renderizar os meteoros.
     */
	public void desenharMeteoro(SpriteBatch batch) {
		spawn.desenhar(batch);
	}

	 /**
     * Gera meteoros na tela.
     */
	public void spawnMeteoros() {
		spawn.spawnObjetos();
	}

	 /**
     * Move os meteoros na tela.
     */
	public void moverMeteoros() {
		spawn.moverX(499999999, 8);
	}

}
