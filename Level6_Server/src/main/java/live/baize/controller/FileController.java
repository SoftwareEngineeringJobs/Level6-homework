package live.baize.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import live.baize.entity.File;
import live.baize.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * FileController
 *
 * @author CodeXS
 * @since 2023-05-10
 */
@RestController
@RequestMapping("/file")
public class FileController {


    @Resource
    private FileService fileService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<File>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<File> aPage = fileService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<File> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(fileService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody File params) {
        fileService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        fileService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody File params) {
        fileService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
