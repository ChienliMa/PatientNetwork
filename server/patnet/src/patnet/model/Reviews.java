package patnet.model;

import java.util.Date;

public class Reviews{
	private Long ReviewId;
	private String UserName;
	private Date Created;
	private Integer Rating;
	private String Content;

	public Reviews() {
	}

	public Reviews(String userName, Integer rating, String content,
			Date created) {
		UserName = userName;

		Rating = rating;
		Content = content;
		Created = created==null?new Date():created;
	}

	public Reviews(Long reviewId, String userName, String content, Date created, Integer rating) {
		ReviewId = reviewId;
		Content = content;
		Created = created;
		Rating = rating;
		UserName = userName;
	}

	public Date getCreated(){
		return Created;
	}

	public void setCreated(Date Created){
		this.Created=Created;
	}

	public Integer getRating(){
		return Rating;
	}

	public void setRating(Integer Rating){
		this.Rating=Rating;
	}

	public String getUsername(){
		return UserName;
	}

	public void setUsername(String UserName){
		this.UserName=UserName;
	}

	public Long getReviewId() {
		return ReviewId;
	}

	public void setReviewId(Long reviewId) {
		ReviewId = reviewId;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}