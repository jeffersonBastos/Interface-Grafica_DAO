package InterfaceGrafica;

import java.awt.Color;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	public void move(JButton button) {
		if(primeiraAcao) {
			removePeca(button);
		}else {
			putPeca(button);
		}
		
				
	} 
	public void removePeca(JButton button) {
		if(button.getIcon().equals(pecaBranca)) {
			button.setIcon(posicaoVazia);
			primeiraAcao = false;
			pecaBrancaSelecionada = true;
		} else if (button.getIcon().equals(pecaPreta)) {
			button.setIcon(posicaoVazia);
			primeiraAcao = false;
			pecaBrancaSelecionada = false;
		}		
	} 
	public void putPeca(JButton button) {
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
	
	
	/**
	 * Create the application.
	 */
	public InterfaceJogo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		atorJogador = new AtorJogador();
		
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
		
		
		JButton button_11 = new JButton("");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_11);
			}
		});
		button_11.setHideActionText(true);
		button_11.setIcon(pecaPreta);
		button_11.setBounds(10, 32, 80, 80);
		frame.getContentPane().add(button_11);
		
		JButton button_21 = new JButton("");
		button_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_21);
			}
		});
		button_21.setIcon(posicaoVazia);
		button_21.setBounds(10, 114, 80, 80);
		frame.getContentPane().add(button_21);
		
		JButton button_31 = new JButton("");
		button_31.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_31);
			}
		});
		button_31.setIcon(posicaoVazia);
		button_31.setBounds(10, 196, 80, 80);
		frame.getContentPane().add(button_31);
		
		JButton button_22 = new JButton("");
		button_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_22);
			}
		});
		button_22.setIcon(pecaPreta);
		button_22.setBounds(92, 114, 80, 80);
		frame.getContentPane().add(button_22);
		
		JButton button_12 = new JButton("");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_12);
			}
		});
		button_12.setIcon(posicaoVazia);
		button_12.setBounds(92, 32, 80, 80);
		frame.getContentPane().add(button_12);
		
		JButton button_32 = new JButton("");
		button_32.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_32);
			}
		});
		button_32.setIcon(pecaBranca);
		button_32.setBounds(92, 196, 80, 80);
		frame.getContentPane().add(button_32);
		
		JButton button_23 = new JButton("");
		button_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_23);
			}
		});
		button_23.setIcon(pecaBranca);
		button_23.setBounds(174, 114, 80, 80);
		frame.getContentPane().add(button_23);
		
		JButton button_33 = new JButton("");
		button_33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_33);
			}
		});
		button_33.setIcon(pecaPreta);
		button_33.setBounds(174, 196, 80, 80);
		frame.getContentPane().add(button_33);
		
		JButton button_13 = new JButton("");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_13);
			}
		});
		button_13.setIcon(posicaoVazia);
		button_13.setBounds(174, 32, 80, 80);
		frame.getContentPane().add(button_13);
		
		JButton button_14 = new JButton("");
		button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_14);
			}
		});
		button_14.setIcon(pecaBranca);
		button_14.setBounds(256, 32, 80, 80);
		frame.getContentPane().add(button_14);
		
		JButton button_24 = new JButton("");
		button_24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_24);
			}
		});
		button_24.setIcon(posicaoVazia);
		button_24.setBounds(256, 114, 80, 80);
		frame.getContentPane().add(button_24);
		
		JButton button_34 = new JButton("");
		button_34.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_34);			
			}
		});
		button_34.setIcon(posicaoVazia);
		button_34.setBounds(256, 196, 80, 80);
		frame.getContentPane().add(button_34);
		
		JButton button_41 = new JButton("");
		button_41.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_41);
			}
		});
		button_41.setIcon(pecaBranca);
		button_41.setBounds(10, 278, 80, 80);
		frame.getContentPane().add(button_41);
		
		JButton button_42 = new JButton("");
		button_42.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_42);
			}
		});
		button_42.setIcon(posicaoVazia);
		button_42.setBounds(92, 278, 80, 80);
		frame.getContentPane().add(button_42);
		
		JButton button_43 = new JButton("");
		button_43.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move(button_43);
			}
		});
		button_43.setIcon(posicaoVazia);
		button_43.setBounds(174, 278, 80, 80);
		frame.getContentPane().add(button_43);
		
		JButton button_44 = new JButton("");
		button_44.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move( button_44);
			}
		});
		button_44.setIcon(pecaPreta);
		button_44.setBounds(256, 278, 80, 80);
		frame.getContentPane().add(button_44);
		
		JPanel panelFundoTabuleiro = new JPanel();
		panelFundoTabuleiro.setBackground(Color.BLACK);
		panelFundoTabuleiro.setBounds(10, 32, 326, 326);
		frame.getContentPane().add(panelFundoTabuleiro);
		
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
			// Necessário definir endereço do servidor e nome do jogador
			String mensagem = atorJogador.conectar("localhost", "nomeJogador?");
			JOptionPane.showMessageDialog(null, mensagem);
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
			String mensagem = atorJogador.desconectar();
			JOptionPane.showMessageDialog(null, mensagem);
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
			String mensagem = atorJogador.iniciarPartida();
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}
}
