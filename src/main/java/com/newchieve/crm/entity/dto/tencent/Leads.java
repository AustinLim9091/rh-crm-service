package com.newchieve.crm.entity.dto.tencent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Leads {
	private int accountId;
	private long leadsId;
	private String clickId;
	private String accountType;
	private String wechatAppid;
	private String agencyId;
	private String agencyName;
	private String advertiserId;
	private int claimAdvertiserId;
	private String campaignId;
	private String campaignName;
	private String adgroupId;
	private String adgroupName;
	private String componentId;
	private String componentName;
	private String pageId;
	private String pageUrl;
	private String pageName;
	private String pageTools;
	private String creativeId;
	private String creativeName;
	private String adId;
	private String adName;
	private String adcreativeId;
	private String adcreativeName;
	private String leadsType;
	private String leadsSubType;
	private String chatId;
	private String leadsTags;
	private String leadsName;
	private String leadsTel;
	private String telLocation;
	private String leadsArea;
	private String leadsEmail;
	private String leadsQq;
	private String leadsWechat;
	private String leadsGender;
	private String leadsNationality;
	private String leadsWorkingYears;
	private String leadsAge;
	private String leadsProfession;
	private String leadsIdNumber;
	private String leadsAddress;
	private String bundle;
	private String leadsPotentialScore;
	private String leadsFollowTag;
	private String leadsSource;
	private String leadsSubSource;
	private String leadsTouchTag;
	private String leadsCreateTime;
	private String leadsActionTime;
	private String leadsComeFrom;
	private String shopName;
	private String shopAddress;
	private String callMiddleNum;
	private String callConsumerHotline;
	private String callTouchTag;
	private String callDuration;
	private String callRecordUrl;
	private String layerFormContent;
	private String nickName;
	private String isBroadCastLeads;
	private String ownerName;
	private String allFollowRecords;
	private String customQa;
	private int leadsResponseDuration;

}