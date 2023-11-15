package wonderwalker.project.mini.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wonderwalker.project.mini.dao.CourseDAO;
import wonderwalker.project.mini.vo.CourseVO;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/Course")
public class CourseControlloer {

    // GET : 코스 목록 조회
    @GetMapping("")
    public ResponseEntity<List<CourseVO>> courseList(@RequestParam String course_area) {
        System.out.println("corese" + course_area);
        CourseDAO dao = new CourseDAO();
        List<CourseVO> list = dao.selectCourseList(course_area);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    // GET : 코스 상세 조회
    @GetMapping("/Info")
    //조회정보 가져올떈 리퀘어파람으로 id값 뺴오기
    public ResponseEntity<List<CourseVO>> courseDetail(@RequestParam String course_code) {
        CourseDAO dao = new CourseDAO();
        List<CourseVO> list = dao.selectCourseDetail(course_code);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //즐겨찾기 가져가기
    @GetMapping("/SelectFavor")
    public ResponseEntity<List<CourseVO>> SelectFavor(@RequestParam String id) {
        CourseDAO dao= new CourseDAO();
        List<CourseVO> list =dao.faveoList(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
