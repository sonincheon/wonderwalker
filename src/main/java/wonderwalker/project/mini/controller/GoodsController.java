package wonderwalker.project.mini.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wonderwalker.project.mini.dao.GoodsDAO;
import wonderwalker.project.mini.vo.GoodsVO;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
// 3000번포트로 넘어갔다가 8011번포트로 넘어가면 에러인데 이걸 풀기위해 CrossOrigin으로 선언하여 풀어줌
@RestController
// restful-API ??? 컨트롤러
@RequestMapping("/Goods")
// 그룹을 위한 Mapping
public class GoodsController {
    //GET : 회원 조회
    @GetMapping("")
    //조회정보 가져올떈 리퀘어파람으로 id값 뺴오기
    public ResponseEntity<List<GoodsVO>> GoodsList(@RequestParam String world){

        System.out.println(" world :  " + world);
        GoodsDAO dao =new GoodsDAO(); //로그인
        List<GoodsVO> list = dao.GoodsSelect(world);
        //맴버 리스트 리턴해줌
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("info")
    //조회정보 가져올떈 리퀘어파람으로 id값 뺴오기
    public ResponseEntity<List<GoodsVO>> GoodsInfo(@RequestParam String itemCode){

        System.out.println("itemcode: "+itemCode);
        GoodsDAO dao =new GoodsDAO(); //로그인
        List<GoodsVO> list = dao.GoodsInfo(itemCode);
        //맴버 리스트 리턴해줌
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}