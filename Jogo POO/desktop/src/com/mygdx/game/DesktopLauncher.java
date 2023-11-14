package com.mygdx.game;

/**
 * Esta classe é responsável por iniciar o jogo no ambiente de desktop usando a biblioteca libGDX.
 * Ela configura as propriedades da janela e inicia a aplicação do jogo.
 */
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	/**
	 * Método principal que inicia a aplicação do jogo.
	 *
	 * @param arg Argumentos de linha de comando (não utilizado neste contexto).
	 */
	public static void main(String[] arg) {
		// Configurar as opções da janela do jogo
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60); // Define a taxa de quadros em primeiro plano
		config.setTitle("Jogo"); // Define o título da janela do jogo
		config.setWindowedMode(1536, 864); // Define o tamanho da janela

		// Iniciar a aplicação do jogo com a configuração especificada
		new Lwjgl3Application(new MyGdxGame(), config);
	}
}
