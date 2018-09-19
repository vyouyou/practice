package spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
public class TestDTO {
    @JsonFormat(pattern = "YYYY-MM-DD")
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private Date startDate;
}
