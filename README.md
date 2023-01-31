# NGordnet (https://fa22.datastructur.es/materials/proj/proj2a/ + https://fa22.datastructur.es/materials/proj/proj2b/)
- The Google Ngram dataset provides many terabytes of information about the historical frequencies of all observed words and phrases in English (or more precisely all observed ngrams). Google provides the Google Ngram Viewer on the web , allowing users to visualize the relative historical popularity of words and phrases.

## Description: 
  - This project is inspired by Google's Ngram viewer. It creates a browser based tool that trackers the history of the usage English words and its hyponyms in text.
  - Explores how the volume of printed works in English has changed over time, as well as the structure of the is-a relationships of words in English.
  
## Wordnet Dataset
- WordNet is a “semantic lexicon for the English language” that is used extensively by computational linguists and cognitive scientists; for example, it was a key component in IBM’s Watson. WordNet groups words into sets of synonyms called synsets and describes semantic relationships between them. 


## Features:
  - Implemented generic Abstract Data Structures in Java to analyze the hyponyms, relative popularity of words, categories & lengths of words over time using Zipf's law. Effectively utilized the semantic lexicon WordNet, Google's Ngram dataset and TimeSeries with an YearlyRecord to aid the NGordNet User Interface.
  - Used directed graphs to store entries from the WordNet dataset into ADTs to allow for rapid queries for hyponyms and hypernyms.

## NGramMap
<p>The <code class="language-plaintext highlighter-rouge">NGramMap</code> has the following constructors and functions:</p>

<ul>
  <li><code class="language-plaintext highlighter-rouge">NGramMap(String wordsFilename, String countsFilename)</code>: The constructor for a NGramMap.</li>
  <li><code class="language-plaintext highlighter-rouge">TimeSeries countHistory(String word)</code>: Returns yearwise count of the given word for all available years.</li>
  <li><code class="language-plaintext highlighter-rouge">TimeSeries totalCountHistory()</code>: Returns yearwise count of all words for all time. This data should come from the
data in the file specified by <code class="language-plaintext highlighter-rouge">countsFilename</code>, not from summing all words in the file given by <code class="language-plaintext highlighter-rouge">wordsFilename</code>.</li>
  <li><code class="language-plaintext highlighter-rouge">TimeSeries weightHistory(String word)</code>: Returns yearwise relative frequency (a.k.a. normalized count) of the given
word for all time. For example, if there were 100,000 words across all volumes in 1575 and 2,100 occurrences of the
word “guitar”, then <code class="language-plaintext highlighter-rouge">weightHistory("guitar")</code> would have a value of 2100/100000 for the year 1575. If a word does not
appear in a given year, that year should not be included in the TimeSeries.</li>
  <li><code class="language-plaintext highlighter-rouge">TimeSeries summedWeightHistory(Collection&lt;String&gt; words)</code>: Returns the yearwise sum of the relative frequencies (
a.k.a. normalized counts) for the given words for all time.</li>
  <li>Another version of <code class="language-plaintext highlighter-rouge">countHistory</code>, <code class="language-plaintext highlighter-rouge">weightHistory</code>, and <code class="language-plaintext highlighter-rouge">summedWeightHistory</code> that take starting and
ending year arguments.</li>
</ul>


![Screen Shot 2023-01-31 at 12 57 37 AM](https://user-images.githubusercontent.com/98563830/215714640-9733ec19-a2ad-4989-9b1c-89a424ec35da.png)
![Screen Shot 2023-01-31 at 2 57 52 PM](https://user-images.githubusercontent.com/98563830/215903026-949480e0-1fba-418e-8125-9694631a860e.png)

