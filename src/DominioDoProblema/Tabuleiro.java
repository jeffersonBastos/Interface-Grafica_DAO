package DominioDoProblema;

import DominioDoProblema.Posicao;


public class Tabuleiro {
	
	protected Posicao posicoes[][] = new Posicao[4][4];; 
	protected Jogador jogadorLocal;
	protected Jogador jogadorRemoto;
	protected boolean partidaEmAndamento = false;
	protected EstadoDao estado;
	
	public Tabuleiro () {
		this.inicia();
	}
	
	public void inicia() {
		partidaEmAndamento = false;

		for (int linha=0; linha<4; linha++) {
			for (int coluna=0; coluna<4; coluna++) {
				posicoes[linha][coluna] = new Posicao(linha, coluna);
			}	
		}

		posicoes[3][0].definirOcupante(jogadorLocal); 
		posicoes[2][1].definirOcupante(jogadorLocal);
		posicoes[1][2].definirOcupante(jogadorLocal);
		posicoes[0][3].definirOcupante(jogadorLocal);

		posicoes[0][0].definirOcupante(jogadorRemoto);
		posicoes[1][1].definirOcupante(jogadorRemoto);
		posicoes[2][2].definirOcupante(jogadorRemoto);
		posicoes[3][3].definirOcupante(jogadorRemoto);
	
	}
	
	public void efetuarMovimentoPedra(Posicao posicaoAntiga, Posicao posicaoAtual) {
		
		posicaoAtual.definirOcupante(posicaoAntiga.getOcupante());
		posicaoAntiga.definirOcupante(null);
		
		this.avaliarEncerramentoPartida();
		
		Lance lance = new Lance();
		
		lance.definirPosicaoAntiga(posicaoAntiga.getLinha(),posicaoAntiga.getColuna());
		lance.definirPosicaoAtual(posicaoAtual.getLinha(), posicaoAtual.getColuna());
		
		this.atualizarEstado(lance);
		
	}
	
	public Posicao obterPosicao(int linha, int coluna) {
		return posicoes[linha][coluna];
	}
	
	public String movimentarPedra(int linhaAntiga, int colunaAntiga, int linhaAtual, int colunaAtual) {
		String notificacao = "";
		Posicao posicaoAntiga = this.obterPosicao(linhaAntiga, colunaAntiga);
		Posicao posicaoAtual = this.obterPosicao(linhaAtual, colunaAtual);
		
		if (partidaEmAndamento) {
			boolean turno = jogadorLocal.informarTurno();
			if (turno) {
				if(avaliarMovimento(posicaoAntiga, posicaoAtual)) {
					this.efetuarMovimentoPedra(posicaoAntiga, posicaoAtual);
				} else {
					notificacao = "Movimento não e valido";
				}
		    } else {
				notificacao = "Nao e seu turno";
			}
		} else {
			notificacao = "Nao ha partida em andamento";
		}
		return notificacao;	
	}
	
	public EstadoDao informarEstado() {
		return estado;
	}
	
	public void iniciarNovaPartida(Integer ordem, String adversario) {
        this.jogadorLocal.iniciar();
        this.jogadorRemoto = new Jogador();
        this.jogadorRemoto.definirNome(adversario);
        if (ordem.equals(1)) {
            this.jogadorLocal.definirComoPrimeiro();
        } else {
            this.jogadorRemoto.definirComoPrimeiro();
        }

        this.partidaEmAndamento = true;
        this.definirEstadoInicial();
		this.esvaziar();

	}
	
	public void esvaziar() {
		this.inicia();
	}

	public EstadoDao informarEstado(Lance lance) {
		return estado;
	}
	
	public boolean encerrarHavendoPartida() {
		if (partidaEmAndamento) {
			this.encerrarPartidaLocalmente();
			return true;
		} else return false;	
	}
		
	public void definirEstadoInicial() {
	        this.estado = new EstadoDao();
	        if (this.jogadorLocal.informarTurno()) {
	            this.estado.assumirMensagem("Vez de " + this.jogadorLocal.informarNome());
	        } else {
	            this.estado.assumirMensagem("Vez de " + this.jogadorRemoto.informarNome());
	        }

	        this.definirPartidaEmAndamento(true);
	}
	
	public void encerrarPartidaLocalmente() {
		 this.esvaziar();
		 this.jogadorLocal.iniciar();
		 this.jogadorRemoto = new Jogador();
		 this.jogadorRemoto.iniciar();
		 this.estado = new EstadoDao();
	}

	public boolean encerrarPartida() {	
		if (partidaEmAndamento) {
			this.encerrarPartidaLocalmente();
			return true;
		} else return false;
	}
	
	public void receberJogada(Lance lance) {
	    int linhaAntiga = lance.informarLinhaAntiga();
	    int colunaAntiga = lance.informarColunaAntiga();
	    int colunaAtual = lance.informarColunaAtual();
	    int linhaAtual  = lance.informarLinhaAtual();
	    
	    Posicao posicaoAntiga = posicoes[linhaAntiga][colunaAntiga];
	    Posicao posicaoAtual = posicoes[linhaAtual][colunaAtual];
	    
		
		this.efetuarMovimentoPedra(posicaoAntiga, posicaoAtual);
	        		
	}
	
