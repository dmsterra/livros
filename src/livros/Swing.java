package livros;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Swing{

	private JFrame frame;
	private JTextField textNome;
	private JTextField textEdicao;
	private JTextField textPreco;
	private JTextField textID;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Livros livros = new Livros();
					livros.igualaID();
					Swing window = new Swing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Swing() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 696, 439);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Loja de livros");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(234, 11, 210, 55);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(150, 66, 330, 182);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 31, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edição");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 83, 46, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Preço");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 140, 46, 14);
		panel.add(lblNewLabel_1_2);
		
		textNome = new JTextField();
		textNome.setBounds(108, 30, 183, 20);
		panel.add(textNome);
		textNome.setColumns(10);
		
		textEdicao = new JTextField();
		textEdicao.setColumns(10);
		textEdicao.setBounds(108, 82, 183, 20);
		panel.add(textEdicao);
		
		textPreco = new JTextField();
		textPreco.setColumns(10);
		textPreco.setBounds(108, 139, 183, 20);
		panel.add(textPreco);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Pesquisar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(150, 316, 330, 73);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("ID do livro");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1.setBounds(24, 32, 72, 14);
		panel_1.add(lblNewLabel_1_2_1);
		
		textID = new JTextField();
		textID.setColumns(10);
		textID.setBounds(126, 31, 163, 20);
		panel_1.add(textID);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(150, 270, 100, 35);
		frame.getContentPane().add(btnSalvar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(270, 270, 90, 35);
		frame.getContentPane().add(btnSair);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(380, 270, 100, 35);
		frame.getContentPane().add(btnLimpar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(511, 146, 126, 55);
		frame.getContentPane().add(btnAtualizar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setBounds(511, 230, 126, 55);
		frame.getContentPane().add(btnDeletar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(511, 62, 126, 55);
		frame.getContentPane().add(btnConsultar);
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Livros livros = new Livros();
				int edicao = Integer.parseInt(textEdicao.getText());
				String nome = textNome.getText().trim();
				double preco = Double.parseDouble(textPreco.getText());
				if (nome.length()==0) {
					JOptionPane.showMessageDialog(panel, "Preencha o campo nome!");
					textNome.requestFocus();
				}
				else if(preco == 0) {
							JOptionPane.showMessageDialog(panel, "Preencha o campo preço!");
							textPreco.requestFocus();
				}
				if(!livros.cadastrarProduto(nome, edicao, preco)) {
					JOptionPane.showMessageDialog(panel, "Erro no Cadastro!");
				}
				else {
					JOptionPane.showMessageDialog(panel, "Cadastro do livro realizado com sucesso!");
				}
			}
		});
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textEdicao.setText(""); 
				textPreco.setText(""); 
				textNome.setText(""); 
				textID.setText("");
				textEdicao.setEnabled(true);
				textPreco.setEnabled(true);
				textNome.setEnabled(true);
				textID.setEnabled(true);
				btnConsultar.setEnabled(true);
				btnSalvar.setEnabled(true);
			}
		});
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Livros livros = new Livros();
				int id = Integer.parseInt(textID.getText());
				if(!livros.consultarLivraria(id)) {
					JOptionPane.showMessageDialog(panel, "Livro não existe ou não encontrado.");
				}
				else {
					if(livros.deletarLivro(id));
					JOptionPane.showMessageDialog(panel, "Livro removido!");
				}
			}
		});
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Livros livros = new Livros();
				int id = Integer.parseInt(textID.getText());
				int edicao = Integer.parseInt(textEdicao.getText());
				String nome = textNome.getText().trim();
				double preco = Double.parseDouble(textPreco.getText());
				if (nome.length()==0) {
					JOptionPane.showMessageDialog(panel, "Preencha o campo nome!");
					textNome.requestFocus();
				}
				else if(preco == 0) {
							JOptionPane.showMessageDialog(panel, "Preencha o campo preço!");
							textPreco.requestFocus();
				}
				if(!livros.atualizarLivro(id, edicao, preco, nome)) {
					JOptionPane.showMessageDialog(panel, "Erro na Atualização!");
				}
				else {
					JOptionPane.showMessageDialog(panel, "Atualização do livro realizado com sucesso!");
				}
			}
		});
		
		btnConsultar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	Livros livros = new Livros();
	        	try {
					int id = Integer.parseInt(textID.getText());
					String nome;
					int edicao=0;
					double preco=0;
					if (!livros.consultarLivraria(id))
						nome = "";
					else {
						nome = livros.getNome();
						edicao = livros.getEdicao();
						preco = livros.getPreco();
						id = livros.getId();
					}
					textNome.setText(nome);
					textEdicao.setText(String.valueOf(edicao));
					textPreco.setText(String.valueOf(preco));
					textID.setText(String.valueOf(id));
					textID.setEnabled(false);
					textNome.setEnabled(false);
					btnConsultar.setEnabled(false);
					textEdicao.setEnabled(false);
					textPreco.setEnabled(false);
					textNome.requestFocus();
					textID.requestFocus();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(panel,
							"Preencha os campos corretamente!!");
				}
	        }
	    });
	}
}
