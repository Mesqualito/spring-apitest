
package com.eigenbaumarkt.spring.apitest.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Billing implements Serializable
{

    private Card card;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -1851431012083044163L;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
