package models.constants;

public final class Constant {
	
	private Constant() {
	}
	
	public final static int maxSecondInAMinut = 59;
	public final static int minSecondInAMinut = 0;
	public final static int maxMinuteInAnHour = 59;
	public final static int minMinuteInAnHour = 0;
	public final static int maxHourInADay = 23;
	public final static int minHourInADay = 0;
	public final static int minDayInAMonth = 1;
	public final static int maxMonthInAYear = 12;
	public final static int minMonthInAYear = 1;
	
	public final static int maxPresetInRadio = 3;
	public final static int indexOfFirstPreset = 0;
	public final static int indexOfSecondPreset = 1;
	public final static int indexOfThirdPreset = 2;
	
	public final static boolean savingPreset = true;
	public final static boolean loadingPreset = false;
	
	public final static int noMediaBehindPreset = -1;
}
