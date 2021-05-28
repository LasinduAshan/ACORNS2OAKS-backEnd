package lk.wixis360.website.service;


import lk.wixis360.website.dto.RatingDTO;
import lk.wixis360.website.dto.StudentReviewDTO;

import java.util.List;

public interface RatingService {
    public void saveRating(RatingDTO dto);
    public List<RatingDTO> getAllReviewsByCourse(String courseId);
}
