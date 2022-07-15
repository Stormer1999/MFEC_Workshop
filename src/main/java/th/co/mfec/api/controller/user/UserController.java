package th.co.mfec.api.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.co.mfec.api.model.common.SuccessResponse;
import th.co.mfec.api.model.user.*;
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

  @PostMapping("/create")
  public ResponseEntity<SuccessResponse<UserResponse>> create(
      @RequestBody UserRequest userRegisterRequest) {
     return ResponseEntity.ok(new SuccessResponse<>(userService.createUser(userRegisterRequest)));
  }

  @PostMapping("/register")
  public ResponseEntity<SuccessResponse<UserRegisterResponse>> register(
      @RequestBody UserRegisterRequest userRegisterRequest) {
//    SuccessResponse<UserRegisterResponse> successResponse = new SuccessResponse<>();
//    UserRegisterResponse userResponse = userService.registerUser(userRegisterRequest);
//    successResponse.setData(userResponse);

//    return ResponseEntity.ok(successResponse);
    return ResponseEntity.ok(new SuccessResponse<>(userService.registerUser(userRegisterRequest)));
  }

  @PostMapping("/authen")
  public ResponseEntity<SuccessResponse<UserAuthenResponse>> authen(
      @RequestBody UserAuthenRequest userAuthenRequest) {
    return ResponseEntity.ok(new SuccessResponse<>(userService.authenUser(userAuthenRequest)));
  }
}
