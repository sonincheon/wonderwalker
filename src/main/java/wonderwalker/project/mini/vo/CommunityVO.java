package wonderwalker.project.mini.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommunityVO {
    private int communityNum;
    private String uerId;
    private Date reportingDate;
    private int  views;
    private String title;
    private String content;


}
