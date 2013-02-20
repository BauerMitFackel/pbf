
package de.pbf.util;

/**
 * Utility to transform values from one range to another.
 * @author Ulrich Raab
 */
public enum RangeTransformer {

    /**
     * Singleton.
     */
    INSTANCE;

    /**
     * Transforms a value from one range to another.
     * @param oRangeMin The minimum value in the original range
     * @param oRangeMax The maximum value in the original range
     * @param nRangeMin The minimum value in the target range
     * @param nRangeMax The maximum value in the target range
     * @param value The value to transform
     * @return The transformed value as float
     */
    public float transform(float oRangeMin, float oRangeMax, float nRangeMin, float nRangeMax, float value) {

        double scale = (double) (nRangeMax - nRangeMin) / (oRangeMax - oRangeMin);
        return (float) (nRangeMin + ((value - oRangeMin) * scale));
    }
}
