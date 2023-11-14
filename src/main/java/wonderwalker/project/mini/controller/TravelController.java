package wonderwalker.project.mini.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wonderwalker.project.mini.dao.TravelDAO;
import wonderwalker.project.mini.vo.Travel2VO;
import wonderwalker.project.mini.vo.TravelVO;

import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;


@CrossOrigin(origins = "http://localhost:3000")
// 3000번포트로 넘어갔다가 8111번포트로 넘어가면 에러인데 이걸 풀기위해 CrossOrigin으로 선언하여 풀어줌
@RestController

@RequestMapping("/DiyPage")
// 그룹을 위한 Mapping

public class TravelController {
    @GetMapping("")
    public ResponseEntity<List<TravelVO>> TravelInfo(@RequestParam String world, String theme){

        System.out.println(" world :  " + world);
        System.out.println("theme : "+ theme);
        TravelDAO dao =new TravelDAO();
        List<TravelVO> list = dao.TravelInfo(world, theme);
        System.out.println(list);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("Diyview")

    public ResponseEntity<List<Travel2VO>> TravelInfo2(@RequestParam String travelNum){

        System.out.println("travel_num: "+travelNum);
        TravelDAO dao =new TravelDAO();
        List<Travel2VO> list = dao.TravelContent(travelNum);

        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    //완료
    @PostMapping("DiyWrite")
        public ResponseEntity<String> travelinsert(@RequestBody Map<String, String> insert) {

        String travel_userid=insert.get("userId");
        String travel_world =insert.get("world");
        String travel_area=insert.get("area");
        String travel_startdate= insert.get("toDate");
        String travel_enddate= insert.get("toDate1");
        String travel_theme=insert.get("theme");
        String travel_title=insert.get("title");

        TravelDAO dao = new TravelDAO();
        String result = dao.travelInsert(travel_world, travel_area, travel_theme, travel_title, travel_startdate,travel_enddate, travel_userid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("DiyWrite/days")
    public ResponseEntity<Boolean> travelinsert2(@RequestBody Map<String, String> insert2) {
        System.out.println("inserttravel");
        String travel_num=insert2.get("tvNum");
        String d_day=insert2.get("day");
        String travel_map=insert2.get("url");
        String travel_pic =insert2.get("file");
        String travel_writing=insert2.get("content");
        System.out.println("왜안돼는대대대대");
        TravelDAO dao = new TravelDAO();
        boolean result =dao.travelInsert2(travel_num,d_day,travel_map,travel_pic,travel_writing);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }










}