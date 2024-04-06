package com.hplat.view;

import java.util.Scanner;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hplat.controller.ChangeFontSizeListener;
import com.hplat.controller.ChangeTextColorListener;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextArea textArea;
	public JSpinner spinner;
	private JTextField pathTxt;
	public JTree tree;
	private JScrollPane scrollPane_1;
	private JButton saveBtn;
	private JLabel fileTitleLbl;
	public JTextField fileTitleTxt;

	/**
	 * Launch the application.
	 */

	public Dashboard() {
		init();
		this.setTitle("hplat Editor");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	void init() {
		// ---Listener---//
		ChangeFontSizeListener changeFontSizeListenner = new ChangeFontSizeListener(this);
		ChangeTextColorListener changeTextColorListener = new ChangeTextColorListener(this);
		// --------------//
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 812, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(191, 142, 553, 275);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);
		
				textArea = new JTextArea();
				scrollPane.setViewportView(textArea);
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				textArea.setFont(new Font("Roboto", Font.PLAIN, 20));

		spinner = new JSpinner();
		spinner.setBounds(235, 78, 63, 28);
		spinner.setValue(20);
		spinner.addChangeListener(changeFontSizeListenner);
		contentPane.add(spinner);

		JLabel changeColorLbl = new JLabel("Change Color");
		changeColorLbl.setHorizontalAlignment(SwingConstants.CENTER);
		changeColorLbl.setBackground(new Color(0, 0, 0));
		changeColorLbl.setForeground(Color.white);
		changeColorLbl.setOpaque(true);
		changeColorLbl.setBounds(644, 74, 89, 34);
		changeColorLbl.addMouseListener(changeTextColorListener);
		contentPane.add(changeColorLbl);

		JLabel lblNewLabel = new JLabel("Font Size:");
		lblNewLabel.setBounds(180, 77, 63, 28);
		contentPane.add(lblNewLabel);

		JLabel pathLbl = new JLabel("Path");
		pathLbl.setBounds(180, 29, 54, 28);
		contentPane.add(pathLbl);

		pathTxt = new JTextField();
		pathTxt.setBounds(238, 29, 378, 28);
		contentPane.add(pathTxt);
		pathTxt.setColumns(10);

		JButton openFileBtn = new JButton("Open Folder");
		openFileBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

				int result = fileChooser.showOpenDialog(contentPane);
				if (result == JFileChooser.APPROVE_OPTION) {
					String path = fileChooser.getSelectedFile().getAbsolutePath();
					pathTxt.setText(path);
					listAllFile(path);
				}
			}
		});
		openFileBtn.setBounds(626, 26, 107, 34);
		contentPane.add(openFileBtn);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 133, 475);
		contentPane.add(scrollPane_1);

		tree = new JTree();
		scrollPane_1.setViewportView(tree);
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				// Lấy đường dẫn của nút đã chọn
				TreePath path = e.getPath();
				// Lấy tên của nút cuối cùng (tên của tệp)
				String filename = path.getLastPathComponent().toString();
				String filePath = pathTxt.getText();
				String sumFile = filePath + "\\" + filename;
				fileTitleTxt.setText(sumFile);
				File f = new File(sumFile);
				
				try {
					Scanner sc = new Scanner(f);
					String text = "";
					while (sc.hasNextLine()) {
						text += sc.nextLine() + "\n";
					}
					textArea.setText(text);

					sc.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				System.out.println(filename + path);
			}
		});
		tree.setShowsRootHandles(true);
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("root") {
			{
			}
		}));
		
		saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		saveBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 Writer writer = null;
			        try {
			        	String file = fileTitleTxt.getText();
			            writer = new FileWriter(file);
			            textArea.write(writer);
			            JOptionPane.showMessageDialog(null,"Save successfully");
			        } catch (IOException ex) {
			            ex.printStackTrace();
			        } finally {
			            try {
			                writer.close();
			            } catch (IOException ex) {
			                ex.printStackTrace();
			            }
			        }
			}
		});
		saveBtn.setBounds(438, 427, 92, 21);
		contentPane.add(saveBtn);
		
		fileTitleLbl = new JLabel("File: ");
		fileTitleLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fileTitleLbl.setBounds(191, 116, 63, 28);
		contentPane.add(fileTitleLbl);
		
		fileTitleTxt = new JTextField();
		fileTitleTxt.setBounds(235, 116, 381, 28);
		contentPane.add(fileTitleTxt);
		fileTitleTxt.setColumns(10);
		
		JLabel bgLbl = new JLabel("");
		bgLbl.setBounds(136, 3, 662, 472);
		ImageIcon bg = new ImageIcon("bg.jpg");
		bgLbl.setIcon(new ImageIcon("C:\\Users\\latru\\Desktop\\hplat_TextEditor\\TextEditor\\src\\com\\hplat\\bg.jpg"));
//		Image img = bg.getImage();
//		this.setIconImage(img);
		contentPane.add(bgLbl);

	}

	public void listAllFile(String path) {
		File myFile = new File(path);
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(myFile.getName());
		for (File f : myFile.listFiles()) {
			DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(f.getName());
			rootNode.add(childNode);

			if (f.isDirectory()) {
				addSubNodes(childNode, f);
			}
		}
		DefaultTreeModel defaultTreeModel = new DefaultTreeModel(rootNode);
		tree.setModel(defaultTreeModel);
	}

	private void addSubNodes(DefaultMutableTreeNode parentNode, File parentFile) {
		for (File f : parentFile.listFiles()) {
			DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(f.getName());
			parentNode.add(childNode);

			if (f.isDirectory()) {
				addSubNodes(childNode, f);
			}
		}
	}
}
