package ngordnet.main;

import ngordnet.ngrams.NGramMap;
import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap maps;
    public HistoryTextHandler(NGramMap map) {
        maps = map;
    }
    @Override
    public String handle(NgordnetQuery q) {
        int startYear = q.startYear();
        int endYear = q.endYear();

        String response = "";
        for (String word: q.words()) {
            response += word + ": " + maps.weightHistory(word, startYear, endYear).toString() + "\n";
        }
        return response;
    }
}
