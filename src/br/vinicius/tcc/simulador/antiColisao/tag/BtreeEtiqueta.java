package br.vinicius.tcc.simulador.antiColisao.tag;

public class BtreeEtiqueta extends Etiqueta{
	private String sn = "E0";
	
	public BtreeEtiqueta (){
		for (int y = 0; y < 16; y++){
			hexCode = "";
			for (int z = 0; z < 4; z++){
				hexCode = hexCode + (int)(Math.random() * (2));
				sn += tagSNCreator(hexCode);
			}
		}
		
		setSerialNumber(sn);
		setContador(0);
	}
}