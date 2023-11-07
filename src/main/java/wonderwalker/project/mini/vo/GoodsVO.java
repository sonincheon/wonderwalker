package wonderwalker.project.mini.vo;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
public class GoodsVO {
    private Integer item_num;
    private String i_world;
    private Integer price;
    private String tag;
    private String i_name;
    private String title;
    private String i_main_img ;
    private String i_sub_img1;
    private String i_sub_img2;
    private String i_sub_img3;
    private Integer departure_num;
    private Date i_date;
    private Integer i_date_num;
    private Integer i_oder_num;
    private String info_img;
    private Integer i_hit;
    private String i_area;
    private String oder_re;
    private String oder_info;
}