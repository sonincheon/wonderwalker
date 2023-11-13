package wonderwalker.project.mini.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wonderwalker.project.mini.dao.GoodsDAO;
import wonderwalker.project.mini.vo.GoodsVO;
import wonderwalker.project.mini.vo.SellVO;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
// 3000번포트로 넘어갔다가 8011번포트로 넘어가면 에러인데 이걸 풀기위해 CrossOrigin으로 선언하여 풀어줌
@RestController
// restful-API ??? 컨트롤러
@RequestMapping("/Goods")
// 그룹을 위한 Mapping
public class GoodsController {
    //상품리스트
    @GetMapping("")
    //조회정보 가져올떈 리퀘어파람으로 id값 뺴오기
    public ResponseEntity<List<GoodsVO>> GoodsList(@RequestParam String world,String area){

        System.out.println(" world :  " + world);
        GoodsDAO dao =new GoodsDAO(); //로그인
        List<GoodsVO> list = dao.GoodsSelect(world,area);
        //맴버 리스트 리턴해줌
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("info")
    //조회정보 가져올떈 리퀘어파람으로 id값 뺴오기
    public ResponseEntity<List<GoodsVO>> GoodsInfo(@RequestParam String itemCode){

        System.out.println("itemcode: "+itemCode);
        GoodsDAO dao =new GoodsDAO(); //로그인
        List<GoodsVO> list = dao.GoodsInfo(itemCode);

        System.out.println("테스트중"+list.get(0).getDeparture_num());
        //맴버 리스트 리턴해줌
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    //상품판매
    @PostMapping("/Complete")
    public ResponseEntity<Boolean> ItemSales(@RequestBody Map<String, String> salesInfo) {

        String userId =salesInfo.get("id");
        String itemNum =salesInfo.get("itemNum");
        String i_Date =salesInfo.get("i_date");
        String date_num =salesInfo.get("date_num");
        String person =salesInfo.get("person");
        String price =salesInfo.get("price");

        System.out.println("되겠냐?");

        GoodsDAO dao = new GoodsDAO();
        boolean isTrue = dao.Itemsales(userId,itemNum,i_Date,date_num,person,price);
        System.out.println(isTrue);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }
    //구매 리스트 출력
    @GetMapping("/cancle")
    //조회정보 가져올떈 리퀘어파람으로 id값 뺴오기
    public ResponseEntity<List<SellVO>> SellList(@RequestParam String userId){

        System.out.println(" userId :  " + userId);
        GoodsDAO dao =new GoodsDAO(); //로그인
        List<SellVO> list = dao.SellView(userId);
        //맴버 리스트 리턴해줌
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    //구매 취소
    @PostMapping("/cancle")
    public ResponseEntity<Boolean> SaleCancle(@RequestBody Map<String, String> salesCancle) {

        String saleNum =salesCancle.get("sale_num");
        System.out.println("되겠냐?");
        GoodsDAO dao = new GoodsDAO();
        boolean isTrue = dao.SaleCancle(saleNum);
        System.out.println(isTrue);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }
    //상품 조회 수증가
    @PostMapping("")
    public ResponseEntity<Boolean> GoodsHitup(@RequestBody Map<String, String> itemnum) {
        System.out.println(itemnum);
        String itemNum =itemnum.get("item_num");
        GoodsDAO dao = new GoodsDAO();
        boolean isTrue = dao.GoodsHitup(itemNum);
        System.out.println(isTrue);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }







}