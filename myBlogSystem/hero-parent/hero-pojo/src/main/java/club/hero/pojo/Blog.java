package club.hero.pojo;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class Blog implements Serializable{
	
	@Field
    private Integer id;

	@Field("blog_title")
    private String title;

	@Field("blog_summary")
    private String summary;

	@Field("blog_releasedate")
    private String releasedate;

	
    private Integer clickhit;

    @Field("blog_replyhit")
    private String replyhit;

    
    private Integer typeid;

    @Field("blog_keyword")
    private String keyword;

    
    private String content;

    
    
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public Integer getClickhit() {
        return clickhit;
    }

    public void setClickhit(Integer clickhit) {
        this.clickhit = clickhit;
    }

 

    public String getReplyhit() {
		return replyhit;
	}

	public void setReplyhit(String replyhit) {
		this.replyhit = replyhit;
	}

	public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}