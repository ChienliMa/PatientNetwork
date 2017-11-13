package patnet.model;

import java.util.Date;

public class OrganizationReviews extends Reviews{
	private Long OrganizationId;

	public OrganizationReviews() {
		super();
	}
	
	public OrganizationReviews(String userName, Long OrganizationId, Integer rating, String content, Date created) {
		super(userName, rating, content, created);
		this.OrganizationId = OrganizationId;
	}

	public OrganizationReviews(Long reviewId, String userName, Long OrganizationId, String content, Date created, Integer rating) {
		super(reviewId, userName, content, created, rating);
		this.OrganizationId = OrganizationId;
	}
	
	public Long getOrganizationId() {
		return OrganizationId;
	}

	public void setOrganizationId(Long OrganizationId) {
		this.OrganizationId = OrganizationId;
	}
}
