package wonderwalker.project.mini.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wonderwalker.project.mini.dao.TravelDAO;
import wonderwalker.project.mini.vo.TravelVO;

import java.sql.SQLOutput;
import java.util.List;

@CrossOrigin(origins = "*")
// 3000번포트로 넘어갔다가 8111번포트로 넘어가면 에러인데 이걸 풀기위해 CrossOrigin으로 선언하여 풀어줌
@RestController
// restful-API ??? 컨트롤러
@RequestMapping("/DiyPage")
// 그룹을 위한 Mapping

public class TravelController {
    @GetMapping("/Diyview")
    //조회정보 가져올떈 리퀘어파람으로 id값 뺴오기
    public ResponseEntity<List<TravelVO>> TravelInfo(@RequestParam String world, String theme){

        System.out.println(" world :  " + world);
        System.out.println("theme : "+ theme);
        TravelDAO dao =new TravelDAO(); //로그인
        List<TravelVO> list = dao.TravelInfo(world, theme);
        System.out.println(list);
        //맴버 리스트 리턴해줌
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/Diyview")
    //조회정보 가져올떈 리퀘어파람으로 id값 뺴오기
    public ResponseEntity<List<TravelVO>> TravelInfo2(@RequestParam String travelNum){

        System.out.println("travelNum: "+travelNum);
        TravelDAO dao =new TravelDAO();
        List<TravelVO> list = dao.TravelContent(travelNum);
        //맴버 리스트 리턴해줌
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

//    @GetMapping("/DiyWrite")
//
//    public ResponseEntity<List<TravelVO>> TravelContent(@RequestParam String travelNum, Integer travelDday, String travelPic, String travelWriting, String travelMap){
//
//        System.out.println("travelNum: "+travelNum,"travelDday: "+travelDday,"travelPic: "+travelPic,"travelWriting: "+travelWriting, "travelMap: "+travelMap);
//        TravelDAO dao =new TravelDAO(); //로그인
//        List<TravelVO> list = dao.TravelContent(travelNum,travelDday,travelPic,travelWriting,travelMap);
//        //맴버 리스트 리턴해줌
//        return new ResponseEntity<>(list,HttpStatus.OK);
//    }

}