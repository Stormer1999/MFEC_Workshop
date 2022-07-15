package th.co.mfec.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.co.mfec.api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  User findByUsername(String username);
}
