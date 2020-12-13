package DominioDoProblema;

import DominioDoProblema.Posicao;


public class Tabuleiro {
	
	protected Posicao posicoes[][]; 
	protected Jogador jogadorLocal;
	protected Jogador jogadorRemoto;
	protected boolean partidaEmAndamento;
	protected boolean jogadaEmAndamento;
	
	public Tabuleiro () {
		posicoes =  new Posicao[4][4];
		this.inicia();
	}
	public void inicia() {
		for (int linha=0; linha<4; linha++) {
			for (int coluna=0; coluna<4; coluna++) {
				posicoes[linha][coluna] = new Posicao(linha, coluna);
			}
			
		}
	}
	
	public void efetuarMovimentoPedra(int linha, int coluna, Jogador jogador) {
		
	}
	
	public Posicao obterPosicao(int linha, int coluna) {
		return posicoes[linha][coluna];
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
		Posicao linha[] = new Posicao[4];
		for(int coluna = 0;coluna > 4; coluna ++) {
			linha[coluna] = posicoes[indice][coluna];	
		}
		RenquePosicao renqueLinha = new RenquePosicao(linha, false);
		return renqueLinha;
	}
	public RenquePosicao informarColuna(int indice) {
		Posicao coluna[] = new Posicao[4];
		for(int linha = 0;linha > 4; linha ++) {
			coluna[linha] = posicoes[linha][indice];	
		}
		RenquePosicao renqueLinha = new RenquePosicao(coluna, false);
		return renqueLinha;
	}
	public RenquePosicao informaCanto(int indiceCanto) {
		Posicao canto[] = new Posicao[4];
		switch (indiceCanto) {
		case 1:
			canto[0] = posicoes[0][0];
			canto[1] = posicoes[0][1];
			canto[2] = posicoes[1][1];
			canto[3] = posicoes[1][0];
			break;
		case 2:
			canto[0] = posicoes[0][3];
			canto[1] = posicoes[0][2];
			canto[2] = posicoes[1][2];
			canto[3] = posicoes[1][3];
			break;
		case 3:
			canto[0] = posicoes[3][0];
			canto[1] = posicoes[3][1];
			canto[2] = posicoes[2][1];
			canto[3] = posicoes[2][0];
			break;
		case 4:
			canto[0] = posicoes[3][3];
			canto[1] = posicoes[2][3];
			canto[2] = posicoes[2][2];
			canto[3] = posicoes[3][2];
			break;

		default:
			break;
		}
		RenquePosicao renqueLinha = new RenquePosicao(canto, true);
		return renqueLinha;	
	}
	public boolean avaliaMovimentoLinha(int linhaAtual, int colunaAtual, int colunaAntiga, int diferencaColuna) {		    
		
		
		
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
		
		System.out.println("RONALDO");
		
		int linhaAntiga = posicaoAntiga.getLinha();
		int colunaAntiga = posicaoAntiga.getColuna();
		int linhaAtual = posicaoAtual.getLinha();
		int colunaAtual = posicaoAtual.getColuna();
		int diferencaColuna = colunaAtual - colunaAntiga;
	    int diferencaLinha = linhaAtual - linhaAntiga;

		if(linhaAtual == linhaAntiga && linhaAntiga == linhaAtual) {
			//NAO ANDOU
			System.out.println("Ronaldo- nao andou");
			return false;
		}else if(linhaAntiga == linhaAtual){
			//ANDOU EM LINHA
			System.out.println("Ronaldo- andou linha");
			return this.avaliaMovimentoLinha(linhaAtual, colunaAtual, colunaAntiga, diferencaColuna);
		} else if(colunaAntiga == colunaAtual){
			//ANDOU EM COLUNA
			System.out.println("Ronaldo- andou coluna");
			return this.avaliaMovimentoColuna(colunaAtual, linhaAtual, linhaAntiga, diferencaLinha);
		}else if( Math.abs(colunaAtual - colunaAntiga) == Math.abs(linhaAtual - linhaAntiga)  ){
			//ANDOU EM DIAGONAL
			System.out.println("Ronaldo- andou diagona");
			return this.avaliaMovimentoDiagonal(linhaAtual, linhaAntiga, colunaAntiga, colunaAtual, diferencaLinha);
		}else {
			System.out.println("Ronaldo- caiu no ultimo else");
			return false;
		}	
	}	
		
		
		
		
		
}
