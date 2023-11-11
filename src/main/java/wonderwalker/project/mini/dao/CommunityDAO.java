package wonderwalker.project.mini.dao;

import wonderwalker.project.mini.comon.Common;
import wonderwalker.project.mini.vo.CommunityVO;
import wonderwalker.project.mini.vo.UserInfoVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CommunityDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;
    // 모든 게시글 조회
    public List<CommunityVO> SelectAllCommunity() {
        List<CommunityVO> list = new ArrayList<>();
        String sql = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT * FROM COMMUNITY";
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int conmmunityNum = Integer.parseInt(rs.getString("COMMUNITYNUM"));
                String userId = rs.getString("USERID");
                Date reportingdate = rs.getDate("REPORTINGDATE");
                int views = Integer.parseInt(rs.getString("VIEWS"));
                String title = rs.getString("TITLE");
                String content1 = rs.getString("CONTENT1");

                CommunityVO vo = new CommunityVO();
                vo.setCommunityNum(conmmunityNum);
                vo.setUerId(userId);
                vo.setReportingDate(reportingdate);
                vo.setViews(views);
                vo.setTitle(title);
                vo.setContent(content1);
                list.add(vo);

                System.out.println("리스트 출력 테스트");
                for (CommunityVO community : list) {
                    System.out.println(community.getCommunityNum());
                    System.out.println(community.getUerId());
                    System.out.println(community.getReportingDate());
                    System.out.println(community.getViews());
                    System.out.println(community.getTitle());
                    System.out.println(community.getContent());
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
    //  게시글 한개 조회
    public List<CommunityVO> SelectOneCommunity(int num) {
        List<CommunityVO> list = new ArrayList<>();
        String sql = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT * FROM COMMUNITY WHERE  COMMUNITYNUM =+ "+num;
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int conmmunityNum = Integer.parseInt(rs.getString("COMMUNITYNUM"));
                String userId = rs.getString("USERID");
                Date reportingdate = rs.getDate("REPORTINGDATE");
                int views = Integer.parseInt(rs.getString("VIEWS"));
                String title = rs.getString("TITLE");
                String content1 = rs.getString("CONTENT1");

                CommunityVO vo = new CommunityVO();
                vo.setCommunityNum(conmmunityNum);
                vo.setUerId(userId);
                vo.setReportingDate(reportingdate);
                vo.setViews(views);
                vo.setTitle(title);
                vo.setContent(content1);
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
    // 글 등록
    public boolean InsertCommunity(String ueryId,Date reportingDate, int views, String title, String content){
        int result = 0;
        String sql = "Insert into COMMUNITY (COMMUNITYNUM,USERID,REPORTINGDATE,VIEWS,TITLE,CONTENT1) values (COMMUNITYSEQUENCE.nextval,'?',SYSDATE,0,'?',?)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, ueryId);
            pStmt.setString(2, title);
            pStmt.setString(3, content);
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








}
