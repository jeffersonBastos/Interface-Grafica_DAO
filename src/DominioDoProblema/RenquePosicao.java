package DominioDoProblema;

public class RenquePosicao {
	protected Posicao posicoes[] = new Posicao[4];
	protected boolean ehCanto;
	
	public RenquePosicao(Posicao posicoes[], boolean ehCanto) {
		this.posicoes = posicoes;
		this.ehCanto = ehCanto;
	}
	
	public boolean avaliarCondicaoVitoria(Jogador jogador) {
		int contagem = 0;
		for(int i = 0;i< 4; i++) {
			if(posicoes[i].ocupadaPor(jogador)   ) {
				contagem ++;
			}			
		}
		if(contagem == 4) {
			return true;
		}else if (!ehCanto || !(posicoes[0].ocupadaPor(jogador)) ) {
			return false;
		}else {
			contagem = 0;
			for(int i = 1;i< 4; i++) {
				if(!posicoes[i].ocupadaPor(jogador) && posicoes[i].informarOcupada() ) {
					contagem ++ ;
				}
			}
			if(contagem == 3) {
				return true;
			}else {
				return false;
			}
		}
	}
}
