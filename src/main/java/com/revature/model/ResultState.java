package com.revature.model;

import java.util.HashMap;
import java.util.HashSet;

public class ResultState {
	// Pool of default questions mapped to API requests
	private HashMap<String, String> questions = new HashMap<String, String>();
	private HashSet<String> answers = new HashSet<String>();	
}