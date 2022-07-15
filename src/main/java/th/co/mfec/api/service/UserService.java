package th.co.mfec.api.service;

import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import th.co.mfec.api.constant.StatusCode;
import th.co.mfec.api.entity.User;
import th.co.mfec.api.exception.BaseException;
import th.co.mfec.api.model.user.*;
import th.co.mfec.api.repository.UserRepository;
import th.co.mfec.api.security.util.JwtUtil;

import java.util.Date;

@Service
public class UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final JwtUtil jwtUtil;

  public UserService(
      UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtUtil = jwtUtil;
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

  public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) {
    User user = new User();
    user.setUsername(userRegisterRequest.getUsername());
    user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
    user.setIsEnable('Y');
    user.setIsLocked('N');
    user.setExpiredDate(new Date());
    user.setCreatedBy(1);
    user.setCreatedAt(new Date());
    user.setDeleteFlag('N');
    userRepository.save(user);

    UserRegisterResponse userResponse = new UserRegisterResponse();
    userResponse.setUserId(user.getId());
    userResponse.setUsername(user.getUsername());
    userResponse.setExpiredDate(user.getExpiredDate());

    return userResponse;
  }

  public UserAuthenResponse authenUser(UserAuthenRequest userAuthenRequest) {
    User user = userRepository.findByUsername(userAuthenRequest.getUsername());
    if (user == null) {
      throw new BaseException(
          HttpStatus.UNAUTHORIZED, StatusCode.ERR_CODE_401, StatusCode.ERR_DESC_401);
    }
    if (!passwordEncoder.matches(userAuthenRequest.getPassword(), user.getPassword())) {
      throw new BaseException(
          HttpStatus.UNAUTHORIZED, StatusCode.ERR_CODE_401, StatusCode.ERR_DESC_401);
    }

    String token = jwtUtil.generateToken(user.getUsername());
    UserAuthenResponse userAuthenResponse = new UserAuthenResponse();
    userAuthenResponse.setUserId(user.getId());
    userAuthenResponse.setUsername(user.getUsername());
    userAuthenResponse.setToken(token);

    return userAuthenResponse;
  }
}
