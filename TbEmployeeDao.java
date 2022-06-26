package dao;

import utils.JDBCUtils;

public class TbEmployeeDao {
	
	/**
	 * ��ѯ����Ա����Ϣ
	 * @return
	 */
	public String[][] select(){
		String sql = "SELECT * FROM `personal`.`tb_employee`";
		String[][] data = JDBCUtils.select(sql, 5);
		return data;
	}
	
	/**
	 * ��ѯ����Ա����Ϣ-ģ����ѯ
	 * @return
	 */
	public String[][] select(String empName){
		String sql = "SELECT * FROM `personal`.`tb_employee` where empName like ?";
		String[][] data = JDBCUtils.select(sql, 5,"%"+empName+"%");
		return data;
	}
	
	/**
	 * ����empIdɾ��Ա����Ϣ
	 * @param empId
	 */
	public void delete(String empId){
		String sql = "DELETE FROM `personal`.`tb_employee` WHERE `empId` = ?";
		JDBCUtils.addAndUpdate(sql, empId);
	}

}
