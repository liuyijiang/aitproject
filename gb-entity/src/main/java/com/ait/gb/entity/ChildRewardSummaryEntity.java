package com.ait.gb.entity;
/**
 * 小孩各种奖励汇总
 * @author Administrator
 *
 */
public class ChildRewardSummaryEntity {
   
	private String id;
	private String childId;
	private String parentId;
	private String rewardName;
	private String rewardType;//对应文件
	private long rewardSum;
	
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
	public String getRewardName() {
		return rewardName;
	}
	public void setRewardName(String rewardName) {
		this.rewardName = rewardName;
	}
	public String getRewardType() {
		return rewardType;
	}
	public void setRewardType(String rewardType) {
		this.rewardType = rewardType;
	}
	public long getRewardSum() {
		return rewardSum;
	}
	public void setRewardSum(long rewardSum) {
		this.rewardSum = rewardSum;
	}
	
	
	
}
