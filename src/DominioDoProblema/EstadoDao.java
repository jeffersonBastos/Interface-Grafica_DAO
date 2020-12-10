package DominioDoProblema;

public class EstadoDao {

	protected String mensagem;
	protected Lance lance;
	protected int[][] valoresTabuleiro;
	
	public EstadoDao(String mensagem, Lance lance, int[][] valoresTabuleiro) {
		this.mensagem = mensagem;
		this.lance = lance;
		this.valoresTabuleiro = valoresTabuleiro;
	}
	
	public Lance informarLance() {
		return lance;
	}
	
}
