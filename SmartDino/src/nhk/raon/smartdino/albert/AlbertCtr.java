package nhk.raon.smartdino.albert;

public class AlbertCtr {
	private static AlbertCtr _albertCtr;
	
	public static AlbertCtr getAlbertCtr() {
		if(_albertCtr == null)
			_albertCtr = new AlbertCtr();
		return _albertCtr;
	}
	
//	public 
}
