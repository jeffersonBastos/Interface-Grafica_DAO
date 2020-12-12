package Rede;

import javax.swing.JOptionPane;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

public class AtorNetgames implements OuvidorProxy {
	
	private static final long serialVersionUID = 1L;
	protected Proxy proxy;
	protected AtorJogador atorJogador;
	protected boolean conectado = false;
	
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
			proxy.iniciarPartida(new Integer(2)); // supondo 2 jogadores, o que pode ser alterado
		} catch (NaoConectadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Falha ao tentar enviar solicitacao de inicio enviada a Netgames Server";
		}
		return "Sucesso: solicitacao de inicio enviada a Netgames Server";
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		int indiceAdversario = 1;
		if(posicao.equals(1)) indiceAdversario = 2;
		String adversario = proxy.obterNomeAdversario(indiceAdversario);
		atorJogador.iniciarNovaPartida(posicao, adversario);
		JOptionPane.showMessageDialog(null, "o servidor enviou solicitacao de inicio de partida e isso deve ser tratado segundo as regras do seu jogo");
	}

	@Override
	public void finalizarPartidaComErro(String message) {
		atorJogador.encerrarPartida();
	}

	@Override
	public void receberMensagem(String msg) {
	}

	@Override
	public void receberJogada(Jogada jogada) {
		atorJogador.receberJogada((Lance) jogada);
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

	public boolean informarConectado() {
		return conectado;
	}

	public void definirConectado(boolean valor) {
		conectado = valor;
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

}
