package DominioDoProblema;

import DominioDoProblema.Posicao;


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
	public boolean avaliaMoviemtoLinha(int linhaAtual, int colunaAtual, int colunaAntiga, int diferencaColuna) {		    
		if(diferencaColuna > 0){
			//andou pra direita
			if (colunaAtual != 3){
				if(!this.posicoes[linhaAtual][(colunaAtual + 1)].informarOcupada() ){
		                return false;
		            }
		        }
		        for(int i = colunaAtual - 1; i != colunaAntiga; i--){
		            if(this.posicoes[linhaAtual][i].informarOcupada() ){
		                return false;
		            }
		        }
		        return true;
		    } else { //andou pra esquerda
		        if (colunaAtual != 0){
		        	if(!this.posicoes[linhaAtual][(colunaAtual - 1)].informarOcupada() ){
		        		return false;
		            }
		        }
		        for(int i = colunaAtual + 1; i != colunaAntiga; i++){
		        	if(this.posicoes[linhaAtual][i].informarOcupada() ){
		        		return false;
		            }
		        }     
		        return true;
	    }
	}
	public boolean avaliaMovimentoColuna(int colunaAtual, int linhaAtual,int linhaAntiga,int diferencaLinha) {
	    if(diferencaLinha > 0){
	        //andou para baixo
	        if (linhaAtual != 3){
	            if(!this.posicoes[(linhaAtual + 1)][colunaAtual].informarOcupada() ){
	                return false;
	            }
	        }
	        for(int i = linhaAtual - 1; i != linhaAntiga; i--){
	            if(this.posicoes[i][colunaAtual].informarOcupada() ){
	                return false;
	            }
	        }
	        return true;
	    } else{//andou pra cima
	        if (linhaAtual != 0){
	        	if(!this.posicoes[(linhaAtual - 1)][colunaAtual].informarOcupada() ){
	        		return false;
	            }
	        }
	        for(int i = linhaAtual + 1; i != linhaAntiga; i++){
	        	if(this.posicoes[i][colunaAtual].informarOcupada() ){
	        		return false;
	            }
	        }
	        return true;
	    }
	}
	public boolean avaliaMovimentoDiagonal(int linhaAtual, int linhaAntiga, int colunaAntiga, int colunaAtual, int diferencaLinha) {
        
		if(linhaAtual > linhaAntiga && colunaAtual > colunaAntiga){
            //Aumenta linha e coluna
            //direita pra baixo
            if( !(linhaAtual == 3 || colunaAtual == 3) ){ 
          		if(!this.posicoes[linhaAtual + 1][colunaAtual + 1].informarOcupada() ){
          			return false; 
          		}	
            }else if(diferencaLinha == 1){
            	return true;
            }else { 
            	for(int i = 1;!( (linhaAtual - i) == 0 || (colunaAtual - i) == 0); i++) {
            		if(this.posicoes[linhaAtual - i][colunaAtual - i].informarOcupada()){
                    	return false;
            		}
            	}
            }
            return true;               
		}else if(linhaAtual < linhaAntiga && colunaAtual < colunaAntiga){
		    //Diminui linha e coluna
		    // esquerda pra cima
			if(! ( linhaAtual == 0 || colunaAtual == 0) ){
				if(!this.posicoes[linhaAtual - 1][colunaAtual - 1].informarOcupada() ){
					return false;
		        }
		    }else if(diferencaLinha == -1){
		        return true;
		    }else {
		    	for(int i = 1;!( (linhaAtual + i) == 3 || (colunaAtual + i) == 3); i++) {
            		if(this.posicoes[linhaAtual + i][colunaAtual + i].informarOcupada()){
                    	return false;
            		}
		    	}	
		    }
		    return true;
		}else if(linhaAtual > linhaAntiga && colunaAtual < colunaAntiga){
			//Aumenta linha e diminui coluna
		    //Esquerda baixo
		    if(!  (linhaAtual == 3 || colunaAtual == 0) ){
		      	if(!this.posicoes[linhaAtual + 1][colunaAtual - 1].informarOcupada() ){
		       		return false;
		        }
		    }else if(diferencaLinha == 1){
		    	return true;
		    }else {
		    	for(int i = 1;!( (linhaAtual - i) == 0 || (colunaAtual + i) == 3); i++) {
		    		if(this.posicoes[linhaAtual - i][colunaAtual + i].informarOcupada()){
				    	return false;
		    		}
		    	}
		    }
		    return true;
		}else if(linhaAtual < linhaAntiga && colunaAtual > colunaAntiga){
			//Diminui linha  e aumenta coluna
			//direita pra cima
			if(! (linhaAtual == 3 || linhaAtual == 0 || colunaAtual == 3|| colunaAtual == 0) ){
				if(!this.posicoes[linhaAtual - 1][colunaAtual + 1].informarOcupada() ){
					return false;
			    }
			}
			if(diferencaLinha == -1){
				return true;
			}else {
		    	for(int i = 1;!( (linhaAtual + i) == 3 || (colunaAtual - i) == 0); i++) {
		    		if(this.posicoes[linhaAtual + i][colunaAtual - i].informarOcupada()){
				    	return false;
		    		}
		    	}
		    }
		    return true;
		}
		return false;
	}	
	public boolean avaliarMovimento(Posicao posicaoAntiga, Posicao posicaoAtual) {
		int linhaAntiga = posicaoAntiga.getLinha();
		int colunaAntiga = posicaoAntiga.getColuna();
		int linhaAtual = posicaoAtual.getLinha();
		int colunaAtual = posicaoAtual.getColuna();
		int diferencaColuna = colunaAtual - colunaAntiga;
	    int diferencaLinha = linhaAtual - linhaAntiga;

		if(linhaAtual == linhaAntiga && linhaAntiga == linhaAtual) {
			//NAO ANDOU
			return false;
		}else if(linhaAntiga == linhaAtual){
			//ANDOU EM LINHA		    
			return this.avaliaMoviemtoLinha(linhaAtual, colunaAtual, colunaAntiga, diferencaColuna);
		} else if(colunaAntiga == colunaAtual){
			//ANDOU EM COLUNA
			return this.avaliaMovimentoColuna(colunaAtual, linhaAtual, linhaAntiga, diferencaLinha);
		}else if( Math.abs(colunaAtual - colunaAntiga) == Math.abs(linhaAtual - linhaAntiga)  ){
			//ANDOU EM DIAGONAL
			return this.avaliaMovimentoDiagonal(linhaAtual, linhaAntiga, colunaAntiga, colunaAtual, diferencaLinha);
		}else {
			return false;
		}	
	}	
		
		
		
		
		
}
