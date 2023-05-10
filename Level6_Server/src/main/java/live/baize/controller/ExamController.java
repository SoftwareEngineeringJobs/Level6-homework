package live.baize.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import live.baize.entity.Exam;
import live.baize.service.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * ExamController
 *
 * @author CodeXS
 * @since 2023-05-10
 */
@RestController
@RequestMapping("/exam")
public class ExamController {


    @Resource
    private ExamService examService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<Exam>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Exam> aPage = examService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Exam> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(examService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Exam params) {
        examService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        examService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Exam params) {
        examService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
