package com.revature.model;

public class Recommendation {
	private int id;
	private int userId;
	private int movieId;
	private boolean helpful;
	private String date;
	
	public Recommendation() {}
	
	public Recommendation(int id, int userId, int movieId, boolean helpful, String date) {
		super();
		this.id = id;
		this.userId = userId;
		this.movieId = movieId;
		this.helpful = helpful;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public boolean isHelpful() {
		return helpful;
	}

	public void setHelpful(boolean helpful) {
		this.helpful = helpful;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (helpful ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + movieId;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recommendation other = (Recommendation) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (helpful != other.helpful)
			return false;
		if (id != other.id)
			return false;
		if (movieId != other.movieId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Recommendation [id=" + id + ", userId=" + userId + ", movieId=" + movieId + ", helpful=" + helpful
				+ ", date=" + date + "]";
	}
}