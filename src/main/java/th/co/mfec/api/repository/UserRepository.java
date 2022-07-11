package th.co.mfec.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.co.mfec.api.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findByUsername(String username);
}
