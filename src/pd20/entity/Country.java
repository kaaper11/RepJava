package pd20.entity;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class Country {
    private String region;
    private List<String> capital;
}
