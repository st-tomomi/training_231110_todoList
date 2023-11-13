package model;

import java.sql.Date;

/**
 * Todoの情報
 * @author tomomisato
 *
 */
public class Todo {

	//ID
	private int id;
	//タイトル
	private String title;
	//期限
	private Date deadline;

	//ここより下は余力があれば
	//メモ
	private String memoText;
	//状態(0:not done 1:done)
	private int status;

	/**
	 * コンストラクタ（引数なし）
	 */
	public Todo() {
		setId(0);
		setTitle("");
		setDeadline(new Date(System.currentTimeMillis()));
	}


	public Todo(int id, String title, Date deadline) {
		this.setId(id);
		this.setTitle(title);
		this.setDeadline(deadline);
		this.setMemoText("");
		this.setStatus(0);
	}

	public Todo( int id, String title, Date deadline, String memoText, int status) {
		this.setId(id);
		this.setTitle(title);
		this.setDeadline(deadline);
		this.setMemoText(memoText);
		this.setStatus(status);
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Date getDeadline() {
		return deadline;
	}


	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}


	public String getMemoText() {
		return memoText;
	}


	public void setMemoText(String memoText) {
		this.memoText = memoText;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}

}
