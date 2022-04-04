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
	//在开发中，编写的测试代码的方法名建议和需要测试的方法名称一致
	@Test
	public void selectEmpById() {
		InputStream in = TestMapper.class.getClassLoader().getResourceAsStream("mybatis-configs.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//这个返回值sqlSession可以认为是Connection statement
		SqlSession session = sessionFactory.openSession();
		System.out.println(session);
		//拿到session后就可以通过mybatis的API来进行sql的操作
		Object one = session.selectOne("com.tedu.bean.EmpMapper" + ".selectEmpById",2);
		System.out.println(one);
		
		//修改功能
		EmpBean bean = (EmpBean)one;
		bean.setName("李田所");
		bean.setSalary(7000);
		int i = session.update("com.tedu.bean.EmpMapper.updateEmpById",bean);
		//事务默认开启，不提交insert和update，delete不会存储到数据库
		session.commit(); //提交 或者是session.rollback();
		System.out.println(i);
		
		//插入语句
//		bean.setName("yyut");
//		bean.setSalary(1000);
//		int x = session.insert("com.tedu.bean.EmpMapper.insertEmp", bean);
//		System.out.println(x);
//		session.commit();
		
		//删除
		int y = session.delete("com.tedu.bean.EmpMapper.deleteEmpById", 10);
		System.out.println(y);
		session.commit();
		
		//查询所有
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
			//调用资源回收，关闭sta，set等
		}
		return bean;
	}
}
