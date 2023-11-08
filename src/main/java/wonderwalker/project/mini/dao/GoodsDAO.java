package wonderwalker.project.mini.dao;

import wonderwalker.project.mini.comon.Common;
import wonderwalker.project.mini.vo.GoodsVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    //상품 리스트 출력 구문
    public List<GoodsVO> GoodsSelect(String world) {
        List<GoodsVO> list = new ArrayList<>();
        String sql = null;
        System.out.println("world : " + world);

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            if (world.equals("korea")) sql = "SELECT * FROM GOODS_TABLE WHERE I_WORLD='국내'";
            else sql = "SELECT * FROM GOODS_TABLE WHERE I_WORLD='해외 '";
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
}
