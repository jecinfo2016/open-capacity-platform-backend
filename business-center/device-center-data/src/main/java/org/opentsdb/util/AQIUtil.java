package org.opentsdb.util;

import java.util.HashMap;
import java.util.Map;

public class AQIUtil {

	private Map<String, Integer[]> aqiMap = new HashMap<String, Integer[]>();

	private int BPhi, BPlo, IAQIhi, IAQIlo;

	private double Cp,aqi;

	private Integer[] set;

	private Integer[] iaqi;

	public AQIUtil() {

		Integer[] dso2 = { 0, 50, 150, 475, 800, 1600, 2100, 2620 };
		aqiMap.put("DSO2", dso2);

		Integer[] hso2 = { 0, 150, 500, 650, 800 };
		aqiMap.put("HSO2", hso2);

		Integer[] dno2 = { 0, 40, 80, 180, 280, 565, 750, 940 };
		aqiMap.put("DNO2", dno2);

		Integer[] hno2 = { 0, 100, 200, 700, 1200, 2340, 3090, 3840 };
		aqiMap.put("HNO2", hno2);

		Integer[] dklw = { 0, 50, 150, 250, 350, 420, 500, 600 };
		aqiMap.put("DKLW", dklw);

		Integer[] dco = { 0, 2, 4, 14, 24, 36, 48, 60 };
		aqiMap.put("DCO", dco);

		Integer[] hco = { 0, 5, 10, 35, 60, 90, 120, 150 };
		aqiMap.put("HCO", hco);

		Integer[] ho3 = { 0, 160, 200, 300, 400, 800, 1000, 1200 };
		aqiMap.put("HO3", ho3);

		Integer[] do3 = { 0, 100, 160, 215, 265, 800 };
		aqiMap.put("DO3", do3);

		Integer[] xklw = { 0, 35, 75, 115, 150, 250, 350, 500 };
		aqiMap.put("XKLW", xklw);

		Integer[] iaqi = { 0, 50, 100, 150, 200, 300, 400, 500 };
		aqiMap.put("IAQI", iaqi);
	}

	public double aqival(String type, double Cp) {
		this.Cp = Cp;

		set = aqiMap.get(type);
		iaqi = aqiMap.get("IAQI");

		if (set.length == 5) {
			aqi = aqiLenIs5();
		}else if(set.length == 6){
			aqi = aqiLenIs6();
		}else {
			aqi = aqiLenIs8();
		}
		
		return aqi;
	}

	private double aqiLenIs5() {

		if (set[0] < Cp && set[1] > Cp) {
			setVal(1, 0);
		} else if (set[1] < Cp && set[2] > Cp) {
			setVal(2, 1);
		} else if (set[2] < Cp && set[3] > Cp) {
			setVal(3, 2);
		} else {
			setVal(4, 3);
		}
		return aqiCalculation();
	}

	private double aqiLenIs6() {

		if (set[0] < Cp && set[1] > Cp) {
			setVal(1, 0);
		} else if (set[1] < Cp && set[2] > Cp) {
			setVal(2, 1);
		} else if (set[2] < Cp && set[3] > Cp) {
			setVal(3, 2);
		} else if (set[3] < Cp && set[4] > Cp) {
			setVal(4, 3);
		} else {
			setVal(5, 4);
		}
		return aqiCalculation();
	}

	private double aqiLenIs8() {

		
		if (set[0] < Cp && set[1] > Cp) {
			setVal(1, 0);
		} else if (set[1] < Cp && set[2] > Cp) {
			setVal(2, 1);
		} else if (set[2] < Cp && set[3] > Cp) {
			setVal(3, 2);
		} else if (set[3] < Cp && set[4] > Cp) {
			setVal(4, 3);
		} else if (set[4] < Cp && set[5] > Cp) {
			setVal(5, 4);
		} else if (set[5] < Cp && set[6] > Cp) {
			setVal(6, 5);
		} else {
			setVal(7, 6);
		}
		return aqiCalculation();
	}

