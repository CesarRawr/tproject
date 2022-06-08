export default interface EmployeeSchema {
	_id: {
		$oid: string;
	}
	nombre: string;
	apellido_paterno: string;
	apellido_materno: string;
	fecha_nacimiento: string;
	nss: number;
	telefono: number;
	direccion : {
		numero: string;
		calle: string;
		colonia: string;
		entre_calle_1: string;
		entre_calle_2: string;
		cp: number;
	};
	alta: Date;
}