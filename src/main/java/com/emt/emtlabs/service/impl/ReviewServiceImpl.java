package com.emt.emtlabs.service.impl;

import com.emt.emtlabs.model.Review;
import com.emt.emtlabs.repository.ReviewRepository;
import com.emt.emtlabs.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Optional<Review> save(Review review) {
        return Optional.of(reviewRepository.save(review));
    }
}
