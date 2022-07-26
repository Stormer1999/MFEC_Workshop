package th.co.mfec.api.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import th.co.mfec.api.constant.StatusCode;
import th.co.mfec.api.entity.Address;
import th.co.mfec.api.entity.User;
import th.co.mfec.api.entity.UserProfile;
import th.co.mfec.api.exception.BaseException;
import th.co.mfec.api.model.user.request.*;
import th.co.mfec.api.model.user.response.*;
import th.co.mfec.api.repository.AddressRepository;
import th.co.mfec.api.repository.UserProfileRepository;
import th.co.mfec.api.repository.UserRepository;
import th.co.mfec.api.security.util.JwtUtil;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userRepository.findByUsername(username);

    UserProfile userProfile = new UserProfile();
    userProfile.setUser(user);
    userProfile.setFirstNameTh(userProfileRequest.getFirstNameTh());
    userProfile.setLastNameTh(userProfileRequest.getLastNameTh());
    userProfile.setFirstNameEn(userProfileRequest.getFirstNameEn());
    userProfile.setLastNameEn(userProfileRequest.getLastNameEn());
    userProfile.setMobileNumber(userProfileRequest.getMobileNumber());
    userProfile.setBirthDate(userProfileRequest.getBirthDate());
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

  @Transactional
  public UserAddressResponse updateUserAddress(UserAddressRequest userAddressRequest) {
    String username =
        (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userRepository.findByUsername(username);

    List<Address> addresses = new ArrayList<>();
    Address address;
    for (AddressRequest addressRequest : userAddressRequest.getAddresses()) {
      address = new Address();
      address.setUser(user);
      address.setLine1(addressRequest.getLine1());
      address.setLine2(addressRequest.getLine2());
      address.setPostcode(addressRequest.getPostcode());
      address.setType(addressRequest.getType());
      address.setPrefer(addressRequest.getPrefer());
      address.setDeleteFlag('N');
      address.setCreatedBy(user.getId());
      address.setCreatedAt(new Date());
      addresses.add(address);
    }
    addressRepository.saveAll(addresses);

    UserAddressResponse userAddressResponse = new UserAddressResponse();
    List<AddressResponse> responseList = new ArrayList<>();
    AddressResponse addressResponse;
    for (Address addr : addresses) {
      addressResponse = new AddressResponse();
      addressResponse.setLine1(addr.getLine1());
      addressResponse.setLine2(addr.getLine2());
      addressResponse.setPostcode(addr.getPostcode());
      addressResponse.setType(addr.getType());
      addressResponse.setPrefer(addr.getPrefer());
      responseList.add(addressResponse);
    }
    userAddressResponse.setAddresses(responseList);

    return userAddressResponse;
  }

  public UserProfileAddressesResponse getUserProfileAddress() {
    String username =
        (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userRepository.findByUsername(username);

    UserProfileAddressesResponse userProfileAddressesResponse = new UserProfileAddressesResponse();
    UserResponse userResponse = new UserResponse();
    userResponse.setId(user.getId());
    userResponse.setUsername(user.getUsername());
    userResponse.setExpiredDate(user.getExpiredDate());
    userProfileAddressesResponse.setUser(userResponse);

    UserProfileResponse userProfileResponse = new UserProfileResponse();
    if (user.getUserProfile() != null) {
      userProfileResponse.setFirstNameTh(user.getUserProfile().getFirstNameTh());
      userProfileResponse.setLastNameTh(user.getUserProfile().getLastNameTh());
      userProfileResponse.setFirstNameEn(user.getUserProfile().getFirstNameEn());
      userProfileResponse.setLastNameEn(user.getUserProfile().getLastNameEn());
      userProfileResponse.setMobileNumber(user.getUserProfile().getMobileNumber());
      userProfileResponse.setBirthDate(user.getUserProfile().getBirthDate());
    }
    userProfileAddressesResponse.setProfile(userProfileResponse);

    UserAddressResponse userAddressResponse = new UserAddressResponse();
    List<AddressResponse> addresses = new ArrayList<>();
    AddressResponse addressResponse;
    if (user.getAddresses() != null) {
      for (Address address : user.getAddresses()) {
        addressResponse = new AddressResponse();
        addressResponse.setLine1(address.getLine1());
        addressResponse.setLine2(address.getLine2());
        addressResponse.setPostcode(address.getPostcode());
        addressResponse.setType(address.getType());
        addressResponse.setPrefer(address.getPrefer());
        addresses.add(addressResponse);
      }
    }
    userAddressResponse.setAddresses(addresses);
    userProfileAddressesResponse.setAddress(userAddressResponse);

    return userProfileAddressesResponse;
  }
}
