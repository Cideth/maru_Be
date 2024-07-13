package project.maru.instructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.maru.application.dto.questionKrDto.QuestionsKrReadResponse;
import project.maru.domain.QuestionsKr;

public interface QuestionsKrRepository extends JpaRepository<QuestionsKr, String> {


  //@Query("SELECT new project.maru.application.dto.questionKrDto.QuestionsKrReadResponse(q.question, q.answer,q.score,q.contentTypeId) FROM QuestionsKr q WHERE q.contentTypeId = :contentTypeId")
  QuestionsKrReadResponse findByContentTypeId(int contentTypeId);

  QuestionsKr findById(int id);


  @Query("SELECT COUNT(e) FROM QuestionsKr e WHERE e.deletedAt IS NULL")
  long countByDeletedAtIsNull();

}
