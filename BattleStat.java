
/********************************************************
*	Dragons Of Mugloar - Mugloar Defence Force			*
*	A solution by Tommi Nirha							*
*														*
*	class Knightstat is a storage class for knight  	*
*		statistics. It exists to be sortable.			*
*	constructor parses a Hashtable.						*
*	methods: accessors, toString						*
*********************************************************/

import java.util.Collections;

public class BattleStat implements Comparable<BattleStat> {

	private int statorder;
	private int statvalue;
	
	public BattleStat (int orderNo, int power) {
		this.statorder = orderNo;
		this.statvalue = power;
	}
	
 	
	public int getOrder() {
		return this.statorder;
	}
	
	public int getValue() {
		return this.statvalue;
	}
	

	@Override public int compareTo(BattleStat other) {
		int compareValue =((BattleStat)other).getValue();
		return compareValue-this.statvalue;
	}
	
}

