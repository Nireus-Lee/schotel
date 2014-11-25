@XmlSchema(
        elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns={@XmlNs(prefix="ns", namespaceURI="http://www.opentravel.org/OTA/2003/05"),
                @XmlNs(prefix = "xsi", namespaceURI = "http://www.w3.org/2001/XMLSchema-instance"),
                @XmlNs(prefix = "xsd", namespaceURI = "http://www.w3.org/2001/XMLSchema")
        }
)
package cn.buk.api.dto.hotel;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;