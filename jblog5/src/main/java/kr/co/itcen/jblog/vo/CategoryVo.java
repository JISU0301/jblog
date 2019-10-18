package kr.co.itcen.jblog.vo;

public class CategoryVo {
	private Long no;
	private String name;
	private String contents;
	private String regDate;
	private String blog_id;
	private int postCount;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}
	public int getPostCount() {
		return postCount;
	}
	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", contents=" + contents + ", regDate=" + regDate
				+ ", blog_id=" + blog_id + ", postCount=" + postCount + "]";
	}
	public void defaultsetting(String id) {
			this.name = "아이티센";
			this.contents = "교육";
			this.blog_id = blog_id;
		}		
	}
	

	