package routing;

import java.util.ArrayList;

public class InterestNodes {
	public static ArrayList<int[]> interesses; 
	public static ArrayList<Integer> cluster;
	
	public InterestNodes() {
		if(interesses == null){
			interesses = new ArrayList<int[]>();
			interesses.add(0, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(1, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(2, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(3, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(4, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(5, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(6, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(7, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(8, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(9, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(10, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(11, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(12, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(13, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(14, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(15, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(16, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(17, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(18, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(19, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(20, new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(21,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0});
			interesses.add(22,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0});
			interesses.add(23,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(24,new int[]{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0});
			interesses.add(25,new int[]{0,1,0,0,0,0,1,0,0,0,0,1,1,1,0,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,1,1,0,0,0});
			interesses.add(26,new int[]{0,1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1,1,0,0,1,1,1,0,1});
			interesses.add(27,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(28,new int[]{0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(29,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(30,new int[]{0,1,1,1,1,0,0,0,1,0,0,1,1,0,0,0,0,0,0,1,0,0,1,0,1,1,0,1,0,0,0,1,1,0,0,1});
			interesses.add(31,new int[]{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0});
			interesses.add(32,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0});
			interesses.add(33,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(34,new int[]{0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,0,0,0,0,0,0,0});
			interesses.add(35,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(36,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0});
			interesses.add(37,new int[]{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0});
			interesses.add(38,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(39,new int[]{0,1,1,0,0,0,0,0,0,1,0,1,1,1,0,0,1,0,1,1,0,0,0,0,1,0,0,1,1,0,0,1,0,0,0,1});
			interesses.add(40,new int[]{0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,1,0,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,0,0});
			interesses.add(41,new int[]{0,0,0,0,1,0,1,1,0,1,1,0,1,1,0,1,1,0,1,1,0,0,1,1,1,0,0,0,0,1,0,1,1,1,0,0});
			interesses.add(42,new int[]{0,1,1,0,0,0,1,0,0,0,0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1});
			interesses.add(43,new int[]{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,0,0,0,0,0,0,0});
			interesses.add(44,new int[]{0,1,0,0,1,1,0,0,1,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,1,1,0,0,0});
			interesses.add(45,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,1,0,0,0,0,0,1,1,0,0,0,1,0,0});
			interesses.add(46,new int[]{0,1,1,0,0,0,0,0,0,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,1});
			interesses.add(47,new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(48,new int[]{0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0});
			interesses.add(49,new int[]{0,0,1,0,1,0,1,0,1,1,1,0,1,1,0,0,1,0,1,0,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,0});
			interesses.add(50,new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(51,new int[]{0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,0,0,0});
			interesses.add(52,new int[]{0,1,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,1});
			interesses.add(53,new int[]{0,1,0,0,1,1,0,1,0,0,1,1,0,1,0,0,0,0,1,1,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,1});
			interesses.add(54,new int[]{0,1,1,0,0,0,1,0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,1,1,0});
			interesses.add(55,new int[]{0,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(56,new int[]{0,1,1,0,0,0,0,1,0,0,0,1,0,1,0,1,0,0,1,0,0,0,1,1,1,0,1,1,1,1,0,0,1,1,0,1});
			interesses.add(57,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,1});
			interesses.add(58,new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(59,new int[]{0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1});
			interesses.add(60,new int[]{0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1});
			interesses.add(61,new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(62,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,0,1,1,0,0,0,0,0,0,1,0,0,0,0,0});
			interesses.add(63,new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(64,new int[]{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,0});
			interesses.add(65,new int[]{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1});
			interesses.add(66,new int[]{0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(67,new int[]{0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0});
			interesses.add(68,new int[]{0,1,0,0,0,0,0,0,1,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0});
			interesses.add(69,new int[]{0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0});
			interesses.add(70,new int[]{0,1,0,0,0,0,1,1,0,1,0,0,1,0,0,0,1,0,0,1,0,0,0,1,1,1,0,1,0,0,0,0,0,0,0,1});
			interesses.add(71,new int[]{0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1});
			interesses.add(72,new int[]{0,1,0,1,1,1,0,0,0,0,0,1,0,1,0,1,0,0,0,1,1,0,1,0,1,1,0,0,1,0,0,1,0,0,0,1});
			interesses.add(73,new int[]{0,0,0,0,0,0,0,0,0,0,1,1,0,1,0,0,1,0,0,0,0,0,1,0,0,0,1,1,0,1,0,0,0,0,0,0});
			interesses.add(74,new int[]{0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1});
			interesses.add(75,new int[]{0,0,0,0,0,0,1,0,0,1,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1});
			interesses.add(76,new int[]{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,0,0,0});
			interesses.add(77,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0});
			interesses.add(78,new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(79,new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(80,new int[]{0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,0,0,1});
			interesses.add(81,new int[]{0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,0,1,0,0,0});
			interesses.add(82,new int[]{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(83,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1});
			interesses.add(84,new int[]{0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,1,1,0,0,0,1,0,0,0,1,0,0,1,1,1,1,1,0});
			interesses.add(85,new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(86,new int[]{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,1,1,1,1,0,0,0,0,1,0,1});
			interesses.add(87,new int[]{0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,1});
			interesses.add(88,new int[]{0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,1,1,0,0,0,1,1,1,0,1,0,0,0,1});
			interesses.add(89,new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(90,new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(91,new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(92,new int[]{0,0,0,0,0,0,1,0,0,0,0,0,1,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0});
			interesses.add(93,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0});
			interesses.add(94,new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(95,new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
			interesses.add(96,new int[]{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,0,0,0,0});
			interesses.add(97,new int[]{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0,1});
			interesses.add(98,new int[]{0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0});
			interesses.add(99,new int[]{0,1,0,0,1,0,0,0,0,0,1,1,0,1,0,0,1,0,0,1,0,0,1,0,1,0,0,0,1,0,0,0,0,0,0,1});	
			
			
		}
			
	}	
	public int[] getInteresse(int idNode){
		if(idNode <= 99){
			return interesses.get(idNode);
		}else{
			return interesses.get(0);
		}
		
	}
	
	public boolean comparaInteresse(int idNode, int idPreferencia){
		if(idNode <= 99){
			if(interesses.get(idNode)[idPreferencia] == 1){
				return true;
			}else{
				return false;
			}
		}else{
			if(interesses.get(1)[idPreferencia] == 1){
				return true;
			}else{
				return false;
			}
		}
			
	 
	}
	
	public void imprimeInteresse(int idNode){
		if(idNode <= 99){
			for (int i = 0; i < interesses.get(idNode).length; i++) {
				System.out.print("|" + interesses.get(idNode)[i] + "|");
			}
			System.out.println();
		}else{
			for (int i = 0; i < interesses.get(1).length; i++) {
				System.out.print("|" + interesses.get(1)[i] + "|");
			}
			System.out.println();
		}
		
	}
	
	public void imprimeInteressePorColuna(int idNode, int indInteresse){
		if(idNode <= 99){
			System.out.println("|" + interesses.get(idNode)[indInteresse] + "|");
		}else{
			System.out.println("|" + interesses.get(1)[indInteresse] + "|");
		}
	}
	
	public Integer[] getArrayDeNodesPorInteresse(int indInteresse){
		ArrayList<Integer> indNodesInteresses = new ArrayList<Integer>();
		for (int i = 0; i < interesses.size(); i++) {
			if(interesses.get(i)[indInteresse] == 1){
				indNodesInteresses.add(i);
			}
		}
		
		Integer[] nodes = indNodesInteresses.toArray(new Integer[indNodesInteresses.size()]);
		
		return nodes;
				
	}
	
	public boolean isInCLuster(int idNode){
		for (int i = 0; i < cluster.size(); i++) {
			if(cluster.get(i) == idNode){
				return true;
			}
		}
		return false;
	}


}