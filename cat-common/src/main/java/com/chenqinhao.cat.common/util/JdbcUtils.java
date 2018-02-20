package com.chenqinhao.cat.common.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenqinhao on 2017/9/3.
 */
public class JdbcUtils {

    // 定义数据库连接
    private Connection conn;
    // 定义sql语句的执行对象
    private PreparedStatement pstmt;
    // 定义查询返回的结果集合
    private ResultSet rs;

    public JdbcUtils(String driver, String url, String username, String password) {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("数据库连接成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean updateByParam(String sql, List params) throws SQLException {
        int result = 1;
        pstmt = conn.prepareStatement(sql);
        int index = 1;
        if (null != params && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        result = pstmt.executeUpdate();
        return result > 0 ? true : false;
    }

    public List<Map> selectByParams(String sql, List params) throws SQLException {
        List<Map> list = new ArrayList<>();
        int index = 1;
        pstmt = conn.prepareStatement(sql);
        if (null != params && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        rs = pstmt.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        int colsLen = metaData.getColumnCount();
        while (rs.next()) {
            Map map = new HashMap();
            for (int i = 0; i < colsLen; i++) {
                String colsName = metaData.getColumnName(i + 1);
                Object colsValue = rs.getObject(colsName);
                if (null == colsValue) {
                    colsValue = "";
                }
                map.put(colsName, colsValue);
            }
            list.add(map);
        }
        return list;
    }

    public void release() {
        try {
            if (null != rs) rs.close();
            if (null != pstmt) pstmt.close();
            if (null != conn) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("释放数据库连接");
    }
}
