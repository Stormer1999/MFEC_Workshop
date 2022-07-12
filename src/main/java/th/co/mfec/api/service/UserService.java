package th.co.mfec.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.mfec.api.entity.User;
import th.co.mfec.api.model.user.UserRequest;
import th.co.mfec.api.model.user.UserResponse;
import th.co.mfec.api.repository.UserRepository;

import java.util.Date;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserResponse createUser(UserRequest userRequest) {
    User user = new User();
    user.setUsername(userRequest.getUsername());
    user.setPassword(userRequest.getPassword());
    user.setIsEnable('Y');
    user.setIsLocked('N');
    user.setExpiredDate(new Date());
    user.setCreatedBy(1);
    user.setCreatedAt(new Date());
    user.setDeleteFlag('N');
    userRepository.save(user);

    UserResponse userResponse = new UserResponse();
    userResponse.setId(user.getId());
    userResponse.setUsername(user.getUsername());
    userResponse.setExpiredDate(user.getExpiredDate());
    return userResponse;
  }
}
