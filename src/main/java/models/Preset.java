package models;

public class Preset {

	private int presetNumber;
	private Station station;
	
	public Preset(int presetNumber) {
		this.presetNumber = presetNumber;
	}
	
	public int getPresetNumber() {
		return presetNumber;
	}
	
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	
}
