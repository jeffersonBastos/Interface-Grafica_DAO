package DominioDoProblema;


public class Jogador {
	protected String nome;
	protected int cor = 2;
	protected boolean seuTurno = false;
	protected boolean vencedor = false;
	
	public Jogador(String nome) {
		this.nome = nome;	
	}
	
	public void iniciar() {
		
	}
	public void setTurno() {
		seuTurno = true;
	}
	public boolean informarTurno() {
		return seuTurno;
	}
	
	public String getNome() {
		return nome;
	}
	
	
	public void setNome(String nome) {
		this.nome = nome;		
	}
	
	public void definirComoPrimeiro() {
		cor = 1;
		seuTurno = true;
		
	}
	public int getCor() {
		return cor;
	}
	public void inverterTurno() {
		if(seuTurno) {
			seuTurno = false;
		}else {
			seuTurno = true;
		}
	}
	
	public String informarNome() {
		return nome;
	}
	public void definirVencedor(boolean valor) {
		vencedor = valor;
	}
	public boolean informarVencedor() {
		return vencedor;
	}
	//tem q estar aqui RONALDO??
	public boolean avaliarCondicaoVitoria(Jogador jogador) {
		return false;
	}
	

}
