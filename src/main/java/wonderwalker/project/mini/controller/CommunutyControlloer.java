package wonderwalker.project.mini.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wonderwalker.project.mini.dao.CommunityDAO;
import wonderwalker.project.mini.dao.UserInfoDAO;
import wonderwalker.project.mini.vo.CommunityVO;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
// 3000번포트로 넘어갔다가 8011번포트로 넘어가면 에러인데 이걸 풀기위해 CrossOrigin으로 선언하여 풀어줌
@RestController
// restful-API ??? 컨트롤러
@RequestMapping("/community")
// 그룹을 위한 Mapping
public class CommunutyControlloer {
    //  모든 게시글 조회
    @GetMapping("/SelectAllCommunity")
    public ResponseEntity<List<CommunityVO>> CommunityAll() {
        CommunityDAO dao = new CommunityDAO();
        List<CommunityVO> list = dao.SelectAllCommunity();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    //  게시글  한개 조회
    @GetMapping("/SelectOneCommunity")
    public ResponseEntity<List<CommunityVO>> CommunityOne(@RequestParam int num) {
        System.out.println("찾을 넘버"+num);
        System.out.println("11asd5sa65dsa4ds65a54d");
        CommunityDAO dao = new CommunityDAO();
        List<CommunityVO> list = dao.SelectOneCommunity(num);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //게시글 추가
    @GetMapping("/InsertCommunity")
    public ResponseEntity<Boolean> CommunityInsert(@RequestBody Map<String, String> content) {
        String  ueryId=content.get("userid");
       Date reportingDate= Date.valueOf(content.get("reportingDate"));
       int views= Integer.parseInt(content.get("views"));
       String title=content.get("Title");
       String content1=content.get("constent1");
        CommunityDAO dao = new CommunityDAO();

        boolean result =dao.InsertCommunity(ueryId,reportingDate,views,title,content1);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/2")
    public ResponseEntity<Boolean> memberCheck(@RequestParam String id) {
        System.out.println("회원 가입 여부 확인 ID : " + id);
        UserInfoDAO dao = new UserInfoDAO();
        boolean isTrue = dao.SingupIdCheck(id);
        System.out.println(isTrue);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    @PostMapping("/1")
    public ResponseEntity<Boolean> SignUp(@RequestBody Map<String, String>  userInfo) {
        System.out.println("olol22222xxxxx");

        String userid=userInfo.get("id");
    String userPwd=userInfo.get("pwd");
    String nick=userInfo.get("nick");
    String userName=userInfo.get("name");
    String addr=userInfo.get("addr");
    String phone=userInfo.get("phone");
    String email=userInfo.get("email");
        UserInfoDAO dao = new UserInfoDAO();
        boolean isTrue = dao.SignUp(userid,userPwd,nick,userName,addr,phone,email);
        System.out.println(isTrue);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }


}
