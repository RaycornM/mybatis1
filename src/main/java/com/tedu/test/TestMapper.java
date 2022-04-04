package com.tedu.test;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.tedu.bean.EmpBean;

public class TestMapper {
	//�ڿ����У���д�Ĳ��Դ���ķ������������Ҫ���Եķ�������һ��
	@Test
	public void selectEmpById() {
		InputStream in = TestMapper.class.getClassLoader().getResourceAsStream("mybatis-configs.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//�������ֵsqlSession������Ϊ��Connection statement
		SqlSession session = sessionFactory.openSession();
		System.out.println(session);
		//�õ�session��Ϳ���ͨ��mybatis��API������sql�Ĳ���
		Object one = session.selectOne("com.tedu.bean.EmpMapper" + ".selectEmpById",2);
		System.out.println(one);
		
		//�޸Ĺ���
		EmpBean bean = (EmpBean)one;
		bean.setName("������");
		bean.setSalary(7000);
		int i = session.update("com.tedu.bean.EmpMapper.updateEmpById",bean);
		//����Ĭ�Ͽ��������ύinsert��update��delete����洢�����ݿ�
		session.commit(); //�ύ ������session.rollback();
		System.out.println(i);
		
		//�������
//		bean.setName("yyut");
//		bean.setSalary(1000);
//		int x = session.insert("com.tedu.bean.EmpMapper.insertEmp", bean);
//		System.out.println(x);
//		session.commit();
		
		//ɾ��
		int y = session.delete("com.tedu.bean.EmpMapper.deleteEmpById", 10);
		System.out.println(y);
		session.commit();
		
		//��ѯ����
		List<EmpBean> list = session.selectList("com.tedu.bean.EmpMapper.selectAll");
		for(EmpBean b:list) {
			System.out.println(b);
		}
	}
	
	public EmpBean selectEmpById(int id) {
		Connection conn = null; //JDBCUtile.getConnection();
		PreparedStatement sta = null;
		ResultSet set = null;
		String sql = "select * from emp where id=?";
		EmpBean bean = null;
		try {
			sta = conn.prepareStatement(sql);
			sta.setInt(1, id);
			set=sta.executeQuery();
			if(set.next()) {
				bean = new EmpBean();
				bean.setId(set.getInt("id"));
				bean.setName(set.getString("name"));
				//.........
				System.out.println(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//������Դ���գ��ر�sta��set��
		}
		return bean;
	}
}
