package wonderwalker.project.mini.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wonderwalker.project.mini.dao.CommunityDAO;
import wonderwalker.project.mini.dao.ReplyDAO;
import wonderwalker.project.mini.dao.UserInfoDAO;
import wonderwalker.project.mini.vo.CommunityVO;
import wonderwalker.project.mini.vo.ReplyVO;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
// 3000번포트로 넘어갔다가 8011번포트로 넘어가면 에러인데 이걸 풀기위해 CrossOrigin으로 선언하여 풀어줌
@RestController
// restful-API ??? 컨트롤러
@RequestMapping("/Reply")
// 그룹을 위한 Mapping
public class ReplyControlloer {
    //  모든 댓글 조회
    @GetMapping("/SelectReply")
    public ResponseEntity<List<ReplyVO>> SelectReply(@RequestParam int num) {
    ReplyDAO dao = new ReplyDAO();
        List<ReplyVO> list = dao.SelectReply(num);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

//    리플 등록
@PostMapping("/insertReply")
public ResponseEntity<Boolean> InsertReply(@RequestBody Map<String, String> replyData) {
    System.out.println("나오니?");
    String userId = replyData.get("userid");
    String replyInsert = replyData.get("replyInsert");
    int num = Integer.parseInt(replyData.get("num"));
    System.out.println("확인용 " + userId);
    System.out.println(replyInsert);
    ReplyDAO dao = new ReplyDAO();
    boolean isTrue = dao.InsertReply(replyInsert ,userId, num);
    return new ResponseEntity<>(isTrue, HttpStatus.OK);
}

}
