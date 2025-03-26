package com.emt.emtlabs.service;

import com.emt.emtlabs.model.Review;

import java.util.Optional;

public interface ReviewService {
    Optional<Review> save(Review review);
}
