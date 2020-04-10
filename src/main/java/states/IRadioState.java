package states;

import models.RadioPlayer;

public interface IRadioState {
	
	public void leftClick();
	public void rightClick();
	public void upClick();
	public void downClick();
	public void okClick();
	public void menuClick();
	public void autotuneClick();
	public void onOffClick();
	public void alarmClick();
	public void auxOutClick();
	
	public void preset1Click(boolean isForSavingOrForLoading);
	public void preset2Click(boolean isForSavingOrForLoading);
	public void preset3Click(boolean isForSavingOrForLoading);
	
}
