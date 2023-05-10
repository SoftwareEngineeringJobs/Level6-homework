package live.baize.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import live.baize.entity.School;
import live.baize.service.SchoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * SchoolController
 *
 * @author CodeXS
 * @since 2023-05-10
 */
@RestController
@RequestMapping("/school")
public class SchoolController {


    @Resource
    private SchoolService schoolService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<School>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<School> aPage = schoolService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<School> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(schoolService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody School params) {
        schoolService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        schoolService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody School params) {
        schoolService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
