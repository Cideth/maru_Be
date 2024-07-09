package project.maru.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "questions_Kr")
@Getter
@Setter
@NoArgsConstructor
public class QuestionsKr {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String question;
  private String answer;
  private int score;
  private int contentTypeId;
  private int beenCalled;
  private int beenPassed;
  @Column(updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;
  @UpdateTimestamp
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

  @PreRemove
  private void deleteLogical() {
    // 삭제 시간 설정
    this.deletedAt = LocalDateTime.now();
  }

  public QuestionsKr(String question, String answer, int score, int contentTypeId) {
    this.question = question;
    this.answer = answer;
    this.score = score;
    this.contentTypeId = contentTypeId;
  }
}
