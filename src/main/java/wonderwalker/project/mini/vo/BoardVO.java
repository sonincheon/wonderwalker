package wonderwalker.project.mini.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BoardVO {
    private int boardNum;
    private String uerId;
    private Date reportingDate;
    private int  views;
    private String title;
    private String Content;

}
