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

    //  게시글 한개 조회
    public List<CommunityVO> SelectOneCommunity(int num) {
        List<CommunityVO> list = new ArrayList<>();
        String sql = null;
        String sql2 = null;
        try {
            conn = Common.getConnection();
            // 1. 조회수 증가
            sql = "UPDATE COMMUNITY SET VIEWS = VIEWS + 1 WHERE COMMUNITYNUM = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, num);
            pStmt.executeUpdate();
            pStmt.close(); // 쿼리 실행 후에는 즉시 닫아줍니다.
            // 2. 커뮤니티 정보 조회
            sql = "SELECT * FROM COMMUNITY WHERE COMMUNITYNUM = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, num);
            rs = pStmt.executeQuery();

            while(rs.next()) {

                int conmmunityNum = Integer.parseInt(rs.getString("COMMUNITYNUM"));
                String userId = rs.getString("USERID");
                Date reportingdate = rs.getDate("REPORTINGDATE");
                int views = Integer.parseInt(rs.getString("VIEWS"));
                String title = rs.getString("TITLE");
                String content1 = rs.getString("CONTENT1");
                String imgURL = rs.getString("IMGURL");

                CommunityVO vo = new CommunityVO();
                vo.setCommunityNum(conmmunityNum);
                vo.setUserId(userId);
                vo.setReportingDate(reportingdate);
                vo.setViews(views);
                vo.setTitle(title);
                vo.setContent(content1);
                vo.setImgurl(imgURL);
                list.add(vo);

            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        }
        return list;
    }









    public List<CommunityVO> SelectAllCommunity() {
        List<CommunityVO> list = new ArrayList<>();
        String sql = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT * FROM COMMUNITY ORDER BY COMMUNITYNUM DESC";
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
                vo.setUserId(userId);
                vo.setReportingDate(reportingdate);
                vo.setViews(views);
                vo.setTitle(title);
                vo.setContent(content1);
                list.add(vo);

                System.out.println("리스트 출력 테스트");
                for (CommunityVO community : list) {
                    System.out.println(community.getCommunityNum());
                    System.out.println(community.getUserId());
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

    // 글 등록
    public boolean InsertCommunity(String ueryId, String content, String title, String url){
        int result = 0;
        String sql = "Insert into COMMUNITY (COMMUNITYNUM,USERID,REPORTINGDATE,VIEWS,TITLE,CONTENT1,IMGURL) values (COMMUNITY_SEQ.nextval,?,SYSDATE,0,?,?,?)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, ueryId);
            pStmt.setString(2, title);
            pStmt.setString(3, content);
            pStmt.setString(4, url);
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
    // 글 수정
    public boolean updateCommunity (String title,String content,int communityNum){
        int result = 0;
        String sql = "UPDATE COMMUNITY SET title = ?, CONTENT1 = ? WHERE COMMUNITYNUM = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, title);
            pStmt.setString(2, content);
            pStmt.setInt(3, communityNum);
            result = pStmt.executeUpdate();
            System.out.println("Yes?" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if(result == 1) return true;
        else   return false;
    }

    public boolean DeleteCommunity (int communityNum){
        int result = 0;
        String sql = "DELETE FROM COMMUNITY  WHERE COMMUNITYNUM = ? ";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, communityNum);
            result = pStmt.executeUpdate();
            System.out.println("Yes?" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if(result == 1) return true;
        else   return false;
    }




}
