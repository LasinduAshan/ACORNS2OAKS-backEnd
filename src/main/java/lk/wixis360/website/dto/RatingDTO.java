package lk.wixis360.website.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDTO {
    private String id;
    private int rateValue;
    private String studentID;
    private String courseID;

}
