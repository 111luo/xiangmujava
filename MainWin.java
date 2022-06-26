package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.TbEmployeeDao;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class MainWin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	//����dao
	TbEmployeeDao dao = new TbEmployeeDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWin frame = new MainWin();
					frame.setVisible(true);
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
		setTitle("\u4EBA\u4E8B\u4FE1\u606F\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//����
		this.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u5458\u5DE5\u59D3\u540D\u5173\u952E\u5B57");
		lblNewLabel.setBounds(31, 66, 141, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(182, 63, 162, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u5458\u5DE5\u4FE1\u606F\u7BA1\u7406");
		lblNewLabel_1.setBounds(143, 21, 135, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		btnNewButton.setBounds(375, 62, 93, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 118, 439, 168);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("\u5220\u9664");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row!=-1){
					String empId = table.getValueAt(row, 0).toString();
					int temp = JOptionPane.showConfirmDialog(null, "��ȷ��ɾ����Ա����Ϣ");
					if(temp == 0){
						dao.delete(empId);
						JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
						//ˢ�¸�����
						refresh();
					}
				}else{
					JOptionPane.showMessageDialog(null, "������ѡ��һ��Ա��ɾ��");
				}
			}
		});
		btnNewButton_1.setBounds(363, 296, 93, 23);
		contentPane.add(btnNewButton_1);
		
		
		//��һ�μ�������
		refresh();
	}
	
	public void refresh(){
		String empName = textField.getText();
		String[] header = {"Ա�����","Ա����","�Ա�","�ֻ�","��ַ"};
		String[][] data = null;
		if(empName!=null&&!"".equals(empName)){
			//ģ����ѯ
			data = dao.select(empName);
		}else{
			//��ѯȫ��
			data = dao.select();
		}
		//����table������
		DefaultTableModel dtm = new DefaultTableModel(data,header);
		table.setModel(dtm);
	}
}
