package wonderwalker.project.mini.dao;

import wonderwalker.project.mini.comon.Common;
import wonderwalker.project.mini.vo.CourseVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    // 코스 목록 조회
    public List<CourseVO> selectCourseList(String course_area) {
        List<CourseVO> courseList = new ArrayList<>();
        String sql = null;
        System.out.println("world : " + course_area);

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT * FROM COURSE WHERE COURSE_AREA ='"+course_area+"'";


            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CourseVO courseVO = new CourseVO();
                courseVO.setCourse_code(rs.getString("COURSE_CODE"));
                courseVO.setCourse_world(rs.getString("COURSE_WORLD"));
                courseVO.setCourse_area(rs.getString("COURSE_AREA"));
                courseVO.setCourse_hash(rs.getString("COURSE_HASH"));
                courseVO.setTopic(rs.getString("TOPIC"));
                courseVO.setMain_img(rs.getString("MAIN_IMG"));
                courseVO.setCourse_img1(rs.getString("COURSE_IMG1"));
                courseVO.setCourse_path1(rs.getString("COURSE_PATH1"));
                courseVO.setCourse_img2(rs.getString("COURSE_IMG2"));
                courseVO.setCourse_path2(rs.getString("COURSE_PATH2"));
                courseVO.setCourse_img3(rs.getString("COURSE_IMG3"));
                courseVO.setCourse_path3(rs.getString("COURSE_PATH3"));

                courseList.add(courseVO);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return courseList;
    }
    // 코스 상세 조회
    public List<CourseVO> selectCourseDetail(String course_code) {
        List<CourseVO> courseDetail = new ArrayList<>();
        String sql = null;


        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT * FROM COURSE WHERE COURSE_CODE ='"+course_code+"'";

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CourseVO courseVO = new CourseVO();
                courseVO.setCourse_code(rs.getString("COURSE_CODE"));
                courseVO.setCourse_world(rs.getString("COURSE_WORLD"));
                courseVO.setCourse_area(rs.getString("COURSE_AREA"));
                courseVO.setCourse_hash(rs.getString("COURSE_HASH"));
                courseVO.setTopic(rs.getString("TOPIC"));
                courseVO.setMain_img(rs.getString("MAIN_IMG"));
                courseVO.setCourse_img1(rs.getString("COURSE_IMG1"));
                courseVO.setCourse_path1(rs.getString("COURSE_PATH1"));
                courseVO.setCourse_article1(rs.getString("COURSE_ARTICLE1"));
                courseVO.setCourse_article1_1(rs.getString("COURSE_ARTICLE1_1"));
                courseVO.setCourse_img2(rs.getString("COURSE_IMG2"));
                courseVO.setCourse_path2(rs.getString("COURSE_PATH2"));
                courseVO.setCourse_article2(rs.getString("COURSE_ARTICLE2"));
                courseVO.setCourse_article2_1(rs.getString("COURSE_ARTICLE2_1"));
                courseVO.setCourse_img3(rs.getString("COURSE_IMG3"));
                courseVO.setCourse_path3(rs.getString("COURSE_PATH3"));
                courseVO.setCourse_article3(rs.getString("COURSE_ARTICLE3"));
                courseVO.setCourse_article3_1(rs.getString("COURSE_ARTICLE3_1"));
                courseDetail.add(courseVO);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return courseDetail;

    }


    //즐겨찾기 정보가져가기
    public List<CourseVO> faveoList(String id) {
        System.out.println("222222222222222");
        List<CourseVO> list = new ArrayList<>();
        try {
            String sql =" SELECT * FROM FAVOR, COURSE WHERE FAVOR.COURSE_CODE = COURSE.COURSE_CODE AND FAVOR.USERID = ?  ";
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
//            pStmt.setString(1, type);
            pStmt.setString(1, id);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                String COURSE_WORLD = rs.getString("COURSE_WORLD");
                String COURSE_AREA = rs.getString("COURSE_AREA");
                String COURSE_HASH = rs.getString("COURSE_HASH");
                String TOPIC = rs.getString("TOPIC");
                String MAIN_IMG =rs.getString("MAIN_IMG");

                System.out.println(   COURSE_WORLD);
                System.out.println(   COURSE_HASH);
                System.out.println(   COURSE_AREA);

                CourseVO vo = new CourseVO();
                vo.setCourse_world(COURSE_WORLD);
                vo.setCourse_area(COURSE_AREA);
                vo.setCourse_hash(COURSE_HASH);
                vo.setTopic(TOPIC);
                vo.setMain_img(MAIN_IMG);
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