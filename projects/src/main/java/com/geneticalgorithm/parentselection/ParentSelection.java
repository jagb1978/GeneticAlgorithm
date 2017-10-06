package com.geneticalgorithm.parentselection;

import com.geneticalgorithm.beans.Individual;
import com.geneticalgorithm.beans.Population;

/**
 * Parent Selection Interface
 *
 * @author Jose Gonzalez
 */
public interface ParentSelection {

    Individual selection(Population population);
}
