package project.maru.application.dto.loginHistoryDto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class PostLoginRequest {

  private String user;  //name 값 전달됨.
  @Schema(hidden = true)
  private String userId;
}
