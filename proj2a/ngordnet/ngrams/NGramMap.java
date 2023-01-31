package ngordnet.ngrams;

import java.util.Collection;
import java.util.HashMap;
import edu.princeton.cs.algs4.In;

/** An object that provides utility methods for making queries on the
 *  Google NGrams dataset (or a subset thereof).
 *
 *  An NGramMap stores pertinent data from a "words file" and a "counts
 *  file". It is not a map in the strict sense, but it does provide additional
 *  functionality.
 *
 *  @author Josh Hug
 */
public class NGramMap {
    /** Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME. */
    private HashMap<String, TimeSeries> wordsTimeSeries;
    private TimeSeries wordsTotal;
    public NGramMap(String wordsFilename, String countsFilename) {
        wordsTimeSeries = new HashMap<String, ngordnet.ngrams.TimeSeries>();
        In wordsnameIn = new In(wordsFilename);
        In countsnameIn = new In(countsFilename);
        while (wordsnameIn.hasNextLine()) {
            String[] line = wordsnameIn.readLine().split("\\t");
            String word = line[0];
            if (!wordsTimeSeries.containsKey(word)) {
                wordsTimeSeries.put(word, new ngordnet.ngrams.TimeSeries());
            }
            wordsTimeSeries.get(word).put(Integer.parseInt(line[1]), (double) Integer.parseInt(line[2]));
        }
        wordsTotal = new ngordnet.ngrams.TimeSeries();
        while (countsnameIn.hasNextLine()) {
            String[] line = countsnameIn.readLine().split(",");
            int year = Integer.parseInt(line[0]);
            double total = Long.parseLong(line[1]);
            wordsTotal.put(year, total);
        }
    }

    /** Provides the history of WORD. The returned TimeSeries should be a copy,
     *  not a link to this NGramMap's TimeSeries. In other words, changes made
     *  to the object returned by this function should not also affect the
     *  NGramMap. This is also known as a "defensive copy". */
    public TimeSeries countHistory(String word) {
        if (wordsTimeSeries.containsKey(word)) {
            return wordsTimeSeries.get(word);
        }
        return null;
    }

    /** Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     *  returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other words,
     *  changes made to the object returned by this function should not also affect the
     *  NGramMap. This is also known as a "defensive copy". */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        return new ngordnet.ngrams.TimeSeries(countHistory(word), startYear, endYear);
    }

    /** Returns a defensive copy of the total number of words recorded per year in all volumes. */
    public TimeSeries totalCountHistory() {
        return wordsTotal;
    }

    /** Provides a TimeSeries containing the relative frequency per year of WORD compared to
     *  all words recorded in that year. */
    public TimeSeries weightHistory(String word) {
        return countHistory(word).dividedBy(wordsTotal);
    }

    /** Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     *  and ENDYEAR, inclusive of both ends. */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        return new ngordnet.ngrams.TimeSeries(weightHistory(word), startYear, endYear);
    }

    /** Returns the summed relative frequency per year of all words in WORDS. */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        ngordnet.ngrams.TimeSeries sum = new ngordnet.ngrams.TimeSeries();
        for (String word: words) {
            sum = sum.plus(weightHistory(word));
        }
        return sum;
    }

    /** Provides the summed relative frequency per year of all words in WORDS
     *  between STARTYEAR and ENDYEAR, inclusive of both ends. If a word does not exist in
     *  this time frame, ignore it rather than throwing an exception. */
    public TimeSeries summedWeightHistory(Collection<String> words,
                              int startYear, int endYear) {
        return new ngordnet.ngrams.TimeSeries(summedWeightHistory(words), startYear, endYear);
    }
}


