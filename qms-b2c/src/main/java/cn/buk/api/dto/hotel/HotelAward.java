package cn.buk.api.dto.hotel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * User: william
 * Date: 14-10-26
 * Time: 下午11:02
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://www.opentravel.org/OTA/2003/05")
@XStreamAlias("Award")
public class HotelAward {

    @XmlAttribute(name="Provider")
    @XStreamAsAttribute
    @XStreamAlias("Provider")
    private String provider = "HotelStarRate";

    @XmlAttribute(name="Rating")
    @XStreamAsAttribute
    @XStreamAlias("Rating")
    private String rating = "0";



    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }


    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
