package project.maru.presentation;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.maru.application.dto.questionKrDto.GetQuestionCountResponse;
import project.maru.application.dto.questionKrDto.QuestionsKrReadResponse;
import project.maru.application.service.QuestionsKrService;

@RestController
@RequestMapping("/api/learning")
@RequiredArgsConstructor
public class QuestionsKrController {

  private final QuestionsKrService questionsKrService;

  @GetMapping("/questions")
  public  List<QuestionsKrReadResponse> getQuestionsKr(@RequestParam(name = "content_type") int contentTypeId,
      @RequestParam int n) {
    return questionsKrService.getRandomQuestionsByQuotesId(contentTypeId, n);
  }

  @GetMapping("/questions/total-count")
  public GetQuestionCountResponse getQuestionTotalCount() {
    return questionsKrService.getQuestionTotalCount();
  }

}
