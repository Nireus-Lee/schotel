/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yfdai
 * 
 */
@Entity
@Table(name="HotelInfo",uniqueConstraints=@UniqueConstraint(name="uk_hotel_info_hotel_code",	columnNames={"hotelCode"} ))
public class HotelInfo {
	@Id
	@GeneratedValue
	private int id;

    /**
     * 酒店信息提供方给出的的hotel id,
     * ctrip提供的hotelId和hotelCode是一样的
     */
    private int hotelId;

	@Column(length = 15)
	private String hotelCode = "";

	@Column(length = 100)
	private String hotelName;// >酒店名称</HotelName>

    @Column(length=10)
    private String brandCode;  //品牌代码


    /**
     * 所属行政区域代码
     */
    private int areaId;

    /**
     * 城市编号
     */
    private int cityId;

    /**
     * 城市名称
     */
    @Column(length=50)
    private String cityName;

	@Column(length = 100)
	private String address;// >地址</Address>
	@Column(length = 10)
	private String postalCode;// >邮编</PostalCode>

    /**
     * 坐标类型
     */
    @Column(length=10)
    private String positionTypeCode;

    private double latitude;// >纬度</Latitude>

    private double longitude;// >经度</Longitude>

    /**
     * 建造日期
     */
    @Temporal(TemporalType.DATE)
    @Column(name="build_date")
    private Date buildDate;

    /**
     * 消费水平代码，比如4-代表奢侈消费
     */
    private Integer consumerLevel;

    /**
     * 标识该酒店是否有价格计划，没有价格计划的酒店在查找酒店预定时不显示
     * 0: 默认值，不知道是否有价格计划
     * 1：有价格计划
     * -1: 使用接口查询过了，肯定没有价格计划
     */
    @Column(name="rateplan_status")
    private int ratePlanStatus;

    @OneToMany(mappedBy = "hotelInfo", cascade = {CascadeType.ALL})
    private List<HotelAddressZone> hotelAddressZones;


    @OneToMany(mappedBy = "hotelInfo", cascade = {CascadeType.ALL})
    private List<HotelServiceInfo> hotelServices;


    @OneToMany(mappedBy = "hotelInfo", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<HotelAward> hotelAwards;

    @OneToMany(mappedBy = "hotelInfo", cascade = {CascadeType.ALL})
    private List<HotelRelativePosition> relativePositions;

    /**
     * 房型列表
     */
    @OneToMany(mappedBy = "hotelInfo", cascade = {CascadeType.ALL})
    private List<HotelGuestRoom> guestRooms;

    @OneToMany(mappedBy = "hotelInfo", cascade = {CascadeType.ALL})
    private List<HotelRefPoint> refPoints;

    @OneToMany(mappedBy = "hotelInfo", cascade = {CascadeType.ALL})
    private List<HotelMultimediaInfo> medias;

    /**
     * 数据来源
     */
    @Column(length = 10)
    private String source;

    @Column(insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastupdate;

    /**
     * 用于显示符合条件的房型，价格等
     */
    @Transient
    private List<HotelRatePlan> ratePlans;


    public int addGuestRoom(HotelGuestRoom info) {
        for(HotelGuestRoom guestRoom: this.guestRooms) {
            if (guestRoom.getRoomTypeCode().equalsIgnoreCase(info.getRoomTypeCode())) return 0;
        }

        this.guestRooms.add(info);
        return 1;
    }

    public int addAddressZone(HotelAddressZone zone1) {
        for(HotelAddressZone zone: this.hotelAddressZones) {
            if (zone.getZoneCode() == zone1.getZoneCode()) return 0;
        }

        this.hotelAddressZones.add(zone1);
        return 1;
    }

    public int addHotelService(HotelServiceInfo info) {
        for(HotelServiceInfo serviceInfo: this.hotelServices) {
            if (serviceInfo.getServiceCode() == info.getServiceCode()) return 0;
        }
        this.hotelServices.add(info);
        return 1;
    }

    /**
     * 获取酒店星级
     * @return
     */
    public int getHotelStarRate() {
        int rate = 0;
        if (this.hotelAwards != null) {
            for (HotelAward award: this.hotelAwards) {
                if (award.getProvider().equalsIgnoreCase("HotelStarRate")) {
                    rate = (int)award.getRating();
                    break;
                }
            }
        }
        return rate;
    }

    /**
     * 酒店的用户点评星级
     * @return
     */
    public float getHotelUserRate() {
        float rate = 0;
        if (this.hotelAwards != null) {
            for (HotelAward award: this.hotelAwards) {
                if (award.getProvider().equalsIgnoreCase("CtripUserRate")) {
                    rate = award.getRating();
                    break;
                }
            }
        }
        return rate;
    }




    /**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the hotelName
	 */
	public String getHotelName() {
		return hotelName;
	}

	/**
	 * @param hotelName
	 *            the hotelName to set
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	/**
	 * @return the hotelCode
	 */
	public String getHotelCode() {
		return hotelCode;
	}

	/**
	 * @param hotelCode
	 *            the hotelCode to set
	 */
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}


	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the brandCode
	 */
	public String getBrandCode() {
		return brandCode;
	}

	/**
	 * @param brandCode
	 *            the brandCode to set
	 */
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	/**
	 * @return the lastupdate
	 */
	public Date getLastupdate() {
		return lastupdate;
	}

	/**
	 * @param lastupdate
	 *            the lastupdate to set
	 */
	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}


    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getPositionTypeCode() {
        return positionTypeCode;
    }

