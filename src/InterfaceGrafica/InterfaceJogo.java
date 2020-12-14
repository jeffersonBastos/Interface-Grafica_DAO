package InterfaceGrafica;

import java.awt.Color;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DominioDoProblema.EstadoDao;
import DominioDoProblema.Tabuleiro;

import javax.swing.JMenu;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionListener;

public class InterfaceJogo {

	private JFrame frame;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	
	private final ImageIcon pecaPreta = new ImageIcon(InterfaceJogo.class.getResource("/resources/posicao peca preta.jpg"));
	private final ImageIcon posicaoVazia = new ImageIcon(InterfaceJogo.class.getResource("/resources/posicao imagem.jpg"));
	private final ImageIcon pecaBranca = new ImageIcon(InterfaceJogo.class.getResource("/resources/posicao peca branca.jpg"));
	
	private AtorJogador atorJogador;
	private Boolean primeiraAcao = true;
	private Boolean pecaBrancaSelecionada;
	
	private JLabel labelMensagem = null;

	JButton button_00 = new JButton("");
	JButton button_10 = new JButton("");
	JButton button_20 = new JButton("");
	JButton button_30 = new JButton("");

	JButton button_01 = new JButton("");
	JButton button_11 = new JButton("");
	JButton button_21 = new JButton("");
	JButton button_31 = new JButton("");

	JButton button_02 = new JButton("");
	JButton button_12 = new JButton("");
	JButton button_22 = new JButton("");
	JButton button_32 = new JButton("");

	JButton button_03 = new JButton("");
	JButton button_13 = new JButton("");
	JButton button_23 = new JButton("");
	JButton button_33 = new JButton("");

	JButton matrizBotoes[][] = new JButton[4][4];
	
	int linhaAntiga;
	int colunaAntiga;
	int linhaAtual;
	int colunaAtual;
	
	private Tabuleiro tabuleiro = new Tabuleiro();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceJogo window = new InterfaceJogo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public InterfaceJogo() {
		initialize();
	}

	private void initialize() {
		atorJogador = new AtorJogador(this);
				
		frame = new JFrame();
		frame.setBounds(100, 100, 642, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 626, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Jogo");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmConectar = new JMenuItem("conectar");
		mntmConectar.setAction(action);
		mnNewMenu.add(mntmConectar);
		
		JMenuItem mntmDesconectar = new JMenuItem("desconectar");
		mntmDesconectar.setAction(action_1);
		mnNewMenu.add(mntmDesconectar);
		
		JMenuItem mntmIniciarPartida = new JMenuItem("iniciar partida");
		mntmIniciarPartida.setAction(action_2);
		mnNewMenu.add(mntmIniciarPartida);
		
		criarMatrizTabuleiro();
		exibirRegras();
		
		JPanel panelFundoTabuleiro = new JPanel();
		panelFundoTabuleiro.setBackground(Color.BLACK);
		panelFundoTabuleiro.setBounds(10, 32, 326, 326);
		frame.getContentPane().add(panelFundoTabuleiro);
		
	}
	public void exibirRegras() {

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(InterfaceJogo.class.getResource("/resources/JOGO-DAO.png")));
		lblNewLabel.setBounds(380, 21, 168, 32);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("REGRAS-");
		lblNewLabel_1.setBounds(346, 57, 236, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("- Jogadas alternadas entre os jogadores");
		lblNewLabel_2.setBounds(346, 75, 271, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("<html>- As peças podem se movimentar em qualquer <br/> direção.</html>");
		lblNewLabel_3.setBounds(346, 92, 271, 26);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("<html>- Após escolher uma direção, a peça deve <br/> andar o máximo possível.</html>");
		lblNewLabel_4.setBounds(346, 120, 271, 26);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblComoGanhar = new JLabel("COMO GANHAR-");
		lblComoGanhar.setBounds(346, 155, 236, 21);
		frame.getContentPane().add(lblComoGanhar);
		
		JLabel lblNewLabel_5 = new JLabel("1 - Ocupar as 4 casas dos cantos do tabuleiro;");
		lblNewLabel_5.setBounds(346, 178, 271, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("<html> 2 - Formar um quadrado pequeno em <br/> qualquer parte do tabuleiro;</html>");
		lblNewLabel_6.setBounds(346, 196, 271, 26);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("3 - Alinhamento vertical das pe\u00E7as;");
		lblNewLabel_7.setBounds(346, 226, 271, 14);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("<html>4 - Alinhamento horizontal das peças; <br/>             (alinhamento diagonal NÃO É PERMITIDO!);</html>");
		lblNewLabel_8.setBounds(346, 244, 271, 26);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("<html>5 - Se alguma peça que esteja no canto for <br/> bloqueada por 3 peças do adversário, quem foi bloqueado ganha a partida.</html>");
		lblNewLabel_9.setBounds(346, 272, 271, 51);
		frame.getContentPane().add(lblNewLabel_9);		
	}

	public void criarMatrizTabuleiro() {

		button_00.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_00, 1, 1);
			}
		});
		
