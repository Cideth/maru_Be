package project.maru.presentation;


import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.maru.application.dto.rankDto.RankJsonResponse;
import project.maru.application.dto.rankDto.RankUpdateRequest;
import project.maru.application.service.RankService;
import project.maru.presentation.util.ParseToken;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RankController {

  private final RankService rankService;
  private final ParseToken parseToken;

  @Operation(summary = "랭킹 조회 GET")
  @GetMapping("/rankings")
  public RankJsonResponse getScore(
      @RequestHeader("Authorization") @Parameter(name = "Authorization", in = ParameterIn.HEADER, schema = @Schema(hidden = true)) String accessToken,
      @RequestParam int limit, @RequestParam boolean userInfoIncluded)
      throws Exception {
    String userId = parseToken.getParseToken(accessToken);
    return rankService.getMyScoreAndTop20Rank(userId, limit, userInfoIncluded);
  }

  @Operation(summary = "답변 결과 업데이트 POST")
  @PostMapping("/answer")
  public JsonNode updateScore(@RequestHeader("Authorization") String accessToken,
      @RequestBody RankUpdateRequest rankUpdateRequest) throws Exception {
    String userId = parseToken.getParseToken(accessToken);
    return rankService.updateRank(userId, rankUpdateRequest);
  }
}
