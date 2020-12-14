package InterfaceGrafica;

import DominioDoProblema.ElementoDominioProblema;
import DominioDoProblema.EstadoDao;
import DominioDoProblema.Lance;
import DominioDoProblema.Tabuleiro;
import Rede.AtorNetgames;

public class AtorJogador {
	
	protected AtorNetgames ngServer;
	protected ElementoDominioProblema domProblema;
	protected InterfaceJogo gui;
	protected Tabuleiro tabuleiro;

	public AtorJogador() {
		ngServer = new AtorNetgames();
		domProblema = new ElementoDominioProblema();
	}
	
	public AtorJogador(InterfaceJogo interfaceJogo) {
		super();
		iniciar(interfaceJogo);
	}
	
	private void iniciar(InterfaceJogo interfaceJogo) {
		gui = interfaceJogo;
		ngServer = new AtorNetgames();
		tabuleiro = new Tabuleiro();		
		ngServer.definirAtorJogador(this);
	}
	
	public void conectar() {
		boolean conectado = ngServer.informarConectado();
		if(!conectado) {
			String jogador = gui.obterNomeJogador();
			String servidor = gui.obterEnderecoServidor();
			String notificacao = ngServer.conectar(servidor, jogador);
			tabuleiro.registrarJogadorLocal(jogador);
			gui.notificar(notificacao);
		} else {
			gui.notificar("Voce ja esta conectado");
		}
	}
	
	public void receberJogada(Lance lance) {
		tabuleiro.receberJogada(lance);
		gui.exibirEstado();
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
	
	public boolean iniciarPartida() {
		boolean conectado = ngServer.informarConectado();
		boolean atualizarInterface = false;
		if(conectado) {
			System.out.println("ronado-conectado");
			atualizarInterface = tabuleiro.encerrarPartida();
			if (atualizarInterface) ngServer.encerrarPartida();
			String nout = ngServer.iniciarPartida();
			System.out.println("ronado-conectado" + nout );
		} else {
			gui.notificar("Voce nao esta conectado");
		}
		return atualizarInterface;

	}
	
	public EstadoDao informarEstado() {
		return tabuleiro.informarEstado();
	}
	
	public void iniciarNovaPartida(Integer ordem, String adversario) {
		tabuleiro.iniciarNovaPartida(ordem, adversario);
		gui.exibirEstado();
	}
	
	public void encerrarPartida() {
		boolean atualizar = tabuleiro.encerrarPartida();
		gui.notificar("Partida finalizada");
		if (atualizar) gui.exibirEstado();
	}
	

	public String movimentarPedra(int linhaAntiga, int colunaAntiga, int linhaAtual, int colunaAtual) {
		String notificacao = tabuleiro.movimentarPedra(linhaAntiga, colunaAntiga, linhaAtual, colunaAtual);
		if (notificacao == "") {
			EstadoDao estado = tabuleiro.informarEstado();
			Lance lance = estado.informarLance();
			ngServer.enviarJogada(lance);
		}
		return(notificacao);
	}
	
	
	
	
	
	
	
	
	
}