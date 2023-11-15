package wonderwalker.project.mini.dao;

import wonderwalker.project.mini.comon.Common;
import wonderwalker.project.mini.vo.Travel2VO;
import wonderwalker.project.mini.vo.TravelVO;
import wonderwalker.project.mini.vo.UserInfoVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TravelDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    //여행 공유 일정 출력 구문(메인)
    public List<TravelVO> TravelInfo(String world, String theme) {
        List<TravelVO> list = new ArrayList<>();
        String sql = null;
        System.out.println("world : " + world);

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            if (world.equals("All")){
                if (world.equals("korea"))
                    sql = "SELECT * FROM TRAVEL_INFO_TB WHERE TRAVEL_WORLD='국내'";
                else sql = "SELECT * FROM TRAVEL_INFO_TB WHERE TRAVEL_WORLD='해외'";
            }else{
                if (world.equals("korea"))
                    sql = "SELECT * FROM TRAVEL_INFO_TB WHERE TRAVEL_WORLD='국내' AND TRAVEL_THEME ='"+theme+"'";
                else sql = "SELECT * FROM TRAVEL_INFO_TB WHERE TRAVEL_WORLD='해외' AND TRAVEL_THEME ='"+theme+"'";
            }

//                sql = "SELECT * FROM TRAVEL_INFO_TB WHERE TRAVEL_WORLD='국내' AND TRAVEL_THEME='" + theme + "'";
//            else sql = "SELECT * FROM TRAVEL_INFO_TB WHERE TRAVEL_WORLD='해외' AND TRAVEL_THEME='" + theme + "'";

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String travel_num = rs.getString("TRAVEL_NUM");
                String travel_world = rs.getString("TRAVEL_WORLD");
                String travel_theme = rs.getString("TRAVEL_THEME");
                String travel_area = rs.getString("TRAVEL_AREA");
                String travel_title = rs.getString("TRAVEL_TITLE");
                String travel_startdate = rs.getString("TRAVEL_STARTDATE");
                String travel_enddate = rs.getString("TRAVEL_ENDDATE");
                String travel_userid = rs.getString("TRAVEL_USERID");
                Date travel_writedate = rs.getDate("TRAVEL_WRITEDATE");
                Integer travel_view = rs.getInt("TRAVEL_VIEW");
                Integer travel_good = rs.getInt("TRAVEL_GOOD");

                TravelVO vo = new TravelVO();
                vo.setTravel_num(travel_num);
                vo.setTravel_world(travel_world);
                vo.setTravel_theme(travel_theme);
                vo.setTravel_area(travel_area);
                vo.setTravel_title(travel_title);
                vo.setTravel_startdate(travel_startdate);
                vo.setTravel_enddate(travel_enddate);
                vo.setTravel_userid(travel_userid);
                vo.setTravel_writedate(travel_writedate);
                vo.setTravel_view(travel_view);
                vo.setTravel_good(travel_good);
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

    // 여행 정보 리스트 보기
    public List<Travel2VO> TravelContent(String travelNum) {
        List<Travel2VO> list = new ArrayList<>();
        String sql = null;
        System.out.println("travelNum: " + travelNum);

        try {

            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT * FROM TRAVEL_CONTENT_TB WHERE TRAVEL_NUM = '" + travelNum + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String travel_num = rs.getString("TRAVEL_NUM");
                String d_day = rs.getString("D_DAY");
                String travel_pic = rs.getString("TRAVEL_PIC");
                String travel_writing = rs.getString("TRAVEL_WRITING");
                String travel_map = rs.getString("TRAVEL_MAP");


                Travel2VO vo = new Travel2VO();
                vo.setTravel_num(travel_num);
                vo.setD_day(d_day);
                vo.setTravel_pic(travel_pic);
                vo.setTravel_writing(travel_writing);
                vo.setTravel_map(travel_map);
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


    // 게시글 등록하기
    public String travelInsert(String travelWorld, String travelArea, String travel_theme, String travel_title, String travel_startdate,String travel_enddate, String travel_userid) {
        boolean isInsert = false;
        String Tavel_num=null;
        try {
            conn = Common.getConnection();
            String sql = "INSERT INTO TRAVEL_INFO_TB(TRAVEL_NUM,TRAVEL_USERID,TRAVEL_WORLD,TRAVEL_AREA,TRAVEL_STARTDATE,TRAVEL_ENDDATE,TRAVEL_THEME,TRAVEL_TITLE,TRAVEL_WRITEDATE) VALUES('T' || SIRIAL_NUM.nextval,?,?,?,TO_DATE(?,'YYYY-MM-DD'),TO_DATE(?,'YYYY-MM-DD'),?,?,sysdate)";
            String genealColumns[] = {"TRAVEL_NUM"};
            pStmt = conn.prepareStatement(sql, genealColumns);
            pStmt.setString(1, travel_userid);
            pStmt.setString(2, travelWorld);
            pStmt.setString(3, travelArea);
            pStmt.setString(4, travel_startdate);
            pStmt.setString(5, travel_enddate);
            pStmt.setString(6, travel_theme);
            pStmt.setString(7, travel_title);
            pStmt.executeUpdate();
            int result = pStmt.executeUpdate();
            rs = pStmt.getGeneratedKeys();
            while(rs.next()){
                Tavel_num=rs.getString(1);
            }
            if (result == 1) isInsert = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        return Tavel_num;
    }


    public boolean travelInsert2(String tvNum,String num,String travel_map, String travel_pic, String travel_writing) {
        boolean isInsert = false;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "INSERT INTO TRAVEL_CONTENT_TB(TRAVEL_NUM,D_DAY,TRAVEL_MAP,TRAVEL_PIC,TRAVEL_WRITING) VALUES (?,?,?,?,?)";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, tvNum);
            pStmt.setString(2, num);
            pStmt.setString(3, travel_map);
            pStmt.setString(4, travel_pic);
            pStmt.setString(5, travel_writing);
            int result = pStmt.executeUpdate();
            if (result == 1) isInsert = true;
            System.out.println("성공!!!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        return isInsert;
    }

    public List<TravelVO> faveoList(String id) {
        List<TravelVO> list = new ArrayList<>();
        try {
            String sql =" SELECT * FROM FAVOR , TRAVEL_INFO_TB WHERE FAVOR.COURSE_CODE  =  TRAVEL_INFO_TB.TRAVEL_NUM   AND FAVOR.USERID = ?";
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
//            pStmt.setString(1, type);
            pStmt.setString(1, id);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                String TRAVEL_WORLD = rs.getString("TRAVEL_WORLD");
                String TRAVEL_THEME = rs.getString("TRAVEL_THEME");
                String TRAVEL_AREA = rs.getString("TRAVEL_AREA");
                String TRAVEL_TITLE = rs.getString("TRAVEL_TITLE");
                String TRAVEL_STARTDATE = String.valueOf(rs.getDate("TRAVEL_STARTDATE"));
                String TRAVEL_ENDDATE = String.valueOf(rs.getDate("TRAVEL_ENDDATE"));
                System.out.println(TRAVEL_WORLD);
                System.out.println(TRAVEL_TITLE);
                TravelVO vo = new TravelVO();
                vo.setTravel_world(TRAVEL_WORLD);
                vo.setTravel_theme(TRAVEL_THEME);
                vo.setTravel_area(TRAVEL_AREA);
                vo.setTravel_title(TRAVEL_TITLE);
                vo.setTravel_startdate(TRAVEL_STARTDATE);
                vo.setTravel_enddate(TRAVEL_ENDDATE);
                list.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("에러: " + e.getMessage()); // 에러 메시지 출력
        } finally {
            // 자원 해제는 finally 블록에서 수행합니다.
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        }
        System.out.println("list 돌려줌");
        return list;
    }
}