	private double aqiCalculation() {
		return (IAQIhi - IAQIlo) / (BPhi - BPlo) * (Cp - BPlo) + IAQIlo;
	}

	private void setVal(int max, int min) {
		BPhi = set[max];
		BPlo = set[min];
		IAQIhi = iaqi[max];
		IAQIlo = iaqi[min];
	}
	public int returnNewAqi(double pm25,double pm10,double so2,double no2,double co,double o3){
		int aqi=0, iaqipm25=0,iaqipm10=0,iaqico=0,iaqio3=0,iaqiso2=0,iaqino2=0;
		// PM25的IAQI
		if (pm25 >= 0 && pm25 <= 35) {
			iaqipm25 = (int) (pm25 * 10 / 7);
		} else if (pm25 > 35 && pm25 <= 75) {
			iaqipm25 = (int) ((pm25 - 35) * 5 / 4 + 50);
		} else if (pm25 > 75 && pm25 <= 115) {
			iaqipm25 = (int) ((pm25 - 75) * 5 / 4 + 100);
		} else if (pm25 > 115 && pm25 <= 150) {
			iaqipm25 = (int) ((pm25 - 115) * 10 / 7 + 150);
		} else if (pm25 > 150 && pm25 <= 350) {
			iaqipm25 = (int) (pm25 + 50);
		} else if (pm25 > 350 && pm25 <= 500) {
			iaqipm25 = (int) ((pm25 - 350) * 2 / 3 + 400);
		} else {
			iaqipm25 = 500;
		}
		// PM10的IAQI
		if (pm10 >= 0 && pm10 <= 50) {
			iaqipm10 = (int) pm10;
		} else if (pm10 > 50 && pm10 <= 150) {
			iaqipm10 = (int) ((pm10 - 50) / 2 + 50);
		} else if (pm10 > 150 && pm10 <= 250) {
			iaqipm10 = (int) ((pm10 - 150) / 2 + 100);
		} else if (pm10 > 250 && pm10 <= 350) {
			iaqipm10 = (int) ((pm10 - 250) / 2 + 150);
		} else if (pm10 > 350 && pm10 <= 420) {
			iaqipm10 = (int) ((pm10 - 350) * 10 / 7 + 200);
		} else if (pm10 > 420 && pm10 <= 500) {
			iaqipm10 = (int) ((pm10 - 420) * 5 / 4 + 300);
		} else if (pm10 > 500 && pm10 <= 600) {
			iaqipm10 = (int) (pm10 - 100);
		} else {
			iaqipm10 = 500;
		}
		// SO2的IAQI
		if (so2 >= 0 && so2 <= 150) {
			iaqiso2 = (int) (so2 / 3);
		} else if (so2 > 150 && so2 <= 500) {
			iaqiso2 = (int) ((so2 - 150) / 7 + 50);
		} else if (so2 > 500 && so2 <= 650) {
			iaqiso2 = (int) ((so2 - 500) / 3 + 100);
		} else if (so2 > 650 && so2 <= 800) {
			iaqiso2 = (int) ((so2 - 650) / 3 + 150);
		} else if (so2 > 800 && so2 <= 1600) {
			iaqiso2 = (int) ((so2 - 800) / 8 + 200);
		} else if (so2 > 1600 && so2 <= 2100) {
			iaqiso2 = (int) ((so2 - 1600) / 5 + 300);
		} else if (so2 > 2100 && so2 <= 2620) {
			iaqiso2 = (int) ((so2 - 2100) * 5 / 26 + 400);
		} else {
			iaqiso2 = 500;
		}
		// NO2的IAQI
		if (no2 >= 0 && no2 <= 100) {
			iaqino2 = (int) (no2 / 2);
		} else if (no2 > 100 && no2 <= 200) {
			iaqino2 = (int) ((no2 - 100) / 2 + 50);
		} else if (no2 > 200 && no2 <= 700) {
			iaqino2 = (int) ((no2 - 200) / 10 + 100);
		} else if (no2 > 700 && no2 <= 1200) {
			iaqino2 = (int) ((no2 - 700) / 10 + 150);
		} else if (no2 > 1200 && no2 <= 2340) {
			iaqino2 = (int) ((no2 - 1200) * 5 / 57 + 200);
		} else if (no2 > 2340 && no2 <= 3090) {
			iaqino2 = (int) ((no2 - 2340) * 2 / 15 + 300);
		} else if (no2 > 3090 && no2 <= 3840) {
			iaqino2 = (int) ((no2 - 3090) * 2 / 15 + 400);
		} else {
			iaqino2 = 500;
		}
		// CO的IAQI
		if (co >= 0 && co <= 5) {
			iaqico = (int) (co * 10);
		} else if (co > 5 && co <= 10) {
			iaqico = (int) ((co - 5) * 10 + 50);
		} else if (co > 10 && co <= 35) {
			iaqico = (int) ((co - 10) * 2 + 100);
		} else if (co > 35 && co <= 60) {
			iaqico = (int) ((co - 35) * 2 + 150);
		} else if (co > 60 && co <= 90) {
			iaqico = (int) ((co - 60) * 10 / 3 + 200);
		} else if (co > 90 && co <= 120) {
			iaqico = (int) ((co - 90) * 10 / 3 + 300);
		} else if (co > 120 && co <= 150) {
			iaqico = (int) ((co - 120) * 10 / 3 + 400);
		} else {
			iaqico = 500;
		}
		// O3的IAQI
		if (o3 >= 0 && o3 <= 160) {
			iaqio3 = (int) (o3 * 5 / 16);
		} else if (o3 > 160 && o3 <= 200) {
			iaqio3 = (int) ((o3 - 160) * 5 / 4 + 50);
		} else if (o3 > 200 && o3 <= 300) {
			iaqio3 = (int) ((o3 - 200) / 2 + 100);
		} else if (o3 > 300 && o3 <= 400) {
			iaqio3 = (int) ((o3 - 300) / 2 + 150);
		} else if (o3 > 400 && o3 <= 800) {
			iaqio3 = (int) ((o3 - 400) / 4 + 200);
		} else if (o3 > 800 && o3 <= 1000) {
			iaqio3 = (int) ((o3 - 800) / 2 + 300);
		} else if (o3 > 1000 && o3 <= 1200) {
			iaqio3 = (int) ((o3 - 1000) / 2 + 400);
		} else {
			iaqio3 = 500;
		}
		int array[] = { iaqico, iaqino2, iaqio3, iaqipm10, iaqipm25,
				iaqiso2 };
		for (int i = 0; i < array.length; i++) {
			if (array[i] > aqi) {
				aqi = array[i];
			}
		}
		return aqi;
	}

	//限值过滤
	public boolean thresholdFilter(Map<String,Double> index_value){
		if(index_value.get("SO2")<thresholdMin("HSO2")||index_value.get("SO2")>thresholdMax("HSO2"))
			return true;
		if(index_value.get("O3")<thresholdMin("HO3")||index_value.get("O3")>thresholdMax("HO3"))
			return true;
		if(index_value.get("CO")<thresholdMin("HCO")||index_value.get("CO")>thresholdMax("HCO"))
			return true;
		if(index_value.get("NO2")<thresholdMin("HNO2")||index_value.get("NO2")>thresholdMax("HNO2"))
			return true;
		if(index_value.get("PM10")<thresholdMin("DKLW")||index_value.get("PM10")>thresholdMax("DKLW"))
			return true;
		if(index_value.get("PM25")<thresholdMin("XKLW")||index_value.get("PM25")>thresholdMax("XKLW"))
			return true;

		return false;

	}

	public Integer thresholdMin(String index){
		return  aqiMap.get(index)[0];
	}

	public Integer thresholdMax(String index){
		int len = aqiMap.get(index).length;
		return aqiMap.get(index)[len-1];
	}
	
}
