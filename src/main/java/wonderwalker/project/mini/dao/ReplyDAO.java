package wonderwalker.project.mini.dao;

import wonderwalker.project.mini.comon.Common;
import wonderwalker.project.mini.vo.CommunityVO;
import wonderwalker.project.mini.vo.ReplyVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ReplyDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;
    // 게시글  모든 리플 조회
    public List<ReplyVO> SelectReply(int num) {
        List<ReplyVO> list = new ArrayList<>();
        String sql = null;
        System.out.println("리플조회 시작");
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT * FROM REPLY WHERE BOARDNUM = " +num;
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int conmmunityNum = rs.getInt("REPLYNUM");
                String userId = rs.getString("USERID");
                String reply = rs.getString("TRAVERL_COMMENT");
                ReplyVO vo = new ReplyVO();
                vo.setCommentNum(conmmunityNum);
                vo.setUserId(userId);
                vo.setTravelComment(reply);
                list.add(vo);

                System.out.println("리스트 출력 테스트");
                for (ReplyVO community : list) {
                    System.out.println(community.getCommentNum());
                    System.out.println(community.getUserId());
                    System.out.println(community.getTravelComment());

                    break; // 첫 번째 원소만 출력하고 나가도록 break 추가
                }


            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //  리플 추가
        public boolean InsertReply(String content1,String USERID, int num){
        int result = 0;
        String     sql = "INSERT  INTO REPLY (REPLYNUM, TRAVERL_COMMENT, USERID, BOARDNUM) values (REPLYSEQUENCE.nextval,?,?,?)";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,content1);
            pStmt.setString(2, USERID);
            pStmt.setInt(3, num);
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
    //  리플 수정
    public boolean  updateReply(String content1,int num){
        int result = 0;
        String sql = "UPDATE REPLY SET traverl_comment = ? WHERE replynum = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,content1);
            pStmt.setInt(2, num);
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


    public boolean  deleteReply(int num) {
        //SQL 결과를 확인할 변수
        boolean isNotReg = false;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "DELETE FROM SCOTT.REPLY WHERE REPLYNUM = "+num ;
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
        System.out.println("cccc"+isNotReg);
        return isNotReg;
    }






}
