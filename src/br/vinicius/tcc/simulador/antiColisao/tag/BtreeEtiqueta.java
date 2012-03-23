package br.vinicius.tcc.simulador.antiColisao.tag;

public class BtreeEtiqueta extends Etiqueta{
	
	public BtreeEtiqueta (){
		String hexCode;
		serialNumber = "E0";
		for (int y = 0; y < 16; y++){
			hexCode = "";
			for (int z = 0; z < 4; z++){
				hexCode = hexCode + (int)(Math.random() * (2));
				serialNumber += tagSNCreator(hexCode);
			}
		}
		
		contador = 0;
	}
}