package th.co.mfec.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER_PROFILE")
@Getter
@Setter
public class UserProfile {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "first_name_th")
  private String firstNameTh;

  @Column(name = "last_name_th")
  private String lastNameTh;

  @Column(name = "first_name_en")
  private String firstNameEn;

  @Column(name = "last_name_en")
  private String lastNameEn;

  @Column(name = "mobile_number")
  private String mobileNumber;

  @Column(name = "birth_date")
  private Date birthDate;

  // common

  @Column(name = "delete_flag")
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

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;
}
