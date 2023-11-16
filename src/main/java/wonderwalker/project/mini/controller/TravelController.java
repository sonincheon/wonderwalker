package wonderwalker.project.mini.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wonderwalker.project.mini.dao.TravelDAO;
import wonderwalker.project.mini.vo.Travel2VO;
import wonderwalker.project.mini.vo.TravelVO;

import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://192.168.10.23:3000")
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

    public ResponseEntity<List<TravelVO>> TravelInfo2(@RequestParam String travelNum){

        System.out.println("travel_num: "+travelNum);
        TravelDAO dao =new TravelDAO();
        List<TravelVO> list = dao.TravelContent(travelNum);

        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PostMapping("DiyWrite")
    public ResponseEntity<Boolean> travelinsert(@RequestBody Map<String, String> insert) {
        String travel_userid=insert.get("userId");
        String travel_world =insert.get("world");
        String travel_area=insert.get("area");
        String travel_startdate= insert.get("toDate");
        String travel_enddate= insert.get("toDate1");
        String travel_theme=insert.get("theme");
        String travel_title=insert.get("title");

        TravelDAO dao = new TravelDAO();
        boolean result = dao.travelInsert(travel_world, travel_area, travel_theme, travel_title, travel_startdate,travel_enddate, travel_userid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    //조회수증가 + 2번째 출력가능
    @GetMapping("Diyviewitem")
    public ResponseEntity<List<TravelVO>> travelinsert2(@RequestParam  String travelNum) {
        System.out.println("inserttravel");
        TravelDAO dao = new TravelDAO();
        List<TravelVO> list =dao.TravelContent(travelNum);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("SelectDIY")
    public ResponseEntity<List<Travel2VO>> SelectDIY(@RequestParam  String travelNum) {
        System.out.println("inserttravel");
        TravelDAO dao = new TravelDAO();
        List<Travel2VO> list =dao.SelectDIY(travelNum);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //즐겨찾기 가져가기
    @GetMapping("/SelectFavor")
    public ResponseEntity<List<TravelVO>> SelectFavor(@RequestParam String id) {
        System.out.println("SelectFavorSelectFavorSelectFavorSelectFavorSelectFavorSelectFavorSelectFavor");
        TravelDAO dao= new TravelDAO();
        List<TravelVO> list =dao.faveoList(id);
        System.out.println("12312321");
         System.out.println(   list.get(0).getTravel_title());
        return new ResponseEntity<>(list, HttpStatus.OK);


    }






}