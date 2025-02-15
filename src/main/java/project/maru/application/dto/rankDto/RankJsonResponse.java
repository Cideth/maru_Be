package project.maru.application.dto.rankDto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class RankJsonResponse {

  private List<RankReadResponse> rankings;
  private RankReadResponse userRank;

  public RankJsonResponse(List<RankReadResponse> rankings) {
    this.rankings = rankings;
  }
}

