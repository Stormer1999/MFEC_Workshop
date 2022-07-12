package th.co.mfec.api;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import th.co.mfec.api.model.user.UserRequest;
import th.co.mfec.api.model.user.UserResponse;
import th.co.mfec.api.service.UserService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

  private final UserService userService;

  @Autowired
  public UserServiceTest(UserService userService) {
    this.userService = userService;
  }

  interface DataTestCreateUser {
    String username = "email@hotmail.com";
    String password = "password";
  }

  @Order(1)
  @Test
  void testCreateUser() {
    UserRequest userRequest = new UserRequest();
    userRequest.setUsername(DataTestCreateUser.username);
    userRequest.setPassword(DataTestCreateUser.password);
    UserResponse userResponse = userService.createUser(userRequest);

    Assertions.assertNotNull(userResponse);
    Assertions.assertNotNull(userResponse.getId());
    Assertions.assertEquals(DataTestCreateUser.username, userResponse.getUsername());
  }
}
