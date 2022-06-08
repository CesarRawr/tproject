export default interface CloseoutSchema {
	_id: {
		$oid: string;		
	};
	cliente_id: string;
	vendedor_id: string;
	precio_tortilla: number; // Precio al momento de la transacción
	precio_totopos: number; // Precio al momento de la transacción
	venta: {
		tortilla: number; // Cantidad en kilos
		totopos: number; // Cantidad en bolsas
	};
	merma: {
		tortilla: number; // Cantidad en kilos
		totopos: number; // Cantidad en bolsas
	};
	fecha: Date;
	total: number;
}