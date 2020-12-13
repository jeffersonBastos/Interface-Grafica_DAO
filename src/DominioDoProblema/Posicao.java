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
		if(ocupante == null) {
			return false;
		}
		return true;
	}
	public boolean ocupadaPor(Jogador jogador) {
		if(ocupante == null) {
			return false;
		}else if(jogador.getCor() == ocupante.getCor()){
			return true;
		}
		return false;
	}
	public int getLinha() {
		return this.linha;
	}
	
	public int getColuna() {
		return this.coluna;
		
	}
	
}
