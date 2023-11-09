package wonderwalker.project.mini.dao;
import wonderwalker.project.mini.comon.Common;
import wonderwalker.project.mini.vo.UserInfoVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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


    // 회원 가입
    public boolean SignUp(String userid, String userPwd, String nick, String userName, String addr, String phone, String email){
        int result = 0;
        String sql = "Insert into USERINFO (USERID,NICK,USERPW,USERNAME,ADDR,PHONENUM,EMAIL) values (?,?,?,?,?,?,?)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userid);
            pStmt.setString(2, nick);
            pStmt.setString(3, userPwd);
            pStmt.setString(4, userName);
            pStmt.setString(5, addr);
            pStmt.setString(6, phone);
            pStmt.setString(7, email);
            result = pStmt.executeUpdate();
            System.out.println("Yes?" + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if(result == 1) return true;
        else return false;
    }


    // 회원정보 조회
    public List<UserInfoVO> Userinfo(String id) {
        List<UserInfoVO> list = new ArrayList<>();
        String sql = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            sql = "SELECT * FROM USERINFO WHERE USERID = " + "'" + id + "'";
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String userid = rs.getString("USERID");
                String nick = rs.getString("NICK");
                String abab = rs.getString("USERNAME");
                String addr = rs.getString("ADDR");
                String phonel = rs.getString("PHONENUM");
                String email = rs.getString("EMAIL");


                UserInfoVO vo = new UserInfoVO();
                vo.setUserId(userid);
                vo.setUserName(abab);
                vo.setNick(nick);
                vo.setAddr(addr);
                vo.setPhoneNum(phonel);
                vo.setEmail(email);
                list.add(vo);




            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }



}
