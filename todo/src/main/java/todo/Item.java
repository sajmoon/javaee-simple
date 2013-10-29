package todo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {
	@Id @GeneratedValue
	private long id;

	private String title;
	private Date createdAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Item(String title) {
		this.setTitle(title);
		this.setDate(new Date());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public Date getDate() {
		return createdAt;
	}


	public void setDate(Date date) {
		this.createdAt = date;
	}
}
