package project.maru.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "questions_lang")
@Getter
@Setter
@NoArgsConstructor
public class QuestionLanguage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String question;
  private String answer;

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
  @JoinColumn(name = "lang_code")
  @JsonIgnore
  private Languages languages;

  @ManyToOne
  @JoinColumn(name = "questions_kr_id")
  @JsonIgnore
  private QuestionsKr questionsKr;

  @PreRemove
  private void deleteLogical() {
    // 삭제 시간 설정
    this.deletedAt = LocalDateTime.now();
  }

}
