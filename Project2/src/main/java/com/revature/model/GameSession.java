package com.revature.model;

public class GameSession {
	private int id;
	private int playerId;
	private int gameId;
	private GameSessionStatus status;
	private String date;
	
	public GameSession(int id, int playerId, int gameId, GameSessionStatus status, String date) {
		super();
		this.id = id;
		this.playerId = playerId;
		this.gameId = gameId;
		this.status = status;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public GameSessionStatus getStatus() {
		return status;
	}

	public void setStatus(GameSessionStatus status) {
		this.status = status;
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
		result = prime * result + gameId;
		result = prime * result + id;
		result = prime * result + playerId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		GameSession other = (GameSession) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (gameId != other.gameId)
			return false;
		if (id != other.id)
			return false;
		if (playerId != other.playerId)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GameSession [id=" + id + ", playerId=" + playerId + ", gameId=" + gameId + ", status=" + status
				+ ", date=" + date + "]";
	}
}
