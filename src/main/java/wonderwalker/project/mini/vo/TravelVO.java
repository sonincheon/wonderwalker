package wonderwalker.project.mini.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter

public class TravelVO {
    private String travel_num;
    private String travel_world;
    private String travel_theme;
    private String travel_area;
    private String travel_title;
    private Date travel_startdate;
    private Date travel_enddate;
    private String travel_userid;
    private Date travel_writedate;
    private Integer travel_view;
    private Integer travel_good;
    private Date d_day;
    private String travel_pic;
    private String travel_writing;
    private String travel_map;
}
