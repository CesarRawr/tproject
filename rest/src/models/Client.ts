export default interface ClientSchema {
	_id: {
		$oid: string;
	};
	type: string;
	tienda: string;
	nombre: string;
	apellido_paterno: string;
	apellido_materno: string;
	fecha_nacimiento: Date;
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
	credito: number;
	deuda: number;
	alta: Date;
	coordinates: {
		lat: number;
		long: number;
	};
}