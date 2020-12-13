package DominioDoProblema;


public class Jogador {
	protected String nome;
	protected int cor;
	protected boolean seuTurno;
	protected boolean vencedor;
	
	public Jogador(String nome, int cor, boolean seuTurno, boolean vencedor) {
		this.nome = nome;
		this.cor = cor;
		this.seuTurno = seuTurno;
		this.vencedor = vencedor;
	}
	
	
	public boolean informarTurno() {
		return seuTurno;
	}
	
	public void iniciar() {
		
	}
	
	public void definirNome(String adversario) {
		
	}
	
	public void definirComoPrimeiro() {
		
	}
	public int getCor() {
		return cor;
	}
	public void inverterTurno() {
		if(seuTurno == true) {
			seuTurno = false;
		}else {
			seuTurno = true;
		}
	}
	
	public String informarNome() {
		return nome;
	}
	
	public boolean informarVencedor() {
		return vencedor;
	}
	
	public boolean avaliarCondicaoVitoria(Jogador jogador) {
		return false;
	}
	

}
