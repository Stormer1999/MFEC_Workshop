package th.co.mfec.api.controller.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  // http://localhost:8080/api/v1/user/register

  @GetMapping("/")
  public String hello() {
    return "hello haha";
  } // self met

  @GetMapping("/test/{id}")
  public String test(@PathVariable String id) {
    return id;
  }
}
