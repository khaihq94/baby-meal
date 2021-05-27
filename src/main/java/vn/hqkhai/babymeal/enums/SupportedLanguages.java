package vn.hqkhai.babymeal.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum SupportedLanguages {

	VIETNAMESE("vi"),
	ENGLISH("en");
	
	private String id;
	
	SupportedLanguages(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public static List<String> getAllIds() {
		return Arrays.stream(SupportedLanguages.values()).map(lang -> lang.getId()).collect(Collectors.toList());
	}
	
}
