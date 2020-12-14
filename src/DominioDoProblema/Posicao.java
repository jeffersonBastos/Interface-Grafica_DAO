package DominioDoProblema;

public class Posicao {
	
	protected Jogador ocupante;
	protected int linha;
	protected int coluna;
	
	public Posicao(int linha, int coluna) {
		this.ocupante = null;
		this.linha = linha;
		this.coluna = coluna;
	}
	
	public void definirOcupante(Jogador jogador) {
		this.ocupante = jogador;		
	}
	
	public void setSemOcupante() {
		this.ocupante = null;
	}
	
	public boolean informarOcupada() {
		return (ocupante != null);
	}
	public boolean ocupadaPor(Jogador jogador) {
		return ( jogador.getCor() == ocupante.getCor());
	}
	public int getLinha() {
		return this.linha;
	}
	
	public int getColuna() {
		return this.coluna;
		
	}
	public Jogador getOcupante() {
		return ocupante;
	}
	
}
