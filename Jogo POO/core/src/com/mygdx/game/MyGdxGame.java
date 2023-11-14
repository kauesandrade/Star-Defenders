/**
 * Esta classe representa o ponto de entrada principal para o jogo, estendendo a classe base
 * {@link com.badlogic.gdx.Game}. É responsável por iniciar o jogo e gerenciar as diferentes telas.
 */
package com.mygdx.game;

import com.badlogic.gdx.Game;
import screens.MenuScreen;

public class MyGdxGame extends Game {
	/**
	 * Método chamado quando o jogo é iniciado. Define a tela inicial como a tela de
	 * menu.
	 */
	@Override
	public void create() {
		setScreen(new MenuScreen());
	}
}