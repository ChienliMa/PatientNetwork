package patnet.model;

import patnet.model.*;
import java.util.Date;


public class PhysicianReviews extends Reviews {
	protected Long providerId;
	
	public PhysicianReviews() {
		super();
	}
	
	public PhysicianReviews(Long reviewId) {
		super(reviewId);
	}
	
	public PhysicianReviews(String userName, Long providerId, Integer rating, String content, Date created) {
		super(userName, rating, content, created);
		this.providerId = providerId;
	}
	
	public PhysicianReviews(Long reviewId, String userName, Long providerId, Integer rating, String content, Date created) {
		super(reviewId, userName, content, created, rating);
		this.providerId = providerId;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}
}
