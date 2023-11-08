package wonderwalker.project.mini.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wonderwalker.project.mini.dao.UserInfoDAO;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
// 3000번포트로 넘어갔다가 8011번포트로 넘어가면 에러인데 이걸 풀기위해 CrossOrigin으로 선언하여 풀어줌
@RestController
// restful-API ??? 컨트롤러
@RequestMapping("/users")
// 그룹을 위한 Mapping
public class UserControlloer {

    @PostMapping("/login")
    //I아이디,비밀번호를 받아와서 데이터베이스와
    public ResponseEntity<Boolean> memberLogin(@RequestBody Map<String, String> loginData) {
        String userid = loginData.get("id");
        String userPwd = loginData.get("pwd");
        System.out.println("ID : " + userid);
        System.out.println("PWD : " + userPwd);
        UserInfoDAO dao = new UserInfoDAO();
        boolean result = dao.loginCheck(userid, userPwd);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/idcheck")
    public ResponseEntity<Boolean> memberCheck(@RequestParam String id) {
        System.out.println("회원 가입 여부 확인 ID : " + id);
        UserInfoDAO dao = new UserInfoDAO();
        boolean isTrue = dao.SingupIdCheck(id);
        System.out.println(isTrue);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    @PostMapping("/signup")
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
