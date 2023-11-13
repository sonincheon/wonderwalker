package wonderwalker.project.mini.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wonderwalker.project.mini.dao.TravelDAO;
import wonderwalker.project.mini.vo.Travel2VO;
import wonderwalker.project.mini.vo.TravelVO;

import java.util.List;


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

    @PostMapping("DiyWrite")
    public ResponseEntity<Boolean> travelinsert(@RequestBody TravelVO travelVO) {
        System.out.println("travel_num : " + travelVO.getTravel_num());
        System.out.println("travel_userid : " + travelVO.getTravel_userid());
        System.out.println("travel_world : " + travelVO.getTravel_world());
        System.out.println("travel_area : " + travelVO.getTravel_area());
        System.out.println("travel_startdate : " + travelVO.getTravel_startdate());
        System.out.println("travel_enddate : " + travelVO.getTravel_enddate());
        System.out.println("travel_theme : " + travelVO.getTravel_theme());
        System.out.println("travel_title : " + travelVO.getTravel_title());
        System.out.println("travel_writedate : " + travelVO.getTravel_writedate());
        TravelDAO dao = new TravelDAO();
        boolean isTrue = dao.travelInsert(travelVO);
        System.out.println("댓글 등록 결과 : " + isTrue);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    @PostMapping("DiyWrite/days")
    public ResponseEntity<Boolean> travelinsert2(@RequestBody Travel2VO travel2VO) {
        System.out.println("travel_map : " + travel2VO.getTravel_map());
        System.out.println("travel_pic : " + travel2VO.getTravel_pic());
        System.out.println("travel_writing : " + travel2VO.getTravel_writing());
        TravelDAO dao = new TravelDAO();
        boolean isTrue = dao.travelInsert2(travel2VO);
        System.out.println("댓글 등록 결과 : " + isTrue);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

}