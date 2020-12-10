package DominioDoProblema;

public class RenquePosicao {
	protected Posicao posicoes[];
	protected boolean ehCanto;
	
	public RenquePosicao(Posicao posicoes[], boolean ehCanto) {
		this.posicoes = posicoes;
		this.ehCanto = ehCanto;
	}
	
	public boolean avaliarCondicaoVitoria(Jogador jogador) {
		return false;
	}
}
