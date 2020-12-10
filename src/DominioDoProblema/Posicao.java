package DominioDoProblema;

public class Posicao {
	
	protected Jogador ocupante;
	protected int linha;
	protected int coluna;
	
	public Posicao(Jogador ocupante, int linha, int coluna) {
		this.ocupante = ocupante;
		this.linha = linha;
		this.coluna = coluna;
	}
	
	public void definirOcupante(Jogador jogador) {
		
	}
	
	public boolean informarOcupada() {
		return false;
	}
	
	public int getLinha() {
		return 0;
	}
	
	public void getColuna() {
		
	}
	
}
