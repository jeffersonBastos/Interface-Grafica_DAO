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
	
	public boolean ehSeuTurno() {
		return false;
	}
	
	public boolean informarTurno() {
		return false;
	}
	
	public void iniciar() {
		
	}
	
	public void definirNome(String adversario) {
		
	}
	
	public void definirComoPrimeiro() {
		
	}
	
	public void inverterTurno() {
		
	}
	
	public String informarNome() {
		return null;
	}
	
	public boolean informarVencedor() {
		return false;
	}
	
	public boolean avaliarCondicaoVitoria(Jogador jogador) {
		return false;
	}
	

}
