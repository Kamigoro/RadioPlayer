package models;

public class Media {
	
	private int index;
	private String name;
	private String artistOrFrequency;
	private String mediaLogo;
	private String songImagePath;
	private String songPath;
	
	public Media(int index,String name, String artistOrFrequency, String mediaLogo, String songImagePath, String songPath) {
		this.index = index;
		this.name = name;
		this.artistOrFrequency = artistOrFrequency;
		this.mediaLogo = mediaLogo;
		this.songImagePath = songImagePath;
		this.songPath = songPath;
	}

	/////////////////////////
	//  Getters et setters //
	/////////////////////////
	
	public int getIndex() {
		return this.index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtistOrFrequency() {
		return artistOrFrequency;
	}

	public void setArtistOrFrequency(String artistOrFrequency) {
		this.artistOrFrequency = artistOrFrequency;
	}

	public String getMediaLogo() {
		return mediaLogo;
	}

	public void setMediaLogo(String mediaLogo) {
		this.mediaLogo = mediaLogo;
	}

	public String getSongImagePath() {
		return songImagePath;
	}

	public void setSongImagePath(String songImagePath) {
		this.songImagePath = songImagePath;
	}

	public String getSongPath() {
		return songPath;
	}

	public void setSongPath(String songPath) {
		this.songPath = songPath;
	}
	
}