	public void registrarJogadorLocal(String jogador) {
	        this.jogadorLocal = new Jogador();
	        this.jogadorLocal.definirNome(jogador);
	        this.jogadorLocal.iniciar();
	}
	
	public void assumirEstado(EstadoDao estado) {
		this.estado = estado;
	}
	
	public void atualizarEstado(Lance lance) {
		EstadoDao novoEstado = new EstadoDao(lance);
		String mensagem;
		if (partidaEmAndamento) {
			jogadorLocal.inverterTurno();
			jogadorRemoto.inverterTurno();
			boolean turno = jogadorLocal.informarTurno();
			String nome = jogadorRemoto.informarNome();
			if (turno) nome = jogadorLocal.informarNome();
			mensagem = "Vez de "+nome;
		} else {
			boolean localVencedor = jogadorLocal.informarVencedor();
			boolean remotoVencedor = jogadorRemoto.informarVencedor();
			if (localVencedor || remotoVencedor) {
				String nome = jogadorRemoto.informarNome();
				if (localVencedor) nome = jogadorLocal.informarNome();
				mensagem = "VENCEDOR: "+nome;
			} else {
				mensagem = "Partida encerrada sem vencedor";
			}
		}
		novoEstado.assumirMensagem(mensagem);	
		for (int linha=0; linha<4; linha++) {
			for (int coluna=0; coluna<4; coluna++) {
				int valor = this.informarValor(linha, coluna);
				novoEstado.assumirValorTabuleiro(linha, coluna, valor);
			}
		}
		this.assumirEstado(novoEstado);
		
	}
	
	public int informarValor(int linha, int coluna) {
		int valor;
		Posicao posicao = posicoes[linha][coluna];
		boolean ocupada = posicao.informarOcupada();
		if (ocupada) {
			Jogador jogador = posicao.getOcupante();
			valor = jogador.getCor();
		} else {
			valor = 0;
		}
		return valor;
	}
	
	public void definirPartidaEmAndamento(boolean valor) {	
		partidaEmAndamento = valor;
	}
	
	public void avaliarEncerramentoPartida() {
		Jogador jogadorTurno;
		Jogador adversario;
		boolean adversarioVencedor = false;
		boolean registroTurnoVencedor = false;
		RenquePosicao auxLinha;
		RenquePosicao auxColuna;
		RenquePosicao auxCanto;
		
		if (jogadorLocal.informarTurno()) {
			jogadorTurno = jogadorLocal;
			adversario = jogadorRemoto;
		} else {
			jogadorTurno = jogadorRemoto;
			adversario = jogadorLocal;		
		}
		
		for (int indice=0; indice<4; indice++) {		// avaliacao de linhas
			
			auxLinha = this.informarLinha(indice);
			
			if (!adversarioVencedor) {
				adversarioVencedor = auxLinha.avaliarCondicaoVitoria(adversario);
			}
			if (!adversarioVencedor) { 
				if (!registroTurnoVencedor) {
					registroTurnoVencedor = auxLinha.avaliarCondicaoVitoria(jogadorTurno);
				}
			}
		}
		if (!adversarioVencedor) {
			for (int indice=0; indice<4; indice++) {	// avaliacao de colunas
				auxColuna = this.informarColuna(indice);
				if (!adversarioVencedor) {
					adversarioVencedor = auxColuna.avaliarCondicaoVitoria(adversario);
				}
				if (!adversarioVencedor) { 
					if (!registroTurnoVencedor) {
						registroTurnoVencedor = auxColuna.avaliarCondicaoVitoria(jogadorTurno);
					}
				}
			}
		}
		if (!adversarioVencedor) {
			for (int indice=0; indice<4; indice++) {	// avaliacao de cantos
				auxCanto = this.informarCanto(indice);
				if (!adversarioVencedor) {
					adversarioVencedor = auxCanto.avaliarCondicaoVitoria(adversario);
				}
				if (!adversarioVencedor) 
					if (!registroTurnoVencedor) registroTurnoVencedor = auxCanto.avaliarCondicaoVitoria(jogadorTurno);
			}
		}
		if (adversarioVencedor) {		// verificacao de existencia de vencedor
			adversario.definirVencedor(true);
			this.definirPartidaEmAndamento(false);
		} else {
			if (registroTurnoVencedor) {
				jogadorTurno.definirVencedor(true);
				this.definirPartidaEmAndamento(false);
			}
		}
		
	}
	
	public RenquePosicao informarLinha(int indice) {
		Posicao linha[] = new Posicao[4];
		for(int coluna = 0;coluna < 4; coluna ++) {
			linha[coluna] = posicoes[indice][coluna];	
		}
		RenquePosicao renqueLinha = new RenquePosicao(linha, false);
		return renqueLinha;
	}
	
	public RenquePosicao informarColuna(int indice) {
		Posicao coluna[] = new Posicao[4];
		for(int linha = 0;linha < 4; linha ++) {
			coluna[linha] = posicoes[linha][indice];	
		}
		RenquePosicao renqueLinha = new RenquePosicao(coluna, false);
		return renqueLinha;
	}
	
	public RenquePosicao informarCanto(int indiceCanto) {
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
			return this.avaliaMovimentoLinha(linhaAtual, colunaAtual, colunaAntiga, diferencaColuna);
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
