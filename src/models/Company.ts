export default interface CompanySchema {
	_id: {
		$oid: string;
	};
	type: string;
	rfc: number;
	nombre: string;
	direccion: {
		numero: string;
		calle: string;
		colonia: string;
		entre_calle_1: string;
		entre_calle_2: string;
		cp: number;
		telefono: number;
	};
	precio_preferencial: number;
	deuda: number;
	liquidacion: string[];
	alta: Date;
}