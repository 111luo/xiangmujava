package com.hoosee1.utils;
import java.sql.*;


public class JDBCUtils {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/goods_db";
    private static final String user = "root";
    private static final String password = "123456";

    static {
        // 1.�������ݿ�����
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ɾ�Ĳ���
     * @param sql �������
     * @param objects �������Ĳ���
     */
    public static void addAndUpdate(String sql,Object...objects){
        Connection conn = null;
        PreparedStatement statement = null;
        //2.��ȡ���ݿ�����
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("���ݿ����ӳɹ�!");

            //4.��ȡPreparedStatement����
            statement = conn.prepareStatement(sql);

            //5.���sql����
            for(int i=0;i<objects.length;i++){
                statement.setObject(i+1, objects[i]);
            }


            //6.ִ��sql���
            statement.execute();
            System.out.println("ִ�гɹ�!");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //7.�ͷ���Դ
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @param sql ��ѯ���
     * @param column ��ѯ���������
     * @param objects ��ѯ���Ĳ���
     */
    public static String[][] select(String sql,int column,Object...objects){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String[][] data = null;
        //2.��ȡ���ݿ�����
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("���ݿ����ӳɹ�!");

            //4.��ȡPreparedStatement����
            statement = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

            //5.���sql����
            for(int i=0;i<objects.length;i++){
                statement.setObject(i+1, objects[i]);
            }

            //6.ִ��sql���
            rs = statement.executeQuery();

            //7.��װ����
            //ͳ����������
            int rows = 0;
            while(rs.next()){
                rows++;
            }
            //����resultset��ָ��
            rs.beforeFirst();

            data = new String[rows][column];
            int rowsNow = 0;//��ǰ�к�
            while(rs.next()){
                for(int i = 0;i<column;i++){
                    data[rowsNow][i] = rs.getString(i+1);
                }
                rowsNow++;
            }

            System.out.println("ִ�гɹ�");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //7.�ͷ���Դ
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

}
