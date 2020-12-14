package Rede;

import javax.swing.JOptionPane;

import DominioDoProblema.Lance;
import InterfaceGrafica.AtorJogador;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

public class AtorNetgames implements OuvidorProxy {
	
	private static final long serialVersionUID = 1L;
	protected Proxy proxy;
	boolean conectado = false;
	protected AtorJogador atorJogador;

	
	public AtorNetgames() {
		super();
		this.proxy = Proxy.getInstance();
		proxy.addOuvinte(this);	
	}
	
	public void definirAtorJogador(AtorJogador ator) {
		atorJogador = ator;
	}
	
	public String conectar(String servidor, String nome) {
			try {
				proxy.conectar(servidor, nome);
			} catch (JahConectadoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Voce ja esta conectado";
			} catch (NaoPossivelConectarException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Nao foi possivel conectar";
			} catch (ArquivoMultiplayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Voce esqueceu o arquivo de propriedades";
			}
			this.definirConectado(true);
			return "Sucesso: conectado a Netgames Server";
		
	}

	public void definirConectado(boolean valor) {
		conectado = valor;
	}

	public String desconectar() {
			try {
				proxy.desconectar();
			} catch (NaoConectadoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Voce nao esta conectado";
			}
			this.definirConectado(false);
			return "Sucesso: desconectado de Netgames Server";
	}

	public String iniciarPartida() {
		try {
			proxy.iniciarPartida(new Integer(2)); 
		} catch (NaoConectadoException e) {
			e.printStackTrace();
			return "Falha ao tentar enviar solicitacao de inicio enviada a Netgames Server";
		}
		return "Sucesso: solicitacao de inicio enviada a Netgames Server";
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		int indiceAdversario = 1;
		if (posicao.equals(1)) indiceAdversario = 2;
		String adversario = proxy.obterNomeAdversario(indiceAdversario);
		atorJogador.iniciarNovaPartida(posicao, adversario);
		JOptionPane.showMessageDialog(null, "o servidor enviou solicitacao de inicio de partida e isso deve ser tratado segundo as regras do seu jogo");
	}
	
	public boolean informarConectado() {
		return conectado;
	}
	
	public void encerrarPartida() {
		try {
			proxy.finalizarPartida();
		} catch (NaoConectadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NaoJogandoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void finalizarPartidaComErro(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receberMensagem(String msg) {
		// TODO Auto-generated method stub
	}

	@Override
	public void receberJogada(Jogada jogada) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void enviarJogada(Lance lance) {
		try {
			proxy.enviaJogada(lance);
		} catch (NaoJogandoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}