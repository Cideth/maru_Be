package project.maru.application.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.maru.application.dto.rankDto.RankJsonResponse;
import project.maru.application.dto.rankDto.RankReadResponse;
import project.maru.application.dto.rankDto.RankUpdateRequest;
import project.maru.domain.QuestionsKr;
import project.maru.domain.Rank;
import project.maru.infrastructure.RankRepository;


@Service
@RequiredArgsConstructor
public class RankService {

  private final RankRepository rankRepository;
  private final QuestionsKrService questionsKrService;
  private RankJsonResponse rankJsonResponse;


  public RankReadResponse findTotalScoreByUserId(String userId) {
    Optional<Rank> rankOptional = rankRepository.findByUserId(userId);
    Integer ranking = rankRepository.findRankingByUserId(userId);
    if (rankOptional.isEmpty()) {
      return null;
    }
    Rank rank = rankOptional.get();
    return new RankReadResponse(rank.getName(), rank.getScore(), ranking != null ? ranking : 0);
  }

  public List<RankReadResponse> getTopRankLimitByScore(int quantity) {
    return rankRepository.findTopSubScores(quantity);
  }

  public RankJsonResponse getMyScoreAndTop20Rank(String userId, int limit,
      boolean includeUser) {

    List<RankReadResponse> topRankLimitByScore = getTopRankLimitByScore(limit);
    for (int i = 0; i < topRankLimitByScore.size(); i++) {
      RankReadResponse ith = topRankLimitByScore.get(i);
      ith.setRank(i + 1);
    }
    if (includeUser) {
      rankJsonResponse = new RankJsonResponse(topRankLimitByScore,
          findTotalScoreByUserId(userId));
    } else {
      rankJsonResponse = new RankJsonResponse(topRankLimitByScore);
    }
    return rankJsonResponse;
  }



  //Issue : 음성에 대한 정답 시 음성 저장과 점수 데이터를 같이 주는지?
  public QuestionsKr updateRank(String accessToken, RankUpdateRequest rankUpdateRequest) {
    Rank r = rankRepository.findScoreByUserId(accessToken);
    int totalScore = 100 + r.getScore();
    r.setScore(totalScore);
    rankRepository.save(r);

    return questionsKrService.putUpdatePassed(rankUpdateRequest);
  }
}