		button_00.setHideActionText(true);
		button_00.setIcon(pecaPreta);
		button_00.setBounds(10, 32, 80, 80);
		frame.getContentPane().add(button_00);
		
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_10,1,0);
			}
		});
		
		
		button_10.setIcon(posicaoVazia);
		button_10.setBounds(10, 114, 80, 80);
		frame.getContentPane().add(button_10);
		
		button_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_20,2,0);
			}
		});
		button_20.setIcon(posicaoVazia);
		button_20.setBounds(10, 196, 80, 80);
		frame.getContentPane().add(button_20);
		
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_11,1,1);
			}
		});
		button_11.setIcon(pecaPreta);
		button_11.setBounds(92, 114, 80, 80);
		frame.getContentPane().add(button_11);
		
		button_01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_01,0,1);
			}
		});
		button_01.setIcon(posicaoVazia);
		button_01.setBounds(92, 32, 80, 80);
		frame.getContentPane().add(button_01);
		
		button_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_21,2,1);
			}
		});
		button_21.setIcon(pecaBranca);
		button_21.setBounds(92, 196, 80, 80);
		frame.getContentPane().add(button_21);
		
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_12,1,2);
			}
		});
		button_12.setIcon(pecaBranca);
		button_12.setBounds(174, 114, 80, 80);
		frame.getContentPane().add(button_12);
		
		button_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_22,2,2);
			}
		});
		button_22.setIcon(pecaPreta);
		button_22.setBounds(174, 196, 80, 80);
		frame.getContentPane().add(button_22);
		
		button_02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_02,0,2);
			}
		});
		button_02.setIcon(posicaoVazia);
		button_02.setBounds(174, 32, 80, 80);
		frame.getContentPane().add(button_02);
		
		button_03.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_03,0,3);
			}
		});
		button_03.setIcon(pecaBranca);
		button_03.setBounds(256, 32, 80, 80);
		frame.getContentPane().add(button_03);
		
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_13,1,3);
			}
		});
		button_13.setIcon(posicaoVazia);
		button_13.setBounds(256, 114, 80, 80);
		frame.getContentPane().add(button_13);
		
		button_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_23,2,3);			
			}
		});
		button_23.setIcon(posicaoVazia);
		button_23.setBounds(256, 196, 80, 80);
		frame.getContentPane().add(button_23);
		
		button_30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_30,3,0);
			}
		});
		button_30.setIcon(pecaBranca);
		button_30.setBounds(10, 278, 80, 80);
		frame.getContentPane().add(button_30);
		
		button_31.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_31,3,1);
			}
		});
		button_31.setIcon(posicaoVazia);
		button_31.setBounds(92, 278, 80, 80);
		frame.getContentPane().add(button_31);
		
		button_32.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_32,3,2);
			}
		});
		button_32.setIcon(posicaoVazia);
		button_32.setBounds(174, 278, 80, 80);
		frame.getContentPane().add(button_32);
		
		button_33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move( button_33,3,3);
			}
		});
		button_33.setIcon(pecaPreta);
		button_33.setBounds(256, 278, 80, 80);
		frame.getContentPane().add(button_33);
		
		matrizBotoes[0][0] = button_00;
		matrizBotoes[0][1] = button_01;
		matrizBotoes[0][2] = button_02;
		matrizBotoes[0][3] = button_03;

		matrizBotoes[1][0] = button_10;
		matrizBotoes[1][1] = button_11;
		matrizBotoes[1][2] = button_12;
		matrizBotoes[1][3] = button_13;
					
		matrizBotoes[2][0] = button_20;
		matrizBotoes[2][1] = button_21;
		matrizBotoes[2][2] = button_22;
		matrizBotoes[2][3] = button_23;
				
		matrizBotoes[3][0] = button_30;
		matrizBotoes[3][1] = button_31;
		matrizBotoes[3][2] = button_32;
		matrizBotoes[3][3] = button_33;
		
	}
	
	public void exibirTabuleiro(EstadoDao estado) {
		
		int valor = 0;
		
		for (int linha=0; linha<4; linha++){
			for (int coluna=0; coluna<4; coluna++){
				valor = estado.informarValorTabuleiro(linha, coluna);
				switch (valor){
				case 0: matrizBotoes[(linha)][(coluna)].setIcon(posicaoVazia);
				break;
				case 1: matrizBotoes[(linha)][(coluna)].setIcon(pecaBranca);
				break;
				case 2: matrizBotoes[(linha)][(coluna)].setIcon(pecaPreta);
				}
			};
		};		
		
	}
	
	public void exibirEstado() {
		EstadoDao estado;
		estado = atorJogador.informarEstado();
		this.exibirMensagem(estado.informarMensagem());
		this.exibirTabuleiro(estado);
	}
	private void exibirMensagem(String mensagem) {
		labelMensagem.setText(mensagem);
	}	
	
	public void move(JButton button, int linha, int coluna) {
		if(primeiraAcao) {
			System.out.println("primeira açao");
			removePeca(button);
			linhaAntiga = linha;
			colunaAntiga = coluna;
		}else {
			System.out.println("2 ação");
			putPeca(button, linhaAntiga, colunaAntiga, linha, coluna);
			linhaAtual = linha;
			colunaAtual = coluna;
		}
		
				
	} 
	public void removePeca(JButton button) {
		if(button.getIcon().equals(pecaBranca)) {
			button.setIcon(posicaoVazia);
			primeiraAcao = false;
			pecaBrancaSelecionada = true;
			System.out.println("RODALDO");
		} else if (button.getIcon().equals(pecaPreta)) {
			button.setIcon(posicaoVazia);
			primeiraAcao = false;
			pecaBrancaSelecionada = false;
			System.out.println("RONALDO");
		}		
	} 
	public void putPeca(JButton button, int linhaAntiga, int colunaAntiga, int linhaAtual, int colunaAtual) {
		System.out.println( "resultado-"+tabuleiro.avaliarMovimento(tabuleiro.obterPosicao(linhaAntiga, colunaAntiga), tabuleiro.obterPosicao(linhaAtual, colunaAtual)));
		System.out.println("RONALDO");


		if(button.getIcon().equals(posicaoVazia)) {
			
			if(pecaBrancaSelecionada) {
									
				
				button.setIcon(pecaBranca);
				primeiraAcao = true;
				
			} else {
				button.setIcon(pecaPreta);
				primeiraAcao = true;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Casa ocupada");
		}
	} 
	
	public String obterNomeJogador() {
		String nome = JOptionPane.showInputDialog("Qual o seu nome?");
		return nome;
	}
	
	public String obterEnderecoServidor() {
		String idServidor = ("localhost");
		idServidor = JOptionPane.showInputDialog(null, "Insira o endere�o do servidor", idServidor);
		return idServidor;
	}
	
	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "conectar");
			putValue(SHORT_DESCRIPTION, "conectar a Netgames Server");
		}
		public void actionPerformed(ActionEvent e) {
			atorJogador.conectar();
		}
	}
	
	private class SwingAction_1 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_1() {
			putValue(NAME, "desconectar");
			putValue(SHORT_DESCRIPTION, "desconectar de Netgames Server");
		}
		public void actionPerformed(ActionEvent e) {
			boolean atualizarInterface = atorJogador.desconectar();
			if (atualizarInterface) exibirEstado();
		}
	}
	private class SwingAction_2 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_2() {
			putValue(NAME, "iniciar partida");
			putValue(SHORT_DESCRIPTION, "iniciar partida do seu jogo");
		}
		public void actionPerformed(ActionEvent e) {
			boolean atualizarInterface = atorJogador.iniciarPartida();
			if (atualizarInterface) exibirEstado();
		}
	}
	

	public void notificar(String notificacao) {
		JOptionPane.showMessageDialog(null, notificacao);
	}
	








}