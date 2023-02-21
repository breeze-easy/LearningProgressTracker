package tracker.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberFormatter {
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        return new BigDecimal(value).setScale(places, RoundingMode.HALF_UP).doubleValue();

//        BigDecimal bd = BigDecimal.valueOf(value);
//        bd = bd.setScale(places, RoundingMode.HALF_UP);
//        return bd.doubleValue();
    }
}
