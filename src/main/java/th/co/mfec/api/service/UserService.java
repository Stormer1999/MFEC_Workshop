package th.co.mfec.api.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import th.co.mfec.api.constant.StatusCode;
import th.co.mfec.api.entity.User;
import th.co.mfec.api.entity.UserProfile;
import th.co.mfec.api.exception.BaseException;
import th.co.mfec.api.model.user.request.UserAuthenRequest;
import th.co.mfec.api.model.user.request.UserProfileRequest;
import th.co.mfec.api.model.user.request.UserRegisterRequest;
import th.co.mfec.api.model.user.request.UserRequest;
import th.co.mfec.api.model.user.response.UserAuthenResponse;
import th.co.mfec.api.model.user.response.UserProfileResponse;
import th.co.mfec.api.model.user.response.UserRegisterResponse;
import th.co.mfec.api.model.user.response.UserResponse;
import th.co.mfec.api.repository.AddressRepository;
import th.co.mfec.api.repository.UserProfileRepository;
import th.co.mfec.api.repository.UserRepository;
import th.co.mfec.api.security.util.JwtUtil;

import java.util.Date;

@Service
public class UserService {

  private final UserRepository userRepository;

  private final UserProfileRepository userProfileRepository;

  private final AddressRepository addressRepository;

  private final PasswordEncoder passwordEncoder;

  private final JwtUtil jwtUtil;

  public UserService(
      UserRepository userRepository,
      UserProfileRepository userProfileRepository,
      AddressRepository addressRepository,
      PasswordEncoder passwordEncoder,
      JwtUtil jwtUtil) {
    this.userRepository = userRepository;
    this.userProfileRepository = userProfileRepository;
    this.addressRepository = addressRepository;
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

  public UserAuthenResponse refreshToken() {
    String username =
        (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userRepository.findByUsername(username);

    String token = jwtUtil.generateToken(user.getUsername());
    UserAuthenResponse userAuthenResponse = new UserAuthenResponse();
    userAuthenResponse.setUserId(user.getId());
    userAuthenResponse.setUsername(user.getUsername());
    userAuthenResponse.setToken(token);

    return userAuthenResponse;
  }

  public UserProfileResponse updateUserProfile(UserProfileRequest userProfileRequest) {
    String username =
        (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    User user = userRepository.findByUsername(username);

    UserProfile userProfile = new UserProfile();
    userProfile.setUser(user);
    userProfile.setFirstNameTh(userProfileRequest.getFirstNameTh());
    userProfile.setLastNameTh(userProfile.getLastNameTh());
    userProfile.setFirstNameEn(userProfile.getFirstNameEn());
    userProfile.setLastNameEn(userProfile.getLastNameEn());
    userProfile.setMobileNumber(userProfile.getMobileNumber());
    userProfile.setBirthDate(userProfile.getBirthDate());
    userProfile.setDeleteFlag('N');
    userProfile.setCreatedBy(user.getId());
    userProfile.setCreatedAt(new Date());
    userProfileRepository.save(userProfile);

    UserProfileResponse userProfileResponse = new UserProfileResponse();
    userProfileResponse.setFirstNameTh(userProfile.getFirstNameTh());
    userProfileResponse.setLastNameTh(userProfile.getLastNameTh());
    userProfileResponse.setFirstNameEn(userProfile.getFirstNameEn());
    userProfileResponse.setLastNameEn(userProfile.getLastNameEn());
    userProfileResponse.setMobileNumber(userProfile.getMobileNumber());
    userProfileResponse.setBirthDate(userProfile.getBirthDate());

    return userProfileResponse;
  }
}
