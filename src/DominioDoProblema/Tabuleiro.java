package DominioDoProblema;

public class Tabuleiro {
	
	protected Posicao posicoes[][];
	protected Jogador jogadorLocal;
	protected Jogador jogadorRemoto;
	protected boolean partidaEmAndamento;
	protected boolean jogadaEmAndamento;
	
	public Tabuleiro (Posicao posicoes[][], Jogador jogadorLocal, Jogador jogadorRemoto, 
			boolean partidaEmAndamento, boolean jogadaEmAndamento ) {
		
		this.posicoes = posicoes;
		this.jogadorLocal = jogadorLocal;
		this.jogadorRemoto = jogadorRemoto;
		this.partidaEmAndamento = partidaEmAndamento;
		this.partidaEmAndamento = jogadaEmAndamento;
	}
	
	public void efetuarMovimentoPedra(int linha, int coluna, Jogador jogador) {
		
	}
	
	public Posicao obterPosicao(int linha, int coluna) {
		return null;
	}
	
	public void definirJogadaEmAndamento(boolean valor) {
		
	}
	
	public EstadoDao informarEstado(Lance lance) {
		return null;
	}
	
	public String movimentarPedra(int linham, int coluna) {
		return null; 
	}
	
	public EstadoDao informarEstado() {
		return null;
	}
	
	public void receberJogada(Lance lance) {
		
	}
	
	public boolean encerrarHavendoPartida() {
		return false;
	}
	
	public void iniciarNovaPartida(int ordem, String adversario) {
		
	}
	
	public void iniciarTabuleiro() {
		
	}
	
	public void encerrarPartida() {
		
	}
	
	public void encerrarPartidaLocalmente() {
		
	}
	
	public void atualizarEstado(Lance lance) {
		
	}
	
	public void avaliarEncerramentoPartida() {
		
	}
	
	public RenquePosicao informarLinha(int indice) {
		return null;
	}
	
	public RenquePosicao informarColuna(int indice) {
		return null;
	}
	
	public RenquePosicao informaCanto(int indiceCanto) {
		return null;
	}
	
	public boolean avaliarMovimento(Posicao posicaoAntiga, Posicao posicaoAtual) {
		return false;
	}
	
}
