package InterfaceGrafica;

import DominioDoProblema.ElementoDominioProblema;
import Rede.AtorNetgames;

public class AtorJogador {
	
	protected AtorNetgames ngServer;
	protected ElementoDominioProblema domProblema;
	protected Tabuleiro tabuleiro;
	protected InterfaceGrafica gui;

	public AtorJogador() {
		ngServer = new AtorNetgames();
		domProblema = new ElementoDominioProblema();
		tabuleiro = new Tabuleiro();
	}

	public AtorJogador(InterfaceGrafica interfaceDao) {
		super();
		iniciar(interfaceDao);
	}

	public void iniciar(InterfaceGrafica interfaceDao) {
		gui = interfaceDao;
		ngServer = new AtorNetgames();
		tabuleiro = new Tabuleiro();
		ngServer.definirAtorJogador(this);
	}

	public String movimentarPedra(int linha, int coluna) {
		String notificacao = tabuleiro.movimentarPedra(linha, coluna);
		if (notificacao == "") {
			EstadoDao estado = tabuleiro.informarEstado();
			Lance lance = estado.informarLance();
			ngServer.enviarJogada(lance);
		}
		return(notificacao);
	}

	public EstadoDao informarEstado() {
		return tabuleiro.informarEstado();
	}

	public void receberJogada(Lance lance) {
		tabuleiro.receberJogada(lance);
		gui.exibirEstado();
	}

	public void conectar() {
		boolean conectado = ngServer.informarConectado();
		if (!conectado) {
			String jogador = gui.obterNomeJogador();
			String servidor = gui.obterEnderecoServidor();
			String notificacao = ngServer.conectar(servidor, jogador);
			tabuleiro.registrarJogadorLocal(jogador);
			gui.notificar(notificacao);
		}else {
			gui.notificar("Voce ja esta conectado");
		}
	}
	
	public boolean desconectar() {
		boolean conectado = ngServer.informarConectado();
		boolean atualizarInterface = false;
		if(conectado) {
			atualizarInterface = tabuleiro.encerrarPartida();
			if (atualizarInterface) ngServer.encerrarPartida();
			ngServer.desconectar();
			gui.notificar("Voce esta desconectado");
		} else {
			gui.notificar("Voce nao esta conectado");
		}
		return atualizarInterface;
	}
	
	public String iniciarPartida() {
		boolean conectado = ngServer.informarConectado();
		boolean atualizarInterface = false;
		if (conectado) {
			atualizarInterface = tabuleiro.encerrarPartida();
			if (atualizarInterface) ngServer.encerrarPartida();
			ngServer.iniciarPartida();
		} else {
			gui.notificar("Voce nao esta conectado");
		}
		return atualizarInterface;
	}

	public void iniciarNovaPartida(Integer ordem, String adversario) {
		tabuleiro.iniciarNovaPartida(ordem, adversario);
		gui.exibirEstado();
	}

	public void encerrarPartida() {
		boolean atualizar = tabuleiro.encerrarPartida();
		gui.notificar("Partida finalizada");
		if(atualizar) gui.exibirEstado();
	}

}
