package DominioDoProblema;

public class EstadoDao {

	protected String mensagem = "Jogo DAO - aguardando iniciar partida";
	protected Lance lance = new Lance();
	protected int[][] valoresTabuleiro = new int [4][4];
		
	
	
	public EstadoDao() {
		
	}

	public EstadoDao(Lance lance) {
		this.lance = lance;
	}
	public void assumirMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Lance informarLance() {
		return lance;
	}
	
	public String informarMensagem() {
		return mensagem;
	}
	
	public void assumirValorTabuleiro(int linha, int coluna, int valor) {
		valoresTabuleiro [linha] [coluna] = valor;
	}
	
	public int informarValorTabuleiro(int linha, int coluna) {
		return valoresTabuleiro [linha] [coluna];
	}

}
