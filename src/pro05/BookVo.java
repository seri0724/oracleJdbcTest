package pro05;

public class BookVo {
	
	private int id;
	private String title;
	private String pubs;
	private String pub_date;
	private String author_name;
	private int state_code = 1;
	
	public BookVo() {
		super();
	}
	public BookVo(int id, String title, String pubs, String pub_date, String author_name, int state_code) {
		super();
		this.id = id;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.author_name = author_name;
		this.state_code = state_code;
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
	public String getPubs() {
		return pubs;
	}
	public void setPubs(String pubs) {
		this.pubs = pubs;
	}
	public String getPub_date() {
		return pub_date;
	}
	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public int getState_code() {
		return state_code;
	}
	public void setState_code(int state_code) {
		this.state_code = state_code;
	}
	@Override
	public String toString() {
		String state = "";
		if(state_code == 0) {
			state = "대여중";
		}else if(state_code == 1) {
			state = "재고있음";
		}
		return "책 제목:"+title+", 작가:"+author_name+", 대여 유무:"+state;
	}

}
