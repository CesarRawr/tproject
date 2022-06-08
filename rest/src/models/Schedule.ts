export default interface ScheduleSchema {
	_id?: {
		$oid: string;
	};
	cliente: {
		_id: {
			$oid: string;
		};
		tienda: string;
		type: string;
	};
	vendedor: {
		_id: {
			$oid: string;
		};
		nombre: string;
	};
	tortilla: number;
	totopos: number;
	fecha_entrega: Date;
}