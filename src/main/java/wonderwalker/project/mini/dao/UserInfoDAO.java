package wonderwalker.project.mini.dao;
import wonderwalker.project.mini.comon.Common;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserInfoDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;
// 로그인창에 입력한 값과 DB에 있는 값을 확인하여 boolean으로 반환
    public boolean loginCheck(String id, String pwd) {
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement(); // Statement 객체 얻기
            String sql = "SELECT * FROM USERINFO WHERE USERID = " + "'" + id + "'";
            rs = stmt.executeQuery(sql);

            while(rs.next()) { // 읽은 데이타가 있으면 true
                String sqlId = rs.getString("USERID"); // 쿼리문 수행 결과에서 ID값을 가져 옴
                String sqlPwd = rs.getString("USERPW");
                System.out.println("ID : " + sqlId);
                System.out.println("USERPW : " + sqlPwd);
                if(id.equals(sqlId) && pwd.equals(sqlPwd)) {
                    Common.close(rs);
                    Common.close(stmt);
                    Common.close(conn);
                    return true;
                }
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }



//회원가입시 아이디가 DB에 있는지 확인하여 boolean으로 반환
public boolean SingupIdCheck(String id) {
        //SQL 결과를 확인할 변수
    boolean isNotReg = false;
    try {
        conn = Common.getConnection();
        stmt = conn.createStatement();
        String sql = "SELECT * FROM USERINFO WHERE USERID = " + "'" + id +"'";
        rs = stmt.executeQuery(sql);
       //SQL 결과 있으면 가입이 못하게 false ,없으면 가능하게 true로 if문 작성
        if(rs.next()){
            isNotReg = false;
        }
        else isNotReg = true;
    } catch(Exception e) {
        e.printStackTrace();
    }
    Common.close(rs);
    Common.close(stmt);
    Common.close(conn);
    return isNotReg;
}
}
