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

	private long account_id;
	private long leads_id;
	private String click_id;
	private String account_type;
	private String wechat_appid;
	private String agency_id;
	private String agency_name;
	private long claim_advertiser_id;
	private String campaign_id;
	private String campaign_name;
	private String adgroup_id;
	private String adgroup_name;
	private String page_id;
	private String page_url;
	private String page_name;
	private String page_tools;
	private String leads_type;
	private String leads_sub_type;
	private String leads_name;
	private String leads_tel;
	private String tel_location;
	private String leads_area;
	private String leads_email;
	private String leads_qq;
	private String leads_wechat;
	private String leads_gender;
	private String bundle;
	private String leads_potential_score;
	private String leads_follow_tag;
	private String leads_create_time;
	private String leads_action_time;
	private String leads_come_from;
	private String leads_tags;
	private String leads_nationality;
	private String leads_working_years;
	private String leads_age;
	private String leads_profession;
	private String leads_id_number;
	private String leads_address;
	private String leads_source;
	private String leads_touch_tag;
	private String creative_id;
	private String creative_name;
	private long ad_id;
	private String ad_name;
	private long adcreative_id;
	private String adcreative_name;
	private String layer_form_content;
	private String is_broad_cast_leads;
	private String nick_name;
	private String owner_name;
	private String all_follow_records;

}