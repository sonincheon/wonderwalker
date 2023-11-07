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
                String title = rs.getString("TITLE");
                String info1 = rs.getString("ODER_REQIURE");
                String info2 = rs.getString("ODER_INFO");
                Integer date_num = rs.getInt("I_DATE_NUM");
                Date date = rs.getDate("I_DATE");
                Integer code_num =rs.getInt("ITEM_NUM");
                Integer price =rs.getInt("PRICE");
                GoodsVO vo = new GoodsVO();
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

}
