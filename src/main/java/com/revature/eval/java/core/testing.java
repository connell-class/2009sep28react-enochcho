package com.revature.eval.java.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class testing {
	
	public static void main(String[] args) {
		//System.out.println(isArmstrongNumber(9474));
		//System.out.println(rotate("a"));
		
		
		//System.out.println(ab(10));
		String string = "First In, First Out";
		//String[] split = string.split(" ");
		//System.out.println(Arrays.toString(split));
		System.out.println(acronym(string));
		
		//System.out.println(Pattern.compile("[^0-9]").matcher(a).find());
		
		
		//isLuhnValid("046 454 286");
	}
	
	public static String acronym(String phrase) {
		// TODO Write an implementation for this method declaration
		String[] spaced = phrase.split("[^a-zA-Z]"); //splits the phrase by white space
		String fin=""; //holds final acronym
		System.out.println(Arrays.toString(spaced));
		for(String x:spaced) {  // loop to go through the phrase
			if(!x.isEmpty()) {
				fin = fin + x.charAt(0); //assigns acronym by first char of each word in the string. 
			}
		}
		fin = fin.toUpperCase();
		return fin;
	}
	
	public static int solveWordProblem(String string) {
		// TODO Write an implementation for this method declaration
		int oper = 0;
		if(string.contains("plus")) {
			oper = 1;
		} else if (string.contains("minus")) {
			oper = 2;
			
		} else if (string.contains("multiplied")) {
			oper = 3;
		} else if (string.contains("divided")) {
			oper = 4;
		} else {
			throw new IllegalArgumentException();
		}
		//splitting words up by space
		String[] split = string.split(" ");
		int a = Integer.parseInt(split[2]);
		int b = Integer.parseInt(split[4].substring(0, 1));
		System.out.println(a + " " + b);
		
		switch(oper) {
		case 1: 
			return Math.addExact(a, b);
		case 2:
			return Math.subtractExact(a, b);
		case 3:
			return Math.multiplyExact(a, b);
		case 4:
			return Math.floorDiv(a, b);
		}
		return 0;
	}
	
	
	public static boolean isLuhnValid(String string) {
		// TODO Write an implementation for this method declaration
		//removing any non digit characters
		string = string.replaceAll("[^0-9]", "");
		//turn digits into string array, then place them into integer array reverse order
		String[] split = string.split("");
		int[] onetwo = new int[split.length];
		for(int i = split.length-1; i >-1; i--) {
			onetwo[Math.abs(i-(split.length-1))] = Integer.parseInt(split[i]);
		}
		//looping through every second digit
		for(int i = 1; i < onetwo.length; i += 2) {
			int newNum = onetwo[i]*2;
			if(newNum > 9) {
				newNum -= 9;
			}
			onetwo[i] = newNum;
		}
		// adding them all together
		int sum = 0;
		for(int x: onetwo) {
			sum += x;
		}
		//evaluating whether it divides evenly into 10
		return sum%10 == 0;
	}
	
	public static boolean ab(int x) {
		return x%11 == 0? true:false;
	}
	
	public static String encode(String string) {
		// TODO Write an implementation for this method declaration
		HashMap<Character, Character> cipher = new HashMap<Character,Character>()
				{{
					put('a','z');
					put('b','y');
					put('c','x');
					put('d','w');
					put('e','v');
					put('f','u');
					put('g','t');
					put('h','s');
					put('i','r');
					put('j','q');
					put('k','p');
					put('l','o');
					put('m','n');
					put('n','m');
					put('o','l');
					put('p','k');
					put('q','j');
					put('r','i');
					put('s','h');
					put('t','g');
					put('u','f');
					put('v','e');
					put('w','d');
					put('x','c');
					put('y','b');
					put('z','a');
				}};
				
		//get rid of spaces and non letters
		string = string.replaceAll("\\W", "").toLowerCase();
		char[] holder = string.toCharArray();
		for(int i = 0; i<holder.length;i++) {
			if(cipher.containsKey(holder[i])) { //checks if its a letter
				holder[i] = cipher.get(holder[i]);
			} else { //if not, keep going. 
				continue;
			}
			
		}
		String almost = String.valueOf(holder);
		//add spaces every five letters
		int a = 0; // a and b will mark the start and end of subString
		int b = 5;
		String encoded = "";
		for(int i = 0; i<(almost.length()/5)+1;i++) {
			if(b<almost.length()) {
				encoded += almost.substring(a,b) + " ";
			}else {
				encoded += almost.substring(a);
			}
			
			a+=5;
			b+=5;
		}
		
		return encoded;
	}
	
	public static String rotate(String string) {
		// TODO Write an implementation for this method declaration
		//get number of rotations
		//make key usable, within 26
		int incr = 27;
		// a is a, b is z, aa is A, and bb is B in unicode code point
		int a = 97;
		int b = 122;
		int aa = 65;
		int bb = 90;
		
		//holds unicode points
		int[] code = new int[string.length()];
		for(int i = 0;i<code.length;i++) {
			code[i] = string.codePointAt(i);
		}
		//convert string to unicode points
		for(int i = 0;i<code.length;i++) {
			int x = code[i];
			if(x >= a && x <= b) { // lowercase letters
				if(x+incr <= b) {
					x += incr;
					
				} else {
					x = a+ ((x+incr)-b-1);// wrap around: wrapping around to a is a rotation so incr -1. 
				}
			} else if(x >= aa && x <= bb) {//uppercase letters
				if(x+incr <= bb) {
					x += incr;
				} else {
					x = aa + ((x+incr)-bb-1);
				}
			} else { // skip everything else. 
				continue;
			}
			//update the code
			code[i] = x;
		}
		
		//convert unicode points array into string
		String rotated = new String(code,0,string.length());
		
		
		return rotated;
	}
	
	public static List<Long> calculatePrimeFactorsOf(long l) {
		// TODO Write an implementation for this method declaration
		//holds prime numbers
		List<Long> primes = new ArrayList<Long>();
		//loop through all the digits, i will be first factor
		for(long i = 2; i <= (l/2);i++) {
			long b = 0L; // holds the second factor
			//test if its a factor
			if(l%i == 0) {
				b = l/i;
				primes.addAll(calculatePrimeFactorsOf(b));
				primes.addAll(calculatePrimeFactorsOf(i));
				break;
			}
		}
		//if there are no factors then l is prime
		if(primes.size() == 0) {
			primes.add(l);
		}		
		return primes;
	}
	
	public static boolean isArmstrongNumber(int input) {
		// TODO Write an implementation for this method declaration
		//change input to string to split the digits up
		String num = Integer.toString(input);
		int len = num.length();
		String[] separated = num.split("");
		
		//holds the sum
		int sum = 0;
		
		//holds the integers
		int[] numbers = new int[len];
		for(int i = 0; i<len;i++) {
			numbers[i] = Integer.parseInt(separated[i]);
		}
		for(int x:numbers) {
			sum += Math.pow(x, len);
		}
		return sum == input;
	}
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			// TODO Write an implementation for this method declaration
			List<T> copy = sortedList;
			int middle = (copy.size())/2;
			int start = 0;
			int end = copy.size()-1;
			
			//if String type
			if(sortedList.get(0) instanceof String) {
				while(true) {
					int x = ((String)t).compareTo((String)copy.get(middle));
					if(x == 0) {
						return middle;
					} else if(x == -1) {
						end = middle-1;
						middle = ((end-start)/2) +start;
					} else if(x == 1) {
						start = middle+1;
						middle = ((end-start)/2)+middle+1; 
					}
				
				}
			} else if(sortedList.get(0) instanceof Integer) {
				while(true) {
					int x = ((Integer)t).compareTo((Integer)copy.get(middle));
					if(x == 0) {
						return middle;
					} else if(x == -1) {
						end = middle-1;
						middle = ((end-start)/2) +start;
					} else if(x == 1) {
						start = middle+1;
						middle = ((end-start)/2)+middle+1; 
					}
				}
			} else {
				throw new IllegalArgumentException("Unrecognized list type");
			}
			}
		
			
		

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

}
