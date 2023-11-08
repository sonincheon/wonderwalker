package wonderwalker.project.mini.dao;

import wonderwalker.project.mini.comon.Common;
import wonderwalker.project.mini.vo.TravelVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TravelDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    //여행 공유 일정 출력 구문
    public List<TravelVO> TravelInfo(String world) {
        List<TravelVO> list = new ArrayList<>();
        String sql = null;
        System.out.println("world : " + world);

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            if (world.equals("korea")) sql = "SELECT * FROM TRAVEL_INFO_TB WHERE TRAVEL_WORLD='국내'";
            else sql = "SELECT * FROM TRAVEL_CONTENT_TB WHERE TRAVEL_WORLD='해외'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Integer travel_num =rs.getInt("TRAVEL_NUM");
                String travel_world = rs.getString("TRAVEL_WORLD");
                String travel_theme = rs.getString("TRAVEL_THEME");
                String travel_area = rs.getString("TRAVEL_AREA");
                String travel_title = rs.getString("TRAVEL_TITLE");
                Date travel_startdate = rs.getDate("TRAVEL_STARTDATE");
                Date travel_enddate =rs.getDate("TRAVEL_ENDDATE");
                String travel_userid =rs.getString("TRAVEL_USERID");
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


    public List<TravelVO> TravelContent(String itemCode) {
        List<TravelVO> list = new ArrayList<>();
        String sql = null;
        System.out.println("itemcode: "+itemCode);

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT * FROM TRAVEL_CONTENT_TB WHERE TRAVEL_NUM = '"+itemCode+"'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Integer travel_num = rs.getInt("TRAVEL_NUM");
                Date d_day = rs.getDate("D_DAY");
                String travel_pic = rs.getString("TRAVEL_PIC");
                String writing=rs.getString("TRAVEL_WRITING");
                String travel_map = rs.getString("TRAVEL_MAP");

                TravelVO vo = new TravelVO();
                vo.setTravel_num(travel_num);
                vo.setD_day(d_day);
                vo.setTravel_pic(travel_pic);
                vo.setTravel_writing(writing);
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
}
