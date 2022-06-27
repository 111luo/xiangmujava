package dao;

import model.TbOrder;
import utils.JDBCUtils;

public class TbOrderDao {
	
	/**
	 * ��ѯȫ��
	 * @return
	 */
	public String[][] select(){
		String sql = "SELECT * FROM `orderdb`.`tb_order`";
		String[][] data = JDBCUtils.select(sql, 5);
		return data;
	}
	
	/**
	 * ����idɾ������
	 * @param id
	 */
	public void delete(String id){
		String sql = "DELETE FROM `orderdb`.`tb_order` WHERE `id` = ?";
		JDBCUtils.addAndUpdate(sql, id);
	}
	
	/**
	 * ����������
	 * @param tbOrder
	 */
	public void insert(TbOrder tbOrder){
		String sql = "INSERT INTO `orderdb`.`tb_order`(`name`, `price`, `orderId`, `descinfo`) VALUES (?, ?, ?, ?)";
		JDBCUtils.addAndUpdate(sql, tbOrder.getName(),tbOrder.getPrice(),tbOrder.getOrderId(),tbOrder.getDescinfo());
	}

}
