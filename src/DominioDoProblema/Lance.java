package DominioDoProblema;

public class Lance {

	protected int linhaAtual;
	protected int colunaAtual;
	protected int linhaAntiga;
	protected int colunaAntiga;
	
	
	public Lance(int linhaAtual, int colunaAtual, int linhaAntiga, int colunaAntiga) {
		this.linhaAtual = linhaAtual;
		this.colunaAtual = colunaAtual;
		this.linhaAntiga = linhaAntiga;
		this.colunaAntiga = colunaAntiga;
	}
	
	public void definirPosicao(int linha, int coluna) {
		
	}
	
	public void definirPosicaoAntiga() {
		
	}
	
	public Posicao informarPosicao(int linha, int coluna) {
		return null;
	}
	
	public int informarLinhaAtual() {
		return this.linhaAtual;
	}
	
	public int informarColunaAtual() {
		return this.colunaAtual;
	}
	
	public int informarLinhaAntiga() {
		return this.linhaAntiga;
	}
	
	public int informarColunaAntiga() {
		return this.colunaAntiga;
	}
}
