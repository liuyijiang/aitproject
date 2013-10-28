package com.ait.gb.entity;

import org.springframework.data.annotation.Id;

public class RecordEntity {

	@Id
	private String id;
	private String childId;
	private String parentId;
	private String imageMax;
	private String imageSmall;
	private String createTime;
	private String rewardType;
	private String comment;
	private int rewardNumber;
	private String recordType;//优秀 良好 合格
	private String from;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChildId() {
		return childId;
	}
	public void setChildId(String childId) {
		this.childId = childId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getImageMax() {
		return imageMax;
	}
	public void setImageMax(String imageMax) {
		this.imageMax = imageMax;
	}
	public String getImageSmall() {
		return imageSmall;
	}
	public void setImageSmall(String imageSmall) {
		this.imageSmall = imageSmall;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRewardType() {
		return rewardType;
	}
	public void setRewardType(String rewardType) {
		this.rewardType = rewardType;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getRewardNumber() {
		return rewardNumber;
	}
	public void setRewardNumber(int rewardNumber) {
		this.rewardNumber = rewardNumber;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	
	
}
