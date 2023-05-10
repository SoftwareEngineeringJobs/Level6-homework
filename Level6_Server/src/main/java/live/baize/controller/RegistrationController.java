package live.baize.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import live.baize.entity.Registration;
import live.baize.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * RegistrationController
 *
 * @author CodeXS
 * @since 2023-05-10
 */
@RestController
@RequestMapping("/registration")
public class RegistrationController {


    @Resource
    private RegistrationService registrationService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<Registration>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Registration> aPage = registrationService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Registration> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(registrationService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Registration params) {
        registrationService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        registrationService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Registration params) {
        registrationService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
