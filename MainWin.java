package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import dao.TbOrderDao;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWin extends JFrame {

	private JPanel contentPane;
	private JTable table;
	//ʵ����dao��
	TbOrderDao dao = new TbOrderDao();
	
	//�����ڴ�һ�������ڵ�ʵ��
	public static MainWin mainWin;
	//�����ڴ�һ���Ӵ��ڵ�ʵ��
	public static AddWin addWin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWin = new MainWin();
					mainWin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("\u7269\u6D41\u8DDF\u8E2A\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setBounds(220, 34, 166, 15);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 76, 480, 191);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//����������
				addWin = new AddWin(mainWin);
				addWin.setVisible(true);
			}
		});
		btnNewButton.setBounds(53, 293, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u5220\u9664");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ȡ��ѡ�е���
				int row = table.getSelectedRow();
				if(row!=-1){
					String id = table.getValueAt(row, 0).toString();
					int a = JOptionPane.showConfirmDialog(null, "��������Ҫɾ��������","��ܰ��ʾ",JOptionPane.OK_OPTION);
					if(a==0){
						dao.delete(id);
						JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
						//ˢ�¸�����
						refresh();
					}
				}
			}
		});
		btnNewButton_1.setBounds(431, 293, 93, 23);
		contentPane.add(btnNewButton_1);
		
		refresh();
	}
	
	/**
	 * ˢ�±������
	 */
	public void refresh(){
		String[] header = {"���","��Ʒ����","��Ʒ�۸�","�������","��������"};
		String[][] data = dao.select();
		//���±������
		DefaultTableModel dtm = new DefaultTableModel(data,header);
		table.setModel(dtm);
	}
	
}
