/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.api.dto;

import cn.buk.api.util.xstream.MyDateConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import java.util.Date;
import java.util.List;

/**
 * User: yfdai
 * Date: 14-10-17
 * Time: 下午5:10
 */
@XStreamAlias("Flight")
public class Flight {

    @XStreamAlias("FlightID")
    private int id;

    @XStreamAlias("Carrier")
    private Carrier carrier;

    @XStreamAlias("FlightNumber")
    private String flightNo;

    @XStreamAlias("OperatingCarrier")
    private Carrier operatingCarrier;

    @XStreamAlias("OperatingFlightNumber")
    private String operatingFlightNo;

    @XStreamAlias("DepartureCity")
    private DtoCity departureCity;

    @XStreamAlias("ArrivalCity")
    private DtoCity arrivalCity;

    @XStreamAlias("DepartureAirport")
    private Airport departureAirport;

    @XStreamAlias("ArrivalAirport")
    private Airport arrivalAirport;

    @XStreamAlias("TakeoffDateTime")
    @XStreamConverter(value=MyDateConverter.class, strings={"yyyy-MM-dd'T'HH:mm:ss"})
    private Date takeoffDate;

    @XStreamAlias("ArrivalDateTime")
    @XStreamConverter(value=MyDateConverter.class, strings={"yyyy-MM-dd'T'HH:mm:ss"})
    private Date arrivalDate;

    @XStreamAlias("EquipmentCode")
    private String equipmentCode;

    @XStreamAlias("Duration")
    private int duration;

    @XStreamAlias("ArrivalDays")
    private int arrivalDays;

    @XStreamAlias("SeatCountList")
    private List<SeatCount> seatCounts;

    @XStreamAlias("StopList")
    private List<Stop> stops;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public DtoCity getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(DtoCity departureCity) {
        this.departureCity = departureCity;
    }

    public DtoCity getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(DtoCity arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Date getTakeoffDate() {
        return takeoffDate;
    }

    public void setTakeoffDate(Date takeoffDate) {
        this.takeoffDate = takeoffDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getArrivalDays() {
        return arrivalDays;
    }

    public void setArrivalDays(int arrivalDays) {
        this.arrivalDays = arrivalDays;
    }

    public List<SeatCount> getSeatCounts() {
        return seatCounts;
    }

    public void setSeatCounts(List<SeatCount> seatCounts) {
        this.seatCounts = seatCounts;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public Carrier getOperatingCarrier() {
        return operatingCarrier;
    }

    public void setOperatingCarrier(Carrier operatingCarrier) {
        this.operatingCarrier = operatingCarrier;
    }

    public String getOperatingFlightNo() {
        return operatingFlightNo;
    }

    public void setOperatingFlightNo(String operatingFlightNo) {
        this.operatingFlightNo = operatingFlightNo;
    }
}
