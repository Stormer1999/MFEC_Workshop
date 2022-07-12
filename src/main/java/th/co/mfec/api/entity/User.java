package th.co.mfec.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER")
@Getter
@Setter
public class User {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "user_name")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "is_enabled")
  private Character isEnable;

  @Column(name = "is_locked")
  private Character isLocked;

  @Column(name = "expired_date")
  private Date expiredDate;

  @Column(name = "deleted_flag")
  private Character deleteFlag;

  @Column(name = "created_by")
  private Integer createdBy;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "updated_by")
  private Integer updatedBy;

  @Column(name = "updated_at")
  private Date updatedAt;

  @Column(name = "deleted_by")
  private Integer deletedBy;

  @Column(name = "deleted_at")
  private Date deletedAt;
}
