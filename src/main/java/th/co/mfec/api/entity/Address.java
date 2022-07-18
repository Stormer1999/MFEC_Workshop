package th.co.mfec.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ADDRESS")
@Getter
@Setter
public class Address {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "line_1")
  private String line1;

  @Column(name = "line_2")
  private String line2;

  @Column(name = "postcode")
  private String postcode;

  @Column(name = "type")
  private String type;

  @Column(name = "prefer")
  private String prefer;

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

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
