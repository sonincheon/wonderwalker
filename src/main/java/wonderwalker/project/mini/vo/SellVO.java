package wonderwalker.project.mini.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SellVO {
    private String userid;
    private String sale_num;
    private String item_num;
    private String i_date;
    private Integer date_num;
    private Integer person;
    private Integer price;
    private String title; // 상품명 조인

}
