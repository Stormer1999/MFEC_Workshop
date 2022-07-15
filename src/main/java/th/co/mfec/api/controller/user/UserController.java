package th.co.mfec.api.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.co.mfec.api.model.common.SuccessResponse;
import th.co.mfec.api.model.user.UserRequest;
import th.co.mfec.api.model.user.UserResponse;
import th.co.mfec.api.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  // http://localhost:8080/api/v1/user/register

  @GetMapping("/")
  public String hello() {
    return "hello haha";
  } // self met

  @GetMapping("/test/{id}")
  public String test(@PathVariable String id) {
    return id;
  }

  //  @PostMapping("/create")
  //  public ResponseEntity<SuccessResponse<UserResponse>> create(
  //      @RequestBody UserRequest userRequest) {
  //    return null;
  //  }

  @PostMapping("/register")
  public ResponseEntity<SuccessResponse<UserResponse>> register(
      @RequestBody UserRequest userRequest) {
    SuccessResponse<UserResponse> successResponse = new SuccessResponse<>();
    UserResponse userResponse = userService.createUser(userRequest);
    successResponse.setData(userResponse);

    return ResponseEntity.ok(successResponse);
    // return ResponseEntity.ok(new SuccessResponse<>(userService.createUser(userRequest)));
  }
}
