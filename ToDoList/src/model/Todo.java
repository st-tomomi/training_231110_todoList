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
	private Date duedate;
	//完了日
	private Date completiondate;
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
		setDuedate(new Date(System.currentTimeMillis()));
	}


//	public Todo(int id, String title, Date duedate) {
//		this.setId(id);
//		this.setTitle(title);
//		this.setDuedate(duedate);
//		this.setMemoText("");
//		this.setStatus(0);
//	}

	public Todo( int id, String title, Date duedate, Date completiondate, String memoText, int status) {
		this.setId(id);
		this.setTitle(title);
		this.setDuedate(duedate);
		this.setCompletiondate(completiondate);
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


	public Date getDuedate() {
		return duedate;
	}


	public void setDuedate(Date duedate) {
		this.duedate = duedate;
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


	public Date getCompletiondate() {
		return completiondate;
	}


	public void setCompletiondate(Date completiondate) {
		this.completiondate = completiondate;
	}

}