    public void setPositionTypeCode(String positionTypeCode) {
        this.positionTypeCode = positionTypeCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<HotelAward> getHotelAwards() {
        if (hotelAwards == null)
            hotelAwards = new ArrayList<HotelAward>();
        return hotelAwards;
    }

    public void setHotelAwards(List<HotelAward> hotelAwards) {
        this.hotelAwards = hotelAwards;
    }

    public List<HotelRelativePosition> getRelativePositions() {
        if (relativePositions == null)
            relativePositions = new ArrayList<HotelRelativePosition>();
        return relativePositions;
    }

    public void setRelativePositions(List<HotelRelativePosition> relativePositions) {
        this.relativePositions = relativePositions;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    public int getConsumerLevel() {
        return consumerLevel;
    }

    public void setConsumerLevel(int consumerLevel) {
        this.consumerLevel = consumerLevel;
    }

    public List<HotelServiceInfo> getHotelServices() {
        return hotelServices;
    }

    public void setHotelServices(List<HotelServiceInfo> hotelServices) {
        this.hotelServices = hotelServices;
    }

    public List<HotelGuestRoom> getGuestRooms() {
        return guestRooms;
    }

    public void setGuestRooms(List<HotelGuestRoom> guestRooms) {
        this.guestRooms = guestRooms;
    }

    public List<HotelRefPoint> getRefPoints() {
        return refPoints;
    }

    public void setRefPoints(List<HotelRefPoint> refPoints) {
        this.refPoints = refPoints;
    }

    public List<HotelMultimediaInfo> getMedias() {
        return medias;
    }

    public void setMedias(List<HotelMultimediaInfo> medias) {
        this.medias = medias;
    }

    public List<HotelAddressZone> getHotelAddressZones() {
        return hotelAddressZones;
    }

    public void setHotelAddressZones(List<HotelAddressZone> hotelAddressZones) {
        this.hotelAddressZones = hotelAddressZones;
    }


    public int getRatePlanStatus() {
        return ratePlanStatus;
    }

    public void setRatePlanStatus(int ratePlanStatus) {
        this.ratePlanStatus = ratePlanStatus;
    }

    public List<HotelRatePlan> getRatePlans() {
        return ratePlans;
    }

    public void setRatePlans(List<HotelRatePlan> ratePlans) {
        this.ratePlans = ratePlans;
    }
}
