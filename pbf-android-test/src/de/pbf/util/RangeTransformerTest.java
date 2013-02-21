
package de.pbf.util;

import android.test.AndroidTestCase;

public class RangeTransformerTest extends AndroidTestCase {

    private static final float ORIGINAL_RANGE_MIN = 0F;
    private static final float ORIGINAL_RANGE_MAX = 1000F;
    private static final float TARGET_RANGE_MIN = 0F;
    private static final float TARGET_RANGE_MAX = 100F;
    private static final float ORIGINAL_VALUE = 100F;
    private static final float TRANSFORMED_VALUE = 10F;
    private static final double ASSERT_DELTA = 0.0001D;

    
    public void testTransform() {

        RangeTransformer transformer = RangeTransformer.INSTANCE;
        float result = transformer.transform(ORIGINAL_RANGE_MIN, ORIGINAL_RANGE_MAX, TARGET_RANGE_MIN, TARGET_RANGE_MAX, ORIGINAL_VALUE);

        assertEquals(TRANSFORMED_VALUE, result, ASSERT_DELTA);
    }

}
