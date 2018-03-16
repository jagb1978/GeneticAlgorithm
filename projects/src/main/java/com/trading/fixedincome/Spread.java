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
public class Spread extends Instrument{
    private Map<Instrument, Leg> legMap = new HashMap<>();
    private Instrument instrument;


    public Spread(String feedCode,List<Leg> legsList) {
        super(feedCode);
        legsList.forEach(leg -> this.legMap.put(leg.getInstrument(), leg));
        StringBuilder stringBuilder = new StringBuilder();

        this.legMap.values().forEach(leg -> stringBuilder.append(leg.getQuantity())
                .append("_")
                .append(leg.getInstrument().getFeedCode())
                .append("|")
        );

        this.instrument = new Instrument(stringBuilder.toString());
    }

    /**
     * Getter and Setter
     */

    public Instrument getInstrument() {
        return instrument;
    }

    public Map<Instrument, Leg> getLegMap() {
        return legMap;
    }

    public void setLegMap(Map<Instrument, Leg> legMap) {
        this.legMap = legMap;
    }

}
