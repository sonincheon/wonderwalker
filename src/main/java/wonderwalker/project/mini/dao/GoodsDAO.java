package wonderwalker.project.mini.dao;

import wonderwalker.project.mini.comon.Common;
import wonderwalker.project.mini.vo.GoodsVO;
import wonderwalker.project.mini.vo.SellVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;


    //상품 리스트 출력 구문
    public List<GoodsVO> GoodsList(String world,String area,Integer count) {
        List<GoodsVO> list = new ArrayList<>();
        String sql = null;
        System.out.println("world : " + world);

        int count1 =(count*5)+1;
        int count2 =(count*5)+5;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            if(area.equals("ALL")){
                if (world.equals("korea"))
                    sql = "SELECT * FROM (SELECT ROWNUM AS NUM, GOODS_TABLE.* FROM GOODS_TABLE WHERE  I_WORLD='국내')WHERE NUM BETWEEN "+count1+" AND "+count2;
                else sql = "SELECT * FROM (SELECT ROWNUM AS NUM, GOODS_TABLE.* FROM GOODS_TABLE WHERE  I_WORLD='해외')WHERE NUM BETWEEN "+count1+" AND "+count2;
            }else{
                if (world.equals("korea"))
                    sql = "SELECT * FROM (SELECT ROWNUM AS NUM, GOODS_TABLE.* FROM GOODS_TABLE WHERE  I_WORLD='국내' AND I_AREA = '"+area+"')WHERE NUM BETWEEN "+count1+" AND "+count2;
                else sql = "SELECT * FROM (SELECT ROWNUM AS NUM, GOODS_TABLE.* FROM GOODS_TABLE WHERE  I_WORLD='해외' AND I_AREA = '"+area+"')WHERE NUM BETWEEN "+count1+" AND "+count2;
            }
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String main_img =rs.getString("I_MAIN_IMG");
                String title = rs.getString("TITLE");
                String info1 = rs.getString("ODER_REQIURE");
                String info2 = rs.getString("ODER_INFO");
                Integer date_num = rs.getInt("I_DATE_NUM");
                Date date = rs.getDate("I_DATE");
                String code_num =rs.getString("ITEM_NUM");
                String price =rs.getString("PRICE");
                GoodsVO vo = new GoodsVO();
                price = price.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
                vo.setI_main_img(main_img);
                vo.setTitle(title);
                vo.setOder_info(info1);
                vo.setOder_re(info2);
                vo.setI_date_num(date_num);
                vo.setI_date(date);
                vo.setItem_num(code_num);
                vo.setPrice(price);
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



    // 상품 상세페이지
    public List<GoodsVO> GoodsInfo(String itemCode) {
        List<GoodsVO> list = new ArrayList<>();
        String sql = null;
        System.out.println("itemcode: "+itemCode);

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT * FROM GOODS_TABLE WHERE ITEM_NUM = '"+itemCode+"'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String item_num =rs.getString("ITEM_NUM");
                String i_world=rs.getString("I_WORLD");
                String price=rs.getString("PRICE");
                String tag=rs.getString("TAG");
                String i_name=rs.getString("I_NAME");
                String title=rs.getString("TITLE");
                String i_main_img =rs.getString("I_MAIN_IMG");
                String i_sub_img1=rs.getString("I_SUB_IMG1");
                String i_sub_img2=rs.getString("I_SUB_IMG2");
                String i_sub_img3=rs.getString("I_SUB_IMG3");
                Integer departure_num=rs.getInt("DEPARTURE_NUM");
                Date i_date=rs.getDate("I_DATE");
                Integer i_date_num=rs.getInt("I_DATE_NUM");
                Integer i_oder_num=rs.getInt("I_ODER_NUM");
                String info_img=rs.getString("INFO_IMG");
                String oder_re=rs.getString("ODER_REQIURE");
                String oder_info=rs.getString("ODER_INFO");
                Integer i_hit=rs.getInt("I_HIT");
                String i_area=rs.getString("I_AREA");

                GoodsVO vo = new GoodsVO();
                vo.setI_world(i_world);
                vo.setTag(tag);
                vo.setI_name(i_name);
                vo.setI_main_img(i_main_img);
                vo.setI_sub_img1(i_sub_img1);
                vo.setI_sub_img2(i_sub_img2);
                vo.setI_sub_img3(i_sub_img3);
                vo.setDeparture_num(departure_num);
                vo.setI_oder_num(i_oder_num);
                vo.setInfo_img(info_img);
                vo.setI_hit(i_hit);
                vo.setI_area(i_area);
                vo.setTitle(title);
                vo.setOder_info(oder_info);
                vo.setOder_re(oder_re);
                vo.setI_date_num(i_date_num);
                vo.setI_date(i_date);
                vo.setItem_num(item_num);
                vo.setPrice(price);

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

    //상품 판매 완료
    public boolean Itemsales(String userId,String itemNum,String i_Date,String date_num,String person,String price){
        int result = 0;
        String sql = "INSERT INTO SALES_TABLE (SALE_NUM,USERID,ITEM_NUM,I_DATE,DATE_NUM,PERSON,PRICE) VALUES ('S' || SIRIAL_NUM.NEXTVAL,?,?,TO_DATE(?,'YYYY-MM-DD'),?,?,?)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userId);
            pStmt.setString(2, itemNum);
            pStmt.setString(3, i_Date);
            pStmt.setString(4, date_num);
            pStmt.setString(5, person);
            pStmt.setString(6, price);
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

    //판매완료 리스트 출력
    public List<SellVO> SellView(String userId) {
        List<SellVO> list = new ArrayList<>();
        String sql = null;
        System.out.println("userid: "+userId);
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT SALES_TABLE.SALE_NUM,SALES_TABLE.PRICE ,SALES_TABLE.USERID ,SALES_TABLE.ITEM_NUM ,SALES_TABLE.I_DATE ,SALES_TABLE.DATE_NUM,SALES_TABLE.PERSON,GOODS_TABLE.TITLE FROM SALES_TABLE LEFT JOIN GOODS_TABLE ON SALES_TABLE.ITEM_NUM = GOODS_TABLE.ITEM_NUM WHERE SALES_TABLE.USERID ='"+userId+"'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String sale_num =rs.getString("SALE_NUM");
                String userid =rs.getString("USERID");
                String item_num=rs.getString("ITEM_NUM");
                String i_date=rs.getString("I_DATE");
                Integer date_num=rs.getInt("DATE_NUM");
                Integer person=rs.getInt("PERSON");
                Integer price=rs.getInt("PRICE");
                String title=rs.getString("TITLE");

                SellVO vo = new SellVO();
                vo.setUserid(userid);
                vo.setSale_num(sale_num);
                vo.setItem_num(item_num);
                vo.setI_date(i_date);
                vo.setDate_num(date_num);
                vo.setPerson(person);
                vo.setPrice(price);
                vo.setTitle(title);

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

    //구매 취소
    public boolean SaleCancle(String sale_num){
        int result = 0;
        String sql = "DELETE FROM SALES_TABLE WHERE SALE_NUM = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, sale_num);
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

    // 조회수 증가
    public boolean GoodsHitup(String item_num){
        int result = 0;
        String sql =  "UPDATE GOODS_TABLE SET I_HIT = I_HIT + 1 WHERE ITEM_NUM = ? ";
        System.out.println(item_num);
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, item_num);
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

    //즐겨찾기 정보가져가기
    public List<GoodsVO> faveoList(String id) {

        List<GoodsVO> list = new ArrayList<>();
        try {

            String sql ="  SELECT * FROM FAVOR, GOODS_TABLE WHERE FAVOR.COURSE_CODE = GOODS_TABLE.ITEM_NUM AND FAVOR.USERID =  ?  ";
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
//            pStmt.setString(1, type);
            pStmt.setString(1, id);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                System.out.println("222222222222222");
                String PRICE = rs.getString("PRICE");
                System.out.println("333333333333");
                String TAG = rs.getString("TAG");
                System.out.println("44444444444444444");
                String I_NAME = rs.getString("I_NAME");
                String TITLE = rs.getString("TITLE");
                String I_MAIN_IMG =rs.getString("I_MAIN_IMG");

                System.out.println(   PRICE);
                System.out.println(   TAG);
                System.out.println(   TITLE);

                GoodsVO vo = new GoodsVO();
                vo.setPrice(PRICE);
                vo.setTag(TAG);
                vo.setI_name(I_NAME);
                vo.setTitle(TITLE);
                vo.setI_main_img(I_MAIN_IMG);
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
