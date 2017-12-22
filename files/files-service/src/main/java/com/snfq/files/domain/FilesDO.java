/*
 * Powered By SinaFenqi
 * Since 2017 - 2017
 */

package com.snfq.files.domain;

import java.util.Date;
import java.math.BigDecimal;

public class FilesDO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/**	构造函数	**/
	public FilesDO() {
	}
	
	/**属性*/
	/**
	 *  
	 */
	private String id;
	/**
	 *  路径
	 */
	private String path;
	/**
	 *  类型
	 */
	private String contentType;
	/**
	 *  大小
	 */
	private Long size;
	/**
	 *  创建人
	 */
	private String creator;
	/**
	 *  创建时间
	 */
	private Date gmtCreated;
	/**
	 *  修改人
	 */
	private String editor;
	/**
	 *  更新时间
	 */
	private Date gmtModified;
	
	/**getter setter方法*/
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPath() {
		return this.path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getContentType() {
		return this.contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Long getSize() {
		return this.size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public String getCreator() {
		return this.creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getGmtCreated() {
		return this.gmtCreated;
	}
	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
	public String getEditor() {
		return this.editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public Date getGmtModified() {
		return this.gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	
	@Override
	public String toString() {
		String result =  "Files [";
			result += "id="+id+",";
			result += "path="+path+",";
			result += "contentType="+contentType+",";
			result += "size="+size+",";
			result += "creator="+creator+",";
			result += "gmtCreated="+gmtCreated+",";
			result += "editor="+editor+",";
			result += "gmtModified="+gmtModified+",";
		result = result.substring(0,result.lastIndexOf(","));
		result += "]";
		return result;
	}
}