package live.baize.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import live.baize.entity.Admin;
import live.baize.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * AdminController
 *
 * @author CodeXS
 * @since 2023-05-10
 */
@RestController
@RequestMapping("/admin")
public class AdminController {


    @Resource
    private AdminService adminService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<Admin>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Admin> aPage = adminService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Admin> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(adminService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Admin params) {
        adminService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        adminService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Admin params) {
        adminService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
