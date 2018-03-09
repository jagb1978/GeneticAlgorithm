package com.trading.fixedincome;

import com.trading.beans.Instrument;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This Class contains the spread definition
 *
 * @author Jose Gonzalez
 */
public class Spread {
    private Map<String, Leg> legMap = new HashMap<>();
    private Instrument instrument;

    public Spread() {
    }

    public Spread(List<Leg> legsList) {
        legsList.forEach(leg -> this.legMap.put(leg.getInstrument().getFeedCode(), leg));
        StringBuilder stringBuilder = new StringBuilder();

        this.legMap.values().forEach(leg -> stringBuilder.append(leg.getQuantity() + "_" + leg.getInstrument().getFeedCode() + "|"));

        this.instrument = new Instrument(stringBuilder.toString());
    }

    /**
     * Getter and Setter
     */

    public Instrument getInstrument() {
        return instrument;
    }

    public Map<String, Leg> getLegMap() {
        return legMap;
    }

    public void setLegMap(Map<String, Leg> legMap) {
        this.legMap = legMap;
    }

}
