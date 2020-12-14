package DominioDoProblema;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Lance implements Jogada {
    
	private static final long serialVersionUID = 1L;
	protected int linhaAtual = 0;
	protected int colunaAtual = 0;
	protected int linhaAntiga = 0;
	protected int colunaAntiga = 0;
	
	
	public Lance() {
	}
	
	public void definirPosicaoAntiga(int linha, int coluna) {
		linhaAntiga = linha;
		colunaAntiga = coluna;
	}
	
	public void definirPosicaoAtual(int linha, int coluna) {
		linhaAtual = linha;
		colunaAtual = coluna;
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
