/**
 * Esta classe representa os alienígenas que aparecem no jogo. Ela lida com a criação, movimento e renderização
 * dos alienígenas.
 */
package objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import utilitarios.Colisao;
import utilitarios.Spawn;

public class Alien {

	private Texture imgAlien = new Texture("alien.png");
	private Spawn spawn;

	/**
	 * Construtor da classe Alien que recebe um objeto Colisao e configura o spawn
	 * dos alienígenas.
	 *
	 * @param colisao Objeto utilizado para detectar colisões.
	 */
	public Alien(Colisao colisao) {
		spawn = new Spawn(imgAlien, colisao);
	}

	/**
	 * Renderiza os alienígenas na tela usando o lote de sprites fornecido.
	 *
	 * @param batch O lote de sprites no qual renderizar os alienígenas.
	 */
	public void desenharAlien(SpriteBatch batch) {
		spawn.desenhar(batch);
	}

	/**
	 * Gera alienígenas na tela.
	 */
	public void spawnAlien() {
		spawn.spawnObjetos();
	}

	/**
	 * Move os alienígenas na tela.
	 */
	public void moverAlien() {
		spawn.moverY(999999999, 4);
	}

}
