package wonderwalker.project.mini.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReplyVO {
    private int commentNum;
    private String travelComment;
    private String userId;
    private int travelScope;
    private Date travelWriteDate;
    private String travelValue;
    private String travelNum;
    private int boardNum;


}
