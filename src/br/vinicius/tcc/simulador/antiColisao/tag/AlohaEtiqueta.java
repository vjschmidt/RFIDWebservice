package br.vinicius.tcc.simulador.antiColisao.tag;

public class AlohaEtiqueta extends Etiqueta{
	private String sn = "E0";
	
	public AlohaEtiqueta (int initRound){
		for (int x = 0; x < 2; x++){
			hexCode = "";
			for (int y = 0; y<4; y++){
				hexCode += (int)(Math.random() * (2));
				sn += tagSNCreator(hexCode);
			}
		}
		sn += "0000";
		for (int x = 0; x < 8; x++){
			hexCode = "";
			for (int y = 0; y < 4; y++){
				hexCode = hexCode + (int)(Math.random() * (2));
				sn += tagSNCreator(hexCode);
			}
		}
		
		setSerialNumber(sn);
		setContador(1 + (int)(Math.random() * (initRound)));
	}
}