package acme.components;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import acme.entities.systemConfiguration.SystemConfiguration;

public class SpamDetector {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	// Methods
	
	private SpamDetector() {}
	
	public static Boolean isSpam(final String textToCheck, final SystemConfiguration systemConfiguration) {
		
		boolean result = false;
		
		final List<String> wordsToCheck = SpamDetector.textToWords(textToCheck);		
		final Map<String,Double> spanishSpamTerms = SpamDetector.obtainSpamWords(systemConfiguration.getSpamTermsEs());		
		final Map<String,Double> englishSpamTerms = SpamDetector.obtainSpamWords(systemConfiguration.getSpamTermsEn());
		final Map<String,Double> allSpamTerms = new HashMap<>();
		allSpamTerms.putAll(spanishSpamTerms);
		allSpamTerms.putAll(englishSpamTerms);
		
		final Double spamThreshold = systemConfiguration.getSpamThreshold();
		
		final Double spamRatio = SpamDetector.spamRatioCalculator(wordsToCheck, allSpamTerms);
		
		if(spamRatio >= spamThreshold) {
			result = true;
		}
		
		return result;
	}
	
	private static List<String> textToWords(final String originalText){
		return Arrays.asList(originalText.replaceAll("[.,:;/*=|()¡!¿?{}`´<>]"," ")
			.replace("\""," ").replace("\\"," ").replace("["," ").replace("]"," ").trim().split("\\s+"));		
	}
	
	private static Map<String,Double> obtainSpamWords(final String spamTermsArray){
		
		final Map<String,Double> spamWords = new HashMap<String,Double>();
		
		for(final String keyValue : spamTermsArray.split(",")) {
			System.out.println(keyValue);
			final String[] pair = keyValue.replace("("," ").replace(")"," ").replace("'", "").trim().split(":");
			spamWords.put(pair[0], Double.valueOf(pair[1]));
			}
		
		return spamWords;
	}
	
	private static Double spamRatioCalculator(final List<String> wordsToCheck, final Map<String,Double> spamTerms) {
		
		Double totalSpamWeight = 0.;
		
//		final StringBuilder sentenceBuilder = new StringBuilder("/");
//		for(final String word: wordsToCheck) {
//			sentenceBuilder.append(word.toUpperCase() + "/");
//		}
//		
//		final String sentenceToCheck = sentenceBuilder.toString();
		
//		for(final Map.Entry<String, Double> entry : spamTerms.entrySet()) {
//			// Queremos: comprobar si la clave de entry está contenida en sentenceToCheck, en cuyo caso sumarle el valor a totalSpamWeight
//			if(wordsToCheck.contains(entry.getKey().toUpperCase())) {
//				totalSpamWeight += entry.getValue();
//			}
//		}
		
		for(final String word: wordsToCheck) {
			if(spamTerms.keySet().contains(word.toLowerCase())) {
				totalSpamWeight += spamTerms.get(word.toLowerCase());
			}
		}
		
//		for(final List<String> spamTermsSentence: spamTermsSentencesList) {
//			
//			final StringBuilder spamTermBuilder = new StringBuilder("/");
//			for(final String word: spamTermsSentence) {
//				spamTermBuilder.append(word.toUpperCase() + "/");
//			}
//			
//			final String spamTerm = spamTermBuilder.toString();
//			String sentenceToCheckAux = sentenceToCheck;
//			
//			if(sentenceToCheck.contains(spamTerm)) {
//				while (sentenceToCheckAux.indexOf(spamTerm) > -1) {
//					
//					sentenceToCheckAux = sentenceToCheckAux.substring(
//						sentenceToCheckAux.indexOf(spamTerm)+spamTerm.length()-1, sentenceToCheckAux.length());
//				    spamTermRecurrence = spamTermRecurrence + spamTermsSentence.size();
//				}
//			}	
//		}
		
		return totalSpamWeight/wordsToCheck.size();
	}	
}