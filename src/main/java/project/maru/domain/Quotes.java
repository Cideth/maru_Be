package project.maru.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import project.maru.presentation.util.DateUtils;

@Entity
@Table(name = "quotes")
@Getter
@Setter
@NoArgsConstructor
public class Quotes {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String title;

  private String quoteVoiceLink;
  private String quote;

  @Column(updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

  @PrePersist
      @PreUpdate
      protected void onUpdateTimestamp() {
          if (createdAt == null) {
              createdAt = LocalDateTime.now();
          } else {
              updatedAt = LocalDateTime.now();
          }
      }

  @ManyToOne
  @JoinColumn(name = "content_type_id")
  @JsonIgnore
  private ContentType contentType;

  @OneToMany(mappedBy = "quotes")
  private List<QuestionsKr> questionsKrs;

  @PreRemove
  private void deleteLogical() {
    // 삭제 시간 설정
    this.deletedAt = LocalDateTime.now();
  }

}
