package ccp.com.copastel.domain;

import java.util.Date;
import java.util.UUID;

/**
 * Created by lucasmorano on 2/21/15.
 */
public class Element {

    private String id;

    private ElementType elementType;

    private String value;

    private Date createdDate = new Date();

    public ElementType getElementType() {
        return elementType;
    }

    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
