package ngordnet.ngrams;

import java.util.*;

/** An object for mapping a year number (e.g. 1996) to numerical data. Provides
 *  utility methods useful for data analysis.
 *  @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {
    /** Constructs a new empty TimeSeries. */
    public TimeSeries() {
        super();
    }

    /** Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     *  inclusive of both end points. */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        for (Integer year: ts.keySet()) {
            if (year >= startYear && year <= endYear) {
                put(year, ts.get(year));
            }
        }
    }

    /** Returns all years for this TimeSeries (in any order). */
    public List<Integer> years() {
        List<Integer> yearsList = new ArrayList<Integer>(this.keySet());
        return (List<Integer>) yearsList;
    }

    /** Returns all data for this TimeSeries (in any order).
     *  Must be in the same order as years(). */
    public List<Double> data() {
        List<Double> dataList = new ArrayList<Double>(this.values());
        return (List<Double>) dataList;
    }

    /** Returns the yearwise sum of this TimeSeries with the given TS. In other words, for
     *  each year, sum the data from this TimeSeries with the data from TS. Should return a
     *  new TimeSeries (does not modify this TimeSeries). */
    public TimeSeries plus(TimeSeries ts) {
        List<Integer> years = new ArrayList<>(this.keySet());
        TimeSeries sum = new TimeSeries();
        years.addAll(ts.keySet());
        for (Integer year: years) {
            double val = 0;
            if (ts.containsKey(year)) {
                val += ts.get(year);
            }
            if (this.containsKey(year)) {
                val += get(year);
            }
            sum.put(year, val);
        }
        return sum;
    }

    /** Returns the quotient of the value for each year this TimeSeries divided by the
      *  value for the same year in TS. If TS is missing a year that exists in this TimeSeries,
      *  throw an IllegalArgumentException. If TS has a year that is not in this TimeSeries, ignore it.
      *  Should return a new TimeSeries (does not modify this TimeSeries). */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries divided = new TimeSeries();
        for (Integer year: this.keySet()) {
            if (!ts.containsKey(year)) {
                throw new IllegalArgumentException();
            }
            divided.put(year, this.get(year) / ts.get(year));
        }
        return divided;
    }
}
